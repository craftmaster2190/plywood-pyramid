package org.craftmaster2190.pyramid;

import lombok.*;

@Value
public class Point3D {
  Measurement x, y, z;

  public Point3D minus(Point3D other) {
    return new Point3D(x.minus(other.x), y.minus(other.y), z.minus(other.z));
  }

  public Measurement dotProduct(Point3D other) {
    var product = getX().multipliedBy(other.getX());
    product = product.plus(getY().multipliedBy(other.getY()));
    product = product.plus(getZ().multipliedBy(other.getZ()));
    return product;
  }

  public Measurement vectorMagnitude() {
    return (x.squared().plus(y.squared()).plus(z.squared())).squareRoot();
  }
}
