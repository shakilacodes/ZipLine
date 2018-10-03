package com.ziplinegreen.vault.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vault_user")
public class User extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    private String email;
    private String password;
    @OneToMany(
            cascade= {
                CascadeType.PERSIST,
                CascadeType.MERGE
            }, mappedBy = "userId")
    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        if(posts.size()==0){
            posts = new ArrayList<>();
            return posts;
        }
        return posts;
    }

    public User(){}
    public User(@NotNull String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
