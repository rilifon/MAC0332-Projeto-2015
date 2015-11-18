package usr;

import java.util.Scanner;

import budgetchef.Fridge;
import budgetchef.Ingredient;
import budgetchef.Measurement;
import budgetchef.Recipe;
import budgetchef.User;
import budgetchef.UserBase;

public class BudgetChef {
  private static User usr;
  
  private enum Option {
      FRIDGE, PROFILE, RECIPE, RECOMMENDATIONS, EXIT;
  }
  
  public static void main(String[] args) {
    UserBase.createUser("wololo", "heyo_woo");
    UserBase.createUser("1337_U53R", "1337P455W0RD");
    UserBase.createUser("user0", "pass0");
    
    if (!UserBase.validate(args[0], args[1])) {
      System.out.println("Usuario " + args[0] + " nao cadastrado.");
      return;
    }
    
    usr = new User(args[0], args[1]);
    
    System.out.println("Usuario " + usr.getName() + " logged in.");
    
    java.util.Scanner inputStream = new java.util.Scanner(System.in);
    Option opt;
    
    do {
      System.out.println("1. Ver minha geladeira.\n2. Ver meu perfil.\n3. Criar receita.\n4. Ver recomendacoes.\n5. Sair.\n");
      
      opt = Option.values()[inputStream.nextInt()-1];
      switch(opt) {
        case FRIDGE:
          fridgeInteraction();
          break;
        case PROFILE:
          profileInteraction();
          break;
        case RECIPE:
          recipeInteraction();
          break;
        case RECOMMENDATIONS:
          recommendationsInteraction();
          break;
        case EXIT:
          System.err.println("Commando invalido.");
          break;
        default:
          break;
      }
      System.out.print('\n');
    } while (opt != Option.EXIT);
    
    inputStream.close();
  }
  
  private static void recommendationsInteraction() {
    System.out.println("TODO: Recommendations");
  }
  
  private static void recipeInteraction() {    
    Scanner input = new Scanner(System.in);
    int opt;
    
    System.out.println("Nome da receita:");
    Recipe rec = new Recipe(input.nextLine());
    
    System.out.println("1. Adicionar ingredientes.\n2. Adicionar passo.\n3. Remover ingrediente.\n4. Remover passo.\n"
        + "5. Listar ingredientes.\n6. Listar passos.\n7. Voltar.\n");
    
    do {
      opt = input.nextInt();
      switch(opt) {
        case 1: {
          System.out.println("Formato: \"Nome\" \"Quantidade\" \"Unidade\"\nExemplo: \"Batata\" \"2\" \"xicaras\"\n");
          input.nextLine();
          String entity = input.nextLine();
          String[] tokens = entity.split("\"[^\"]*\"");
          for (String s : tokens)
            s = s.replace("\"", "");
          rec.addIngredient(new Ingredient(tokens[0], Double.parseDouble(tokens[1]), Measurement.getEquivalent(tokens[2])));
          System.out.println(tokens[1] + " " + tokens[2] + " de " + tokens[0] + " adicionado.");
        } break;
        case 2: {
          System.out.println("Formato: NumeroDoPasso Texto...\nExemplo: 1 Primeiro coloca-se a farinha no tomate.\n");
          rec.addStep(input.nextInt(), input.nextLine());
          System.out.println("Passo adicionado.");
        } break;
        case 3: {
          System.out.println("Formato: \"Nome\" \"Quantidade\"\nExemplo: \"Batata\" \"2\"\n");
          input.nextLine();
          String entity = input.nextLine();
          String[] tokens = entity.split("\"[^\"]*\"");
          for (String s : tokens)
            s = s.replace("\"", "");
          rec.removeIngredient(tokens[0], Double.parseDouble(tokens[1]));
          System.out.println(tokens[1] + " " + tokens[2] + " de " + tokens[0] + " adicionado.");
        } break;
        case 4: {
          System.out.println("Formato: Indice\nExemplo: 1\n");
          rec.removeStep(input.nextInt());
        } break;
        case 5: {
          Ingredient[] ingredients = rec.getIngredients();
          System.out.println("Ingredientes na receita:");
          for (Ingredient ing : ingredients)
            System.out.printf("\t%s:\t%5d\t%s\n", ing.getName(), ing.getValue(), ing.getMeasurement().getName(ing.getValue()));
          System.out.print('\n');
        } break;
        case 6: {
          String[] steps = rec.getSteps();
          System.out.println("Passos na receita:");
          for (int i = 0; i < steps.length; ++i)
            System.out.printf("Passo %d:\n\t%s\n", i, steps[i]);
          System.out.print('\n');
        } break;
      }
      System.out.print('\n');
    } while (opt != 7);
  }
  
  private static void profileInteraction() {
    System.out.println("Login: " + usr.getName() + "\n1. Listar minhas receitas.\n2. Voltar.\n");
    Scanner input = new Scanner(System.in);
    while (input.nextInt() == 1) {
      input.nextLine();
      for (String recipe : usr.getRecipes())
        System.out.println(recipe);
      System.out.print('\n');
    }
    input.close();
  }
  
  private static void fridgeInteraction() {
    System.out.println("Minha Geladeira:\n1. Listar ingredientes na minha geladeira.\n2. Listar minha lista de compras.\n"
        + "3. Adicionar ingrediente a minha geladeira.\n4. Adicionar ingrediente a minha lista de compra.\n"
        + "5. Remover ingrediente da minha geladeira.\n6. Remover ingrediente da minha lista de compra.\n7. Voltar.\n");
    
    Scanner input = new Scanner(System.in);
    int opt;
    
    do {
      opt = input.nextInt();
      switch(opt) {
        case 1: {
          Ingredient[] list = usr.getIngredients();
          System.out.println("Ingredientes na minha geladeira:");
          for (Ingredient ing : list)
            System.out.printf("\t%s:\t%5d\t%s\n", ing.getName(), ing.getValue(), ing.getMeasurement().getName(ing.getValue()));
          System.out.print('\n');
          } break;
        case 2: {
          Ingredient[] list = usr.getShoppingList();
          System.out.println("Ingredientes na minha lista de compras:");
          for (Ingredient ing : list)
            System.out.printf("\t%s:\t%5d\t%s\n", ing.getName(), ing.getValue(), ing.getMeasurement().getName(ing.getValue()));
          System.out.print('\n');
          } break;
        case 3: {
          System.out.println("Formato: \"Nome\" \"Quantidade\" \"Unidade\"\nExemplo: \"Batata\" \"2\" \"xicaras\"\n");
          input.nextLine();
          String entity = input.nextLine();
          String[] tokens = entity.split("\"[^\"]*\"");
          for (String s : tokens)
            s = s.replace("\"", "");
          usr.getFridge().addIngredient(Fridge.List.STORAGE, new Ingredient(tokens[0], Double.parseDouble(tokens[1]), 
              Measurement.getEquivalent(tokens[2])));
          System.out.println(tokens[1] + " " + tokens[2] + " de " + tokens[0] + " adicionado.");
        } break;
        case 4: {
          System.out.println("Formato: \"Nome\" \"Quantidade\" \"Unidade\"\nExemplo: \"Batata\" \"2\" \"xicaras\"\n");
          input.nextLine();
          String entity = input.nextLine();
          String[] tokens = entity.split("\"[^\"]*\"");
          for (String s : tokens)
            s = s.replace("\"", "");
          usr.getFridge().addIngredient(Fridge.List.SHOP_LIST, new Ingredient(tokens[0], Double.parseDouble(tokens[1]), 
              Measurement.getEquivalent(tokens[2])));
          System.out.println(tokens[1] + " " + tokens[2] + " de " + tokens[0] + " adicionado.");
        } break;
        case 5: {
          System.out.println("Formato: \"Nome\" \"Quantidade\"\nExemplo: \"Batata\" \"2\"\n");
          input.nextLine();
          String entity = input.nextLine();
          String[] tokens = entity.split("\"[^\"]*\"");
          for (String s : tokens)
            s = s.replace("\"", "");
          usr.getFridge().removeIngredient(Fridge.List.STORAGE, tokens[0], Double.parseDouble(tokens[1]));
          System.out.println(tokens[1] + " " + tokens[2] + " de " + tokens[0] + " adicionado.");
        } break;
        case 6: {
          System.out.println("Formato: \"Nome\" \"Quantidade\"\nExemplo: \"Batata\" \"2\"\n");
          input.nextLine();
          String entity = input.nextLine();
          String[] tokens = entity.split("\"[^\"]*\"");
          for (String s : tokens)
            s = s.replace("\"", "");
          usr.getFridge().removeIngredient(Fridge.List.SHOP_LIST, tokens[0], Double.parseDouble(tokens[1]));
          System.out.println(tokens[1] + " " + tokens[2] + " de " + tokens[0] + " adicionado.");
        } break;
      }
      System.out.print('\n');
    } while(opt != 7);
    
    input.close();
  }
}
