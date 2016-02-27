package com.tripoin.xwp.data.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */

@Entity
@Table(name = "mst_bank")
public class EntityBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    @Override
    public String toString() {
        return "EntityBank{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
