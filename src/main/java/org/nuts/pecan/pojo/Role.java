package org.nuts.pecan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 20:54:41
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue
    private long id;
    private String name;

}
