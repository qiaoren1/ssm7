package com.cm.controller;

import com.cm.entity.SysUser;
import com.cm.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2020/5/8.
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController extends BaseController {
    //test123321
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("insert")
    private String insert(SysUser sysUser) {
        System.out.println("sysUser = " + sysUser);
        sysUserService.insert(sysUser);

        return "forward:/WEB-INF/main.jsp";
    }

    @RequestMapping("insert2")
    @ResponseBody
    public Object insert2(SysUser sysUser) {
        System.out.println("sysUser" + sysUser);
        int i = sysUserService.insert(sysUser);
        System.out.println("i= " + i);
        Map<String, String> map = new HashMap<>();
        if (i > 0) {
            map.put("msg", "操作成功！");
        } else {
            map.put("msg", "操作失败！");
        }
        return map;
    }

    @RequestMapping("selectByKey")
    @ResponseBody
    public Object selectByKey(Integer id) {
        SysUser sysUser = sysUserService.selectByPrimaryKey(id);
        return sysUser;
    }

    @RequestMapping("login")
    public String login(SysUser returnSysUser, HttpServletRequest request, HttpSession session) {
        System.out.println("sysUser = " + returnSysUser);
        SysUser sysUser = sysUserService.login(returnSysUser);

        if (sysUser != null) {
            session.setAttribute("ok", sysUser);
            return "forward:/WEB-INF/main.jsp";
        } else {
            request.setAttribute("err", "账号或密码错误!");
            return "forward:/login.jsp";
        }
    }

    /*
分页查询：
查询参数：page  int  表示当前页
          rows  int   表示页大小

 返回的数据：json格式，{"total":28,"rows":[ ]}
         total  int   表示匹配的数据条数
         rows   数组   表示查询出来的数据列表
 */
    @RequestMapping("selectPage")
    @ResponseBody
    public Object selectPage(SysUser sysUser,Integer page,Integer rows){
        //调用业务层查询数据
        PageInfo<SysUser> pageInfo = sysUserService.selectPage(sysUser,page,rows);
        System.out.println("sysUser = "+sysUser);
/*        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("total",pageInfo.getTotal());//获取总记录数
        pageMap.put("rows",pageInfo.getList());//获取用户数据列表*/
        return getPageMap(pageInfo);
    }
}