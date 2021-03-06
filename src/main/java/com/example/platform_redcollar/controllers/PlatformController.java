package com.example.platform_redcollar.controllers;

import common.lib.models.dto.request.PlatformDtoRequest;
import common.lib.models.dto.response.PlatformDtoResponse;
import common.lib.models.dto.response.PersonDtoResponse;
import com.example.platform_redcollar.services.PlatformService;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("{id}/persons")
    public List<PersonDtoResponse> getPersonMakingContent(@PathVariable Long id, HttpServletRequest httpRequest) {
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) httpRequest.getUserPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getAccount().getKeycloakSecurityContext();
        String token = keycloakSecurityContext.getTokenString();
        return platformService.getPersonMakingContent(id, token);
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
