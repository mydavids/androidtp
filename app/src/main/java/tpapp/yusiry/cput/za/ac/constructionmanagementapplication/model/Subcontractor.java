package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model;

/**
 * Created by Yusiry Davids on 9/24/2015.
 */
public class Subcontractor {

    private int id;
    private String name;
    private String speciality;
    private String address;

    public Subcontractor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
