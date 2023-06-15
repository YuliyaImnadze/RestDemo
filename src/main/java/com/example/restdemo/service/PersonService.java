package com.example.restdemo.service;

import com.example.restdemo.dto.PersonDto;
import com.example.restdemo.entity.Person;
import com.example.restdemo.repository.PersonRepository;
import com.example.restdemo.validator.PersonValidator;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper personMapper;

    private final PersonValidator personValidator;


    @Autowired
    public PersonService(PersonRepository personRepository, ModelMapper personMapper, PersonValidator personValidator) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.personValidator = personValidator;
    }
    
    public List<PersonDto> findAll() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(person -> personMapper.map(person, PersonDto.class))
                .sorted(Comparator.comparing(PersonDto::getName))
                .collect(Collectors.toList());
    }

    public PersonDto findById(Integer id) {
        Person personById = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return personMapper.map(personById, PersonDto.class);
    }

    @Transactional
    public Person save(PersonDto personDto) {
        personValidator.validate(personDto);
        Person person = personMapper.map(personDto, Person.class);
        return personRepository.save(person);
    }


    @Transactional
    public void update(PersonDto personDto) {
        Person updatedPerson = personRepository.findById(personDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        personValidator.validate(personDto);
        updatedPerson.setName(personDto.getName());
        updatedPerson.setAge(personDto.getAge());
        updatedPerson.setEmail(personDto.getEmail());
        updatedPerson.setNickname(personDto.getNickname());
        personRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(PersonDto personDto) {
        personRepository.deleteById(personDto.getId());
    }

    public Optional<Person> findByNickname(Person person) {
        return personRepository.findByNickname(person.getNickname());
    }

    public Optional<Person> findByEmail(PersonDto personDto) {
        return personRepository.findByEmail(personDto.getEmail());
    }

}
