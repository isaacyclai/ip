package tom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-09-15 1700", inputFormatter);
        Deadline dl = new Deadline("submit project proposal", by);
        assertEquals("D | 0 | submit project proposal | Sep 15 2025, 05:00 pm", dl.saveDesc());
    }
}
