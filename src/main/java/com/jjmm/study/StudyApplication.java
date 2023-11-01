package com.jjmm.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 自动生成mybatisplus的相关代码
 */

@SpringBootApplication
@MapperScan("com.jjmm.study.mapper")
//@EnableCaching
public class StudyApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(StudyApplication.class,args);
	}


}

interface  A
{
	public abstract void haha();
}
class B implements A
{
	public void haha(){}
}
class C implements A
{
	public void haha(){}

}