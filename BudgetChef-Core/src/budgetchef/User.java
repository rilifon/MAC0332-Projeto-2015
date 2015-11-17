package budgetchef;

public class User {
  private String name_;

  private java.util.ArrayList<Recipe> recipes_ = new java.util.ArrayList<>();
  private Fridge fridge_ = new Fridge();

  public User(String name) {
    name_ = name;
  }

  public String getName() { return name_; }
  public Ingredient[] getIngredients() { return fridge_.getIngredients(Fridge.List.STORAGE); }
  
  public Recipe getRecipe(String name) {
    for (Recipe r : recipes_)
      if (r.getName().equals(name))
        return r;
    return null;
  }
  
  public String[] getRecipes() {
    int recipesSize = recipes_.size();
    String[] array = new String[recipesSize];
    
    for (int i = 0; i < recipesSize; ++i)
      array[i] = recipes_.get(i).getName();
    
    return array;
  }
  
  public void addRecipe(Recipe r) { recipes_.add(r); }
}
