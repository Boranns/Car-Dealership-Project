package com.borangalleri.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name= "gallerists_car",
uniqueConstraints={@UniqueConstraint(columnNames= {"gallerist_id", "car_id"}, name= "uq_gallerist_car")})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GalleristsCar extends BaseEntity{

    @ManyToOne
    private Car car;

    @ManyToOne
    private Gallerist gallerist;

}
