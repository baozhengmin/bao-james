import api.com.bao.james.utils.IdWorker;

/**
 * $DESCRIPTION$
 *
 * @author : your name here
 * @version :
 * @date : Created in 下午5:47$
 */
public class TestId {

  public static void main(String[] args){

    IdWorker idWorker = new IdWorker(0, 0);
    for (int i = 0; i < 100; i++) {
      long id = idWorker.nextId();
      System.out.println(id);
    }

  }

}
