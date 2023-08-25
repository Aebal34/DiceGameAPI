package com.Utopia.Joren.DiceGameAPI.Model.Domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Table(name = "roles")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
