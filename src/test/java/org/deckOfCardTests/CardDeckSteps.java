package org.deckOfCardTests;

import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.deckOfCardTests.Config.Endpoints;
import org.deckOfCardTests.ResponceDto.DrawCardDto;
import org.deckOfCardTests.ResponceDto.NewDeckDto;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

@Slf4j
public class CardDeckSteps {
    @Inject
    private SharedData sharedData;

    @Before()
    public void beforeTest() {

    }

    @Given("new default card deck")
    public void getNewDefaultDeck() {
        sharedData.newDeckData = getNewDeckId();
    }


    @Given("new shuffled card deck")
    public void newShuffledCardDeck() {
        NewDeckDto newDeckData = getNewShuffledDeckId();
        assertTrue("Expected successful result of getting new shuffled deck, but it fails, please take a look",newDeckData.getSuccess().booleanValue());
        sharedData.newDeckData = newDeckData;
        System.out.println("New deck saved to shared"+newDeckData.getDeckId());
    }

    @When("draw {int} cards")
    public void drawSomeNumbersOfCards(int numberOfCardsToDraw) {
        DrawCardDto drawCardRes = drawCards(numberOfCardsToDraw);
        sharedData.drawCardDto=drawCardRes;
        assertTrue("Expected successful result of drawing new cards, but it fails, please take a look",drawCardRes.getSuccess().booleanValue());
        System.out.println(drawCardRes.getRemaining());
    }

    @Then("deck cards left is {int}")
    public void deckCardsLeftIsRemains(int expectedRemains) {
        assertTrue(String.format("Expected card remains is - %d, actual is - %d",expectedRemains,sharedData.drawCardDto.getRemaining()), expectedRemains==sharedData.drawCardDto.getRemaining());
    }

    private NewDeckDto getNewDeckId() {
        NewDeckDto newDeckData = given().get(Endpoints.newDeckUrl).getBody().as(NewDeckDto.class);
        return newDeckData;
    }

    private NewDeckDto getCurrentDeckState(){
        return given().get(Endpoints.currentDeckStateUrl.replace("<<deck_id>>",sharedData.newDeckData.getDeckId())).getBody().as(NewDeckDto.class);
    }

    public NewDeckDto getNewShuffledDeckId() {
        System.out.println(("Try to get new deck by url " + Endpoints.newShuffledDeckUrl));
        NewDeckDto newDeckData = given()
                .get(Endpoints.newShuffledDeckUrl).getBody().as(NewDeckDto.class);
        System.out.println("newDeckData.getDeckId() - "+newDeckData.getDeckId());
        System.out.println(sharedData.deckId);
        return newDeckData;
    }

    private DrawCardDto drawCards(int numberOfCardsToDraw) {
        String url = Endpoints.drawCardUrl.replace("<<deck_id>>", sharedData.newDeckData.getDeckId()) + String.valueOf(numberOfCardsToDraw);
        System.out.println("Try to draw cards bu url " + url);
        DrawCardDto drawCardDto = given().get(url).getBody().as(DrawCardDto.class);
        return drawCardDto;
    }


};
