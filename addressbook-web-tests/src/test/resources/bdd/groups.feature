Feature: Groups

  Scenario Outline: Group creation
    Given a set of groups
    When I create a new group with name <name>, header <header> and footer <footer>
    Then the new set of groups is equal to the old set with the added group

    Examples:
    | name    | header    |  footer    |
    | n1      | h1        |  f1        |
    | n2      | h2        |  f2        |
    | n3      | h3        |  f3        |
