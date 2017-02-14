package com.sunsw.mercury;

import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.model.SysMenu;
import com.sunsw.mercury.service.SysUserService;
import com.sunsw.mercury.service.SysMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AppTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysMenuService sysMenuService;

    @Test
    public void testApp() {
        SysUser user = sysUserService.selectByUsername("tourpm");
        System.out.println(user.toString());
    }

    @Test
    public void testMenu() {
//		List<SysMenu>  list = sysMenuService.selectByUserId(5l);
//		System.out.println(list.size());
        List<SysMenu> result = sysMenuService.selectMenuByUserId(5L, 0L);
        if (null != result && !result.isEmpty()) {
            for (SysMenu menu : result) {
                tree(menu);
            }
        }
    }

    private void tree(SysMenu menu) {
        System.out.println(menu.getMenuTitle());
        if (null != menu.getSubMenus() && !menu.getSubMenus().isEmpty()) {
            for (SysMenu m : menu.getSubMenus()) {
                tree(m);
            }
        }
    }
}
