package com.vending.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "measurement", schema = "cb")
public class Measurement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    private long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @Version
    @Column(name = "version")
    private Timestamp version;


    public  Measurement () {}

    public Measurement(long id, String code, String name, String descr, Timestamp version) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.descr = descr;
        this.version = version;

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

    public void setVersion(Timestamp version) {
        this.version = version;
    }


}

