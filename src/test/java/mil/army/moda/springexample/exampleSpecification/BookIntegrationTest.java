package mil.army.moda.springexample.exampleSpecification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.service.registry.ImportHttpServices;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@Testcontainers
//class BookIntegrationTest {
//
//    @ImportHttpServices.Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @BeforeEach
//    void setUp() {
//        bookRepository.deleteAll();
//        bookRepository.save(new Book(null, "Available Book", "Author A", true));
//        bookRepository.save(new Book(null, "Unavailable Book", "Author B", false));
//    }
//
//    @Test
//    void getAvailableBooks_returnsOnlyAvailable() throws Exception {
//        mockMvc.perform(get("/books").param("available", "true"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].title").value("Available Book"));
//    }
//}
