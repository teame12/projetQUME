package distributeur;

public class Portefeuille {
    private  double montant;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void insererMontant(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant ne doit erte ni negatif ni null");
        }
        this.montant += montant;
    }

    public boolean retirerMontant(double montant) {
        if (montant <= this.montant) {
            this.montant -= montant;
            return true;
        }
        return false;
    }


}
