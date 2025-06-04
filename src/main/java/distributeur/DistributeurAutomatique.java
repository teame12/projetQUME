package distributeur;
import java.util.*;

public class DistributeurAutomatique {
    private Stock stock = new Stock();
    private Portefeuille portefeuille = new Portefeuille();
    private JournalDeVente journal = new JournalDeVente();

    public Stock getStock() {
        return stock;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public JournalDeVente getJournal() {
        return journal;
    }

    public List<Boisson> consulterBoissons() {
        return stock.listerBoissons();
    }

    public Transaction acheterBoisson(String nom, int quantite, double montant) {
        if (quantite <= 0) {
            return null;
        }
        for (Boisson b : stock.listerBoissons()) {
            if (b.getNom().equalsIgnoreCase(nom)) {
                double prixTotal = b.getPrix() * quantite;
                if (stock.getQuantite(b) < quantite || montant < prixTotal) {
                    return null;
                }
                stock.retirerBoisson(b, quantite);
                portefeuille.insererMontant(prixTotal);
                Transaction t = new Transaction(new Date(), b, prixTotal);
                journal.ajouterTransaction(t);
                return t;
            }
        }
        return null;
    }

    public void rechargerBoisson(String nom, int quantite, double prix) {
        Boisson boisson = null;
        for (Boisson b : stock.listerBoissons()) {
            if (b.getNom().equalsIgnoreCase(nom)) {
                boisson = b;
                break;
            }
        }
        if (boisson == null) {
            boisson = new Boisson(nom, prix);
        }
        stock.ajouterBoisson(boisson, quantite);
    }

}
