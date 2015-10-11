package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest;

import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Subcontractor;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusiry Davids on 9/24/2015.
 */
public class RestSubcontractorAPI implements RestAPI<Subcontractor, Integer> {

    final String BASE_URL = "http://app-constructionma.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Subcontractor get(Integer id) {
        final String url = BASE_URL+"subcontractor/"+id.toString();
        HttpEntity<Subcontractor> requestEntity = new HttpEntity<Subcontractor>(requestHeaders);
        ResponseEntity<Subcontractor> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Subcontractor.class);
        Subcontractor subcontractor = responseEntity.getBody();
        return subcontractor;
    }

    @Override
    public String post(Subcontractor entity) {
        final String url = BASE_URL+"subcontractor/create";
        HttpEntity<Subcontractor> requestEntity = new HttpEntity<Subcontractor>(entity, requestHeaders);
        HttpEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Subcontractor entity) {
        final String url = BASE_URL+"subcontractor/update/"+entity.getId();
        HttpEntity<Subcontractor> requestEntity = new HttpEntity<Subcontractor>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Subcontractor entity) {
        final String url = BASE_URL+"/subcontractor/delete/"+entity.getId().toString();
        HttpEntity<Subcontractor> requestEntity = new HttpEntity<Subcontractor>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Subcontractor> getAll() {
        List<Subcontractor> subcontractors = new ArrayList<>();
        final String url = BASE_URL+"subcontractors/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Subcontractor[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Subcontractor[].class);
        Subcontractor[] results = responseEntity.getBody();

        for(Subcontractor subcontractor : results){
            subcontractors.add(subcontractor);
        }

        return subcontractors;
    }
}
