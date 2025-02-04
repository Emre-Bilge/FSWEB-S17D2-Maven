package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.tax.DeveloperTax;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {


    public Map<Integer, Developer> developers;
    private final DeveloperTax developerTax;


    @PostConstruct
    public void init() {
        this.developers = new HashMap<>();
    }

    @Autowired
    public DeveloperController(DeveloperTax developerTax) {
        this.developerTax = developerTax;

    }

    public Map<Integer, Developer> getDevelopers() {
        return developers;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Developer> getAllDeveloper() {
        return developers.values().stream().toList();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Developer findDeveloper(@PathVariable int id) {
        if (developers.containsKey(id)) {
            return developers.get(id);
        } else {
            throw new IllegalArgumentException("böyle bir id ye sahip developer yok ");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void addDeveloper(@RequestBody Developer developer) {
        this.developers.put(developer.getId(), developer);
    }

    @PutMapping("/{id}")
    public Developer update(@PathVariable int id, @RequestBody Developer developer) {
        this.developers.replace(id, developer);
        return this.developers.get(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable Integer id) {
        developers.remove(id);
    }

}


