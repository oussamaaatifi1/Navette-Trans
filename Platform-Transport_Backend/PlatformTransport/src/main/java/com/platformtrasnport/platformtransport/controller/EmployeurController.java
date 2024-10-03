package com.platformtrasnport.platformtransport.controller;

import com.platformtrasnport.platformtransport.dto.EmployeurDto;
import com.platformtrasnport.platformtransport.service.Impl.EmployeurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employeurs")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeurController {

    @Autowired
    private EmployeurServiceImpl employeurService;

    @GetMapping
    public List<EmployeurDto> getAllEmployeurs() {
        return employeurService.getAllEmployeurs();
    }

}
