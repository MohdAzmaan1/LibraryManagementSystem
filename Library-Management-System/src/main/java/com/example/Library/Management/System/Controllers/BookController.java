package com.example.Library.Management.System.Controllers;

import com.example.Library.Management.System.DTO.BookRequestDTO;
import com.example.Library.Management.System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody BookRequestDTO bookRequestDTO){
        return bookService.addBook(bookRequestDTO);
    }
}
