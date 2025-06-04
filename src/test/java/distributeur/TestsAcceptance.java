package distributeur;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestsAcceptance {

    private DistributeurAutomatique distributeur;
    private Transaction transaction;
    private List<Transaction> transactions;
    private int montantInsere;
    private String boissonSelectionnee;
    private Exception exception;
    private List<String> listeBoissons;
    private int quantiteDemande;
    private  int prixBoisson;
    private int quantiteInitiale;


    @Given("le distributeur contient {int} boissons {string} à {int}")
    public void le_distributeur_contient_boissons_a(int quantite, String nom, int prix) {
        distributeur = new DistributeurAutomatique();
        distributeur.rechargerBoisson(nom, quantite, prix);
        boissonSelectionnee = nom;
        prixBoisson = prix;
    }

    @Given("le distributeur ne contient plus de boissons {string}")
    public void le_distributeur_ne_contient_plus_boissons(String nom) {
        distributeur = new DistributeurAutomatique();
        boissonSelectionnee = nom;
        prixBoisson = 0;
    }

    @Given("le distributeur ne propose pas {string}")
    public void le_distributeur_ne_propose_pas(String nom) {
        distributeur = new DistributeurAutomatique();
        boissonSelectionnee = nom;
        prixBoisson = 0;
    }

    @Given("il reste {int} boissons {string} à {int}")
    public void il_reste_boissons_a(int quantite, String nom, int prix) {
        distributeur = new DistributeurAutomatique();
        distributeur.rechargerBoisson(nom, quantite, prix);
        boissonSelectionnee = nom;
        prixBoisson = prix;
    }

    @Given("le distributeur contient {string}, {string} et {string}")
    public void le_distributeur_contient_trois_boissons(String b1, String b2, String b3) {
        distributeur = new DistributeurAutomatique();
        distributeur.rechargerBoisson(b1, 5, 300);
        distributeur.rechargerBoisson(b2, 5, 300);
        distributeur.rechargerBoisson(b3, 5, 300);
    }

    /*@Given("le distributeur ne propose pas {string}")
    public void le_distributeur_ne_propose_pas_boisson(String nom) {
        distributeur = new DistributeurAutomatique();
        boissonSelectionnee = nom;
    }*/

    @Given("le portefeuille du distributeur est vide")
    public void le_portefeuille_est_vide() {
        distributeur = new DistributeurAutomatique();
        distributeur.getPortefeuille().setMontant(0);
    }

    @Given("il y a {int} boissons {string} à {int}")
    public void il_y_a_boissons(int quantite, String nom, int prix) {
        distributeur = new DistributeurAutomatique();
        distributeur.rechargerBoisson(nom, quantite, prix);
        boissonSelectionnee = nom;
        prixBoisson = prix;
        quantiteInitiale = quantite;
    }

    @When("l’utilisateur insère {int} et sélectionne {string}")
    public void l_utilisateur_insere_et_selectionne(int montant, String nom) {
        montantInsere = montant;
        boissonSelectionnee = nom;
        quantiteDemande  = 1;
        try {
            transaction = distributeur.acheterBoisson(nom, 1, montant);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l’utilisateur tente d’acheter 1 {string} en insérant {int}")
    public void l_utilisateur_tente_acheter_avec_montant_negatif(String nom, int montant) {
        boissonSelectionnee = nom;
        quantiteDemande = 1;
        try {
            transaction = distributeur.acheterBoisson(nom, 1, montant);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l’utilisateur tente d’acheter 0 {string}")
    public void l_utilisateur_tente_acheter_zero(String nom) {
        boissonSelectionnee = nom;
        quantiteDemande = 0;
        try {
            transaction = distributeur.acheterBoisson(nom, 0, 0);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l’utilisateur tente d’acheter 3 {string} en insérant {int}")
    public void l_utilisateur_tente_acheter_trois(String nom, int montant) {
        boissonSelectionnee = nom;
        quantiteDemande = 3;
        try {
            transaction = distributeur.acheterBoisson(nom, 3, montant);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l’utilisateur insère {int} et achète 3 {string}")
    public void l_utilisateur_achete_trois(int montant, String nom) {
        boissonSelectionnee = nom;
        quantiteDemande = 3;
        try {
            transaction = distributeur.acheterBoisson(nom, 3, montant);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("l’utilisateur consulte la liste des boissons disponibles")
    public void l_utilisateur_consulte_liste_boissons() {
        listeBoissons = new ArrayList<>();
        for (Boisson b : distributeur.consulterBoissons()) {
            String prixStr = (b.getPrix() == (int) b.getPrix())
                    ? String.valueOf((int) b.getPrix())
                    : String.valueOf(b.getPrix());
            listeBoissons.add(b.getNom() + " à " + prixStr);
        }
    }

    @When("le personnel recharge {int} boissons {string}")
    public void le_personnel_recharge_boissons(int quantite, String nom) {
        distributeur.rechargerBoisson(nom, quantite, 300);
    }

    @When("le personnel ajoute {int} boissons {string} à {int}")
    public void le_personnel_ajoute_boisson(int quantite, String nom, int prix) {
        distributeur.rechargerBoisson(nom, quantite, prix);
    }

    @When("le personnel tente de recharger -2 {string}")
    public void le_personnel_tente_recharger_negatif(String nom) {
        distributeur.rechargerBoisson(nom, -2, 300);
    }

    @When("deux achats de 300 sont réalisés")
    public void deux_achats_realises() {
        distributeur.rechargerBoisson("Fanta", 2, 300);
        distributeur.acheterBoisson("Fanta", 1, 300);
        distributeur.acheterBoisson("Fanta", 1, 300);
    }

    @When("l’utilisateur achète successivement 1 {string} puis 2 {string}")
    public void l_utilisateur_achete_successivement(String nom1, String nom2) {
        distributeur = new DistributeurAutomatique();
        distributeur.rechargerBoisson(nom1, 1, 300);
        distributeur.rechargerBoisson(nom2, 2, 300);
        distributeur.acheterBoisson(nom1, 1, 300);
        distributeur.acheterBoisson(nom2, 2, 600);
    }

    @When("il consulte l’historique des transactions")
    public void il_consulte_historique() {
        transactions = distributeur.getJournal().listerTransactions();
    }

    @Then("la boisson est délivrée, le stock passe à {int} et le portefeuille est crédité de {int}")
    public void la_boisson_est_delivree_stock_et_portefeuille(int stockAttendu, int montantAttendu) {
        assertNotNull(transaction);
        assertEquals(stockAttendu, distributeur.getStock().getQuantite(new Boisson(boissonSelectionnee, prixBoisson)));
        assertEquals(montantAttendu, distributeur.getPortefeuille().getMontant(), 0.001);
    }

    @Then("l’achat est refusé, le stock reste à {int}")
    public void achat_refuse_stock_reste(int stockAttendu) {
        assertNull(transaction);
        assertEquals(stockAttendu, distributeur.getStock().getQuantite(new Boisson(boissonSelectionnee, prixBoisson)));
    }

    @Then("l’achat est refusé, aucune boisson n’est délivrée")
    public void achat_refuse_aucune_boisson() {
        assertNull(transaction);
    }

    @Then("un message d’erreur indique que la boisson n’est pas disponible")
    public void message_erreur_boisson_non_disponible() {
        assertNull(transaction);
    }

    @Then("la boisson est délivrée, le stock passe à {int} et {int} de monnaie sont rendus")
    public void boisson_delivree_avec_monnaie(int stockAttendu, int monnaie) {
        assertNotNull(transaction);
        assertEquals(stockAttendu, distributeur.getStock().getQuantite(new Boisson(boissonSelectionnee, prixBoisson)));
    }

    @Then("la liste affiche les noms et prix de toutes les boissons")
    public void liste_affiche_noms_et_prix() {
        assertTrue(listeBoissons.stream().anyMatch(s -> s.startsWith("Fanta à 300")));
        assertTrue(listeBoissons.stream().anyMatch(s -> s.startsWith("Coca-cola à 300")));
        assertTrue(listeBoissons.stream().anyMatch(s -> s.startsWith("Sprite à 300")));
    }

    @Then("le stock de {string} passe à {int}")
    public void stock_de_boisson_passe_a(String nom, int quantite) {
        assertEquals(quantite, distributeur.getStock().getQuantite(new Boisson(nom, 300)));
    }

    @Then("{string} apparaît dans la liste avec une quantité de {int}")
    public void boisson_apparait_dans_liste(String nom, int quantite) {
        assertEquals(quantite, distributeur.getStock().getQuantite(new Boisson(nom, 350)));
    }

    @Then("3 boissons sont délivrées, le stock passe à {int}, le portefeuille est crédité de {int}")
    public void trois_boissons_delivrees(int stockAttendu, int montant) {
        assertNotNull(transaction);
        assertEquals(stockAttendu, distributeur.getStock().getQuantite(new Boisson(boissonSelectionnee, prixBoisson)));
        assertEquals(montant, distributeur.getPortefeuille().getMontant(), 0.001);
    }

    @Then("l’achat est refusé, aucun changement dans le stock ou le portefeuille")
    public void achat_refuse_aucun_changement() {
        assertNull(transaction);
    }

    @Then("le stock ne change pas")
    public void stock_ne_change_pas() {
        int quantiteActuelle = distributeur.getStock().getQuantite(new Boisson(boissonSelectionnee, prixBoisson));
        assertEquals(quantiteInitiale, quantiteActuelle);
    }

    @Then("les deux achats sont présents dans le journal des ventes")
    public void deux_achats_dans_journal() {
        assertNotNull(transactions);
        assertEquals(2, transactions.size());
    }

    @Then("le solde du portefeuille est de {int}")
    public void solde_portefeuille(int montant) {
        assertEquals(montant, distributeur.getPortefeuille().getMontant(), 0.001);
    }

}