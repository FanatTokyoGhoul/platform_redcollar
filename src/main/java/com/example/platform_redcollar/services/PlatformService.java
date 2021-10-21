package com.example.platform_redcollar.services;

import com.example.platform_redcollar.exceptions.NotFoundEntityException;
import com.example.platform_redcollar.models.dto.request.PlatformDtoRequest;
import com.example.platform_redcollar.models.dto.response.PersonDtoResponse;
import com.example.platform_redcollar.models.dto.response.PlatformDtoResponse;
import com.example.platform_redcollar.models.entities.Platform;
import com.example.platform_redcollar.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlatformService {


    private final PlatformRepository platformRepository;
    private final WebClientService webClientService;

    @Autowired
    public PlatformService(PlatformRepository platformRepository, WebClientService webClientService) {
        this.platformRepository = platformRepository;
        this.webClientService = webClientService;
    }

    public List<PlatformDtoResponse> findAll(){
        List<PlatformDtoResponse> listDtos = new ArrayList<>();
        platformRepository.findAll().forEach(platform -> {
            listDtos.add(platform.toDto());
        });
        return listDtos;
    }

    public PlatformDtoResponse create(PlatformDtoRequest platformDto){
        Platform platform = Platform.fromDto(platformDto);
        return platformRepository.save(platform).toDto();
    }

    public PlatformDtoResponse getPlatform(Long id){
        Optional<Platform> optionalPlatform = platformRepository.findById(id);
        Platform platform = optionalPlatform.orElseThrow(() -> new NotFoundEntityException(id));

        return platform.toDto();
    }

    public List<PersonDtoResponse> getPersonMakingContent(Long id){

        Platform platform = platformRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(id));

        return webClientService.getPersonGenres(platform.getGenres());
    }

    public PlatformDtoResponse update(Long id, PlatformDtoRequest platformDto){
        Optional<Platform> optionalPlatform = platformRepository.findById(id);
        Platform platform = optionalPlatform.orElseThrow(() -> new NotFoundEntityException(id));

        platform.setName(platformDto.getName());
        platform.setGenres(platformDto.getGenres());
        platform.setUrl(platformDto.getUrl());

        return platformRepository.save(platform).toDto();
    }

    public void delete(Long id){
        platformRepository.deleteById(id);
    }
}
