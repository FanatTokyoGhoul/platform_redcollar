package com.example.platform_redcollar.controllers;

import com.example.platform_redcollar.models.dto.request.PlatformDtoRequest;
import com.example.platform_redcollar.models.dto.response.PersonDtoResponse;
import com.example.platform_redcollar.models.dto.response.PlatformDtoResponse;
import com.example.platform_redcollar.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {
    private final PlatformService platformService;

    @Autowired
    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public List<PlatformDtoResponse> findAll() {
        return platformService.findAll();
    }

    @GetMapping("/{id}")
    public PlatformDtoResponse getPlatform(@PathVariable Long id) {
        return platformService.getPlatform(id);
    }

    @GetMapping("/genres")
    public List<PersonDtoResponse> getPersonMakingContent(@RequestParam Long id) {
        return platformService.getPersonMakingContent(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlatformDtoResponse create(@RequestBody PlatformDtoRequest company) {
        return platformService.create(company);
    }

    @PutMapping("/{id}")
    public PlatformDtoResponse update(@PathVariable Long id, @RequestBody PlatformDtoRequest company) {
        return platformService.update(id, company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        platformService.delete(id);
    }
}
