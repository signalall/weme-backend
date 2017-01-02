package cn.seu.weme.common.exception;

import cn.seu.weme.common.result.ErrorInfo;
import cn.seu.weme.common.result.ExceptionResultInfo;
import cn.seu.weme.common.result.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LCN on 2016-12-17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo<String> exceptionHandler(HttpServletRequest req, Exception e){
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage("普通异常");
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

    @ExceptionHandler(value = ExceptionResultInfo.class)
    @ResponseBody
    public ResultInfo MyExceptionHandler(HttpServletRequest req, ExceptionResultInfo e){
       return e.getResultInfo();
    }
}
