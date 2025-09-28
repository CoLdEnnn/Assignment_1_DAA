import algorithms.App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestRunner {
    @Test
    void appStart() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}