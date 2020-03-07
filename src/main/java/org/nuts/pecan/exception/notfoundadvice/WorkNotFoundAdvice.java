package org.nuts.pecan.exception.notfoundadvice;

import org.nuts.pecan.exception.notfoundexception.WorkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mashun
 * @Description pecan project
 * @create 2020/3/7 01:29:28
 */
public class WorkNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(WorkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String workNotFoundAdviceHandler(WorkNotFoundException ex){
        return ex.getMessage();
    }
}
