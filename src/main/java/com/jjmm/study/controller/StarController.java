package com.jjmm.study.controller;


import com.jjmm.study.mapper.StarMapper;
import com.jjmm.study.model.User;
import com.jjmm.study.service.IStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tqz
 * @since 2022-08-12
 */
@RestController
@RequestMapping("/star")
public class StarController
{
    @Autowired
    IStarService iStarService;
    /**
     * 根据被点赞的用户的id在数据库中插入一条点赞记录
     *  注：登录者必然是sendId
     * @param getId
     * @param session
     * @return
     */
    @RequestMapping("/addByGetId")
    public int addByGetId(String getId, HttpSession session)
    {
        int iGetId=Integer.parseInt(getId);
        User user=(User)session.getAttribute("loginUser");
        iStarService.insertStar(user.getId(),iGetId);
        int result=iStarService.starMe(iGetId).size();//再次查询有多少用户给我点赞
        return result;
    }

    /**
     * 根据发起点赞的用户的id在数据库中插入一条点赞记录
     * 被点赞的那个人一定是登录者自己。
     * 这是一个空方法，是为什么存在。因为，开始写项目的时候，不清楚需求，举一反三顺便写一堆方法放在这里。后来没用到，一般也想不到去删除
     * 一般我们肯定建议在代码中及时删除，将来，不记得这是什么情况，删除怕报错，将来就不敢删除了
     * @param getId
     * @return
     */
    @RequestMapping("/addBySendId")
    public int addBySendId(String getId)
    {
        return 0;
    }
//    指定点赞人和被点赞人
    @RequestMapping("/addBySendIdAndGetId")
    public int addBySendIdAndGetId(String sendId,String getId)
    {
        return 0;
    }
    //    登录者必然是sendId
    @RequestMapping("/deleteByGetId")
    public int deleteByGetId(String getId, HttpSession session)
    {
        int iGetId=Integer.parseInt(getId);
        User user=(User)session.getAttribute("loginUser");
        iStarService.deleteStar(user.getId(),iGetId);
        int total=iStarService.starMe(iGetId).size();//再次查询有多少用户给我点赞
        return total;
    }

}
