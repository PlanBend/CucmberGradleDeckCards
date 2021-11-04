package org.deckOfCardTests.Utils;

import io.restassured.response.Response;
import org.deckOfCardTests.Config.Endpoints;
import org.deckOfCardTests.ResponceDto.DrawCardDto;
import org.deckOfCardTests.ResponceDto.NewDeckDto;

import static io.restassured.RestAssured.given;

class Main {
    public static void main(String[] args) {
        System.out.println("test");
        int numberOfCardsToDraw=2;
//        https://deckofcardsapi.com/api/deck/43vnevry9wpw/draw/?count=2


        String url = Endpoints.drawCardUrl.replace("<<deckId>>", "43vnevry9wpw") + String.valueOf(numberOfCardsToDraw);
        System.out.println(url);
        DrawCardDto drawCardDto = given().get(url).getBody().as(DrawCardDto.class);
        System.out.println(drawCardDto.getRemaining());

    }
}
