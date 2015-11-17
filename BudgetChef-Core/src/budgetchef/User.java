package budgetchef;

public class User {
  private String name_;

  private java.util.ArrayList<Recipe> recipes_ = new java.util.ArrayList<>();
  private Fridge fridge_ = new Fridge();

  public User(String name) {
    name_ = name;
  }

  public String getName() { return name_; }
  public Ingredient[] getIngredients() { return fridge_.getIngredients(); }
}
