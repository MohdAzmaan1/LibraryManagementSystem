package com.example.Library.Management.System.Models;

import javax.persistence.*;

import com.example.Library.Management.System.Enums.CardStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;            //Its auto generated

    @CreationTimestamp        //AutoStamp time when an entry is created
    private Date createdOn;  //Its auto generated

    @UpdateTimestamp         //Sets time when any update is made
    private Date updatedOn; //Its auto generated

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;  //Set this attribute

    @OneToOne
    @JoinColumn
    private Student student; // This variable is used in parent class for bidirectional mapping

    //Card is parent wrt book

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> bookIssued = new ArrayList<>();

    //Connecting the card class to the transaction class
    //Bidirectional mapping
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction> transactionList  = new ArrayList<>();

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Book> getBookIssued() {
        return bookIssued;
    }

    public void setBookIssued(List<Book> bookIssued) {
        this.bookIssued = bookIssued;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
