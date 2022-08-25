Feature: Create credit/deposit card

Scenario Template: name - <name>; type - <type>
  Given create '<type>' card with name '<name>'
  And check parameters of created card: '<classType>', '<name>', '<balance>'
  Examples:
    | type   | name    | classType                        | balance |  |
    | Credit | Card    | class products.cards.CreditCard  | 0.0     |  |
    | Debit  | !_tA1 9 | class products.cards.DepositCard | 0.0     |  |