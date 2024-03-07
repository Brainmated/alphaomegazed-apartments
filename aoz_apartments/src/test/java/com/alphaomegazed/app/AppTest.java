package com.alphaomegazed.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.junit.jupiter.api.Assertions;
import com.alphaomegazed.aoz_apartments.AozApartmentsApplication;

/**
 * Unit test for simple App. Where did you see the simple???
 */
@SpringBootTest(classes = AozApartmentsApplication.class)
@EnableJpaRepositories("com.alphaomegazed.aoz_apartments.repository_interfaces")
public class AppTest {

    /**
     * Rigourous Test isnt that rigorous. tf is going on :-)
     */
    @Test
    public void testApp() {
        Assertions.assertTrue(true);
    }
}
