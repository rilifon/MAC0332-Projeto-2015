package usr;

import budgetchef.User;
import budgetchef.UserBase;

public class BudgetChef {
  private static User usr;
  
  private enum Option {
      FRIDGE(1), PROFILE(2), RECIPE(3), RECOMMENDATIONS(4), EXIT(5);
      private int value_;
      private Option(int value) { value_ = value; }
      public int getValue() { return value_; }
  }
  
  public static void main(String[] args) {
    UserBase.createUser("wololo", "heyo_woo");
    UserBase.createUser("1337_U53R", "1337P455W0RD");
    UserBase.createUser("user0", "pass0");
    
    if (!UserBase.validate(args[0], args[1]))
      return;
    
    usr = new User(args[0], args[1]);
    
    System.out.println("Usuario " + usr.getName() + " logged in.");
    
    java.util.Scanner inputStream = new java.util.Scanner(System.in);
    int opt;
    
    do {
      System.out.println("1. Ver minha geladeira.\n2. Ver meu perfil.\n3. Criar receita.\n4. Ver recomendacoes.\n5. Sair.");
      
      opt = inputStream.nextInt();
      
      
    } while (opt != 5);
  }
}
