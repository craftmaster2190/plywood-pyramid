package org.craftmaster2190.pyramid;

import lombok.*;
import lombok.experimental.Accessors;

import static org.craftmaster2190.pyramid.Measurement.FORMAT;

@Data
@Accessors(chain = true)
class Pyramid {
  private Measurement baseWidth;
  private Triangle face;
  private Measurement centerHeight;
  private Measurement faceHeight;
  private double face2FaceAngle;

  public static Pyramid for4Faces(Triangle.IsoscelesTriangle face) {
    val pyramid = new Pyramid().setFace(face);

    pyramid.setBaseWidth(face.calcIsoscelesOddSide());

    pyramid.setFaceHeight(face.calcIsoscelesHeight());

    val inradius = pyramid.getBaseWidth().dividedBy(2);
    pyramid.setCenterHeight(
        Triangle.calcOtherSideOfRightTriangle(inradius, pyramid.getFaceHeight()));

    // calculate triangle points
    val topVertice = new Point3D(Measurement.ZERO, Measurement.ZERO, pyramid.getCenterHeight());
    val bottomLeftVertice = new Point3D(inradius.negate(), inradius.negate(), Measurement.ZERO);
    val bottomRightVertice = new Point3D(inradius, inradius.negate(), Measurement.ZERO);
    val bottomRightBackVertice = new Point3D(inradius, inradius, Measurement.ZERO);

    val face1 = new TrianglePoints3D(bottomLeftVertice, topVertice, bottomRightVertice);
    val face2 = new TrianglePoints3D(bottomRightVertice, topVertice, bottomRightBackVertice);

    // calc face2FaceAngle
    val face1Normal = face1.surfaceNormal();
    val face2Normal = face2.surfaceNormal();
    val dotProduct = face1Normal.dotProduct(face2Normal);
    val face1NormalMagnitude = face1Normal.vectorMagnitude();
    val face2NormalMagnitude = face2Normal.vectorMagnitude();

    val cosineOfAngle = dotProduct.dividedBy(face1NormalMagnitude.multipliedBy(face2NormalMagnitude));

    pyramid.setFace2FaceAngle(cosineOfAngle.arccosine());

    return pyramid;
  }

  @Override
  public String toString() {
    return "Pyramid(" +
        "baseWidth=" + baseWidth +
        ", face=" + face +
        ", centerHeight=" + centerHeight +
        ", faceHeight=" + faceHeight +
        ", face2FaceAngle=" + FORMAT.format(face2FaceAngle) + "°" +
        ')';
  }
}
