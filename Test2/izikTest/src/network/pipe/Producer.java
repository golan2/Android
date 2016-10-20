package network.pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    11/03/2015 23:34
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Producer implements Runnable {

  private final BlockingQueue<String> sharedQueue;

  public Producer(BlockingQueue<String> sharedQueue) {
    this.sharedQueue = sharedQueue;
  }



  @Override
  public void run() {
    BufferedReader br  = null;
    Socket socket = null;

    try {
      socket = initializeSocket("computing.dcu.ie", "/~humphrys/howtomailme.html", 80);

      br = getBufferedReaderFromSocket(socket);

      String line;
      while ((line = br.readLine()) != null)
      {
        this.sharedQueue.put(line);
      }

      sharedQueue.put(Coordinator.END_OF_SEND);


    } catch (InterruptedException | IOException ex) {
      Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally {
      try {
        if (socket != null) socket.close();
        if (br     != null) br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }


  private static Socket initializeSocket(String host, String file, int port) throws IOException {
    Socket s;

    s = new Socket(host, port);

    OutputStream out = s.getOutputStream();
    PrintWriter outw = new PrintWriter(out, false);
    outw.print("GET " + file + " HTTP/1.0\r\n");
    outw.print("Accept: text/plain, text/html, text/*\r\n");
    outw.print("\r\n");
    outw.flush();
    return s;
  }

  private static BufferedReader getBufferedReaderFromSocket(Socket s) throws IOException {
    InputStream in = s.getInputStream();
    InputStreamReader inr = new InputStreamReader(in);
    return new BufferedReader(inr);
  }

}
