package org.deckOfCardTests;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.deckOfCardTests.Config.Endpoints;
import org.deckOfCardTests.ResponceDto.CardDto;
import org.deckOfCardTests.ResponceDto.DrawCardDto;
import org.deckOfCardTests.ResponceDto.NewDeckDto;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        sharedData.newDeckData = getNewDeck();
    }


    @Given("new shuffled card deck")
    public void newShuffledCardDeck() {
        NewDeckDto newDeckData = getNewShuffledDeckId();
        assertTrue("Expected successful result of getting new shuffled deck, but it fails, please take a look", newDeckData.getSuccess().booleanValue());
        sharedData.newDeckData = newDeckData;
        System.out.println("New deck saved to shared " + newDeckData.getDeckId());
    }

    @When("draw {int} cards")
    public void drawSomeNumbersOfCards(int numberOfCardsToDraw) {
        DrawCardDto drawCardRes = drawCards(numberOfCardsToDraw);
        sharedData.drawCardDto = drawCardRes;
        if (drawCardRes.getError() != null)
            log.error("Something went wrong during drawing cards, please take a look, error:" + drawCardRes.getError());
    }

    @Then("deck cards left is {int}")
    public void deckCardsLeftIsRemains(int expectedRemains) {
        assertTrue(String.format("Expected card remains is - %d, actual is - %d", expectedRemains, sharedData.drawCardDto.getRemaining()),
                expectedRemains == sharedData.drawCardDto.getRemaining());
    }

    @Given("^should be an error \"([^\\\"]*)\"$")
    public void shouldBeError(String errorMessage) {
        assertTrue("No expected error message:" + errorMessage, sharedData.drawCardDto.getError().equals(errorMessage));
    }

    @Given("^new card deck contains only \"([^\\\"]*)\"$")
    public void newCardDeckContainsOnly(String listOfCards) {
        sharedData.newDeckData = getNewDeck(listOfCards);
    }

    @Given("^check if deck contains only \"([^\\\"]*)\"$")
    public void checkIfDeckContainsOnly(String listOfCards) {
        Set<String> expectedCards = Arrays.stream(listOfCards.split(",")).collect(Collectors.toSet());
        assertTrue("Looks like expected cards list is empty", expectedCards.size() > 0);
        drawSomeNumbersOfCards(sharedData.newDeckData.getRemaining().intValue());
        List<CardDto> cards = sharedData.drawCardDto.getCards();
        Set<String> cardsInDeck = cards.stream().map(card -> card.getCode()).collect(Collectors.toSet());
        System.out.println("Actual deck cards set is - " + cardsInDeck);
        System.out.println("Expected deck cards set is - " + expectedCards);
        assertTrue(String.format("Expected list of cards %s differs from actual %s,", expectedCards, cardsInDeck),
                cardsInDeck.size() == expectedCards.size() && cardsInDeck.removeAll(expectedCards) && cardsInDeck.size() == 0);
    }

    @Then("check if deck not contains used cards")
    public void checkIfDeckNotContainsUsedCards() {
        Set<String> usedCards = sharedData.drawCardDto.getCards().stream().map(card -> card.getCode()).collect(Collectors.toSet());
        drawSomeNumbersOfCards(sharedData.drawCardDto.getRemaining().intValue());
        Set<String> remainingCards = sharedData.drawCardDto.getCards().stream().map(card -> card.getCode()).collect(Collectors.toSet());
        System.out.println("Used cards -" + usedCards);
        System.out.println("Remaining cards -" + remainingCards);
        int remainingCardsSize = remainingCards.size();
        remainingCards.removeAll(usedCards);
        assertTrue(String.format("Some cards still in deck, used cards -  %s;\r\n remainig cards -  %s;", usedCards, remainingCards),
                remainingCardsSize == remainingCards.size()
        );
    }

    private NewDeckDto getNewDeck() {
        NewDeckDto newDeckData = given().get(Endpoints.newDeckUrl).getBody().as(NewDeckDto.class);
        return newDeckData;
    }

    private NewDeckDto getNewDeck(String cardsList) {
        System.out.println("Get new deck with cards:" + cardsList);
        NewDeckDto newDeckData = given().get(Endpoints.newShuffledLimitedDeckUrl + cardsList).getBody().as(NewDeckDto.class);
        return newDeckData;
    }

    private NewDeckDto getCurrentDeckState() {
        return given().get(Endpoints.currentDeckStateUrl.replace("<<deck_id>>", sharedData.newDeckData.getDeckId())).getBody().as(NewDeckDto.class);
    }

    private NewDeckDto getNewShuffledDeckId() {
        System.out.println(("Try to get new deck by url " + Endpoints.newShuffledDeckUrl));
        NewDeckDto newDeckData = given()
                .get(Endpoints.newShuffledDeckUrl).getBody().as(NewDeckDto.class);
        System.out.println("newDeckData.getDeckId() - " + newDeckData.getDeckId());
        return newDeckData;
    }

    private DrawCardDto drawCards(int numberOfCardsToDraw) {
        String url = Endpoints.drawCardUrl.replace("<<deck_id>>", sharedData.newDeckData.getDeckId()) + String.valueOf(numberOfCardsToDraw);
        System.out.println("Try to draw cards bu url " + url);
        DrawCardDto drawCardDto = given().get(url).getBody().as(DrawCardDto.class);
        return drawCardDto;
    }
};
