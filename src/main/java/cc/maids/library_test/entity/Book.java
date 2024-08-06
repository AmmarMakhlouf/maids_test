package cc.maids.library_test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class Book {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;

    @Column
    private String author;

    @Column(name = "publication_year")
    private int publicationYear;
    @Column(name = "isbn" , unique = true)
    private String isbn;

    @Min(value = 0 , message = "The store quantity must not be less than zero")
    @Column(name = "total_quantity")
    private int totalQuantity;

    // Default constructor
    public Book() {
    }
    public Book(Long id, String title, String author, int publicationYear, String isbn, int totalQuantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.totalQuantity = totalQuantity;
    }

    public Book(String title, String author, int publicationYear, String isbn, int totalQuantity) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.totalQuantity = totalQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
