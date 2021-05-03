package com.example.demo.Repository;

import com.example.demo.Model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducationRepo extends JpaRepository<Education, Integer> {
}
