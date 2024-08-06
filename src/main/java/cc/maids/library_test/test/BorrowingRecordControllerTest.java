package cc.maids.library_test.test;

import cc.maids.library_test.controller.BorrowingRecordController;
import cc.maids.library_test.entity.BorrowingRecord;
import cc.maids.library_test.service.BorrowingRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;

@WebMvcTest(BorrowingRecordController.class)
public class BorrowingRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowingRecordController).build();
    }

    @Test
    public void testGetAllBorrowingRecords() throws Exception {
        BorrowingRecord record1 = new BorrowingRecord();
        record1.setId(1L);
        BorrowingRecord record2 = new BorrowingRecord();
        record2.setId(2L);

        when(borrowingRecordService.getAllBorrowingRecords()).thenReturn(Arrays.asList(record1, record2));

        mockMvc.perform(get("/api/borrowing-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testGetBorrowingRecordById() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L);

        when(borrowingRecordService.getBorrowingRecordById(1L)).thenReturn(Optional.of(record));

        mockMvc.perform(get("/api/borrowing-records/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetBorrowingRecordByIdNotFound() throws Exception {
        when(borrowingRecordService.getBorrowingRecordById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/borrowing-records/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testBorrowBook() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L);

        when(borrowingRecordService.borrowBook(anyLong(), anyLong(), any())).thenReturn(record);

        mockMvc.perform(post("/api/borrowing-records/borrow/1/patron/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testReturnBook() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L);

        when(borrowingRecordService.returnBook(anyLong(), anyLong(), any())).thenReturn(record);

        mockMvc.perform(post("/api/borrowing-records/return/1/patron/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
