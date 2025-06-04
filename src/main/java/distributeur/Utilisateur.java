package distributeur;

public class Utilisateur {
    public void consulterBoissons(DistributeurAutomatique distributeur) {
        distributeur.consulterBoissons().forEach(b -> System.out.println(b.getNom() + " - " + b.getPrix() + " €"));
    }

    public Transaction acheterBoisson(DistributeurAutomatique distributeur, String nom, int quantite, double montant) {
        return distributeur.acheterBoisson(nom,quantite, montant);
    }

}
