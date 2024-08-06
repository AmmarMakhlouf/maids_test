package cc.maids.library_test.entity;

import jakarta.persistence.*;

@Entity
public class Patron {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String email;
    @Column (name = "contact")
    private String contactInformation;
    @Column
    private String address;

    public Patron() {
    }

    public Patron(Long id, String name, String email, String contactInformation, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    public Patron(String name, String email, String contactInformation, String address) {
        this.name = name;
        this.email = email;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
