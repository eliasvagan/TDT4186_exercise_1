package Sushibar;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class implements a waiting area used as the bounded buffer,
 * in the producer/consumer problem.
 */
public class WaitingArea {
  private int maxCustomers;
  private ArrayList<Customer> waitingLine;
  /**
   * Creates a new waiting area.
   *
   * @param size The maximum number of Customers that can be waiting.
   */

  public WaitingArea(int size) {
    // TODO Implement required functionality
    this.maxCustomers = size;
    this.waitingLine = new ArrayList<>();
  }

  /**
   * This method should put the customer into the waitingArea
   *
   * @param customer A customer created by Sushibar.Door, trying to
   *                 enter the waiting area
   */
  public synchronized void enter(Customer customer) {
    // TODO Implement required functionality
  }

  /**
   * @return The customer that is first in line.
   */
  public synchronized Customer next() {
    // TODO: Implement required functionality
    return new Customer();
  }

  // Add more methods as you see fit
}
