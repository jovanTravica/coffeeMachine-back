package com.vending.models;


import com.vending.models.DocumentType;
import com.vending.models.Asset;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "document", schema = "cb")
public class Document {

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

    @Column(name = "datdoc", nullable = false)
    private Date dateDoc;


    @javax.persistence.ManyToOne()
    @JoinColumn(name = "dtid", referencedColumnName = "id", nullable = false)
    private DocumentType documentType;

    @javax.persistence.ManyToOne()
    @JoinColumn(name = "assetid", referencedColumnName = "id", nullable = false)
    private Asset asset;


    public Document() {

    }

    public Document(long id, String code, String name, String descr, Timestamp version, Date dateDoc, Date dateFrom, Date dateTo, DocumentType documentType, Asset asset) {
        this.code = code;
        this.name = name;
        this.descr = descr;
        this.version = version;
        this.dateDoc = dateDoc;
        this.documentType = documentType;

        this.asset = asset;
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





    public Date getDateDoc() {
        return dateDoc;
    }

    public void setDateDoc(Date dateDoc) {
        this.dateDoc = dateDoc;
    }


    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }



    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}

