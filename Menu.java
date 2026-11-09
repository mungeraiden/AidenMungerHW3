import java.util.Scanner;

public class Menu {

    public static void run(Turn turn, Hand hand) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        

        while (running) {
            System.out.println("\n=== Farkle Menu ===");
            System.out.println("A - Add die to meld");
            System.out.println("B - Remove die from meld");
            System.out.println("C - Show current meld");
            System.out.println("D - Show unused dice");
            System.out.println("E - Bank points");
            System.out.println("R - Reroll unused dice");
            System.out.println("Q - Quit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {

                case "A":
                    hand.displayUnusedDice();
                    System.out.print("Select a die letter to add: ");
                    String letter = scanner.nextLine().trim().toUpperCase();
                    if (letter.isEmpty()) {
                        System.out.println("Cancelled.");
                        break;
                    }
                    int index = letter.charAt(0) - 'A';

                    if (index >= 0 && index < hand.unusedSize()) {
                        hand.addToMeld(index);
                        System.out.println("Added die " + letter + " to meld.");
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;

                case "B":
                    hand.displayMeld();
                    System.out.print("Select a die letter to remove: ");
                    String removeLetter = scanner.nextLine().trim().toUpperCase();

                    int removeIndex = removeLetter.charAt(0) - 'A';

                    if (removeIndex >= 0 && removeIndex < hand.meldSize()) {
                        hand.removeFromMeld(removeIndex);
                        System.out.println("Removed die " + removeLetter + " from meld.");
                    } else { 
                        System.out.println("Invalid selection.");
                    }
                    break;

                case "C":
                    System.out.println("Current Meld:");
                    hand.displayMeld();
                    break;

                case "D":
                    System.out.println("Unused Dice:");
                    hand.displayUnusedDice();
                    break;

                case "E":
                    turn.bank();
                    running = false;
                    break;

                case "Q":
                    turn.quit();
                    running = false;
                    break;

                case "R":
                    turn.reroll();
                    if (!turn.isTurnActive()) {
                        running = false;
                    }
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        // scanner.close();
    }
}
