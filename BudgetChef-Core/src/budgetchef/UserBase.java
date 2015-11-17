package budgetchef;

public class UserBase {
  private static java.util.HashMap<String, String> database = new java.util.HashMap<>();
  
  private static boolean checkPassword(String password) { return password.length() > 4; }
  private static boolean checkUser(String usr) { return usr.length() > 4; } 
  
  public static void createUser(String name, String password) {
    boolean pw = checkPassword(password);
    boolean usr = checkUser(name);
    
    if (!pw)
      System.err.println("Senha deve ser maior ou igual a 5 caracteres.");
    if (!usr)
      System.err.println("Login deve ser maior ou igual a 5 caracteres.");
    
    if (pw && usr)
      database.put(name, password);
  }
  
  public static boolean validate(String name, String password) {
    java.util.Set<String> keys = database.keySet();
    
    for (String usr : keys)
      if (usr.equals(name) && database.get(usr).equals(password))
        return true;
    
    return false;
  }
}