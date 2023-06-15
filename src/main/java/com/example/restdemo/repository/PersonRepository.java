package com.example.restdemo.repository;

import com.example.restdemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByNickname(String nickname);

    Optional<Person> findByEmail(String email);




}