package org.nuts.pecan.repository;

import org.nuts.pecan.pojo.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/7 01:28:21
 */
@Transactional(rollbackFor = Exception.class)
public interface WorkRepository extends JpaRepository<Work, Long > {

}
