Feature: Create deposit
  Scenario Template: name - <name>; currency type <currencyType>
    Given create deposit with name '<name>' and currency type '<currencyType>'
    And check params of created deposit: '<classType>', '<currencyType>', '<balance>', '<name>'
    Examples:
      | name            | classType | currencyType | balance |
      | wdq2fv_1CV@$/n  | class products.deposit.Deposit | EURO         | 0.0     |
      | wdq2fv_1C__@$/n | class products.deposit.Deposit | USD          | 0.0     |