package com.jjmm.study.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author tqz
 * @since 2022-08-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Star extends Model {

    private static final long serialVersionUID = 1L;

    private Integer sendid;

    private Integer getid;

    private LocalDate msgtime;

    public Star() {
    }

    public Star(Integer sendid, Integer getid, LocalDate msgtime) {
        this.sendid = sendid;
        this.getid = getid;
        this.msgtime = msgtime;
    }
}
