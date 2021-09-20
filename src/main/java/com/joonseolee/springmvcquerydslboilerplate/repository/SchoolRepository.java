package com.joonseolee.springmvcquerydslboilerplate.repository;

import com.joonseolee.springmvcquerydslboilerplate.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchoolRepository extends JpaRepository<School, Long> {
}
