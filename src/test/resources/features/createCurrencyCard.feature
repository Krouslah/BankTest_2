Feature: Create currencyCard
  Scenario Template: <currencyType>
    Given create Currency card with '<name>' name with '<currencyType>' currency type
    And check parameters of created card: '<classType>', '<name>', '<balance>'
    Examples:
      | name        | classType                         | balance | currencyType |
      | card123     | class products.cards.CurrencyCard | 0.0     | USD          |
      | card!sdq@*_ | class products.cards.CurrencyCard | 0.0     | EURO         |