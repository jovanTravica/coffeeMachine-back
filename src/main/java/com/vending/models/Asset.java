package com.vending.models;

<<<<<<< HEAD

=======
>>>>>>> origin/master
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "asset", schema = "cb")
public class Asset {

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

    @javax.persistence.ManyToOne ()
    @JoinColumn(name = "modelid", referencedColumnName = "id", nullable = false)
    private Model model;

<<<<<<< HEAD
    @javax.persistence.ManyToOne ()
    @JoinColumn(name = "locid", referencedColumnName = "id", nullable = false)
    private Location location;


    public  Asset () {}

    public Asset(long id, String code, String name, String descr, Timestamp version, Model model, Location location) {
=======
//    @javax.persistence.OneToMany(mappedBy = "assetId")
//    private AssetLocation assetLocation;

public  Asset () {}

    public Asset(long id, String code, String name, String descr, Timestamp version, Model modelID, Model model) {
>>>>>>> origin/master
        this.id = id;
        this.code = code;
        this.name = name;
        this.descr = descr;
        this.version = version;

        this.model = model;
<<<<<<< HEAD
        this.location = location;
=======
>>>>>>> origin/master
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



    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
<<<<<<< HEAD

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
=======
>>>>>>> origin/master
}
