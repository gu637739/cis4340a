// Rule 09 - Locking (LCK)
// LCK05-J: Synchronize access to static fields that can be modified by untrusted code
// Non-compliant code
/* This class is not thread-safe */
public final class CountHits {
  private static int counter;
 
  public void incrementCounter() {
    counter++;
  }
}

//Compliant Solution

/* This class is thread-safe */
public final class CountHits {
  private static int counter;
  private static final Object lock = new Object();
 
  public void incrementCounter() {
    synchronized (lock) {
      counter++;
    }
  }
}
