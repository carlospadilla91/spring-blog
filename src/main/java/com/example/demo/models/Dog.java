package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "dogs",
uniqueConstraints = @UniqueConstraint(name = "UK_name_AND_resident_state", columnNames = {"name", "residentState"}))
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned", nullable = false)
    private int id;

    @Column(unique = true, nullable = false, columnDefinition = "tinyint unsigned")
    private int age;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "char(2) default 'XX'")
    private String residentState;

    public Dog(){}

    public Dog(int age, String name, String residentState) {
        this.age = age;
        this.name = name;
        this.residentState = residentState;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResidentState() {
        return residentState;
    }

    public void setResidentState(String residentState) {
        this.residentState = residentState;
    }
}
