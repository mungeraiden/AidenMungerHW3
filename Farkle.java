import java.util.ArrayList;
import java.util.Scanner;

public class Farkle {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------------------------------------");
        System.out.println("|           Zag Farkle by Aiden Munger         |");
        System.out.println("|               Copyright 2026                 |");
        System.out.println("------------------------------------------------");

        System.out.print("How many points would you like to play in this game? [10000] ");
        String input = scanner.nextLine().trim();

        int targetScore;

        if (input.isEmpty()) {
            targetScore = 10000;
        } else {
            targetScore = Integer.parseInt(input);
        }

        System.out.print("How many players are going to be playing? [1] ");
        String playerInput = scanner.nextLine().trim();

        int numPlayers;

        if (playerInput.isEmpty()) {
            numPlayers = 1;
        } else {
            numPlayers = Integer.parseInt(playerInput);
        }

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 1; i <= numPlayers; i++) {

            System.out.print("What is the player #" + i + "'s name? [Unknown Player #" + i + "] ");

            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                name = "Unknown Player #" + i;
            }

            players.add(new Player(name));
        }

        boolean gameOver = false;
        Player triggerPlayer = null;

        while (!gameOver) {

            for (Player player : players) {

                Turn turn = new Turn(player.getName());

                int points = turn.run();

                player.addScore(points);

                System.out.println();
                System.out.println("Current total points:");

                for (Player p : players) {
                    System.out.println(p.getName() + " " + p.getScore());
                }

                System.out.println();

                if (player.getScore() >= targetScore) {
                    triggerPlayer = player;
                    gameOver = true;
                    break;
                }
            }
        }
    
        if (triggerPlayer != null) {

            System.out.println();
            System.out.println("================================");
            System.out.println(triggerPlayer.getName() + " started the endgame!");
            System.out.println("All remaining players get ONE final turn.");
            System.out.println("================================");
            System.out.println();

            int startIndex = players.indexOf(triggerPlayer);

            for (int i = startIndex + 1; i < players.size(); i++) {

                Player player = players.get(i);

                System.out.println();
                System.out.println("Final turn for "
                        + player.getName());

                Turn turn = new Turn(player.getName());

                int points = turn.run();

                player.addScore(points);
            }
        }
        
        Player winner = players.get(0);

        for (Player p : players) {
            if (p.getScore() > winner.getScore()) {
                winner = p;
            }
        }

        System.out.println("================================");
        System.out.println("Winner: " + winner.getName());
        System.out.println("Final Scores:");
        System.out.println();

        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getScore());
        }

        System.out.println("================================");
        //scanner.close();
    }

    public static String normalizeUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            return "Unknown Player";
        }

        return username.trim();
    }
}