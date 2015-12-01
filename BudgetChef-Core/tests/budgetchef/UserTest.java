package budgetchef;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class UserTest {

  private class Pair {
    public String left_, right_;
    Pair(String left, String right) {
      left_ = left;
      right_ = right;
    }
  }
  
  User nicCage;
  User davidLynch;
  User alejandroJodorowsky;
  User jeanpJeunet;
  
  User[] listUsers;
  
  Pair nic;
  Pair david;
  Pair alejandro;
  Pair jean;
  
  Pair[] listPairs;
  
  @Before
  public final void setUp() {
    nic = new Pair("NicolasCage", "MySnakeskinJacket");
    david = new Pair("DavidLynch", "cyc1ic4l_n4ightm4re");
    alejandro = new Pair("PuppetMaster", "who_am_i_who_are_u");
    jean = new Pair("JeanPierre", "questcequecestlavie");
    
    listPairs = new Pair[] {nic, david, alejandro, jean};
    
    nicCage = new User(nic.left_, nic.right_);
    davidLynch = new User(david.left_, david.right_);
    alejandroJodorowsky = new User(alejandro.left_, alejandro.right_);
    jeanpJeunet = new User(jean.left_, jean.right_);
    
    listUsers = new User[] {nicCage, davidLynch, alejandroJodorowsky, jeanpJeunet};
  }
  
  @Test
  public final void testConstructor() {
    for (int i=0; i<listPairs.length; ++i) {
      Assert.assertEquals(listPairs[i].left_, listUsers[i].getName());
      
      try {
        java.lang.reflect.Field passField = User.class.getDeclaredField("password_");
        passField.setAccessible(true);
        String pass = (String) passField.get(listUsers[i]);
        Assert.assertEquals(listPairs[i].right_, pass);
      } catch(java.lang.Exception exc) {
        System.err.println("Error: " + exc.getMessage());
      }
    }
  }
  
  @Test
  public final void testValidation() {
    for (int i=0; i<listPairs.length; ++i) {
      Assert.assertTrue(listUsers[i].validate(listPairs[i].left_, listPairs[i].right_));
    }
    Assert.assertFalse(nicCage.validate(david.left_, david.right_));
    Assert.assertFalse(jeanpJeunet.validate(alejandro.left_, alejandro.right_));
  }
}
