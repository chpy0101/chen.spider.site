package chen.spider.spiderservice.aop;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;

/**
 * Created by chpy on 18/5/30.
 */
public class HttpRequestEntity implements RequestCallback {

    HttpEntity body;

    public HttpEntity getBody() {
        return body;
    }

    public void setBody(HttpEntity body) {
        this.body = body;
    }

    @Override
    public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {

    }
}
