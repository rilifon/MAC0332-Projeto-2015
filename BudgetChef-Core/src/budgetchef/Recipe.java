package budgetchef;

public class Recipe {
  private String name_;
  private java.util.ArrayList<Ingredient> ingredients_;

  private double rating_ = 0;
  private double ratingCount_ = 0;

  public Recipe(String name, Ingredient... ingredients) {
    name_ = name;
    ingredients_ = new java.util.ArrayList<>(java.util.Arrays.asList(ingredients));
  }

  public String getName() { return name_; }
  
  public Ingredient[] getIngredients() {
	 Ingredient[] array = new Ingredient[ingredients_.size()];
	 return ingredients_.toArray(array);
  }
  
  public void addIngredient(Ingredient... ingredients) {
    ingredients_.addAll(java.util.Arrays.asList(ingredients));
  }
 
  public void removeIngredient(String name) {
    for (Ingredient toRem : ingredients_)
      if (toRem.getName().equals(name)) {
        ingredients_.remove(toRem);
        return;
      }
  }
  
  public Ingredient getIngredient(String name) {
    for (Ingredient retVal : ingredients_)
      if (retVal.getName().equals(name))
        return retVal;
    return null;
  }
  
  public void addRating(double newRating) {
    rating_ = rating_*ratingCount_;
    rating_ += newRating;
    ratingCount_++;
    rating_ /= ratingCount_;
  }
  
  public double getRatings() { return rating_; }
}
