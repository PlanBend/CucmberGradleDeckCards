package org.deckOfCardTests.Config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Endpoints {
    public static String rootUrl =PropertiesHelper.getProperties().getProperty("root.url");//https://deckofcardsapi.com/api/deck/
    public static String newDeckUrl =rootUrl+"new";
    public static String newShuffledDeckUrl =rootUrl+"new/shuffle/?deck_count=1";
    public static String drawCardUrl = rootUrl+"<<deck_id>>/draw/?count=";
    public static String currentDeckStateUrl = rootUrl+"<<deck_id>>";
    public static String partialDeckUrl="new/shuffle/?cards=";
}
