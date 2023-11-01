package com.jjmm.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jjmm.study.model.Star;
import com.jjmm.study.model.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tqz
 * @since 2022-08-12
 */
public interface IStarService extends IService<Star> {
    List<User> starMe(int getId);//点赞了getId的所有用户的集合
    List<Integer> starMeId(int getId);//点赞了getId的所有用户的id的集合

    List<User> IStar(int sendId);//sendId虽然在本项目中经常就是登录者，但是它不是登录者。被sendId点赞过的所有用户的集合
    void insertStar(int sendId,int getId);//表中插入一条sendId点赞getId的记录
    void deleteStar(int sendId,int getId);//表中删除一条sendId点赞getId的记录
}
