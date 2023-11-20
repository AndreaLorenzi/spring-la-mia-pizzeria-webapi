package com.experis.course.springlamiapizzeriacrud.repository;

import com.experis.course.springlamiapizzeriacrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
