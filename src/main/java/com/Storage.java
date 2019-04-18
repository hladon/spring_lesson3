package com;

public class Storage {
    private long id;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageSize;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String[] formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }
}
