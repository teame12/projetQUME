package distributeur;

import java.util.Objects;

public class Boisson {
    private String nom;
    private double prix;

    public Boisson(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boisson boisson = (Boisson) o;
        return Double.compare(boisson.prix, prix) == 0 &&
                nom.equalsIgnoreCase(boisson.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom.toLowerCase(), prix);
    }

}
