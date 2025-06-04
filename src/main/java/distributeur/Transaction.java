package distributeur;

import java.util.Date;

public class Transaction {
    private Date date;
    private Boisson boisson;
    private double montant;

    public Transaction(Date date, Boisson boisson, double montant) {
        this.date = date;
        this.boisson = boisson;
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boisson getBoisson() {
        return boisson;
    }

    public void setBoisson(Boisson boisson) {
        this.boisson = boisson;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
