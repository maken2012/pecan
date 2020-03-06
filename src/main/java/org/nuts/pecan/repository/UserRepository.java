package org.nuts.pecan.repository;

import org.nuts.pecan.pojo.organization.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 21:06:21
 */
interface UserRepository extends JpaRepository<User, Long > {

}
