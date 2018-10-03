package com.ziplinegreen.vault.Model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post")
public class Post extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    private String message;
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long userId;
    private String userName;

    public Post(){
    }
    public Post(@NotNull String message, Long userId, String userName) {
        this.message = message;
        this.userId = userId;
        this.userName = userName;
    }

    public Long getId() {
        return Id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Id=" + Id +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
