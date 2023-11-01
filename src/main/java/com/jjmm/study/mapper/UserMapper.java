package com.jjmm.study.mapper;

import com.jjmm.study.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tqz
 * @since 2022-08-10
 */
public interface UserMapper extends BaseMapper<User> {
    User findByNamePassword(User user);

}
