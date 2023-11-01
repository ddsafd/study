package com.jjmm.study;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjmm.study.mapper.StarMapper;
import com.jjmm.study.mapper.UserMapper;
import com.jjmm.study.model.Star;
import com.jjmm.study.model.User;
import com.jjmm.study.service.IUserService;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Log4j


class StudyApplicationTests {

	@Autowired
	private IUserService userService;
	@Autowired
	UserMapper userMapper;

	@Test
	void mm()
	{
		Page<User> page = new Page<>(1, 5);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
		System.out.println(11111111);
		System.out.println(userIPage);
		System.out.println(userIPage.getRecords());
		userIPage.getTotal();
		System.out.println(userIPage.getTotal());
	}
	@Test
	void fdf() {
		for(int i=0;i<190;i++)
		{
			String name="name"+i;
			String password="66666"+i;
			int  age=(i+15)%18;
			String email="haha@qq.com";

			User u=new User(1,name,password,age,email,LocalDate.now(),1);
			userMapper.insert(u);
		}
	}
	@Autowired
	StarMapper starMapper;
	@Test
	void dddd() {
		for(int i=0;i<190;i++)
		{
			int sendId= new Random().nextInt(30)+1;//主键没有0
			int getId= new Random().nextInt(30)+1;
			Star star=new Star(sendId,getId,LocalDate.now());
			starMapper.insert(star);
		}
	}
	@Test
	void contextLoads() {
//传⼊两个参数：当前页和每页显⽰记录数
		Page<User> page = new Page<>(1,3);
//调⽤mybatis-plus分页查询的⽅法
//第⼀个参数为把分页所以数据封装到page对象⾥⾯，第⼆个参数为查询筛选条件
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.gt("id",1);
		userMapper.selectPage(page, userQueryWrapper);
//每页数据list集合
		System.out.println(page.getRecords());
		List<User> list = page.getRecords();
//当前页
		System.out.println(page.getCurrent());
//每页显⽰记录数
		System.out.println(page.getSize());
//总记录数
		System.out.println(page.getTotal());

	}

	@Test
	public void w2f() {
		log.debug("fds");
	}
	@Test
	public void w()
	{
		User u1=new User();
		User u2=new User();
		u1.setName("zs");
		u1.setAge(18);
		u2.setName("zs");
		u2.setAge(18);
		System.out.println(u1.equals(u2));
		System.out.println(1111);
	}
	//注入邮件工具类
	@Autowired
	private JavaMailSender javaMailSender;
	@Test
	void fff() throws Exception {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setText("宏超，我是真爱你，不骗你的。你不能玩了我就没下文了");
		message.setSubject("主题");
//		收件人和发件人不是消息，是有含义的
		message.setFrom("1456065801@qq.com");
		message.setTo("1456065801@qq.com");
		message.setCc("2505078436@qq.com");
//		message.setBcc("密送人");
		javaMailSender.send(message);
	}

	@Test
	void addHashCache() {
		Jedis jedis = new Jedis("127.0.0.1",6379);//参数1为 ip ，参数2为端口号
		//测试 redis 服务是否能正常连通
		String pang = jedis.ping();
		System.out.println(pang);
//设置key-vale值
		String set = jedis.set("name", "zhangsan");
		System.out.println("返回值: "+set); //返回值为 OK

//获取key的值
		String name = jedis.get("name");
		System.out.println("name: "+name);

//向list1添加值
		Long list1 = jedis.lpush("list1", "3", "4", "5");
		System.out.println(list1);

//设置key的过期时间
		jedis.expire("name",10);//可以在xshello中测试 tcl name，多执行几次看下效果
		try
		{
			Thread.sleep(20000);
		}
		catch (Exception e)
		{

		}

//获取key的值
		String name1 = jedis.get("name");
		System.out.println("name: "+name1);

//使用过之后关闭连接
		jedis.close();

	}


}
