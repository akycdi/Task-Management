package com.airtribe.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerApplicationTests {
    @Test
    void testAppPass() {
        assertEquals(2, 1+1);
    }

    @Test
    void testAppFail() {
        assertEquals(3, 1+1, "Intentional fail");
    }
}
