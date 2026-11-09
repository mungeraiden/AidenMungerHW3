import java.util.ArrayList;

public class Score {
    
    public static int calculate(Meld meld) {
        ArrayList<Dice> dice = meld.getDice();

        if (dice.isEmpty()) {
            return 0;
        }

        int[] counts = new int[7];
        for (Dice d : dice) {
            counts[d.getValue()]++;
        }

        int score = 0;


        // Straight
        boolean isStraight = true;
        for (int i = 1; i <= 6; i++) {
            if (counts[i] != 1){
                isStraight = false;
                break;
            }
        }

        if (isStraight){
            return 1000;
        }

        boolean fullHouse = false;
        boolean hasThree = false;
        boolean hasTwo = false;

        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 3) {
                hasThree = true;
            }
            if (counts[i] == 2) {
                hasTwo = true;
            }
        }

        fullHouse = hasThree && hasTwo;

        if (fullHouse) {
            return 1500;
        }

        // Three pairs
        int pairCount = 0;
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 2){
                pairCount++;
            }
        }
        if (pairCount == 3){
            return 750;
        }

        // Triples or more
        for (int face = 1; face <= 6; face++) {
            int count = counts[face];

            // Triples and beyond
            if (count >= 3) {
                if (face == 1) {
                    score += 1000;                 // triple 1s
                    score += (count - 3) * 100;    // extra 1s
                } else {
                    score += face * 100;           // triple face
                    score += (count - 3) * (face * 100); // extra dice beyond triple
                }
            }

            // Single 1s and 5s (only if fewer than 3)
            if (count < 3) {
                if (face == 1) {
                    score += count * 100;
                }
                if (face == 5) {
                    score += count * 50;
                }

            }
        }

        return score;
    }
}