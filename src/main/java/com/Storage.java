package com;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "STORAGE")
public class Storage {
    private long id;
    private List<String> formatsSupported;
    private String storageCountry;
    private long storageSize;
    @Id
    @SequenceGenerator(name = "STORAGE_SQ", sequenceName = "STORAGE_SQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORAGE_SQ")
    @Column(name = "ID",unique = true,nullable = false)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @ElementCollection
    @Column(name = "FORMATS_SUPPORTED")
    public List<String> getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(List<String> formatsSupported ) {
        this.formatsSupported = formatsSupported;
    }
    @Column(name = "STORAGE_COUNTRY")
    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }
    @Column(name = "STORAGE_MAX_SIZE")
    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }


}
