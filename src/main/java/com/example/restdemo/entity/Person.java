package com.example.restdemo.entity;

import com.example.restdemo.events.PersonEventListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@EntityListeners(PersonEventListener.class)
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min = 2, max = 20)
    @Column(name = "name")
    private String name;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "age")
    private Integer age;

    @Email
    @Column(name = "email", length = 50)
    private String email;

    @Size(min = 2, max = 20)
    @NotBlank
    @Column(name = "nickname", unique = true, nullable = false, length = 20)
    private String nickname;

    @Transient
    private String fullName;

    public Integer getId() {
        return id;
    }

    public Person setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Person setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @PostLoad
    public void createFullName() {
        fullName = name + " " + nickname;
    }

}