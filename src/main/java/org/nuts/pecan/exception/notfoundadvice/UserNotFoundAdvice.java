package org.nuts.pecan.exception.notfoundadvice;

import org.nuts.pecan.exception.notfoundexception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/6 22:53:42
 */
@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundAdviceHandler(UserNotFoundException ex){
        return ex.getMessage();
    }
}
