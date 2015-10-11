package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.repositories;

import java.util.List;

/**
 * Created by Yusiry Davids on 9/23/2015.
 */
public interface RestAPI<S, ID> {

    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();
}
