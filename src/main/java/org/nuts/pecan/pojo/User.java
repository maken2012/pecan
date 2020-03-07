package org.nuts.pecan.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 20:37:22
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String role;

    public User() {
    }

    public User(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name){
        this.firstName = name.split(" ")[0];
        this.lastName = name.split(" ")[1];
    }
}

