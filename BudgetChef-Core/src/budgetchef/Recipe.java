package budgetchef;

public class Recipe {
  private java.util.Comparator<Ingredient> nameComparator = new java.util.Comparator<Ingredient>() {
    @Override
    public int compare(Ingredient lval, Ingredient rval) {
      return lval.getName().compareTo(rval.getName());
    }
  };
  
  private String name_;
  private java.util.PriorityQueue<Ingredient> ingredients_;
  
  private java.util.ArrayList<String> steps_ = new java.util.ArrayList<>();
  private double rating_ = 0;
  private double ratingCount_ = 0;

  public Recipe(String name, Ingredient... ingredients) {
    name_ = name;
    ingredients_ = new java.util.PriorityQueue<>(nameComparator);
    for (Ingredient ing : ingredients)
      ingredients_.offer(ing);
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
  
  public void removeIngredient(String name, double quantity) {
    for (Ingredient toRem : ingredients_)
      if (toRem.getName().equals(name)) {
        if (toRem.getValue() <= quantity)
          ingredients_.remove(toRem);
        else
          toRem.substract(quantity);
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
  
  public void addStep(int index, String step) { steps_.add(index, step); }
  public void removeStep(int index) { steps_.remove(index); }
  public String[] getSteps() {
    String[] steps = new String[steps_.size()];
    return steps_.toArray(steps);
  }
}
