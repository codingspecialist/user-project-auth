package com.getinthere.evaluate.domain.user.emp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Long> {
    Emp findByUserId(Long id);
}
