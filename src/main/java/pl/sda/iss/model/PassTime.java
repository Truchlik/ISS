package pl.sda.iss.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pass_times")
@Data
public class PassTime {

    @Id
    private Long risetime;
    private int duration;


    @ManyToOne
    private Coordinates coordinates;


}
