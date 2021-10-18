package com.example.platform_redcollar.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformDtoRequest {
    private String name;
    private String genres;
    private String url;
    @NotNull
    private Long idCompany;
}
