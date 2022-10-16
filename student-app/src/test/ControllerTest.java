import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Nitesh Poudel
 */
public class ControllerTest {

    public static final String BASE_URL = "http://localhost:8090/";
    protected static final String X_REQUEST_CHANNEL = "x-request-channel";
    protected RestTemplate restTemplate;
    protected HttpHeaders headers;

    public ControllerTest() {

        this.restTemplate = new RestTemplate();



        this.headers = new HttpHeaders();

        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.add(X_REQUEST_CHANNEL, "web");

    }

    protected ClientHttpRequestFactory getClientHttpRequestFactory() {

        int timeout = 10 * 1000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(60 * 1000);
        return clientHttpRequestFactory;

    }


}
