package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services.impl;

import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Subcontractor;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.RestAPI;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestSubcontractorAPI;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services.SubcontractorService;

import java.util.List;

/**
 * Created by Yusiry Davids on 9/24/2015.
 */
public class SubcontractorServiceImpl implements SubcontractorService {

    final RestAPI<Subcontractor, Integer> rest = new RestSubcontractorAPI();

    @Override
    public Subcontractor findById(Integer id) {
        return rest.get(id);
    }

    @Override
    public String save(Subcontractor entity) {
        return rest.post(entity);
    }

    @Override
    public String update(Subcontractor entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(Subcontractor entity) {
        return rest.delete(entity);
    }

    @Override
    public List<Subcontractor> findAll() {
        return rest.getAll();
    }
}
