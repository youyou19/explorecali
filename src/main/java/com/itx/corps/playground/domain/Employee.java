package com.itx.corps.playground.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee implements Serializable {

    @Id
    @Column(unique = true, name ="name")
    @Pattern(regexp = "[a-zA-Z ]*", message = "{name should be contained only letter}")
    private String name;

    @Column(name="salary_employee")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_name")
    private Department department;
}
