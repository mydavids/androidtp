package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest;

import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Site;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yusiry Davids on 9/23/2015.
 */
public class RestSiteAPI implements RestAPI<Site, Integer> {

    final String BASE_URL = "http://app-constructionma.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Site get(Integer id) {
        final String url = BASE_URL+"site/"+id.toString();
        HttpEntity<Site> requestEntity = new HttpEntity<Site>(requestHeaders);
        ResponseEntity<Site> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Site.class);
        Site site = responseEntity.getBody();
        return site;
    }

    @Override
    public String post(Site entity) {
        final String url = BASE_URL+"site/create";
        HttpEntity<Site> requestEntity = new HttpEntity<Site>(entity, requestHeaders);
        HttpEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Site entity) {
        final String url = BASE_URL+"site/update/"+entity.getId();
        HttpEntity<Site> requestEntity = new HttpEntity<Site>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(Site entity) {
        final String url = BASE_URL+"site/delete/"+entity.getId().toString();
        HttpEntity<Site> requestEntity = new HttpEntity<Site>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public void deleteSite(int id){
        final String url = BASE_URL+"site/delete/"+id;
        HttpEntity<Site> requestEntity = new HttpEntity<Site>(requestHeaders);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", Integer.toString(id));
        restTemplate.delete(url, id);
    }

    @Override
    public List<Site> getAll() {
        List<Site> sites = new ArrayList<>();
        final String url = BASE_URL+"sites";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Site[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Site[].class);
        Site[] results = responseEntity.getBody();

        for(Site site : results){
            sites.add(site);
        }

        return sites;
    }
}
