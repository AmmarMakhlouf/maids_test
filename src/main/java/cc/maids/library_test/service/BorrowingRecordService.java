package cc.maids.library_test.service;

import cc.maids.library_test.entity.Book;
import cc.maids.library_test.entity.BorrowingRecord;
import cc.maids.library_test.entity.Patron;
import cc.maids.library_test.exception.NoAvailableCopiesException;
import cc.maids.library_test.repository.BookRepository;
import cc.maids.library_test.repository.BorrowingRecordRepository;
import cc.maids.library_test.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    public Optional<BorrowingRecord> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId, LocalDate borrowingDate) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found"));


        // Count the number of active borrowing records for the book
        long activeBorrowings = borrowingRecordRepository.countByBookId(bookId);

        // Check if the number of active borrowings equals the book's total quantity
        if (activeBorrowings >= book.getTotalQuantity()) {
            throw new NoAvailableCopiesException("No more copies of the book are available for borrowing.");
        }

        BorrowingRecord record = new BorrowingRecord(book, patron, borrowingDate, null);
        return borrowingRecordRepository.save(record);
    }

    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId, LocalDate returnDate) {
        BorrowingRecord record = borrowingRecordRepository.findAll()
                .stream()
                .filter(r -> r.getBook().getId().equals(bookId) && r.getPatron().getId().equals(patronId) && r.getReturnDate() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        record.setReturnDate(returnDate);
        record.setReturned(true);
        return borrowingRecordRepository.save(record);
    }
}