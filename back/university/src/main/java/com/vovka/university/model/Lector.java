package com.vovka.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vovka.university.model.enums.Degree;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "lector")
@EqualsAndHashCode(exclude = {"departments"})
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToMany (cascade = { CascadeType.ALL })
    @JoinTable(
            name = "lector_department",
            joinColumns = { @JoinColumn(name = "lector_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    @JsonIgnore
    Set<Department> departments = new HashSet<>();
}
