package com.tn.Repository;

import com.tn.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Departmentrepo extends JpaRepository<Department, Integer> {
}
