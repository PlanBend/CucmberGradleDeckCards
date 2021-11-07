Feature: Check card counts in a deck

  Scenario Outline: Check card counts after drawing
    Given new shuffled card deck
    And draw <someNumbers> cards
    Then <remains> cards left in deck

    Examples:
      | someNumbers | remains |
      | 0           | 52      |
      | 1           | 51      |
      | 7           | 45      |
      | 51          | 1       |
      | 52          | 0       |

  Scenario: Check no card left after drawing
    Given new shuffled card deck
    And draw 53 cards
    Then should be an error "Not enough cards remaining to draw 53 additional"
    #actually looks like a bug, because only 1 is additional

  Scenario: Check limited card in deck
    Given new card deck contains only "AS,AD,AC,AH,AS,AD,AC,AH"
    Then check if deck contains only "AS,AD,AC,AH"

  Scenario: Check if used cards not in deck
    Given new shuffled card deck
    And draw 5 cards
    Then deck should not contain drawn cards