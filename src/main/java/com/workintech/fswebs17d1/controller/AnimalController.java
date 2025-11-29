package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals;

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    @PostConstruct
    public void init() {
        System.out.println("courseName: " + courseName);
        System.out.println("developerName: " + developerName);
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1, "dog"));
    }

    @GetMapping
    public List<Animal> getAnimals() {
        return new ArrayList<>(this.animals.values());
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable int id) {
        return this.animals.get(id);
    }

    @PostMapping
    public Animal save(@RequestBody Animal animal) {
        this.animals.put(animal.getId(), animal);
        return animal;
    }

    @PutMapping("/{id}")
    public Animal update(@PathVariable int id, @RequestBody Animal newAnimal) {
        this.animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable int id) {
        Animal removed = this.animals.get(id);
        this.animals.remove(id);
        return removed;
    }
}
