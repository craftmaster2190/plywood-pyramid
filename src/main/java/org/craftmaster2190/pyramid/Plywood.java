package org.craftmaster2190.pyramid;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
class Plywood {
  private Measurement width, height;
}
