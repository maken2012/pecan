package org.nuts.pecan.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/7 01:17:12
 *
 * 具体表名使用注解Table(name = "USER_WORK") 原案例类目为Order 与数据库中关键字冲突
 */
@Entity
@Data
public class Work {

    private @Id
    @GeneratedValue
    Long id;
    private String description;
    private Status status;

    public Work() {}

    public Work(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}
