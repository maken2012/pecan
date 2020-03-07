package org.nuts.pecan.config;

import lombok.extern.slf4j.Slf4j;
import org.nuts.pecan.pojo.Status;
import org.nuts.pecan.pojo.User;
import org.nuts.pecan.pojo.Work;
import org.nuts.pecan.repository.UserRepository;
import org.nuts.pecan.repository.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 21:17:19
 */
@Configuration
@Slf4j
public class LoadDataBase {

    @Bean
    CommandLineRunner initUserData(UserRepository userRepository){
        return args -> {
            log.info("初始化用户数据");
            // delete 和 save在一个方法中会出现异常
            // 事务问题 启动类注解@EnableTransactionManagement  service类注解@Transactional(rollbackFor = Exception.class)
//            userRepository.deleteByName("admin");
//            userRepository.deleteByName("user");
//            log.info("Preloading" + userRepository.save(new User("admin","admin")));
//            log.info("Preloading" + userRepository.save(new User("user","user")));
        };
    }

    @Bean
    CommandLineRunner initWorkData(WorkRepository workRepository){
        return args -> {
            log.info("初始化任务数据");
            workRepository.save(new Work("MackBook Pro", Status.CANCELLED));
            workRepository.save(new Work("IPhone XS", Status.COMPLETED));
            workRepository.save(new Work("IPad Pro", Status.IN_PROGRESS));
            workRepository.findAll().forEach(work -> log.info("Preloaded: " + work));
        };
    }

}
