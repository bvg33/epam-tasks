package com.epam.ems.controllers;

import com.epam.ems.dto.Certificate;
import com.epam.ems.service.CRDService;
import com.epam.ems.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/certificates")

public class CertificateController {
    @Autowired
    private CRUDService<Certificate> service;

    /**
     *
     * @return
     */

    @GetMapping
    public List<Certificate> getAllCertificates() { //todo javadoc
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Certificate getCertificateById(@PathVariable int id){
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCertificate(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Success");
    }

    @PostMapping("/new") //todo request body
    public ResponseEntity<String> addCertificate(@RequestParam MultiValueMap<String, String> allRequestParams) {
        service.insertIntoDB(allRequestParams);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCertificate(@PathVariable int id, @RequestParam MultiValueMap<String, String> allRequestParams) {
        allRequestParams.add("id", String.valueOf(id));
        service.update(allRequestParams);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Certificate>> filter(@RequestParam MultiValueMap<String, String> allRequestParams) {
        return ResponseEntity.status(HttpStatus.OK).body(service.doFilter(allRequestParams));
    }
}
