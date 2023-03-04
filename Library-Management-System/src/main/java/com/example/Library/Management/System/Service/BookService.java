package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.DTO.BookRequestDTO;
import com.example.Library.Management.System.Models.Author;
import com.example.Library.Management.System.Models.Book;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDTO bookRequestDTO){
        int authorId = bookRequestDTO.getAuthorId();

        //Now fetching the author entity
        Author author = authorRepository.findById(authorId).get();

        //Basic attributes are set from postman
        //Setting the foreign key attribute in child class
        Book book = new Book();
        //Basic attributes are being set from DTO

        book.setGenre(bookRequestDTO.getGenre());
        book.setName(bookRequestDTO.getName());
        book.setPages(bookRequestDTO.getPages());
        book.setIssued(false);

        book.setAuthor(author);

        //Update the list of books written in parent class

        List<Book> currentBooksWritten = author.getBookWritten();
        currentBooksWritten.add(book);

        //Now author needs to be updated
        authorRepository.save(author); //if primary key is already present then save will update it otherwise create a new object

        //bookrepository.save is not required because of cascading effect
        return "Book Added Successfully";
    }
}
