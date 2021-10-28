package org.craftmaster2190.pyramid;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PyramidTest {

  @Test
  void for4Faces() {
    val pyramid = Pyramid.for4Faces(Triangle.isosceles(Measurement.ofFeet(8), 26.56505117707799));

    assertEquals(pyramid.getBaseWidth(), new Measurement(3, 8.112560745093354));
    assertEquals(pyramid.getFace2FaceAngle(), 82.81165214322961);
  }
}
