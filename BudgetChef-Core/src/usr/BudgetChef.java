package usr;

import budgetchef.User;

public class BudgetChef {
  private static User usr;
  
  public static void main(String[] args) {
    usr = new User(args[0]);
    
    System.out.println("Usuario " + usr.getName() + " logged in.");
    System.out.println("1. Ver minha geladeira.\n2. Ver meu perfil.\n3. Criar receita.\n");
  }
}
