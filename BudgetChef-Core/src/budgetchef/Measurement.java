package budgetchef;

public enum Measurement {
  GRAMS(-1),
  ML(1),
  TEA_SPOONS(5),
  SOUP_SPOONS(30),
  TEACUP(200),
  PINCH(-1),
  TO_TASTE(-1),
  NIL(-1);
  
  private double value_;
  
  private Measurement(double valueInMl) {
    value_ = valueInMl;
  }
  
  public double getMillilitres() { return value_; }
  
  public static double convertionRatio(Measurement toChange, Measurement desired) {
    if (toChange.value_ > 0)
      return toChange.value_/desired.value_;
    return 1;
  }
}

