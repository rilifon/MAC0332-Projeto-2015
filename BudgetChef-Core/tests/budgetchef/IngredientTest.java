package budgetchef;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTest {


  @Test
  public final void testGet() {
    Ingredient pickles = new Ingredient("Pickles", 1, Measurement.TO_TASTE);
    
    Assert.assertEquals(pickles.getName(), "Pickles");
    Assert.assertTrue(pickles.getValue() == 1);
    Assert.assertEquals(pickles.getMeasurement(), Measurement.TO_TASTE);
  }

  @Test
  public final void testSet() {
    Ingredient pickles = new Ingredient("Pickles", 1, Measurement.TO_TASTE);
    Ingredient sugar = new Ingredient("Sugar", 1, Measurement.TEA_SPOONS);

    pickles.setValue(2);
    Assert.assertTrue(pickles.getValue() == 2);

    sugar.setMeasurement(Measurement.SOUP_SPOONS);
    Assert.assertEquals(sugar.getMeasurement(), Measurement.SOUP_SPOONS);
    Assert.assertEquals(sugar.getValue(), 6, 0.001);
  }

  @Test
  public final void testIncDec() {
    Ingredient cinnamon = new Ingredient("Cinnamon", 2, Measurement.TEA_SPOONS);
    Ingredient coffee = new Ingredient("Coffee", 1, Measurement.TEACUP);
      
    coffee.increment();
    Assert.assertTrue(coffee.getValue() == 2);

    cinnamon.decrement();
    Assert.assertTrue(cinnamon.getValue() == 1);
  }

  @Test
  public final void testAddSub() {
    Ingredient sesame = new Ingredient("Sesame", 10, Measurement.GRAMS);
    Ingredient carrot = new Ingredient("Carrot", 10, Measurement.NIL);

    sesame.add(5);
    Assert.assertTrue(sesame.getValue() == 15);

    carrot.substract(5);
    Assert.assertTrue(carrot.getValue() == 5);
  }

  @Test
  public final void testEquals() {
    Ingredient corn = new Ingredient("Corn", 1, Measurement.NIL);
    Ingredient potato = new Ingredient("Potato", 1, Measurement.NIL);
    Fridge myFridge = new Fridge();

    Assert.assertEquals(corn.equals(corn), true);
    Assert.assertEquals(corn.equals(potato), false);
    Assert.assertEquals(corn.equals(myFridge), false);
  }
}
