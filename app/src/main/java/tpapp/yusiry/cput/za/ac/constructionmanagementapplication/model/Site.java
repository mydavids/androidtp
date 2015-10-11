package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model;

/**
 * Created by Yusiry Davids on 9/23/2015.
 */
public class Site {
    private int id;
    private String siteName;
    private int siteSize;
    private String address;

    public Site(){

    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return siteName;
    }

    public void setName(String name) {
        this.siteName = name;
    }

    public int getSize() {
        return siteSize;
    }

    public void setSize(int size) {
        this.siteSize = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
