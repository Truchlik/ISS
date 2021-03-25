package pl.sda.iss.model;

import lombok.Data;

import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "coordinates")
@Data
@NoArgsConstructor

public class Coordinates {


    public Coordinates(double longitude, double latitude, List<PassTime> passTimes) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.passTimes = passTimes;
    }


    @Id
    @GeneratedValue
    private Long id;

    private double longitude;
    private double latitude;

    @OneToMany
    @JoinColumn(name = "coordinates_id")
    private List<PassTime> passTimes;



}
