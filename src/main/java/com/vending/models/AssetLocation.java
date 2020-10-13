package com.vending.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "assetlocation", schema = "cb")
public class AssetLocation {

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

    @Column(name = "datefrom", nullable = false)
    private Date dateFrom;

    @Column(name = "dateto", nullable = false)
    private Date dateTo;

    @javax.persistence.ManyToOne ()
    @JoinColumn(name = "assetid", referencedColumnName = "id", nullable = false)
    private Asset asset;

    @javax.persistence.ManyToOne()
    @JoinColumn(name = "locid", referencedColumnName = "id", nullable = false)
    private Location location;


    public AssetLocation() {

    }

    public AssetLocation(long id, String code, String name, String descr, Timestamp version, Date dateFrom, Date dateTo, Asset asset, Location location) {
        this.code = code;
        this.name = name;
        this.descr = descr;
        this.version = version;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;

        this.asset = asset;
        this.location = location;
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


    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
