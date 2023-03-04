package com.example.Library.Management.System.DTO;


import com.example.Library.Management.System.Enums.Genre;

public class BookResponseDTO {
    private String name;
    private int pages;

    private Genre genre;

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
}
