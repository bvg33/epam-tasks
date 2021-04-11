package com.epam.ems.controllers;

import com.epam.ems.service.CRDService;
import com.epam.ems.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {
    @Autowired
    private CRDService<Tag> service;

    @GetMapping
    public List<Tag> AllTags() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Tag TagById(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable int id) {
        service.deleteById(id);
        return "Deleted successfully"; //todo response entity
    }

    @PostMapping("/new")
    public String createTag(@RequestParam MultiValueMap<String, String> allRequestParams) {
        service.insertIntoDB(allRequestParams);
        return "Created successfully"; //todo void or id
    }

    @GetMapping("/filter")
    public List<Tag> sortByParam(@RequestParam MultiValueMap<String, String> allRequestParams) {
        return service.doFilter(allRequestParams);
    }
}
