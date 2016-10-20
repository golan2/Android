package network.pipe;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    11/03/2015 23:42
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Consumer implements Runnable{

  private final BlockingQueue<String> sharedQueue;


  public Consumer (BlockingQueue<String> sharedQueue) {
    this.sharedQueue = sharedQueue;
  }

  @Override
  public void run() {
    while(true){
      try {
        String s = sharedQueue.take();
        System.out.println(s);
        if (Coordinator.END_OF_SEND.equals(s)) return;
      } catch (InterruptedException ex) {
        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

}
