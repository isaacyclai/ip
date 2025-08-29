package tom;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEvent() {
        DateTimeFormatter input_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-08-29 1930", input_formatter);
        LocalDateTime to = LocalDateTime.parse("2025-08-29 2200", input_formatter);
        Event e = new Event("attend concert", from, to);
        assertEquals("E | 0 | attend concert | Aug 29 2025, 07:30 pm | Aug 29 2025, 10:00 pm", e.saveDesc());
    }
}
