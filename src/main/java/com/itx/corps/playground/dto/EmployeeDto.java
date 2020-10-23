package com.itx.corps.playground.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private Double salary;
    @NotNull
    private String department;
}
