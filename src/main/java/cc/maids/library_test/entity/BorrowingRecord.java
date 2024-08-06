package cc.maids.library_test.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;

import java.time.LocalDate;

@Entity
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Patron patron;

    @Column (name = "borrowing_date")
    private LocalDate borrowingDate;

    @Column (name = "return_date")
    private LocalDate returnDate;

    @Column(name = "is_returned" , nullable = false)
    private boolean isReturned = false;

    @AssertTrue(message = "Borrowing date must be before return date")
    public boolean isBorrowingDateBeforeReturnDate() {
        if (borrowingDate == null || returnDate == null) {
            return true; // Let other annotations handle null checks
        }
        return borrowingDate.isBefore(returnDate) || borrowingDate.isEqual(returnDate);
    }

    public BorrowingRecord() {
    }

    public BorrowingRecord(Long id, Book book, Patron patron, LocalDate borrowingDate, LocalDate returnDate, boolean isReturned) {
        this.id = id;
        this.book = book;
        this.patron = patron;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public BorrowingRecord(Book book, Patron patron, LocalDate borrowingDate, LocalDate returnDate, boolean isReturned) {
        this.book = book;
        this.patron = patron;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }
    public BorrowingRecord(Book book, Patron patron, LocalDate borrowingDate, LocalDate returnDate) {
        this.book = book;
        this.patron = patron;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.isReturned = false;
    }

    public void setId(Long id) {
       this.id = id;
    }
    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
