package org.craftmaster2190.pyramid;

import lombok.*;

@Value
public class TrianglePoints3D {
  Point3D p1, p2, p3;

  public Point3D surfaceNormal() {
    //Begin Function CalculateSurfaceNormal (Input Triangle) Returns Vector
    //
    //	Set Vector U to (Triangle.p2 minus Triangle.p1)
    //	Set Vector V to (Triangle.p3 minus Triangle.p1)
    //
    //	Set Normal.x to (multiply U.y by V.z) minus (multiply U.z by V.y)
    //	Set Normal.y to (multiply U.z by V.x) minus (multiply U.x by V.z)
    //	Set Normal.z to (multiply U.x by V.y) minus (multiply U.y by V.x)
    //
    //	Returning Normal
    //
    //End Function

    val vectorU = p2.minus(p1);
    val vectorV = p3.minus(p1);

    val normalX = vectorU.getY().multipliedBy(vectorV.getZ()).minus(vectorU.getZ().multipliedBy(vectorV.getY()));
    val normalY = vectorU.getZ().multipliedBy(vectorV.getX()).minus(vectorU.getX().multipliedBy(vectorV.getZ()));
    val normalZ = vectorU.getX().multipliedBy(vectorV.getY()).minus(vectorU.getY().multipliedBy(vectorV.getX()));

    return new Point3D(normalX, normalY, normalZ);
  }
}
