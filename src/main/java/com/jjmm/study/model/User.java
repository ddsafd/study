package com.jjmm.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author tqz
 * @since 2022-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
public class User extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Size(max=20,message = "姓名长度不能超过20")
    private String name;

    @Size(max = 30,message = "密码长度不能大于30位")
    private String password;

    private Integer age;

    @Size(max=40,message = "邮箱的长度不能超过40")
    @Email
    private String email;

    private LocalDate birth;

    @Max(value=1,message = "1为男，0为女")
    @Min(value=0,message = "1为男，0为女")
    private Integer issingle;

    @TableField(exist = false)
    private boolean isLogerStarMe=false;//登录者是否点赞过我。
    @TableField(exist = false)
    private int numsStarMe;//人数：总共有多少人点赞过我。
    @TableField(exist = false)
    private List<User> lStarMe;//赞过我的所有用户

    public User() {
    }

    public User(Integer id, String name, String password, Integer age, String email, LocalDate birth, Integer issingle) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.birth = birth;
        this.issingle = issingle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isLogerStarMe == user.isLogerStarMe &&
                numsStarMe == user.numsStarMe &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(age, user.age) &&
                Objects.equals(email, user.email) &&
                Objects.equals(birth, user.birth) &&
                Objects.equals(issingle, user.issingle) &&
                Objects.equals(lStarMe, user.lStarMe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, age, email, birth, issingle, isLogerStarMe, numsStarMe, lStarMe);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Integer getIssingle() {
        return issingle;
    }

    public void setIssingle(Integer issingle) {
        this.issingle = issingle;
    }

    public boolean isLogerStarMe() {
        return isLogerStarMe;
    }

    public void setLogerStarMe(boolean logerStarMe) {
        isLogerStarMe = logerStarMe;
    }

    public int getNumsStarMe() {
        return numsStarMe;
    }

    public void setNumsStarMe(int numsStarMe) {
        this.numsStarMe = numsStarMe;
    }

    public List<User> getlStarMe() {
        return lStarMe;
    }

    public void setlStarMe(List<User> lStarMe) {
        this.lStarMe = lStarMe;
    }
}
