package com.jjmm.study.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jjmm.study.model.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tqz
 * @since 2022-08-10
 */
public interface IUserService extends IService<User> {

    User findByNamePassword(User user);

    Page<User> findByPage(int currentPage);
}
