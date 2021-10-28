package org.craftmaster2190.pyramid;

import lombok.val;

import static org.craftmaster2190.pyramid.Measurement.FORMAT;

public class PyramidCalculator {

  public static void main(String... args) {
    // TODO take arguments for width and height
    val plywood = new Plywood()
        .setHeight(Measurement.ofFeet(8))
        .setWidth(Measurement.ofFeet(4));

    System.out.println("given 2 pieces of plywood: " + plywood);

    val pyramid = for2Plywood(plywood);

    System.out.println("you will build a pyramid with measurements: " + pyramid);

    System.out.println("To cut braces, set your miter saw to: " + FORMAT.format(pyramid.getFace2FaceAngle() / 2) + "°");
  }

  // Calculate the shape of a pyramid if cutting from two pieces of plywood
  static Pyramid for2Plywood(Plywood plywood) {
    // TODO don't assume height is larger
    val plywoodCutCorner2CornerTriangle = Triangle.rightTriangle(plywood.getWidth(), plywood.getHeight());

    System.out.println("given a piece of plywood; cut it corner 2 corner to get: " + plywoodCutCorner2CornerTriangle);

    val isoscelesTriangleWithPlywoodHeight = Triangle.isosceles(plywood.getHeight(), plywoodCutCorner2CornerTriangle.getAngleA());

    System.out.println("cut that triangle to: " + isoscelesTriangleWithPlywoodHeight);

    return Pyramid.for4Faces(isoscelesTriangleWithPlywoodHeight);
  }

}
