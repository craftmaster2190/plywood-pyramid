# Pyramid Calculator
Serves as a mathematical helper for making wooden pyramids out of plywood.

## Command
```bash
mvn compile exec:java \
  -Dexec.mainClass="org.craftmaster2190.pyramid.PyramidCalculator" |
  tee output.log
```

## Output

>given 2 pieces of plywood: Plywood(width=Measurement(4' 0"), height=Measurement(8' 0"))

>given a piece of plywood; cut it corner 2 corner to get: Triangle(a=Measurement(4' 0"), b=Measurement(8' 0"), c=Measurement(8' 11.331"), angleA=26.565°, angleB=63.435°, angleC=90°)

>cut that triangle to: IsoscelesTriangle(a=Measurement(8' 0"), b=Measurement(8' 0"), c=Measurement(3' 8.113"), angleA=76.717°, angleB=76.717°, angleC=26.565°, height=Measurement(7' 9.432"))

>you will build a pyramid with measurements: Pyramid(baseWidth=Measurement(3' 8.113"), face=IsoscelesTriangle(a=Measurement(8' 0"), b=Measurement(8' 0"), c=Measurement(3' 8.113"), angleA=76.717°, angleB=76.717°, angleC=26.565°, height=Measurement(7' 9.432")), centerHeight=Measurement(7' 6.791"), faceHeight=Measurement(7' 9.432"), face2FaceAngle=82.812°)

>To cut braces, set your miter saw to: 41.406°
