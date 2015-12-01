package budgetchef;

import org.junit.Assert;
import org.junit.Test;

public class MeasurementTest {

  @Test
  public final void testConvertion() {
    Assert.assertTrue(Measurement.convertionRatio(
        Measurement.GRAMS, Measurement.ML) == 1);
    Assert.assertTrue(Measurement.convertionRatio(
        Measurement.TEACUP, Measurement.ML) == 200);
  }
  
  @Test
  public final void testEquivalence() {
    Assert.assertEquals(Measurement.getEquivalent("pitada"), Measurement.PINCH);
    Assert.assertEquals(Measurement.TEA_SPOONS, Measurement.getEquivalent("colheres de cha"));
    Assert.assertEquals(Measurement.getEquivalent("grama"), Measurement.getEquivalent("gramas"));
  }

  @Test
  public final void testName() {
    Assert.assertEquals(Measurement.SOUP_SPOONS.getName(2), "colheres de sopa");
    Assert.assertEquals(Measurement.SOUP_SPOONS.getName(1), "colher de sopa");
    
    Assert.assertEquals(Measurement.TO_TASTE.getName(1), Measurement.TO_TASTE.getName(2));
    Assert.assertNotEquals(Measurement.TEACUP.getName(1), Measurement.TEACUP.getName(2));
  }
}
