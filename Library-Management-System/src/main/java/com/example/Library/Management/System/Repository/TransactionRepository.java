package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Enums.TransactionStatus;
import com.example.Library.Management.System.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "select * from transaction where book_id=:bookId and card_id=:cardId and is_issue_operation=true", nativeQuery = true)
    List<Transaction> getTransactionsForBookAndCard(int bookId, int cardId);

    @Query("select t from Transaction t where t.card.id=:cardId and t.book.id=:bookId and t.isIssueOperation=:isIssue and t.transactionStatus=:status")
    public List<Transaction> find(int cardId, int bookId, TransactionStatus status, boolean isIssue);
}
