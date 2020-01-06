package com.qianxin.common;

import com.qianxin.enums.ResultEnum;

public class Result<T> {
    private String code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> build(ResultEnum resultEnum) {
        return new Result<T>(resultEnum);
    }

    public static <T> Result<T> build(String code, String message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> build(String code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage(), data);
    }

    public Result(String message) {
        this.code = ResultEnum.ERROR.getCode();
        this.message = ResultEnum.ERROR.getMessage();
    }

    public Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = null;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;

    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
