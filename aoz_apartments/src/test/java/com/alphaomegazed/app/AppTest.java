package com.alphaomegazed.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Assertions;

/**
 * Unit test for simple App. Where did you see the simple???
 */
@SpringBootTest
@ActiveProfiles("test")
public class AppTest {

    /**
     * Rigourous Test isnt that rigorous. tf is going on :-)
     */
    @Test
    public void testApp() {
        Assertions.assertTrue(true);
    }
}
