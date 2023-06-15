package com.example.restdemo.events;


import com.example.restdemo.entity.Person;
import jakarta.persistence.PostLoad;


public class PersonEventListener {

    @PostLoad
    private void afterLoad(Person person) {
        System.out.println("Клиент " + person.getName() + " был добавлен c id " + person.getId()); //
    }

}
