package budgetchef;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RecipeTest {
  
  Recipe pudim;
  Ingredient leite;
  Ingredient ovo;
  Ingredient acucar;
  Ingredient agua;
  Ingredient leite_cond;
  Ingredient[] pudim_ing;
  
  @Before
  public final void setUp() {
    leite = new Ingredient("Leite", 4, Measurement.TEACUP);
    ovo = new Ingredient("Ovo", 4, Measurement.NIL);
    acucar = new Ingredient("Acucar", 1, Measurement.TEACUP);
    agua = new Ingredient("Agua", 0.33, Measurement.TEACUP);
    leite_cond = new Ingredient("Leite condensado", 4, Measurement.TEACUP);
    
    pudim_ing = new Ingredient[] {leite, ovo, acucar, agua, leite_cond};
    pudim = new Recipe("Pudim", leite, ovo, acucar, agua, leite_cond);
  }
  
  @Test
  public final void testConstructor() {
    java.util.List<Ingredient> ingredients = Arrays.asList(pudim.getIngredients());
    for (Ingredient ing : pudim_ing)
      Assert.assertTrue(ingredients.contains(ing));
    Assert.assertEquals(pudim.getName(), "Pudim");
  }
  
  @Test
  public final void testSteps() {
    String[] steps = {
    "Derreta o doce acucar na quente frigideira e adicione "
        + "refrescante agua, formando uma calda deliciosamente doce.",
    "Unte a voluptuosa forma em sweet sweet caramel.",
    "Bata com vigor os diversificados ingredientes restantes em um tecnologico "
        + "liquidificador, e em seguida despeje a deliciosa mistura numa geometrica "
        + "forma que foi delicadamente untada em doce caramelo.",
    "Leve levemente para assar em um delicioso e confortavel banho "
        + "maria, por aproximadamente quatro dezenas multiplicados por sessenta "
        + "segundos.",
    "Tire cuidadosamente o delicioso pudim de sua antiga forma. Uma "
        + "metafora para um processo metamorfosico onde o cru se torna perfeito, "
        + "remetendo as etapas da vida e morte do ser humano. Ressurgindo de um "
        + "casulo que antes o afagava carinhosamente com seus dedos quentes e doces "
        + "mas que no momento metafisico de transformacao expulsa-o com forca e "
        + "violencia diante do mundo cruel que a industria de humanos criou, "
        + "simbolizando a destilacao da essencia metahumana da alma em um destilado "
        + "solido que os poderes ultra-capitalista-socialista-facistas exploram com "
        + "vigor e crueldade. Sirva gelado."
    };
    pudim.addStep(0, steps[0]);
    pudim.addStep(2, steps[2]);
    pudim.addStep(1, steps[1]);
    pudim.addStep(3, steps[3]);
    pudim.addStep(4, steps[4]);
    
    Assert.assertArrayEquals(steps, pudim.getSteps());
  }
  
  @Test
  public final void testRatings() {
    double[] rates = new double[100];
    double avg = 0;
    java.util.Random rand = new java.util.Random();
    
    for (int i=0; i<100; ++i) {
      rates[i] = rand.nextDouble()*10;
      avg += rates[i];
      pudim.addRating(rates[i]);
    }
    
    avg /= 100;
    Assert.assertEquals(avg, pudim.getRatings(), 0.0001);
  }
}
