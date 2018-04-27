package chen.spider.spiderservice.contact;

import java.util.Date;

/**
 * Created by chpy on 18/4/26.
 */
public class BaseResponse<T> {

    private String returnMsg;
    private Integer statusCode;
    private T data;
    private long timestamp;

    private BaseResponse() {
        timestamp = new Date().getTime();
    }

    private BaseResponse(StatusCodeEnum statusEnum) {
        this();
        this.statusCode = statusEnum.getCode();
        this.returnMsg = statusEnum.getMessage();
    }

    private BaseResponse(String returnMsg, Integer statusCode) {
        this();
        this.returnMsg = returnMsg;
        this.statusCode = statusCode;
    }

    public static BaseResponse success() {
        return new BaseResponse(StatusCodeEnum.SUCCESS);
    }

    public static BaseResponse builder(StatusCodeEnum codeEnum) {
        return new BaseResponse(codeEnum);
    }

    public static BaseResponse builder(Integer code, String msg) {
        return new BaseResponse(msg, code);
    }

    public static BaseResponse error(Exception ex) {
        return new BaseResponse(ex.toString(), StatusCodeEnum.THROW_ERROR.getCode());
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public enum StatusCodeEnum {
        /* 100-199 参数验证错误*/
        PARAM_ERROR(100, "Params is error"),
        SUCCESS(200, "Success"),
        /* 400 -500 服务器错误*/
        SERVER_ERROR(400, "Server Error"),
        THROW_ERROR(401, "throw error");

        StatusCodeEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
