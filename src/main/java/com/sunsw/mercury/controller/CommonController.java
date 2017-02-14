package com.sunsw.mercury.controller;

import com.sunsw.mercury.model.SysLoginLog;
import com.sunsw.mercury.model.SysMenu;
import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.service.SysLoginLogService;
import com.sunsw.mercury.service.SysMenuService;
import com.sunsw.mercury.service.SysUserService;
import com.sunsw.mercury.statics.ResultEnum;
import com.sunsw.mercury.statics.RouterKey;
import com.sunsw.mercury.statics.SystemProps;
import com.sunsw.mercury.utils.PasswordUtils;
import com.sunsw.mercury.utils.RequestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 视图控制器,返回jsp视图给前端
 *
 * @author sunsw
 **/
@Controller
public class CommonController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysLoginLogService sysLoginLogService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = RouterKey.INDEX)
    public ModelAndView index() {
        Subject subject = SecurityUtils.getSubject();
        // 已登陆则 跳到首页
        if (subject.isAuthenticated()) {
            ModelAndView model = new ModelAndView("index");
            SysUser sysUser = RequestUtils.currentUser();
            List<SysMenu> menus;
            if (subject.hasRole(SystemProps.ADMIN_ROLE)) {//如果是admin，则提取所有菜单
                menus = sysMenuService.selectMenuTree(0L);
            } else {
                menus = sysMenuService.selectMenuByUserId(sysUser.getId(), 0L);
            }
            model.addObject("menus", menus);
            model.addObject("user", sysUser);
            return model;
        }
        return new ModelAndView("login");
    }

    /**
     * 用户登录
     *
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = RouterKey.LOGIN, method = RequestMethod.POST)
    public String login(SysUser user, Model model, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            // 验证成功在Session中保存用户信息
            final SysUser sysUserInfo = sysUserService.selectByUsername(user.getUsername());
            request.getSession().setAttribute(SystemProps.CURRENT_USER, sysUserInfo);

            SysLoginLog sysLoginLog = new SysLoginLog();
            sysLoginLog.setUserId(sysUserInfo.getId());
            sysLoginLog.setUsername(sysUserInfo.getUsername());
            sysLoginLog.setIp(RequestUtils.getRequest().getRemoteAddr());
            sysLoginLogService.insert(sysLoginLog);
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
        return "redirect:/";
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = RouterKey.LOGOUT)
    public String logout(HttpSession session) {
        session.removeAttribute(SystemProps.CURRENT_USER);
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

    /**
     * session失效，登录锁定
     *
     * @param username
     * @param session
     * @return
     */
    @RequestMapping(value = RouterKey.LOCK)
    public ModelAndView lock(String username, HttpSession session) {
        if (StringUtils.isEmpty(username)) {
            return new ModelAndView("redirect:/");
        }
        SysUser currentUser = sysUserService.selectByUsername(username);
        if (null == currentUser) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("lock");
        session.removeAttribute(SystemProps.CURRENT_USER);
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        mav.addObject("user", currentUser);
        return mav;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = RouterKey.REGISTER, method = RequestMethod.POST)
    public String register(@Valid SysUser user, BindingResult result, Model model, HttpServletRequest request) {
        try {
            String pwd_echo = request.getParameter("password_confirmation");
            if (!user.getPassword().equals(pwd_echo)) {
                model.addAttribute("error", "两次密码输入不一致");
                return "register";
            }
            user.setEmail(user.getEmail());
            String pwd_hash = PasswordUtils.md5Hex(user.getPassword());
            user.setCreateTime(new Date());
            user.setStatus(ResultEnum.USER_STATUS_ENABLE.getMessage());
            user.setPassword(pwd_hash);
            int ret = sysUserService.insert(user);
            if (ret != 1) {
                model.addAttribute("error", "注册失败，请检查输入信息");
                return "register";
            }
        } catch (AuthenticationException e) {
            model.addAttribute("error", "注册失败，请检查输入信息");
            return "register";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "用户已存在");
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "注册失败，请检查输入信息");
            return "register";
        }
        return "redirect:/logout";
    }

}