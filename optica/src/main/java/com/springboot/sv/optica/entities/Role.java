package com.springboot.sv.optica.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"}) //Para quitar la recursivdad
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Role other = (Role) object;
        if(id == null){
            if(other.id != null)
                return  false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null){
            if(other.name != null)
                return false;
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        final  int prime = 31;
        int resul = 1;
        resul = prime * resul + ((id == null) ? 0: id.hashCode());
        resul = prime * resul + ((name == null) ? 0: name.hashCode());
        return resul;
    }
}