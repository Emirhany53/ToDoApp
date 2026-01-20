package com.emirhan.todo.todo_api.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name="todos")
public class ToDo {
    
    @Column(nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private boolean completed=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}

    public boolean isCompleted(){return completed;}
    public void setCompleted(boolean completed){this.completed=completed;}

    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}



}
