package com;

import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class File {
    private long id;
    private String name;
    private String format;
    private long size;
    private Storage storage;

    @Id
    @SequenceGenerator(name = "FILESQ", sequenceName = "FILESQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILESQ")
    @Column(name = "ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() > 10)
            throw new Exception("Such name to long!");
        this.name = name;
    }

    @Column(name = "FORMAT")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Column(name = "FILE_SIZE")
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @ManyToOne
    @JoinColumn(name = "STORAGE_ID")
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
