package com.example.platform_redcollar.controllers;


import com.example.platform_redcollar.services.CompanyService;
import common.lib.models.dto.request.CompanyDtoRequest;
import common.lib.models.dto.response.CompanyDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDtoResponse> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDtoResponse getComapany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDtoResponse create(@RequestBody CompanyDtoRequest company) {
        return companyService.create(company);
    }

    @PutMapping("/{id}")
    public CompanyDtoResponse update(@PathVariable Long id, @RequestBody CompanyDtoRequest company) {
        return companyService.update(id, company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }
}
