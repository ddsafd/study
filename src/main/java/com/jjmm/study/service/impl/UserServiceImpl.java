package com.jjmm.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjmm.study.mapper.UserMapper;
import com.jjmm.study.model.User;
import com.jjmm.study.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tqz
 * @since 2022-08-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
//    @Cacheable(cacheNames = "users",key = "#name")
    public User findByNamePassword(User user) {
        return userMapper.findByNamePassword(user);
    }

    //---------------------------
//将方法的运行结果进入缓存,这样以后再相同的数据直接从缓存中取,不用再调用方法(减少了数据库的交互,提高了效率)
//属性介绍:
// cacheNames/value:缓存组件的名称:用来存放该方法返回值的地方,用数组的方式来存储,可以指定多个缓存
//key:缓存数据用来指定的key,默认key使用方法的参数,或者使用keyGenerator(这个使用在config类中配置),就是key生成器,二选一即可
//CacheManager:指定从哪个缓存管理器取 或者CacheResolver缓存解析器,两者功能一样
//Condition:指定符合条件下,才会进行缓存,condition="#id">0 的情况下才会缓存
//unless:否定缓存,当unless指定的条件时true是就不缓存,可以回去结果进行判断 #result==null;
//sync:是否使用异步模式


    @Override
    public Page<User> findByPage(int currentPage) {

        Page<User> page = new Page<>(currentPage,20);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userMapper.selectPage(page, userQueryWrapper);
//每页数据list集合
//        System.out.println(page.getRecords());
//当前页
//        System.out.println(page.getCurrent());
//每页显⽰记录数
//        System.out.println(page.getSize());
//总记录数
//        System.out.println(page.getTotal());
        return page;
    }
}
