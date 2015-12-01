package budgetchef;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FridgeTest {

  Ingredient pickles;
  Ingredient quinoa;
  Ingredient poutine; 
  
  @Before
  public final void setUp() {
    pickles = new Ingredient("Pickles", 1, Measurement.TO_TASTE);
    quinoa = new Ingredient("Quinoa", 2, Measurement.TEACUP);
    poutine = new Ingredient("Poutine", 3, Measurement.TEACUP);
  }
  
  @Test
  public final void testComparator() {
    try {
      Field nameComparatorField = Fridge.class.getDeclaredField("nameComparator");
      nameComparatorField.setAccessible(true);
      java.util.Comparator<Ingredient> nameComparator = (java.util.Comparator<Ingredient>)
          nameComparatorField.get(new Fridge());
            
      Assert.assertEquals(nameComparator.compare(pickles, pickles), 0);
      Assert.assertTrue(nameComparator.compare(pickles, quinoa) < 0);
      Assert.assertTrue(nameComparator.compare(poutine, pickles) > 0);
      
    } catch(java.lang.Exception exc) {
      System.err.println("Error: " + exc.getMessage());
    }
  }

  @Test
  public final void testGet() {
    Fridge myFridge = new Fridge();
    
    myFridge.addIngredient(Fridge.List.STORAGE, pickles, quinoa);
    myFridge.addIngredient(Fridge.List.SHOP_LIST, poutine);
    
    Ingredient[] storage = myFridge.getIngredients(Fridge.List.STORAGE);
    Assert.assertEquals(storage.length, 2);
    Assert.assertArrayEquals(storage, new Ingredient[] {pickles, quinoa});
    
    Ingredient[] shopList = myFridge.getIngredients(Fridge.List.SHOP_LIST);
    Assert.assertEquals(shopList.length, 1);
    Assert.assertArrayEquals(shopList, new Ingredient[] {poutine});
  }

  @Test
  public final void testAdd() {
    Fridge myFridge = new Fridge();
    
    myFridge.addIngredient(Fridge.List.STORAGE, pickles, pickles, pickles, poutine);
    Ingredient[] storage = myFridge.getIngredients(Fridge.List.STORAGE);
    Assert.assertArrayEquals(storage, new Ingredient[] {pickles, poutine});
    
    myFridge.addIngredient(Fridge.List.SHOP_LIST, quinoa, pickles, poutine);
    Ingredient[] shopList = myFridge.getIngredients(Fridge.List.SHOP_LIST);
    Assert.assertArrayEquals(shopList, new Ingredient[] {pickles, poutine, quinoa});
  }

  @Test
  public final void testRemove() {
    Fridge myFridge = new Fridge();
    
    myFridge.addIngredient(Fridge.List.STORAGE, pickles, poutine, quinoa);
    myFridge.removeIngredient(Fridge.List.STORAGE, "Poutine");
    myFridge.removeIngredient(Fridge.List.STORAGE, "Pickles", 1);
    myFridge.removeIngredient(Fridge.List.STORAGE, "Quinoa", 1);
    
    Ingredient[] storage = myFridge.getIngredients(Fridge.List.STORAGE);
    Assert.assertTrue(storage.length == 1);
    Assert.assertTrue(storage[0].getValue() == 1);
    
    myFridge = new Fridge();
    
    myFridge.addIngredient(Fridge.List.SHOP_LIST, poutine);
    myFridge.removeIngredient(Fridge.List.SHOP_LIST, "Pickles");
    myFridge.removeIngredient(Fridge.List.SHOP_LIST, "Quinoa");
    
    Ingredient[] shopList = myFridge.getIngredients(Fridge.List.SHOP_LIST);
    Assert.assertArrayEquals(shopList, new Ingredient[] {poutine});
    
    myFridge.removeIngredient(Fridge.List.SHOP_LIST, "Poutine");
    shopList = myFridge.getIngredients(Fridge.List.SHOP_LIST);
    Assert.assertArrayEquals(shopList, new Ingredient[0]);
  }
}
