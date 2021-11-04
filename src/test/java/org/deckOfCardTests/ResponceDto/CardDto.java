
package org.deckOfCardTests.ResponceDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {
    private String code;
    private String image;
//    private Images images;
    private String suit;

}
