package com.sktech.academicportal.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 40, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Override
    public String toString() {
        return this.name;
    }
}
