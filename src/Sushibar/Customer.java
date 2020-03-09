package Sushibar;

/**
 * This class implements a customer, which is used for holding data
 * and update the statistics
 */
public class Customer {

  /**
   *  Creates a new Sushibar.Customer.  Each customer should be given a
   *  unique ID
   */
  private int customerID;
  static SynchronizedInteger idCounter = new SynchronizedInteger(0);
  public Customer() {
    // TODO: Implement required functionality.
    this.customerID = idCounter.get();
    idCounter.increment();
  }


  /**
   * Here you should implement the functionality for ordering food
   * as described in the assignment.
   */
  public void order() {
    // TODO: Implement required functionality.
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
