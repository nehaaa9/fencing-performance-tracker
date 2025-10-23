//my passion project
//by: neha annambhotla
//fencing performance tracker
//assuming there are 6 rounds in a season

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FencingPerformanceTracker {

  public static void main(String[] args) {
    int round, bouts, fencers, points, oppPoints;
    int indicator = 0;

    Scanner scan = new Scanner(System.in);

    System.out.println("What round number is this?");
    round = scan.nextInt();

    System.out.println("How many fencers (including yourself) are in your pool?");
    fencers = scan.nextInt();

    bouts = fencers * (fencers - 1) / 2; // formula for calculating number of bouts in a pool

    System.out.println("\nThere are " + bouts + " bouts in your pool.");
    System.out.println("You will fence " + (fencers - 1) + " people.\n");

    StringBuilder log = new StringBuilder(); //to save results to FencingResults.txt
    log.append("=== Fencing Performance Tracker ===\n");
    log.append("Round: ").append(round).append("\n");
    log.append("Fencers in pool: ").append(fencers).append("\n\n");

    for (int i = 1; i < fencers; i++) {
      System.out.println("Bo4ut " + i + ":");

      System.out.print("How many points did you get in this bout? ");
      points = scan.nextInt();
      while (points > 5 || points < 0) {
        System.out.print("Enter a number between 0 and 5: ");
        points = scan.nextInt();
      }

      System.out.print("How many points did your opponent get? ");
      oppPoints = scan.nextInt();
      while (oppPoints > 5 || oppPoints < 0) {
        System.out.print("Enter a number between 0 and 5: ");
        oppPoints = scan.nextInt();
      }

      int diff = points - oppPoints;
      indicator += diff;

      if (diff > 0) {
        System.out.println("You won this bout by " + diff + " points!\n");
      } else if (diff < 0) {
        System.out.println("Your opponent won by " + Math.abs(diff) + " points.\n");
      } else {
        System.out.println("It was a tie!\n");
      }

      log.append("Bout ").append(i).append(": You ").append(points)
         .append(" - Opponent ").append(oppPoints)
         .append(" (Indicator change: ").append(diff).append(")\n");
    }

    double avgIndicator = (double) indicator / (fencers - 1);
    System.out.println("==================================");
    System.out.println("Fencing Performance Summary");
    System.out.println("==================================");
    System.out.println("Round: " + round);
    System.out.println("Fencers in pool: " + fencers);
    System.out.println("Overall indicator: " + indicator);
    System.out.println("==================================");

    log.append("\nTotal indicator: ").append(indicator).append("\n");
    log.append("==================================\n\n");

    try {
      FileWriter writer = new FileWriter("FencingResults.txt", true); // "true" appends instead of overwriting
      writer.write(log.toString());
      writer.close();
      System.out.println(" Results saved to FencingResults.txt");
    } catch (IOException e) {
      System.out.println("Error saving file: " + e.getMessage());
    }

    scan.close();
  }
}
    