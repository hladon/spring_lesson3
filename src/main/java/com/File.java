package com;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "FILES")
public class File {
    private long id;
    @Size(max = 10)
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


    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "FORMAT")
    public String getFormat() {
        return format;
    }

    @Column(name = "FILE_SIZE")
    public long getSize() {
        return size;
    }


    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "STORAGE_ID")
    public Storage getStorage() {
        return storage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) throws Exception {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
