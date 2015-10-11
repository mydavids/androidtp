package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services.impl;

import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Site;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.RestAPI;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestSiteAPI;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services.SiteService;

import java.util.List;

/**
 * Created by Yusiry Davids on 9/23/2015.
 */
public class SiteServiceImpl implements SiteService {

    final RestAPI<Site, Integer> rest = new RestSiteAPI();

    @Override
    public Site findById(Integer id) {
        return rest.get(id);
    }

    @Override
    public String save(Site entity) {
        return rest.post(entity);
    }

    @Override
    public String update(Site entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(Site entity) {
        return rest.delete(entity);
    }

    @Override
    public List<Site> findAll() {
        return rest.getAll();
    }
}
