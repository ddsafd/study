package com.jjmm.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjmm.study.mapper.StarMapper;
import com.jjmm.study.model.Star;
import com.jjmm.study.model.User;
import com.jjmm.study.service.IStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tqz
 * @since 2022-08-12
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements IStarService {

    @Autowired
    StarMapper starMapper;
    @Override
    public List<User> starMe(int getId) {
        List<User> list=starMapper.starMe(getId);
        return list;
    }

    @Override
    public List<Integer> starMeId(int getId) {
        return starMapper.starMeId(getId);
    }

    @Override
    public List<User> IStar(int sendId) {
        List<User> list =starMapper.IStar(sendId);
        return list;
    }

    @Override
    public void insertStar(int sendId, int getId) {
        starMapper.insertStar(sendId,getId);
    }

    @Override
    public void deleteStar(int sendId, int getId) {
        starMapper.deleteStar(sendId,getId);
    }
}
