package com.daoauthenticate.dao.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleModel {

    @Id
    @GeneratedValue
    private long id;

    private String role;


    @ManyToMany(mappedBy = "roles")
    private Set<UserModel> user = new HashSet<>();

    public RoleModel() {
    }

    public RoleModel(long id, String role, Set<UserModel> user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserModel> getUser() {
        return user;
    }

    public void setUser(Set<UserModel> user) {
        this.user = user;
    }
}
