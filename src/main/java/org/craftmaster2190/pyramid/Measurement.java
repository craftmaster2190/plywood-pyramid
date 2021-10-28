package org.craftmaster2190.pyramid;

import lombok.*;

import java.text.DecimalFormat;

@Value
class Measurement {
  public static final Measurement ZERO = ofInches(0);
  public static final DecimalFormat FORMAT = new DecimalFormat("0.###");
  double feet, inches;

  public Measurement(double feet, double inches) {
    val totalInches = getTotalInches(feet, inches);
    this.feet = Math.floor(totalInches / 12);
    this.inches = totalInches % 12;
  }

  public static Measurement ofFeet(double feet) {
    return new Measurement(feet, 0);
  }

  public static Measurement ofInches(double inches) {
    return new Measurement(0, inches);
  }

  public Measurement squared() {
    return ofInches(StrictMath.pow(getTotalInches(), 2));
  }

  public Measurement plus(Measurement other) {
    return ofInches(getTotalInches() + other.getTotalInches());
  }

  public Measurement squareRoot() {
    return ofInches(StrictMath.sqrt(getTotalInches()));
  }

  private double getTotalInches() {
    return inches + (feet * 12);
  }

  private double getTotalInches(double feet, double inches) {
    return inches + (feet * 12);
  }

  public Measurement dividedBy(Measurement other) {
    return ofInches(getTotalInches() / other.getTotalInches());
  }

  public Measurement multipliedBy(Measurement other) {
    return ofInches(getTotalInches() * other.getTotalInches());
  }

  public Measurement multipliedBy(double other) {
    return ofInches(getTotalInches() * other);
  }

  public Measurement minus(Measurement other) {
    return ofInches(getTotalInches() - other.getTotalInches());
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + FORMAT.format(feet) + "' " + FORMAT.format(inches) + "\")";
  }

  public double arctanWith(Measurement other) {
    return Math.toDegrees(StrictMath.atan(getTotalInches() / other.getTotalInches()));
  }

  public Measurement dividedBy(double other) {
    return ofInches(getTotalInches() / other);
  }

  public Measurement negate() {
    return ofInches(getTotalInches() * -1);
  }

  public double arccosine() {
    return Math.toDegrees(StrictMath.acos(getTotalInches()));
  }
}
