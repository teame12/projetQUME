Feature: Achat et gestion de boissons dans le distributeur

  Scenario: Achat réussi d’une boisson
    Given le distributeur contient 9 boissons "Coca-cola" à 300
    When l’utilisateur insère 300 et sélectionne "Coca-cola"
    Then la boisson est délivrée, le stock passe à 8 et le portefeuille est crédité de 300

  Scenario: Achat d’une boisson avec un montant insuffisant
    Given le distributeur contient 9 boissons "Fanta" à 300
    When l’utilisateur insère 100 et sélectionne "Fanta"
    Then l’achat est refusé, le stock reste à 9

  Scenario: Achat d’une boisson en rupture de stock
    Given le distributeur ne contient plus de boissons "Sprite"
    When l’utilisateur insère 300 et sélectionne "Sprite"
    Then l’achat est refusé, aucune boisson n’est délivrée

  Scenario: Achat d’une boisson qui n’existe pas
    Given le distributeur ne propose pas "Pepsi"
    When l’utilisateur insère 300 et sélectionne "Pepsi"
    Then un message d’erreur indique que la boisson n’est pas disponible

  Scenario: Achat avec rendu de monnaie
    Given le distributeur contient 5 boissons "Coca-cola" à 300
    When l’utilisateur insère 500 et sélectionne "Coca-cola"
    Then la boisson est délivrée, le stock passe à 4 et 200 de monnaie sont rendus

  Scenario: Consultation de la liste des boissons
    Given le distributeur contient "Fanta", "Coca-cola" et "Sprite"
    When l’utilisateur consulte la liste des boissons disponibles
    Then la liste affiche les noms et prix de toutes les boissons

  Scenario: Recharge du stock d’une boisson existante
    Given il reste 2 boissons "Fanta" à 300
    When le personnel recharge 5 boissons "Fanta"
    Then le stock de "Fanta" passe à 7

  Scenario: Recharge d’une nouvelle boisson
    Given le distributeur ne propose pas "Bissap"
    When le personnel ajoute 10 boissons "Bissap" à 350
    Then "Bissap" apparaît dans la liste avec une quantité de 10

  Scenario: Achat de plusieurs unités d’une boisson
    Given le distributeur contient 10 boissons "Sprite" à 400
    When l’utilisateur insère 1200 et achète 3 "Sprite"
    Then 3 boissons sont délivrées, le stock passe à 7, le portefeuille est crédité de 1200

  Scenario: Achat avec quantité supérieure au stock
    Given il reste 2 boissons "Bissap" à 350
    When l’utilisateur tente d’acheter 3 "Bissap" en insérant 1050
    Then l’achat est refusé, le stock reste à 2

  Scenario: Achat avec montant négatif
    Given le distributeur contient 5 boissons "Fanta" à 300
    When l’utilisateur tente d’acheter 1 "Fanta" en insérant -300
    Then l’achat est refusé, aucun changement dans le stock ou le portefeuille

  Scenario: Recharge avec quantité négative
    Given il y a 5 boissons "Sprite" à 400
    When le personnel tente de recharger -2 "Sprite"
    Then le stock ne change pas

  Scenario: Historique des transactions après plusieurs achats
    Given l’utilisateur achète successivement 1 "Fanta" puis 2 "Sprite"
    When il consulte l’historique des transactions
    Then les deux achats sont présents dans le journal des ventes

  Scenario: Consultation du solde du portefeuille
    Given le portefeuille du distributeur est vide
    When deux achats de 300 sont réalisés
    Then le solde du portefeuille est de 600

  Scenario: Achat d’une boisson avec une quantité nulle
    Given le distributeur contient 10 boissons "Coca-cola" à 300
    When l’utilisateur tente d’acheter 0 "Coca-cola"
    Then l’achat est refusé, le stock reste à 10
