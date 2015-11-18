package budgetchef;

public class Fridge {
  public enum List { STORAGE, SHOP_LIST; }
  
  private java.util.Comparator<Ingredient> nameComparator = new java.util.Comparator<Ingredient>() {
    @Override
    public int compare(Ingredient lval, Ingredient rval) {
      return lval.getName().compareTo(rval.getName());
    }
  };
  
  private java.util.PriorityQueue<Ingredient> storage_ = new java.util.PriorityQueue<>(nameComparator);
  private java.util.PriorityQueue<Ingredient> shopList_ = new java.util.PriorityQueue<>(nameComparator);
  
  public Fridge() {}
  
  private java.util.PriorityQueue<Ingredient> getRelevantCollection(Fridge.List arg) {
    return arg == Fridge.List.STORAGE? storage_ : shopList_;
  }
  
  public Ingredient[] getIngredients(Fridge.List arg) {
    java.util.PriorityQueue<Ingredient> collection = getRelevantCollection(arg);
    Ingredient[] array = new Ingredient[collection.size()];
    return collection.toArray(array);
  }

  public Ingredient getIngredient(Fridge.List arg, String name) {
    java.util.PriorityQueue<Ingredient> collection = getRelevantCollection(arg);
    for (Ingredient retVal : collection)
      if (retVal.getName().equals(name))
        return retVal;
    return null;
  }
  
  public void addIngredient(Fridge.List arg, Ingredient... ingredients) {
    java.util.PriorityQueue<Ingredient> collection = getRelevantCollection(arg);
    
    for (Ingredient toAdd : ingredients) {
      boolean found = false;
      for (Ingredient toCheck : collection)
        if (toAdd.equals(toCheck)) {
          toCheck.setValue(toCheck.getValue() + toAdd.getValue());
          found = true;
          break;
        }
      if (!found)
        collection.offer(toAdd);
    }
  }

  public void removeIngredient(Fridge.List arg, String toRemove, double quantity) {
    java.util.PriorityQueue<Ingredient> collection = getRelevantCollection(arg);
    
    for (Ingredient ingRem : collection)
      if (ingRem.getName().equals(toRemove)) {
        double currentQuantity = ingRem.getValue();
        if (currentQuantity <= quantity)
          collection.remove(ingRem);
        else 
          ingRem.substract(quantity);
        return;
      }
  }
  
  public void removeIngredient(Fridge.List arg, String toRemove) {
    java.util.PriorityQueue<Ingredient> collection = getRelevantCollection(arg);
    
    for (Ingredient ingRem : collection)
      if(ingRem.getName().equals(toRemove)) {
        collection.remove(ingRem);
        return;
      }
  }
}
