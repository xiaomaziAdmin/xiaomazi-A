package com.xiaomazi.springboot.handler;

import com.alibaba.fastjson.JSONObject;
import com.xiaomazi.springboot.constants.Constants;
import com.xiaomazi.springboot.model.ResultObject;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @program: 15-springboot-Advanced
 * @description
 * @author: XiaoMaZi
 * @create: 2021-05-08 21:47
 **/
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> ,RequestBodyAdvice{


    @ExceptionHandler
    public @ResponseBody ResultObject errorHandlerByJson(HttpServletRequest request, Exception e) throws Exception {
        //可以拿到异常信息
        e.printStackTrace();

        //可以返回统一数据
        return new ResultObject(Constants.ONE, "Sorry，服务器开小差啦，RuntimeException~", null);
    }


    //@ExceptionHandler(value = IOException.class)
    public @ResponseBody ResultObject errorHandlerByJson2(HttpServletRequest request, Exception e) throws Exception {
        //可以拿到异常信息
        e.printStackTrace();

        //可以返回统一数据
        return new ResultObject(Constants.ONE, "Sorry，IOException~", null);
    }


    /**
     * 当发生异常的时候，跳转到统一的错误页
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    //@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IOException.class)
    public ModelAndView errorHandlerByView(HttpServletRequest request, Exception e) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e.getMessage());
        modelAndView.addObject("url", request.getRequestURL());


        //设置发生异常的时候，跳转到哪个页面
        modelAndView.setViewName("50x");
        //可以返回统一数据
        return modelAndView;
    }

    /**
     * 处理404页面找不到错误
     * 配置一个bean
     *
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return ((ConfigurableWebServerFactory factory) -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"); //OK
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
            factory.addErrorPages(error401Page, error404Page, error500Page);
        });
    }


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        System.out.println("beforeBodyWrite.............................................");
        Object out = null;
        if (body instanceof ResultObject) {
            out = body;
        } else if (body instanceof String) {
            //因为controller返回类型是String，这里需要将ResultObject转成String
            ResultObject resultObject = new ResultObject<>(Constants.ZERO, "操作成功", body);
            out = JSONObject.toJSONString(resultObject);
            out = body;
        } else {
            out = body;
        }
        return out;
    }




    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return null;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }
}
