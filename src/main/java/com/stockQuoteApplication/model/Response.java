package com.stockQuoteApplication.model;

public class Response {
    private String successMsg;

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public Response(String successMsg) {
        this.successMsg = successMsg;
    }
}
