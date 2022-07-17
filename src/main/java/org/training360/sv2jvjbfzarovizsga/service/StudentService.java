package org.training360.sv2jvjbfzarovizsga.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.sv2jvjbfzarovizsga.exceptions.EntityNotFoundException;
import org.training360.sv2jvjbfzarovizsga.model.Student;
import org.training360.sv2jvjbfzarovizsga.repository.StudentRepository;

@Service
@Transactional
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
}
