package Sushibar;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements a customer, which is used for holding data
 * and update the statistics
 */
public class Customer {
  static SynchronizedInteger idCounter = new SynchronizedInteger(0);
  private int customerID;
  /**
   *  Creates a new Customer.  Each customer should be given a
   *  unique ID
   */
  public Customer() {
    this.customerID = idCounter.get();
    idCounter.increment();
  }

  /**
   * Here you should implement the functionality for ordering food
   * as described in the assignment.
   */
  public void order() {
    int serveCount = ThreadLocalRandom.current().nextInt(1, SushiBar.maxOrder + 1);
    int takeawayCount = ThreadLocalRandom.current().nextInt(0, SushiBar.maxOrder - serveCount + 1);

    SushiBar.orderTakeaway(takeawayCount, this);
    SushiBar.orderServed(serveCount, this);
    SushiBar.write(this + " is now eating.");
    try {
      Thread.sleep(SushiBar.customerWait); // Makes the customer sleep for a set time
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    SushiBar.write(this + " left the sushi bar.");
  }

  // I want this to write customer IDs more streamlined
  public String toString() {
    // return "Customer no." + this.getCustomerID();
    return Name.names[this.getCustomerID()];
  }

  /**
   *
   * @return Should return the customerID
   */
  public int getCustomerID() {
    return this.customerID;
  }

  // Add more methods as you see fit.
}
