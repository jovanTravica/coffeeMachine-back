package com.vending.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "location")
public class Location {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    private long id;

    @Column(name = "code", unique = true ,nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @Version
    @Column(name = "version")
    private Timestamp version;

    @Column(name = "adress", nullable = false)
    private String adress;

    @Column(name = "datefrom", nullable = false)
    private Date dateFrom;

    @Column(name = "dateto", nullable = false)
    private Date dateTo;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public Location() {

    }

    public Location(long id, String code, String name, String descr, Timestamp version,String adress, Date dateFrom, Date dateTo, Boolean active) {
        this.code = code;
        this.name = name;
        this.descr = descr;
        this.version = version;
        this.adress = adress;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.active = active;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }


    public Timestamp getVersion() {
        return version;
    }




    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
