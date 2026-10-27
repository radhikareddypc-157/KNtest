import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreatestOfThreeTest {
    @Test
    public void testFindGreatest() {
        assertEquals(25, GreatestOfThree.findGreatest(10, 25, 15));
        assertEquals(30, GreatestOfThree.findGreatest(30, 20, 10));
        assertEquals(50, GreatestOfThree.findGreatest(10, 20, 50));
    }
}