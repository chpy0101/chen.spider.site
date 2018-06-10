package chen.spider.spiderservice.contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;


/**
 * Created by chpy on 18/5/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequest<T> {

    private String reqNo;

    private LocalDateTime reqTime;
    private T content;

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public LocalDateTime getReqTime() {
        return reqTime;
    }

    public void setReqTime(LocalDateTime reqTime) {
        this.reqTime = reqTime;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
