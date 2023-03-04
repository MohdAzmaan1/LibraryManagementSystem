package com.example.Library.Management.System.Models;

import javax.persistence.*;

import com.example.Library.Management.System.Enums.TransactionStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private int fine;
    private String transactionId = UUID.randomUUID().toString();

    @CreationTimestamp
    private Date transactionDate;

    private boolean isIssueOperation;

    @ManyToOne
    @JoinColumn
    private Book book; //Book entity will be a foreign key here

    @ManyToOne
    @JoinColumn
    private Card card;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public boolean isIssueOperation() {
        return isIssueOperation;
    }

    public void setIssueOperation(boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
