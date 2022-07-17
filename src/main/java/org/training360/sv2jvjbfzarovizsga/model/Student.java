package org.training360.sv2jvjbfzarovizsga.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student {

    public static final int LEGAL_AGE = 16;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "school_age_status")
    private SchoolAgeStatus schoolAgeStatus;
    @ManyToOne
    private School school;

    public void setSchoolAgeStatus() {
        int age = (int) ChronoUnit.YEARS.between(LocalDate.now(), dateOfBirth);
        schoolAgeStatus = age < LEGAL_AGE ? SchoolAgeStatus.SCHOOL_AGED : SchoolAgeStatus.NOT_SCHOOL_AGED;
    }
}
