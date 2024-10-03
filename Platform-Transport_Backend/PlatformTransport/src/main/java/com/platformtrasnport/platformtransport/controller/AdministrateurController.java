package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.service.Impl.AdministrateurServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administrateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class AdministrateurController {

    private final AdministrateurServiceImpl administrateurService;

    public AdministrateurController(AdministrateurServiceImpl administrateurService) {
        this.administrateurService = administrateurService;
    }

    @GetMapping("/count")
    public long getAdministrateurCount() {
        return administrateurService.getAdministrateurCount();
    }

}
