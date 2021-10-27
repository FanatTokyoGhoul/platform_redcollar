package com.example.platform_redcollar.services;

import com.example.platform_redcollar.exceptions.NotFoundEntityException;
import com.example.platform_redcollar.models.dto.request.CompanyDtoRequest;
import com.example.platform_redcollar.models.dto.response.CompanyDtoResponse;
import com.example.platform_redcollar.models.entities.Company;
import com.example.platform_redcollar.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyDtoResponse> findAll() {
        List<CompanyDtoResponse> listDtos = new ArrayList<>();
        companyRepository.findAll().forEach(company -> {
            listDtos.add(company.toDto());
        });

        return listDtos;
    }

    public CompanyDtoResponse create(CompanyDtoRequest companyDtoRequest) {
        Company company = Company.fromDto(companyDtoRequest);
        return companyRepository.save(company).toDto();
    }


    public CompanyDtoResponse getCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Company company = optionalCompany.orElseThrow(() -> new NotFoundEntityException(id));

        return company.toDto();
    }

    public CompanyDtoResponse update(Long id, CompanyDtoRequest companyDtoRequest) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Company company = optionalCompany.orElseThrow(() -> new NotFoundEntityException(id));

        company.setName(companyDtoRequest.getName());

        return companyRepository.save(company).toDto();
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
