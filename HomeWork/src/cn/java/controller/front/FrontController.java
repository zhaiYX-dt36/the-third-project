/**
 * Project Name:dt36_ssm1
 * File Name:FrontController.java
 * Package Name:cn.java.controller.front
 * Date:2017年7月28日上午9:52:22
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.controller.front;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.Member;
import cn.java.service.FrontService;
import cn.java.utils.FieldValidator;

/**
 * Description: <br/>
 * date: 2017年8月2日 下午4:48:54 <br/>
 *
 * @author dingP
 * @version
 */
@Controller
@Scope("prototype")
public class FrontController {
    @Autowired
    private FrontService fs;

    @RequestMapping("/selectAll")
    public String selectAll(HttpServletRequest request) {
        List<Map<String, Object>> lists = fs.selectAll();
        request.setAttribute("lists", lists);
        return "admin/show.jsp";
    }

    @RequestMapping("/insert")
    public @ResponseBody Map<String, Object> insert(@Valid Member member, BindingResult errors) {
        boolean flag = errors.hasErrors();
        if (!flag) {
            fs.insertSelective(member);
        }
        Map<String, Object> mp = FieldValidator.checkFiled(errors);
        return mp;
    }

    @RequestMapping("/update_front")
    public String update_front(Member member, HttpServletRequest request) {
        request.setAttribute("member", member);
        return "admin/update.jsp";
    }

    @RequestMapping("/update")
    public String update(Member member) {
        System.out.println(member);
        fs.updateByPrimaryKeySelective(member);
        return "admin/show_front.jsp";
    }

    @RequestMapping("/delete")
    public String delete(Long mid) {
        fs.deleteByPrimaryKey(mid);
        return "admin/show_front.jsp";
    }
}
