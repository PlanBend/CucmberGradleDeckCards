package org.deckOfCardTests;

import io.cucumber.guice.ScenarioScoped;
import lombok.Setter;
import org.deckOfCardTests.ResponceDto.DrawCardDto;
import org.deckOfCardTests.ResponceDto.NewDeckDto;


@Setter
@ScenarioScoped
public class SharedData {
    public NewDeckDto newDeckData;
    public DrawCardDto drawCardDto;
    public String testIt = "testItvalue";
    public String deckId = "";

}
