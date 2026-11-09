public class ScoreTest {
    private static int passed = 0;
    private static int failed = 0;

    

    public static void main(String[] args) {
        System.out.println("ScoreTest started.\n");

        testScore("Straight", new int[]{1, 2, 3, 4, 5, 6}, 1000);
        testScore("Three pairs", new int[]{1, 1, 4, 4, 6, 6}, 750);
        testScore("Single one", new int[]{1}, 100);
        testScore("Three threes", new int[]{3, 3, 3}, 300);
        testScore("Triple ones", new int[]{1, 1, 1}, 1000);
        testScore("Full house", new int[]{2, 2, 4, 4, 4}, 1500);
        Hand hand = new Hand(2, 3, 4, 6, 2, 3);
        testBoolean("Farkle detection", true, hand.isFarkle());

        testName("Player name", "Aiden", "Aiden");
        testName("Blank player name", "", "Unknown Player");
        testName("Whitespace player name", "   ", "Unknown Player");

        System.out.println("\n--------------------");
        System.out.println("Tests passed: " + passed);
        System.out.println("Tests failed: " + failed);
        System.out.println("--------------------");
    }

    private static void testScore(String name, int[] values, int expected) {
        Meld meld = new Meld();

        for (int value : values) {
            meld.addDie(new Dice(value));
        }

        int actual = Score.calculate(meld);

        if (actual == expected) {
            passed++;
            System.out.println(name + " passed.");
        } else {
            failed++;
            System.out.println(name + " failed. Expected: " + expected + ", Actual: " + actual);
        }
    }

     private static void testBoolean(String name, boolean expected, boolean actual) {
        if (actual == expected) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name + " — expected " + expected + ", got " + actual);
        }
    }

    private static void testName(String name, String input, String expected) {
        String actual = Farkle.normalizeUsername(input);

        if (actual.equals(expected)) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name + " - expected [" + expected + "], got [" + actual + "]");
        }
    }
}
