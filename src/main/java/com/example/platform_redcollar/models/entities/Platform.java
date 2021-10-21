package com.example.platform_redcollar.models.entities;

import com.example.platform_redcollar.models.dto.request.PlatformDtoRequest;
import com.example.platform_redcollar.models.dto.response.PlatformDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("platform")
public class Platform {
    @Id
    private Long id;

    private String url;
    private String name;
    private String genres;
    @NotNull
    private Long idCompany;

    public PlatformDtoResponse toDto() {
        return PlatformDtoResponse.builder().
                id(id).
                name(name).
                url(url).
                genres(genres).
                idCompany(idCompany).
                build();
    }

    public static Platform fromDto(PlatformDtoRequest platformDtoRequest) {
        return Platform.builder().
                name(platformDtoRequest.getName()).
                url(platformDtoRequest.getUrl()).
                genres(platformDtoRequest.getGenres()).
                idCompany(platformDtoRequest.getIdCompany()).
                build();
    }
}
