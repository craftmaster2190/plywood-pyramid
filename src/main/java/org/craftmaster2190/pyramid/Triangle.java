package org.craftmaster2190.pyramid;

import lombok.*;
import lombok.experimental.Accessors;

import static org.craftmaster2190.pyramid.Measurement.FORMAT;

@Data
@Accessors(chain = true)
class Triangle {
  private Measurement a, b, c;
  private double angleA, angleB, angleC;

  public static Triangle rightTriangle(Measurement a, Measurement b) {
    val triangle = new Triangle().setA(a).setB(b).setAngleC(90);

    val hypotenuse = (a.squared().plus(b.squared())).squareRoot();
    triangle.setC(hypotenuse);

    // A = arctan (a / b)
    val angleA = a.arctanWith(b);
    triangle.setAngleA(angleA);

    val angleB = 90 - angleA;
    triangle.setAngleB(angleB);

    return triangle;
  }

  public static IsoscelesTriangle isosceles(Measurement lengthOfDuplicatedEdge, double topAngle) {
    val triangle = (IsoscelesTriangle) new IsoscelesTriangle().setAngleC(topAngle).setA(lengthOfDuplicatedEdge).setB(lengthOfDuplicatedEdge);

    val otherAngles = (180 - topAngle) / 2;
    triangle.setAngleA(otherAngles).setAngleB(otherAngles);

    // Law of cosines c^2 = a^2 + b^2 - 2ab * cos(C)
    val aSquared = triangle.getA().squared();
    val bSquared = triangle.getB().squared();
    val twoAB = (triangle.getA().multipliedBy(2)).multipliedBy(triangle.getB());
    val cosC = StrictMath.cos(Math.toRadians(triangle.getAngleC()));
    val twoABTimesCosC = twoAB.multipliedBy(cosC);
    val cSquared = aSquared.plus(bSquared).minus(twoABTimesCosC);

    val edgeA = cSquared.squareRoot();
    triangle.setC(edgeA);

    triangle.setHeight(triangle.calcIsoscelesHeight());

    return triangle;
  }

  public static Measurement calcOtherSideOfRightTriangle(Measurement knownSide, Measurement hypotenuse) {
    // a2 + b2 = c2
    // b2 = c2 - a2
    // b = sqrt ( c2 - a2 )
    return (hypotenuse.squared().minus(knownSide.squared())).squareRoot();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "a=" + a +
        ", b=" + b +
        ", c=" + c +
        ", angleA=" + FORMAT.format(angleA) + "°" +
        ", angleB=" + FORMAT.format(angleB) + "°" +
        ", angleC=" + FORMAT.format(angleC) + "°" +
        ')';
  }

  public Measurement calcIsoscelesOddSide() {
    if (a.equals(b)) {
      return c;
    }
    else if (a.equals(c)) {
      return b;
    }
    else if (b.equals(c)) {
      return a;
    }
    else {
      throw new IllegalStateException("Not an isosceles triangle: " + this);
    }
  }

  public Measurement calcIsoscelesHeight() {
    if (a.equals(b)) {
      return calcIsoscelesHeight(c, a);
    }
    else if (a.equals(c)) {
      return calcIsoscelesHeight(b, a);
    }
    else if (b.equals(c)) {
      return calcIsoscelesHeight(a, b);
    }
    else {
      throw new IllegalStateException("Not an isosceles triangle: " + this);
    }

  }

  private Measurement calcIsoscelesHeight(Measurement oddSide, Measurement duplicatedSide) {
    val rightSideA = oddSide.dividedBy(2);
    val rightSideC = duplicatedSide;

    return calcOtherSideOfRightTriangle(rightSideA, rightSideC);
  }

  @Data
  @Accessors(chain = true)
  public static class IsoscelesTriangle extends Triangle {
    private Measurement height;

    @Override
    public String toString() {
      var superString = super.toString();
      superString = superString.substring(0, superString.length() - 1);

      return superString + ", " +
          "height=" + height +
          ')';
    }
  }
}
