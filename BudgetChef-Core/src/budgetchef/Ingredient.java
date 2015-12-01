package budgetchef;

public class Ingredient {
  private String name_;
  private double value_;
  private Measurement type_;

  public Ingredient(String name, double value, Measurement type) {
    name_ = name;
    value_ = value;
    type_ = type;
  }

  public String getName() { return name_; }
  public double getValue() { return value_; }
  public Measurement getMeasurement() { return type_; }

  public void setValue(double value) { value_ = value; }
  public void setMeasurement(Measurement type) {
    value_ *= Measurement.convertionRatio(type_, type);
    type_ = type;
  }

  public double increment() { return ++value_; }
  public double decrement() { return --value_; }

  public void add(double val) { value_ += val; }
  public void substract(double val) { value_ -= val; }

  @Override
  public boolean equals(Object e) {
    if (e instanceof Ingredient) {
      Ingredient rval = (Ingredient)e;
      return (type_ == rval.type_) && name_.equals(rval.name_);
    }
    return false;
  }
}
