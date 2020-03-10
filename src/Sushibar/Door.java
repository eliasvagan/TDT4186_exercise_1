package Sushibar;

/**
 * This class implements the Door component of the sushi bar
 * assignment The Door corresponds to the Producer in the
 * producer/consumer problem
 */
public class Door implements Runnable {
  private WaitingArea waitingArea;
  /**
   * Creates a new Door Make sure to save the
   * @param waitingArea   The customer queue waiting for a seat
   */
  public Door(WaitingArea waitingArea) {
    this.waitingArea = waitingArea;
  }

  /**
   * This method will run when the door thread is created (and started).
   * The method should create customers at random intervals and try to put them in the waiting area.
   */
  @Override
  public void run() {
    while (SushiBar.isOpen) {
      Customer customer = new Customer();
      SushiBar.write(customer + " just came through the door.");
      waitingArea.enter(customer); // New customer received in line
      try {
        Thread.sleep(SushiBar.doorWait); // Wait for next customer
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    SushiBar.write("The door is finished letting people in.");
  }

  // Add more methods as you see fit
}
