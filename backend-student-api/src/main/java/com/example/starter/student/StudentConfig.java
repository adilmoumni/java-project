package com.example.starter.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner (
            StudentRepository repo
    ) {
        return args -> {
            Student student1 =  new Student(
                    "mariam",
                    LocalDate.of(2000, Month.JUNE,10),
                    "moumniabdou.adil@gmail.com"
            );
            Student student2 =  new Student(
                    "ail",
                    LocalDate.of(1999, Month.JANUARY,10),
                    "moumniabdou.adil@gmail.com"
            );
            repo.saveAll(
                    List.of(student1, student2)
            );
        };
    }
}
