package com.jjmm.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jjmm.study.model.Star;
import com.jjmm.study.model.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tqz
 * @since 2022-08-12
 */
public interface StarMapper extends BaseMapper<Star>
{
    List<User> starMe(int getId);
    List<Integer> starMeId(int getId);
    List<User> IStar(int sendId);
    void insertStar(int sendId,int getId);
    void deleteStar(int sendId,int getId);
}
