package com.mynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 查询模型服务测试
 *
 * @author Jinhua
 * @version 1.0
 * @date 2020/11/4 14:44
 */
public class ModelServiceQueryTest {

    /**
     * rest请求对象
     */
    private static final RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
    }


    @SneakyThrows
    public static void main(String[] args) {
        HttpClient client = HttpClientBuilder.create().build();

        String modelLabel = "building";

        URIBuilder builder = new URIBuilder();
        String host = KettlePropertiesResolver.getProperty("model_service_ip");
        String port = KettlePropertiesResolver.getProperty("model_service_port");
        String path = "/model-meta/v1/models/label/" + modelLabel;
        builder.setScheme("http").setHost(host).setPort(Integer.parseInt(port)).setPath(path);

        URI uri = builder.build();
        HttpGet httpGet = new HttpGet(uri);

        HttpResponse getResp = client.execute(httpGet);
        HttpEntity entity = getResp.getEntity();
        StringBuffer contentBuffer = new StringBuffer();
        String line;
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(entity.getContent()))) {
            while ((line = bReader.readLine()) != null) {
                contentBuffer.append(line);
            }
        }
        System.out.println(contentBuffer);

        ObjectMapper objectMapper = new ObjectMapper();
        Result result = objectMapper.readValue(contentBuffer.toString(), Result.class);
        System.out.println(result);

    }


    private URI buildUri(String modelLabel) throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        String host = KettlePropertiesResolver.getProperty("model_service_ip");
        String port = KettlePropertiesResolver.getProperty("model_service_port");
        String path = "/model-meta/v1/models/label/" + modelLabel;
        builder.setScheme("http").setHost(host).setPort(Integer.parseInt(port)).setPath(path);

        return builder.build();
    }

    /**
     * 测试restTemplate工具
     */
    @Test
    public void restTemplateTest() throws URISyntaxException {
        String modelLabel = "building";
        Result<?> result = restTemplate.getForObject(buildUri(modelLabel), Result.class);
        assert result != null;
        System.out.println(result.getCode());
    }


}
