package org.training360.sv2jvjbfzarovizsga.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.sv2jvjbfzarovizsga.dto.CreateSchoolCommand;
import org.training360.sv2jvjbfzarovizsga.dto.CreateStudentCommand;
import org.training360.sv2jvjbfzarovizsga.dto.SchoolDto;
import org.training360.sv2jvjbfzarovizsga.exceptions.EntityNotFoundException;
import org.training360.sv2jvjbfzarovizsga.model.Address;
import org.training360.sv2jvjbfzarovizsga.model.School;
import org.training360.sv2jvjbfzarovizsga.model.Student;
import org.training360.sv2jvjbfzarovizsga.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchoolService {

    private SchoolRepository schoolRepository;
    private StudentService studentService;
    private ModelMapper modelMapper;

    public SchoolService(SchoolRepository schoolRepository, StudentService studentService, ModelMapper modelMapper) {
        this.schoolRepository = schoolRepository;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    public SchoolDto createSchool(CreateSchoolCommand command) {

        String name = command.getSchoolName();
        String postalCode = command.getPostalCode();
        String city = command.getCity();
        String street = command.getStreet();
        int houseNumber = command.getHouseNumber();
        Address address = new Address(postalCode, city, street, houseNumber);
        School school = new School(name, address);
        schoolRepository.save(school);
        return modelMapper.map(school, SchoolDto.class);
    }

    public SchoolDto addStudent(Long id, CreateStudentCommand command) {
        School school = findSchoolById(id);
        Student student = modelMapper.map(command, Student.class);
        student.setSchoolAgeStatus();
        studentService.saveStudent(student);
        school.addStudent(student);
        return modelMapper.map(school, SchoolDto.class);
    }

    private School findSchoolById(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("School", id));
    }

    public List<SchoolDto> getSchools(Optional<String> city) {
        List<School> schools = schoolRepository.findSchoolsByCity(city);
        return schools.stream().map(s -> modelMapper.map(s, SchoolDto.class)).toList();
    }

    public SchoolDto studentOff(Long schoolId, Long studentId) {
        School school = findSchoolById(schoolId);
        Student student = studentService.findStudentById(studentId);
        if (!school.getStudents().contains(student)) {
            throw new EntityNotFoundException("Student", studentId);
        }
        school.removeStudent(student);
        return modelMapper.map(school, SchoolDto.class);
    }
}
