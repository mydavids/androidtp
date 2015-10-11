package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpHead;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Yusiry Davids on 9/23/2015.
 */
public class RestMethods {

    public static RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        //Add the Gson message converter
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        return restTemplate;
    }

    public static HttpHeaders getHeaders(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        return requestHeaders;
    }
}
