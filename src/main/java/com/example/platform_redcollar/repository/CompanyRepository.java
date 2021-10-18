package com.example.platform_redcollar.repository;

import com.example.platform_redcollar.models.entities.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
