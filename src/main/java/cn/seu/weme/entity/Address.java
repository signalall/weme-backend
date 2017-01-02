package cn.seu.weme.entity;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by LCN on 2016-12-19.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String detail;

//    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY, targetEntity = Person.class)
//    @JoinColumn(name = "person_id", nullable = false, updatable = false)

//    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Person.class)
//    @JoinColumn(name = "person_id")
//    private Person person;


    @OrderBy("name desc")
    @ManyToMany(mappedBy = "addresses",fetch = FetchType.LAZY,targetEntity = Person.class)
    private Set<Person> persons = new HashSet<>();

    public Address() {
    }

    public Address(String detail) {
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

}

