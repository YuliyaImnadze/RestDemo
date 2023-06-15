package com.example.restdemo.validator;

import com.example.restdemo.dto.PersonDto;
import com.example.restdemo.entity.Person;
import com.example.restdemo.exeption.ValidException;
import com.example.restdemo.repository.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class PersonValidator {

    private final PersonRepository personRepository;

    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void validate(PersonDto personDto) {
        Optional<Person> personByNickname = personRepository.findByNickname(personDto.getNickname());
        if (personByNickname.isPresent()) {
            Integer personId = personByNickname.get().getId();
            if (!Objects.equals(personId, personDto.getId())) {
                throw new ValidException("Nickname is already taken");
            }

        }

        if (!personDto.getEmail().equals("")) {
            Optional<Person> personByEmail = personRepository.findByEmail(personDto.getEmail());
            if (personByEmail.isPresent()) {
                Integer personId = personByEmail.get().getId();
                if (!Objects.equals(personId, personDto.getId()) && personId != null) {
                    throw new ValidException("This email is already taken");
                }
            }

        }

    }


}
