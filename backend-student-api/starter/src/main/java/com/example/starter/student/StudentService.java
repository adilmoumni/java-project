package com.example.starter.student;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.message.StructuredDataId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository ;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void createNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw  new IllegalStateException("Email taken");
        }

        studentRepository.save(student);
    }

    public void deletStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist) throw new IllegalStateException("User do not exist " + studentId );

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("User not found")
        );

        // update name
        if(name != null && name.length() > 0 && name != student.getName()){
            student.setName(name);
        }
        // Update email
        if(email != null && email.length() > 0 && email != student.getEmail()){
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if(studentOptional.isPresent()) throw new IllegalStateException("Email exist");
            student.setEmail(email);
        }



    }
}
