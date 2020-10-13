package com.vending.models;



import javax.persistence.*;

@Entity
@Table(name = "stanje", schema = "cb")
public class Stanje {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    private long id;

    @Column(name = "dani")
    private Integer dani;

    @javax.persistence.ManyToOne()
    @JoinColumn(name = "assetid", referencedColumnName = "id", nullable = false)
    private Asset asset;


    public  Stanje () {}

    public Stanje(long id, Integer dani, Asset asset) {
        this.id = id;


        this.dani = dani;
        this.asset = asset;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Integer getDani() {
        return dani;
    }

    public void setDani(Integer dani) {
        this.dani = dani;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}


