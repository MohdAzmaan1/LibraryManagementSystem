package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.DTO.AuthorEntryDTO;
import com.example.Library.Management.System.DTO.AuthorResponseDTO;
import com.example.Library.Management.System.DTO.BookResponseDTO;
import com.example.Library.Management.System.Models.Author;
import com.example.Library.Management.System.Models.Book;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorEntryDTO authorEntryDTO){
        //Here the object is of DTO type but the repo interacts only with entities
        //Solution: convert authorEntryDTO to author entity
        Author author = new Author();
        //We are setting its attribute so that we can set correct attribute in the DB
        author.setName(authorEntryDTO.getName());
        author.setAge(authorEntryDTO.getAge());
        author.setCountry(authorEntryDTO.getCountry());
        author.setRating(authorEntryDTO.getRating());

        authorRepository.save(author);

        return "Author is Added Successfully";
    }

    public AuthorResponseDTO getAuthor(Integer authorID){
        Author author = authorRepository.findById(authorID).get();
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();

        // Set the attributes
        //converting list of books to lis of book response dto
        List<Book> bookList = author.getBookWritten();
        List<BookResponseDTO> bookWrittenDTO = new ArrayList<>();

        for(Book b : bookList){
            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            bookResponseDTO.setGenre(b.getGenre());
            bookResponseDTO.setName(b.getName());
            bookResponseDTO.setPages(b.getPages());
            bookWrittenDTO.add(bookResponseDTO);
        }

        //Author response DTO
        authorResponseDTO.setBooksWritten(bookWrittenDTO);
        authorResponseDTO.setName(author.getName());
        authorResponseDTO.setAge(author.getAge());
        authorResponseDTO.setRating(author.getRating());
        authorResponseDTO.setCountry(author.getCountry());

        return authorResponseDTO;
    }
}
