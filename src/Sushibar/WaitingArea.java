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
    this.maxCustomers = size;
    this.waitingLine = new ArrayList<>();
  }

  /**
   * This method should put the customer into the waitingArea
   *
   * @param customer A customer created by Door, trying to
   *                 enter the waiting area
   */
  public synchronized void enter(Customer customer) {
    while (true) {
      if (this.customersInLine() < maxCustomers) {
        waitingLine.add(customer);
        SushiBar.write(customer + " is in line.");
        notify(); // Waitresses know that they can take customer
        break;
      } else {
        try {
          SushiBar.write("The waiting room is full!");
          wait(); // Door must wait
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

  }

  /**
   * @return The customer that is first in line.
   */
  public synchronized Customer next() {
    while (true) {
      if (this.noCustomers()) {
        SushiBar.write("The waiting area is empty, and awaits new customers from the door.");
        try {
          wait(); // Waitress must wait for new customer
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        notify(); // Tell the door to let in more customers
        return waitingLine.remove(0);
      }
    }
  }
  public int customersInLine() {
    return waitingLine.size();
  }
  public boolean noCustomers() {
    return this.waitingLine.size() == 0;
  }

  // Add more methods as you see fit
}
