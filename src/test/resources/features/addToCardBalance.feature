Feature: Add to card balance
  Scenario Template: <type> <sum>
    Given create '<type>' card with name '<name>'
    And check parameters of created card: '<classType>', '<name>', '<balance>'
    Then add '<sum>' to current balance card with name '<name>'
    And check current card with name '<name>' balance = '<sum>'
    Examples:
      | type   | name    | classType                        | balance | sum  |
      | Credit | Card    | class products.cards.CreditCard  | 0.0     | 20   |
      | Debit  | !_tA1 9 | class products.cards.DepositCard | 0.0     | 15.5 |