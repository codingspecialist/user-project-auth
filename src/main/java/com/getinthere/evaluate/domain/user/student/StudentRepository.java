package com.getinthere.evaluate.domain.user.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserId(Long id);
}
