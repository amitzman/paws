package com.compozed.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shelters")
@Access(AccessType.PROPERTY)
public class Shelter {
    private int id;
    private String name;
    private Date opened;

    public Shelter(String name, Date opened) {
        this.name = name;
        this.opened = opened;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "dateOpened", nullable = false)
    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }
}
