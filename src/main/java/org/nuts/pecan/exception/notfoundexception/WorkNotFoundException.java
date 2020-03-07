package org.nuts.pecan.exception.notfoundexception;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/7 01:29:53
 */
public class WorkNotFoundException extends RuntimeException{
    public WorkNotFoundException (Long id) {
        super("Could not find Work " + id);
    }
}
