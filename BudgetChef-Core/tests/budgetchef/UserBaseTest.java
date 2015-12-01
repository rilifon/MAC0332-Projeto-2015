package budgetchef;

import org.junit.Assert;
import org.junit.Test;

public class UserBaseTest {

  @Test
  public void testChecking() {
    java.lang.reflect.Method usr, pass;
    
    try {
      usr = UserBase.class.getDeclaredMethod("checkUser", String.class);
      usr.setAccessible(true);
      pass = UserBase.class.getDeclaredMethod("checkPassword", String.class);
      pass.setAccessible(true);
      
      Assert.assertFalse((Boolean) usr.invoke(null, "chef"));
      Assert.assertFalse((Boolean) pass.invoke(null,  "(:D)"));
      
      Assert.assertTrue((Boolean) usr.invoke(null, "Chicago"));
      Assert.assertTrue((Boolean) pass.invoke(null, "the_band_not_the_city"));
    } catch(java.lang.Exception exc) {
      System.err.println("Error: " + exc.getMessage());
    }
  }

  @Test
  public void testCreation() {
    try {
      java.lang.reflect.Field db = UserBase.class.getDeclaredField("database");
      db.setAccessible(true);
      java.util.HashMap<String, String> database = (java.util.HashMap<String, String>) db.get(null);
      
      class Pair {
        public String l, r;
        Pair(String left, String right) {
          l = left;
          r = right;
        }
      }
      Pair dualityMan = new Pair("man", "pig");
      UserBase.createUser(dualityMan.l, dualityMan.r);
      
      Assert.assertFalse(database.containsKey(dualityMan.l));
      
      Pair hypocrisySociety = new Pair("hubris", "equality");
      
      UserBase.createUser(hypocrisySociety.l, hypocrisySociety.r);
      Assert.assertTrue(database.containsKey(hypocrisySociety.l));
      Assert.assertEquals(hypocrisySociety.r, database.get(hypocrisySociety.l));
      
      Pair essenceMan = new Pair("", "");
      UserBase.createUser(essenceMan.l, essenceMan.r);
      Assert.assertFalse(database.containsKey(essenceMan.l));
    } catch(Exception exc) {
      System.err.println("Error: " + exc.getMessage());
    }
  }
}
