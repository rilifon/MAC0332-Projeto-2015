package budgetchef;

public class Fridge {
  private java.util.PriorityQueue<Ingredient> ingredients_ = new java.util.PriorityQueue<>(
    new java.util.Comparator<Ingredient>() {
      @Override
      public int compare(Ingredient lval, Ingredient rval) {
        return lval.getName().compareTo(rval.getName());
      }
    });

  public Fridge() {}
  
  public Ingredient[] getIngredients() {
    Ingredient[] array = new Ingredient[ingredients_.size()];
    return ingredients_.toArray(array);
  }

  public void add(Ingredient... ingredients) {
    for (Ingredient toAdd : ingredients) {
      boolean found = false;
      for (Ingredient toCheck : ingredients_)
        if (toAdd.equals(toCheck)) {
          toCheck.setValue(toCheck.getValue() + toAdd.getValue());
          found = true;
          break;
        }
      if (!found)
        ingredients_.offer(toAdd);
    }
  }

  public void remove(Ingredient toRemove) {
    ingredients_.remove(toRemove);
  }
}
