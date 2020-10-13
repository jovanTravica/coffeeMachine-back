package com.vending.models;


import com.vending.models.Asset;
import com.vending.models.Document;
import com.vending.models.Measurement;


import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "documentitem", schema = "cb")
public class DocumentItem {

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

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "amount")
    private long amount;

    @Column(name = "grossamount")
    private long grossAmount;

    @javax.persistence.ManyToOne ()
    @JoinColumn(name = "docid", referencedColumnName = "id", nullable = false)
    private Document document;



    @javax.persistence.ManyToOne ()
    @JoinColumn(name = "mid", referencedColumnName = "id", nullable = false)
    private Measurement measurement;


    public DocumentItem() {

    }

    public DocumentItem(long id, String code, String name, String descr, Timestamp version, long quantity, long amount, long grossAmount, Document document, Measurement measurement) {
        this.code = code;
        this.name = name;
        this.descr = descr;
        this.version = version;
        this.quantity = quantity;
        this.amount = amount;
        this.grossAmount = grossAmount;


        this.document = document;

        this.measurement = measurement;

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


    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }



    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(long grossAmount) {
        this.grossAmount = grossAmount;
    }
}

