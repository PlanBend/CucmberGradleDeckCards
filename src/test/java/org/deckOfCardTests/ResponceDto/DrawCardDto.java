
package org.deckOfCardTests.ResponceDto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrawCardDto {
    private List<CardDto> cards;
    private String deck_id;
    private Long remaining;
    private Boolean success;
    private String error;

    public String getDeckId() {
        return deck_id;
    }


}
