import me.jd79317.ValidSentence;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidSentenceTest {

    @Test
    public void testValidSentences() {
        assertTrue(ValidSentence.isValidSentence("The quick brown fox said “hello Mr lazy dog”."));
        assertTrue(ValidSentence.isValidSentence("The quick brown fox said hello Mr lazy dog."));
        assertTrue(ValidSentence.isValidSentence("One lazy dog is too few, 13 is too many."));
        assertTrue(ValidSentence.isValidSentence("One lazy dog is too few, thirteen is too many."));
        assertTrue(ValidSentence.isValidSentence("How many \"lazy dogs\" are there?"));
        assertTrue(ValidSentence.isValidSentence("Test sentence starts with a capital letter."));
        assertTrue(ValidSentence.isValidSentence("Another test sentence ends with a period."));
        assertTrue(ValidSentence.isValidSentence("Test123 has an even number of \"test\" quotes."));
        assertTrue(ValidSentence.isValidSentence("This test sentence ends with a question mark?"));
        assertTrue(ValidSentence.isValidSentence("Yet another test sentence ends with an exclamation mark!"));
        assertTrue(ValidSentence.isValidSentence("A test with period."));
        assertTrue(ValidSentence.isValidSentence("B test with question mark?"));
        assertTrue(ValidSentence.isValidSentence("C test with exclamation mark!"));
    }

    @Test
    public void testInvalidSentences() {
        assertFalse(ValidSentence.isValidSentence("The quick brown fox said \"hello Mr. lazy dog\"."));
        assertFalse(ValidSentence.isValidSentence("the quick brown fox said “hello Mr lazy dog\"."));
        assertFalse(ValidSentence.isValidSentence("\"The quick brown fox said “hello Mr lazy dog.\""));
        assertFalse(ValidSentence.isValidSentence("One lazy dog is too few, 12 is too many."));
        assertFalse(ValidSentence.isValidSentence("Are there 11, 12, or 13 lazy dogs?"));
        assertFalse(ValidSentence.isValidSentence("There is no punctuation in this sentence"));
        assertFalse(ValidSentence.isValidSentence("invalid sentence starts with a lowercase letter."));
        assertFalse(ValidSentence.isValidSentence("This \"invalid sentence has an odd number of quotes."));
        assertFalse(ValidSentence.isValidSentence("Incomplete sentence with a comma. instead of a period."));
        assertFalse(ValidSentence.isValidSentence("Invalid Sentence has a period . in the middle."));
        assertFalse(ValidSentence.isValidSentence("Test 12,13 not spelled out."));
        assertFalse(ValidSentence.isValidSentence("Invalid sentence 11 eleven."));
        assertFalse(ValidSentence.isValidSentence("a"));
    }


    @Test
    public void testNull() {
        assertFalse(ValidSentence.isValidSentence(null));
    }

    @Test
    public void testEmptyString() {
        assertFalse(ValidSentence.isValidSentence(""));
    }


    @Test
    public void testExtremelyLongString() {
        StringBuilder longSentence = new StringBuilder("A");
        for (int i = 0; i < 10000; i++) {
            longSentence.append("a");
        }
        longSentence.append(".");
        assertTrue(ValidSentence.isValidSentence(longSentence.toString()));
    }

    private static final Random RANDOM = new Random();

    @Test
    public void testPerformanceContainsNumberLessThan() {
        int threshold = 1000;
        int iterations = 10_000;

        long start = System.nanoTime();
        boolean dummy = false; // dummy value to prevent result being dead branch
        for (int i = 0; i < iterations; i++) {

            StringBuilder inputBuilder = new StringBuilder(); // build test string
            for (int j = 0; j < RANDOM.nextInt(10000); j++) {
                inputBuilder.append(RANDOM.nextInt(threshold)).append(' ');
            }

            dummy = ValidSentence.containsNumberLessThan(inputBuilder.toString(), threshold);

        }
        long end = System.nanoTime();
        long time = TimeUnit.NANOSECONDS.toMillis(end - start);

        System.out.println("containsNumberLessThan took: " + time + " ms (for " + iterations + " iterations) " + dummy);
    }
}