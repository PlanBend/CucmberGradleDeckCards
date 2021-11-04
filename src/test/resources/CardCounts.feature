Feature: Check card counts in a deck

  Scenario Outline: Check card counts after drawing
    Given new shuffled card deck
    And draw <someNumbers> cards
    Then deck cards left is <remains>

    Examples:
      | someNumbers | remains |
      | 0           | 52      |
      | 1           | 51      |
      | 7           | 45      |
      | 51          | 1       |
      | 52          | 0       |
      | 53          | -1      |
