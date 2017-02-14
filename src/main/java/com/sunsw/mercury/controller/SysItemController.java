package com.sunsw.mercury.controller;

import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.statics.RouterKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统代码控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.ITEM)
public class SysItemController {

    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult status(@PathVariable String typeId) {
        Map<String, String> map = new HashMap<>();
        map.put("Y", "启用");
        map.put("N", "禁用");
        return new AjaxResult(map);
    }

}
