package com.example.platform_redcollar.models.entities;


import common.lib.models.dto.request.CompanyDtoRequest;
import common.lib.models.dto.response.CompanyDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("company")
public class Company {
    @Id
    private Long id;
    private String name;
    @MappedCollection(idColumn = "id_company")
    private Set<Platform> platforms;

    public CompanyDtoResponse toDto() {
        return CompanyDtoResponse.builder().
                id(id).
                name(name).build();
    }

    public static Company fromDto(CompanyDtoRequest companyDtoRequest) {
        return Company.builder()
                .name(companyDtoRequest.getName())
                .build();
    }
}
