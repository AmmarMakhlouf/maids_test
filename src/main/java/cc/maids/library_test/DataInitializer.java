package cc.maids.library_test;
import cc.maids.library_test.entity.Book;
import cc.maids.library_test.entity.BorrowingRecord;
import cc.maids.library_test.entity.Patron;
import cc.maids.library_test.repository.BookRepository;
import cc.maids.library_test.repository.BorrowingRecordRepository;
import cc.maids.library_test.repository.PatronRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    public DataInitializer(BookRepository bookRepository, PatronRepository patronRepository, BorrowingRecordRepository borrowingRecordRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Initialize Books
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setPublicationYear(faker.number().numberBetween(1900, 2023));
            book.setIsbn(faker.code().isbn13());
            book.setTotalQuantity(faker.number().numberBetween(0, 100));
            books.add(book);
        }
        bookRepository.saveAll(books);

        // Initialize Patrons
        List<Patron> patrons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Patron patron = new Patron();
            patron.setName(faker.name().fullName());
            patron.setEmail(faker.internet().emailAddress());
            patron.setAddress(faker.address().fullAddress());
            patron.setContactInformation(faker.phoneNumber().phoneNumber());
            patrons.add(patron);
        }
        patronRepository.saveAll(patrons);

        // Initialize Borrowing Records
        List<BorrowingRecord> borrowingRecords = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BorrowingRecord record = new BorrowingRecord();
            record.setBook(books.get(faker.number().numberBetween(0, books.size())));
            record.setPatron(patrons.get(faker.number().numberBetween(0, patrons.size())));
            record.setBorrowingDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)));
            record.setReturnDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));
            record.setReturned(faker.bool().bool());
            borrowingRecords.add(record);
        }
        borrowingRecordRepository.saveAll(borrowingRecords);
    }
}
