package org.training360.sv2jvjbfzarovizsga.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schools")
@NoArgsConstructor
@Getter
@Setter
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "school_name")
    private String schoolName;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "school")
    private List<Student> students;

    public School(String schoolName, Address address) {
        this.schoolName = schoolName;
        this.address = address;
    }

    public void addStudent(Student student) {
        student.setSchool(this);
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setSchool(null);
    }
}
