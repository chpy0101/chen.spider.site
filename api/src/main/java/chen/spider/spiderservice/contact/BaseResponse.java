package chen.spider.spiderservice.contact;

/**
 * Created by chpy on 18/4/26.
 */
public class BaseResponse<T> {

    private BaseResponse() {
    }

    private BaseResponse(StatusCodeEnum statusEnum) {
        this.statusCode = statusEnum.getCode();
        this.returnMsg = statusEnum.getMessage();
    }

    private BaseResponse(String returnMsg, Integer statusCode) {
        this.returnMsg = returnMsg;
        this.statusCode = statusCode;
    }

    private String returnMsg;
    private Integer statusCode;
    private T data;

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static BaseResponse success() {
        return new BaseResponse(StatusCodeEnum.SUCCESS);
    }

    public static BaseResponse error() {
        return new BaseResponse(StatusCodeEnum.SERVER_ERROR);
    }

    public static BaseResponse fail(int code, String msg) {
        return new BaseResponse(msg, code);
    }

    public static BaseResponse error(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(StatusCodeEnum.THROW_ERROR.getCode());
        response.setReturnMsg(ex.toString());
        return response;
    }

    public enum StatusCodeEnum {
        /* 100-199 */
        SUCCESS(200, "Success"),
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
