package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.service.Impl.AdministrateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurServiceImpl administrateurService;

    @GetMapping("/count")
    public long getAdministrateurCount() {
        return administrateurService.getAdministrateurCount();
    }
}
