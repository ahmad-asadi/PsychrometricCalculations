package starter;

import java.io.Serializable;

/**
 * Created by root on 3/15/17.
 *
 */
public class Authenticated implements Serializable{
   public String license ;
   public Integer remainingTimes ;
   public Boolean registered = false;
   public String macAddress = "" ;

}
