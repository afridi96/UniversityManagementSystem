package com.tangent.ums.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Semester {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
