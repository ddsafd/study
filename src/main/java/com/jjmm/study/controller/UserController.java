package com.jjmm.study.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjmm.study.model.User;
import com.jjmm.study.service.IStarService;
import com.jjmm.study.service.IUserService;
import com.jjmm.study.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {


    @Autowired
    IUserService iUserService;
    @RequestMapping("/index")
    public String login(Model model, @ModelAttribute User user)
    {
        System.out.println(user);
        return "/index";
    }


    @PostMapping("/login")
    public String check(Model model, @Valid User user, Errors errors, HttpSession session,Integer pageSize, Integer pageNo, String queryContent) {
        if (errors.hasErrors()) {
            return "/index";
        }
        User user1=iUserService.findByNamePassword(user);
        if(user1==null)
        {
            model.addAttribute("msg","该用户不存在，或者密码错");
            return "/index";
        }
        session.setAttribute("loginUser",user1);//将来判断靠拦截器
        Page<User> page=iUserService.findByPage(1);
        model.addAttribute("page",page);
        long total=page.getTotal();
        model.addAttribute("pageNo", 1);
//        总页数：
//        如果刚好是20的整数倍，则total/20
//        如果不是20的整数倍，多出来的是一页，所以，页数是total/20+1
        model.addAttribute("pageCount", total%20==0?total/20:total/20+1);

        //登录者点赞情况代码
        User loginer=(User)session.getAttribute("loginUser");
        List<User> starLoginer=iStarService.starMe(loginer.getId());//所有页用户对登录者点过赞的
        loginer.setlStarMe(starLoginer);
        loginer.setNumsStarMe(starLoginer.size());
//        登录者自己不需要点赞登录者


        //本页20个用户点赞情况代码
        List<User> listPage=page.getRecords();
        for(int i=0;i<20;i++)
        {
            User u=listPage.get(i);
//            List<User> list=iStarService.starMe(u.getId());
//            u.setNumsStarMe(list.size());
//            if(list.contains(loginer))//有非数据库字段，重写equals都没有用，因为只有数据库字段部分一一相同
//            判断点赞过我的用户中是否包含登录用户，这是比较费事的，不如再写一个mapper方法，抓取判断过我的用户有哪些id.
            List<Integer> list=iStarService.starMeId(u.getId());

            u.setNumsStarMe(list.size());//被点赞次数
            if(list.contains(loginer.getId()))
            {
                u.setLogerStarMe(true);//登录者是否点选过这个用户，如果点选过，页面上将显示红色星星
            }
            else
            {
                u.setLogerStarMe(false);
            }
        }

        return "userList";

    }

    @Autowired
    IStarService iStarService;

    @RequestMapping(value = "/userList")
    public String history(Model model, Integer pageSize, Integer pageNo, String queryContent,HttpSession session) {
        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = 20;

        Page<User> page=iUserService.findByPage(pageNo);
        model.addAttribute("page",page);//第pageNo页的全部用户

        model.addAttribute("pageNo", pageNo);//第pageNo页的页码
        long total=page.getTotal();//所有页所有用户总数
        model.addAttribute("pageCount", total/pageSize);

        //登录者点赞情况代码
        User loginer=(User)session.getAttribute("loginUser");
        List<User> starLoginer=iStarService.starMe(loginer.getId());//所有页用户对登录者点过赞的
        loginer.setlStarMe(starLoginer);
        loginer.setNumsStarMe(starLoginer.size());
//        补充说明，登录者自己不需要点赞登录者


        //本页20个用户点赞情况代码
        List<User> listPage=page.getRecords();
        for(int i=0;i<20;i++)
        {
            User u=listPage.get(i);
            List<User> list=iStarService.starMe(u.getId());
            u.setNumsStarMe(list.size());
            if(list.contains(loginer))
            {
                u.setLogerStarMe(true);
            }
            else
            {
                u.setLogerStarMe(false);
            }
        }

        return "userList";
    }
    @GetMapping("/userDetail")
    public String detail(int id,Model model)
    {
        Object o=iUserService.getById(id);
        model.addAttribute("userDetail",o);
        return "userDetail";
    }
    @RequestMapping("/starLoginer")
    public String sb(HttpSession session,Model model)
    {
        User loginer=(User)session.getAttribute("loginUser");
//        List<User> starLoginer=iStarService.starMe(loginer.getId());//所有页用户对登录者点过赞的
//        loginer.setlStarMe(starLoginer);
//        loginer.setNumsStarMe(starLoginer.size());

        
        List<User> listStarLoginer=loginer.getlStarMe();
        for(int i=0;i<listStarLoginer.size();i++)
        {
            User u=listStarLoginer.get(i);
            List<User> list=iStarService.starMe(u.getId());//点赞过我的全部的用户
            u.setNumsStarMe(list.size());//不管多少人点赞我，不分页，一页全部显示
            List<Integer> ilist=iStarService.starMeId(u.getId());//点赞过我的全部的用户，这个数据没有用。这个垃圾数据怎么产生的？
            if(ilist.contains(loginer.getId()))
            {
                u.setLogerStarMe(true);
            }
            else
            {
                u.setLogerStarMe(false);
            }
        }

        return "starLoginer";
    }

}
