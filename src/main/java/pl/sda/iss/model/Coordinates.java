package pl.sda.iss.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "coordinates")
@Data

public class Coordinates {

    @Id
    @GeneratedValue
    private Long id;

    private double longitude;
    private double latitude;

    @OneToMany
    @JoinColumn(name = "coordinates_id")
    private List<PassTime> passTimes;



}
