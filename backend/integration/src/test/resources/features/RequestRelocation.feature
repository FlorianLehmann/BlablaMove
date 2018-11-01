Feature: fr.unice.request a relocation

  Scenario: Create a relocation

    Given a relocation from a client named Arthur
    And an address of departure : 06410, 930 Route des Colles, Biot
    And an address of arrival : 13009, 163 Avenue de Luminy, Marseille
    And 11/11/2018 as the start date
    And 11/11/2018 as the end date
    When he confirms his relocation
    Then he get 1 relocation in his profile