package me.jd79317;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidSentence {
    public static void main(String[] args) {
        System.out.println(isValidSentence("Hello world!"));
    }

    // Set.of() is more optimized than a hashset for a small number of elements
    private static final Set<Character> VALID_TERMINATORS = Set.of('.', '?', '!');

    public static boolean isValidSentence(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        // Ensures string starts with a capital letter
        if (!Character.isUpperCase(input.charAt(0))) {
            return false;
        }

        // Ensures string ends with termination character
        char lastChar = input.charAt(input.length() - 1);
        if (!VALID_TERMINATORS.contains(lastChar)) { // java sets are constant time
            return false;
        }

        // Ensures string has an even number of quotation marks
        int quotesCount = 0;
        for (int i = 0; i < input.length(); i++) {
            char at = input.charAt(i);
            if (at == '"') {
                quotesCount++;
            }
        }
        if (quotesCount > 0 && quotesCount % 2 != 0) {
            return false;
        }

        // Ensures string has no period characters other than the last character.
        String inputWithoutLastCharacter = input.substring(0, input.length() - 1);
        if (inputWithoutLastCharacter.contains(".")) {
            return false;
        }

        // Checks that no number under 13 exists in numeric form
        boolean containsNumericNumberUnder13 = containsNumberLessThan(input, 13);
        if (containsNumericNumberUnder13) {
            return false;
        }

        return true;
    }

    // finds standalone integers, word boundry, one or more digits, then another word boundry
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\b\\d+\\b");

    public static boolean containsNumberLessThan(String input, int max) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);
        while (matcher.find()) {
            try {
                if (Integer.parseInt(matcher.group()) < max) {
                    return true;
                }
            } catch (NumberFormatException ignored) {
            } // some numbers may not be valid
        }
        return false;
    }
}