package org.training360.sv2jvjbfzarovizsga.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
//@AllArgsConstructor
public class CreateSchoolCommand {

    @NotBlank(message = "Schoolname cannot be blank!")
    private String schoolName;
    private String postalCode;
    private String city;
    private String street;
    private int houseNumber;

    public CreateSchoolCommand(String schoolName, String postalCode, String city, String street, int houseNumber) {
        this.schoolName = schoolName;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public static void main(String[] args) {
        CreateSchoolCommand command = new CreateSchoolCommand("Petőfi Sándor School", "1123", "Budapest", "Petőfi u.", 8);
        System.out.println(command);
    }
}
