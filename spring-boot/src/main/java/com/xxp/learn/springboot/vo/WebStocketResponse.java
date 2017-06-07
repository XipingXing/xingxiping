package com.xxp.learn.springboot.vo;

/**
 * Created by xingxiping on 2017/6/7.
 */
public class WebStocketResponse {

    private String responseMessage;

    public WebStocketResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
