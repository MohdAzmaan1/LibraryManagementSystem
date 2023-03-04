package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Modifying
    @Transactional
    @Query("update Book b set b.issued =:#{#book.issued}, b.card =:#{#book.card} where b.id =:#{#book.id}")
    int updateBook(Book book);
}
