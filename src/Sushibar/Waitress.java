package Sushibar;

/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

  /**
   * Creates a new waitress. Make sure to save the parameter in the class
   *
   * @param waitingArea The waiting area for customers
   */
  private WaitingArea waitingArea;
  Waitress(WaitingArea waitingArea) {
    this.waitingArea = waitingArea;
  }

  /**
   * This is the code that will run when a new thread is
   * created for this instance
   */
  @Override
  public void run() {
    SushiBar.write("Added waitress to bar in thread " + Thread.currentThread().getName());
    while (SushiBar.isOpen || !waitingArea.noCustomers()) {
      Customer customer = waitingArea.next();
      SushiBar.write(customer + " is ordering food.");
      customer.order();
      try {
        Thread.sleep(SushiBar.waitressWait); // Sleep the specified time for a waitress to serve.
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
    // SushiBar.write(Thread.currentThread().getName() + " finished their shift.");
  }

}

