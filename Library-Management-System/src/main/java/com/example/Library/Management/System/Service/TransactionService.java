package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.DTO.IssueBookRequestDTO;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Enums.TransactionStatus;
import com.example.Library.Management.System.Models.Book;
import com.example.Library.Management.System.Models.Card;
import com.example.Library.Management.System.Models.Transaction;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Value("${books.max_allowed}")
    int max_allowed_books;

    @Value("${books.max_allowed_days}")
    int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    int fine_per_day;

    public String issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception{
        int bookID = issueBookRequestDTO.getBookId();
        int cardID = issueBookRequestDTO.getCardId();

        //Get the book entity and card entity to set the transaction attributes
        Book book = bookRepository.findById(bookID).get();
        Card card = cardRepository.findById(cardID).get();

        // Setting the transaction attributes and save it

        Transaction transaction = new Transaction();

        //Setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //Attribute left is success or failure
        //Doing some validations
        if(book == null || book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            throw new Exception("Book is not available");
        }

        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not valid");
        }

        if(card.getBookIssued().size() >= max_allowed_books){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book limit has reached for this card");
        }
        //Success case
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //Set the attributes of the book
        book.setIssued(true);
        book.setCard(card);
        List<Transaction> lisOfBookTransaction = book.getListOfTransaction();
        lisOfBookTransaction.add(transaction);

        //I need to make changes in the card
        //Book and the card
        List<Book> issuedBooksForCard = card.getBookIssued();
        issuedBooksForCard.add(book);
        card.setBookIssued(issuedBooksForCard);

        //Card and the Transaction : bidirectional (parent class)
        List<Transaction> transactionListForCard = card.getTransactionList();
        transactionListForCard.add(transaction);
        card.setTransactionList(transactionListForCard);

        //Saving the parent
        cardRepository.save(card);
        //automatically by cascading : book and transaction will be saved.
        //Saving the parent

        return "Book issued Successfully";

    }

    public String getTransactions(int bookId,int cardId){

        List<Transaction> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);
        return transactionsList.get(0).getTransactionId();
    }

    public String returnBook(int cardId, int bookId) throws Exception{

        List<Transaction> transactions = transactionRepository.find(cardId, bookId,TransactionStatus.SUCCESS, true);

        Transaction transaction = transactions.get(transactions.size() - 1);

        Date issueDate = transaction.getTransactionDate();

        long timeIssuetime = Math.abs(System.currentTimeMillis() - issueDate.getTime());

        long no_of_days_passed = TimeUnit.DAYS.convert(timeIssuetime, TimeUnit.MILLISECONDS);

        int fine = 0;
        if(no_of_days_passed > getMax_allowed_days){
            fine = (int)((no_of_days_passed - getMax_allowed_days) * fine_per_day);
        }

        Book book = transaction.getBook();

        book.setIssued(false);
        book.setCard(null);

        bookRepository.updateBook(book);

        Transaction transaction1 = new Transaction();
        transaction1.setBook(transaction.getBook());
        transaction1.setCard(transaction.getCard());
        transaction1.setIssueOperation(false);
        transaction1.setFine(fine);
        transaction1.setTransactionStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction1);

        return transaction1.getTransactionId();
    }
}
