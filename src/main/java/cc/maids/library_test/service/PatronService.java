package cc.maids.library_test.service;

import cc.maids.library_test.entity.Patron;
import cc.maids.library_test.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Cacheable(value = "patrons", key = "#id")
    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Transactional
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    @CachePut(value = "patrons", key = "#id")
    public Patron updatePatron(Long id, Patron patron) {
        if (patronRepository.existsById(id)) {
            return patronRepository.save(patron);
        }
        throw new RuntimeException("Patron not found");
    }

    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(Long id) {
        if (patronRepository.existsById(id)) {
            patronRepository.deleteById(id);
        } else {
            throw new RuntimeException("Patron not found");
        }
    }
}
