package com.jjmm.study.controller.aop;

import com.jjmm.study.model.Loglogin;
import com.jjmm.study.model.User;
import com.jjmm.study.service.ILogloginService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;

//解开以下注释就是切面生效
//@Aspect
//@Component

public class MyAop
{
    //注入邮件工具类
    @Autowired
    private JavaMailSender javaMailSender;
    private static final Logger log = LoggerFactory.getLogger(MyAop.class);

    /*
    这是AOP，拦截登录，则只在登录的时候运行这个方法
    这是一个语法示例方法，是空方法，没有什么用处。
    学生可以改Before的参数来拦截不同的方法，并且写处理代码
     */
    @Before("execution(public * com.jjmm.study.controller.UserController.check(..))")
    public void isLogined(JoinPoint joinPoint)throws Exception
    {

    }
    //发件人的邮箱在properties中配置
    @Value("${spring.mail.username}")
    private String sendMailer;

//    login的时候执行方法check()
//    @AfterReturning(pointcut = "execution(public * com.jjmm.study.controller.UserController.check(..))",returning = "retObject")
    public void doAfterReturning(Object retObject) throws Throwable
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setText("内容");
        message.setSubject("主题");
        //		message.setFrom 收件人和发件人不是消息，是有含义的
//        message.setFrom("1456065801@qq.com");
        message.setFrom(sendMailer);
//        message.setFrom("1456065801@qq.com");
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        HttpSession session=request.getSession();
        User u=(User)session.getAttribute("loginUser");
        //收件人的邮箱在user数据库表中  谁登录给谁发邮件
        String acceptor=u.getEmail();
        message.setTo(acceptor);
//		message.setCc("抄送人");//这里要写邮箱地址
//		message.setBcc("密送人");
        javaMailSender.send(message);
    }

    @Autowired
    ILogloginService iLogloginService;

//    @After("execution(public * com.jjmm.study.controller.UserController.check(..))")
    public void doAfter() throws Throwable
    {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        HttpSession session=request.getSession();
        User u=(User)session.getAttribute("loginUser");
        int userId=u.getId();
        LocalDateTime localDate = LocalDateTime.now();
        Loglogin loglogin=new Loglogin(null,userId,localDate);
        iLogloginService.save(loglogin);
    }
}
