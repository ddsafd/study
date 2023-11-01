package com.jjmm.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tqz
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Loglogin extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("loginId")
    private Integer loginId;

    private LocalDateTime logintime;

    public Loglogin() {
    }

    public Loglogin(Integer id, Integer loginId, LocalDateTime logintime) {
        this.id = id;
        this.loginId = loginId;
        this.logintime = logintime;
    }
}
