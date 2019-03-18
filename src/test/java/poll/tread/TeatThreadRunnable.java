package poll.tread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午5:49$
 */
public class TeatThreadRunnable {

  public static void main(String[] args){

//    System.out.println("执行任务++++");
//return;

    Thread thread = new Thread(() -> {
      System.out.println("执行任务++++");
    });
    thread.start();

  }

}