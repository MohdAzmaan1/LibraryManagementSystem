package com.example.Library.Management.System.Models;

import com.example.Library.Management.System.Enums.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int pages;
    private boolean issued;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;


    // Book is child for Author
    @ManyToOne
    @JoinColumn
    private Author author; //This is the parent entity we are connecting with


    //Book is also child for card
    @ManyToOne
    @JoinColumn
    private Card card;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> listOfTransaction = new ArrayList<>();

    public List<Transaction> getListOfTransaction() {
        return listOfTransaction;
    }

    public void setListOfTransaction(List<Transaction> listOfTransaction) {
        this.listOfTransaction = listOfTransaction;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
