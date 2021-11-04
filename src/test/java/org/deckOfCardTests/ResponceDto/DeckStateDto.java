package org.deckOfCardTests.ResponceDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeckStateDto {
    private Long remaining;
    private String deck_id;

    public String getDeckId() {
        return deck_id;
    }

    public void setDeckId(String deck_id) {
        this.deck_id = deck_id;
    }


}
