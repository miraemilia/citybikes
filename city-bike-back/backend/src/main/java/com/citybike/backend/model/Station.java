package com.citybike.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Station {

    @Id
    private int id;

    private String name;
    private String address;
    private String x;
    private String y;
    
}
