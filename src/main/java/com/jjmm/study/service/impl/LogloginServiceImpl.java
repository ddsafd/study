package com.jjmm.study.service.impl;

import com.jjmm.study.model.Loglogin;
import com.jjmm.study.mapper.LogloginMapper;
import com.jjmm.study.service.ILogloginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tqz
 * @since 2022-08-17
 */
@Service
public class LogloginServiceImpl extends ServiceImpl<LogloginMapper, Loglogin> implements ILogloginService {
    @Autowired
    LogloginMapper logloginMapper;

    /*
    问题是：
    父接口不写一个抽象方法，那是默认返回boolean，意思是默认的实现是返回boolean
    接口的代码是返回int
    只能自己写代码转换类型
     */
    @Override
    @Transactional
    public boolean save(Loglogin entity)
    {
        int x= logloginMapper.insert(entity);
//        int z=1/0;
        if(x>0)
        {
            return true;
        }
        return false;
    }
}
