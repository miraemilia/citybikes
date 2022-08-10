package com.citybike.backend.model;

import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Journey extends AbstractPersistable<Long>  {

	private String departureDate;
    private String returnDate;
    private int departureStation;
    private int returnStation;
    @Min(value = 10, message="distance too short")
    private int distance;
    @Min(value = 10, message="duration too short")
    private int duration;
}
