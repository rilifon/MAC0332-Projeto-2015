package budgetchef;

public enum Measurement {
  GRAMS(-1, "grama", "gramas"),
  ML(1, "mL", "mL"),
  TEA_SPOONS(5, "colher de cha", "colheres de cha"),
  SOUP_SPOONS(30, "colher de sopa", "colheres de sopa"),
  TEACUP(200, "xicara", "xicaras"),
  PINCH(-1, "pitada", "pitadas"),
  TO_TASTE(-1, "a gosto", "a gosto"),
  NIL(-1, "", "");
  
  private final double value_;
  private final String sing_, plural_;
  
  private Measurement(double valueInMl, String sing, String plural) {
    value_ = valueInMl;
    sing_ = sing;
    plural_ = plural;
  }
  
  public double getMillilitres() { return value_; }
  public String getName(double quantity) {
    if (quantity > 1)
      return plural_;
    return sing_;
  }
  
  public static double convertionRatio(Measurement toChange, Measurement desired) {
    if (toChange.value_ > 0)
      return desired.value_/toChange.value_;
    return 1;
  }
  
  public static Measurement getEquivalent(String name) {
    Measurement[] measures = Measurement.values();
    for (Measurement m : measures)
      if (m.sing_.equals(name) || m.plural_.equals(name))
        return m;
    return Measurement.NIL;
  }
}

