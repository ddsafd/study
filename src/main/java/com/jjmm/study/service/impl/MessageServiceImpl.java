package com.jjmm.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjmm.study.mapper.MessageMapper;
import com.jjmm.study.model.Message;
import com.jjmm.study.service.IMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tqz
 * @since 2022-08-12
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
