package com.scaler.bookmyshowsep24.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Feature> features;
}
