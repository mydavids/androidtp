package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.services;

import java.util.List;

/**
 * Created by Yusiry Davids on 9/23/2015.
 */
public interface Services<S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();
}
