import java.util.Scanner;

public class Turn {
    private Hand hand;
    private int totalScore;
    private boolean turnActive;

    public Turn(String username) {
        System.out.println(username + ", it is your turn! Rolling dice...");
        hand = new Hand();
        totalScore = 0;
        turnActive = true;

        hand.rollAllDice();
        System.out.println("Starting hand:");
        hand.displayUnusedDice();

        if (hand.isFarkle()) {
            System.out.println("FARKLE! No scoring combos available.");
            System.out.println("Your turn ends with 0 points.");
            turnActive = false;
        }
    }

    public int run() {
        if (!turnActive) {
            return 0;
        }

        while (turnActive) {
            Menu.run(this, hand);
        }

        System.out.println("Turn comlete. Total score: " + totalScore);

        return totalScore;
    }

    public void bank() {
        if (!turnActive) {
            System.out.println("Turn over! You cannot bank any points...");
            return;
        }
        int meldScore = Score.calculate(hand.getMeld());

        if (meldScore == 0) {
            System.out.println("Cannot bank — meld score is 0.");
            return;
        }

        totalScore += meldScore;
        System.out.println("Banked " + meldScore + " points!");
        turnActive = false;
    }

    public void reroll() {
        int meldScore = Score.calculate(hand.getMeld());

        if (meldScore == 0) {
            System.out.println("Cannot reroll — meld score is 0.");
            return;
        }

        if (hand.unusedSize() == 0) {
            System.out.println("==============================");
            System.out.println("==========HOT HAND!===========");
            System.out.println("==============================");
            System.out.println("You used all 6 dice!");
            System.out.println("Would you like to roll 6 new dice, or bank and end your turn?");
            System.out.println("R = Roll 6 new dice");
            System.out.println("E = Bank and end turn");


            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("R")) {
                hand = new Hand();
                hand.rollAllDice();
                System.out.println("Rolling 6 new dice!");
                hand.displayUnusedDice();
            } else if (choice.equals("E")) {
                bank();
                // scanner.close();
            } else {
                System.out.println("Invalid selection. Turn continues.");
            }

        } else {
            System.out.println("Rerolling unused dice...");
            hand.rerollUnused();
            hand.displayUnusedDice();
        }

        hand.displayUnusedDice();

        if (hand.isFarkle()) {
            System.out.println("FARKLE! No scoring combos available.");
            System.out.println("Your turn ends with 0 points.");
            totalScore = 0;
            turnActive = false;
        }
    }

    public void quit() {
        System.out.println("Quitting turn.");
        System.out.println("Total score: " + totalScore);
        turnActive = false;
    }

    public boolean isTurnActive() {
        return turnActive;
    }

    public int getScore() {
        return totalScore;
    }
}