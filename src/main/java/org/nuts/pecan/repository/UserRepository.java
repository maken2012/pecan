package org.nuts.pecan.repository;

import org.nuts.pecan.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 21:06:21
 */
@Transactional(rollbackFor = Exception.class)
public interface UserRepository extends JpaRepository<User, Long > {

}
