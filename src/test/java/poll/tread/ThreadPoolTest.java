package poll.tread;

import java.math.BigDecimal;
import org.junit.Test;

/**
 * $DESCRIPTION$
 *
 * @author : your name here
 * @version :
 * @date : Created in 下午5:51$
 */
public class ThreadPoolTest implements Runnable {

  public void run()
       {
              try
              {
                    Thread.sleep(3000);
                }
              catch (InterruptedException e)
              {
                    e.printStackTrace();
                }
          }

          @Test
          public void test(){
            BigDecimal zero = BigDecimal.ZERO;
            System.out.println(zero);
            System.out.println(zero.intValue() == 0);

          }
}
