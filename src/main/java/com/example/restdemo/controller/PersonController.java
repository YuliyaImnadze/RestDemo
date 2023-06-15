package com.example.restdemo.controller;

import com.example.restdemo.dto.PersonDto;
import com.example.restdemo.entity.Person;
import com.example.restdemo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDto> showAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDto showById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @PostMapping("/create")
    public Person create(@RequestBody @Valid PersonDto personDto) {

        return personService.save(personDto);
    }


    @PutMapping("/update")
    public void updatePerson(@RequestBody @Valid PersonDto personDto) {
        personService.update(personDto);
    }

    @DeleteMapping("/delete")
    public void deletePerson(@RequestBody PersonDto personDto) {
        personService.delete(personDto);
    }


    @PostMapping("/nickname")
    public Optional<Person> findByNickname(@RequestBody Person person) {
        return personService.findByNickname(person);
    }


    @PostMapping("/email")
    public Optional<Person> findByEmail(@RequestBody PersonDto personDto) {
        return personService.findByEmail(personDto);
    }

}
