package cc.maids.library_test.repository;


import cc.maids.library_test.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
