package shippo.global.api;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class HttpApi {
    static Logger LOG = LoggerFactory.getLogger(HttpApi.class);
    private int connectTimeout;
    private int requestTimeout;

    public HttpApi() {
        this(10000,10000);
    }

    public HttpApi(int connectTimeout, int requestTimeout) {
        this.connectTimeout = connectTimeout;
        this.requestTimeout = requestTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public ContentResponse post(String url, JSONObject header, JSONObject body){
        try {
            SslContextFactory sslContextFactory = new SslContextFactory();
            HttpClient httpClient = new HttpClient(sslContextFactory);
            httpClient.setConnectTimeout(connectTimeout);
            httpClient.start();

//            Fields fields = new Fields();
//            for(String key : body.keySet()){
//                fields.put(key,body.getString(key));
//            }
//            ContentResponse response = httpClient.FORM(url, fields);
            Request request = httpClient.POST(url);
            request.timeout(requestTimeout, TimeUnit.MILLISECONDS);
            for (String key : header.keySet()) {
                request.header(key, header.getString(key));
            }
            request.header(HttpHeader.CONTENT_TYPE, "application/json");
            request.content(new StringContentProvider(body.toString()), "application/json");
            ContentResponse response = request.send();
            httpClient.stop();
            return response;
        }catch (Exception e){
            e.printStackTrace();
            LOG.error(e.toString());
            return null;
        }
    }
}
