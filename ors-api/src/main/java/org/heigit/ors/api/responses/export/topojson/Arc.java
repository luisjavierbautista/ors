package org.heigit.ors.api.responses.export.topojson;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
public class Arc implements Serializable {
    private List<List<Double>> coordinates;
    
    @JsonValue
    public List<List<Double>> getCoordinates() {
        return coordinates;
    }
}
