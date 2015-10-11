package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services.impl;

import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model.Staff;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.RestAPI;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories.rest.RestStaffAPI;
import tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services.StaffService;

import java.util.List;

/**
 * Created by Yusiry Davids on 9/24/2015.
 */
public class StaffServiceImpl implements StaffService {

    final RestAPI<Staff, Integer> rest = new RestStaffAPI();

    @Override
    public Staff findById(Integer id) {
        return rest.get(id);
    }

    @Override
    public String save(Staff entity) {
        return rest.post(entity);
    }

    @Override
    public String update(Staff entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(Staff entity) {
        return rest.delete(entity);
    }

    @Override
    public List<Staff> findAll() {
        return rest.getAll();
    }
}
