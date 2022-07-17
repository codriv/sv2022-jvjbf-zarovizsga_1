package org.training360.sv2jvjbfzarovizsga.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.training360.sv2jvjbfzarovizsga.model.Address;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDto {

    private Long id;
    private String schoolName;
    private Address address;
    private List<StudentDto> students;
}
