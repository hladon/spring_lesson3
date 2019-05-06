package com;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STORAGES")
public class Storage {
    private long id;
    private List<String> formatsSupported;
    private String storageCountry;
    private long storageSize;
    @Id
    @SequenceGenerator(name = "STORAGESQ", sequenceName = "STORAGESQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORAGESQ")
    @Column(name = "ID",unique = true,nullable = false)
    public long getId() {
        return id;
    }

    @ElementCollection
    @CollectionTable(name = "FORMATS",joinColumns = @JoinColumn(name="ID"))
    @Column(name = "FORMATS")
    public List<String> getFormatsSupported() {
        return formatsSupported;
    }

    @Column(name = "STORAGE_COUNTRY")
    public String getStorageCountry() {
        return storageCountry;
    }

    @Column(name = "STORAGE_MAX_SIZE")
    public long getStorageSize() {
        return storageSize;
    }


    public void setFormatsSupported(List<String> formatsSupported ) {
        this.formatsSupported = formatsSupported;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    public void setId(long id) {
        this.id = id;
    }

}
