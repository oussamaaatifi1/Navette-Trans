package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.service.impl.EmployeurServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeurs")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeurController {

    private final EmployeurServiceImpl employeurService;

    public EmployeurController(EmployeurServiceImpl employeurService) {
        this.employeurService = employeurService;
    }

    @GetMapping
    public List<EmployeurDto> getAllEmployeurs() {
        return employeurService.getAllEmployeurs();
    }

}
