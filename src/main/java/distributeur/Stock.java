package distributeur;

import java.util.*;

public class Stock {
    private Map<Boisson, Integer> boissons = new HashMap<>();

    public void ajouterBoisson(Boisson boisson, int quantite){
        if (quantite < 0){
            return;
        }
        boissons.put(boisson, boissons.getOrDefault(boisson, 0) + quantite);
    }

    public boolean retirerBoisson(Boisson boisson, int quantite) {
        int quantiteActuelle = boissons.getOrDefault(boisson, 0);
        if (quantiteActuelle >= quantite && quantite > 0) {
            boissons.put(boisson, quantiteActuelle - quantite);
            return true;
        }
        return false;
    }

    public int getQuantite(Boisson boisson){
        return boissons.getOrDefault(boisson, 0);
    }

    public List<Boisson> listerBoissons() {
        return new ArrayList<>(boissons.keySet());
    }
}
