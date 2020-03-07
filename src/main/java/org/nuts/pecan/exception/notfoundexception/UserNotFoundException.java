package org.nuts.pecan.exception.notfoundexception;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 22:03:41
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("Could not find User " + id);
    }

}
