package cc.maids.library_test.repository;

import cc.maids.library_test.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    @Query("SELECT COUNT(br) FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.isReturned = false")
    long countByBookId(@Param("bookId") Long bookId);
}
