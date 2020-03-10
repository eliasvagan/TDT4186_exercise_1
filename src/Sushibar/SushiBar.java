package Sushibar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;


public class SushiBar {
  // SushiBar settings.
  private static int waitingAreaCapacity = 10;
  private static int waitressCount = 4;
  private static int duration = 5;
  public static int maxOrder = 15;
  public static int waitressWait = 160; // Used to calculate the time the waitress spends before taking the order
  public static int customerWait = 2500; // Used to calculate the time the customer spends eating
  public static int doorWait = 200; // Used to calculate the interval at which the door tries to create a customer
  public static boolean isOpen = true;

  // Creating log file.
  private static File log;
  private static String path = "./";

  // Variables related to statistics.
  public static SynchronizedInteger servedOrders;
  public static SynchronizedInteger takeawayOrders;
  public static SynchronizedInteger totalOrders;

  public static void closeSushiBar() {
    SushiBar.isOpen = false;
    write("----- Bar closed! -----");
  }

  public static void orderServed(int count, Customer customer) {
    servedOrders.add(count);
    totalOrders.add(count);
    write(customer + " ordered " + count + " eat-ins");
  }

  public static void orderTakeaway(int count, Customer customer) {
    takeawayOrders.add(count);
    totalOrders.add(count);
    write(customer + " ordered " + count + " takeaway.");
  }

  // Writes actions in the log file and console.
  public static void write(String str) {
    try {
      FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(Clock.getTime() + ", " + str + "\n");
      bw.close();
      System.out.println(Clock.getTime() + ", " + str);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    log = new File(path + "log.txt");

    // Initializing shared variables for counting number of orders.
    totalOrders = new SynchronizedInteger(0);
    servedOrders = new SynchronizedInteger(0);
    takeawayOrders = new SynchronizedInteger(0);

    // Initialize the bar and start the different threads.
    WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);
    ArrayList<Thread> waitresses = new ArrayList<>();

    Door door = new Door(waitingArea);
    Thread threadDoor = new Thread(door, "Door");

    IntStream.range(0, waitressCount).forEach(i -> {
      Waitress w = new Waitress(waitingArea);
      Thread t = new Thread(w, "Waitress_n." + i);
      t.start(); // Starts this Waitress thread
      waitresses.add(t);
    });

    //Open the door
    threadDoor.start();

    // The sushi bar opens
    Clock clock = new Clock(duration);
    write("The bar is open!");

    // Wait for threads to finish
    for (Thread thread : waitresses) {
      try {
        thread.join();
      } catch (Exception err) {
        System.out.println(err.getMessage());
      }
    }

    // Print stats
    write(
      waitingArea.noCustomers()
        ? "All the customers have left."
        : "The bar is closed, but the waiting area still har " + waitingArea.customersInLine() + " customers left!"
    );
    write("- Orders served: " + servedOrders.get());
    write("- Orders takeaway: " + takeawayOrders.get());
    write("- Orders in total: " + totalOrders.get());
    write("- Customers: " + Customer.idCounter.get());

  }
}
