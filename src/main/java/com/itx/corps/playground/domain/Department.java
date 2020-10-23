package com.itx.corps.playground.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
@Entity
@Table(name ="department")
public class Department implements Serializable {
    @Id
    @Column(name = "department_name", unique = true)
    private String name;
}
