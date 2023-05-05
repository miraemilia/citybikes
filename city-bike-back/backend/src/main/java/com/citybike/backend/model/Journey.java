package com.citybike.backend.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Journey extends AbstractPersistable<Long>  {

	private String departureDate;
    private String returnDate;
    @ManyToOne
    @JoinColumn (name = "departureStationId", referencedColumnName = "id")
    private Station departureStation;
    @ManyToOne
    @JoinColumn (name = "returnStationId", referencedColumnName = "id")
    private Station returnStation;
    @Min(value = 10, message="distance too short")
    private int distance;
    @Min(value = 10, message="duration too short")
    private int duration;
}
