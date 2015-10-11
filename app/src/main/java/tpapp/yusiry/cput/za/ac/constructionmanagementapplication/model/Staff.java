package tpapp.yusiry.cput.za.ac.constructionmanagementapplication.model;

/**
 * Created by Yusiry Davids on 9/24/2015.
 */
public class Staff {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String idnumber;
    private String address;

    public Staff() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIDnumber() {
        return idnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.idnumber = IDnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
