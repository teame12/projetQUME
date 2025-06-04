package distributeur;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DistributeurAutomatiqueTest {

    private DistributeurAutomatique d1;

    @Before
    public void initialiser(){
        d1 = new DistributeurAutomatique();

    }

    @Test
    public void testRechargerBoisson(){
        d1.rechargerBoisson("Fanta", 5, 300);
        int quantiteAvant = d1.getStock().getQuantite(new Boisson("Fanta", 300));
        d1.rechargerBoisson("Fanta", 3, 300);
        int quantiteApres = d1.getStock().getQuantite(new Boisson("Fanta", 300));
        assertEquals(quantiteAvant + 3, quantiteApres);
    }

    @Test
    public  void testVoirListeBoisson(){
        d1.rechargerBoisson("Fanta", 5, 300);
        d1.rechargerBoisson("Sprite", 3, 300);

        List<Boisson> listBoissonsDistributeur = d1.consulterBoissons();
        List<Boisson> listBoissonsStock = d1.getStock().listerBoissons();

        assertEquals(listBoissonsStock.size(), listBoissonsDistributeur.size());
        assertTrue(listBoissonsDistributeur.containsAll(listBoissonsStock));
    }

    @Test
    public void testAcheterBoissonAvecMontantSuffisantEtStockDispo(){
        d1.rechargerBoisson("Fanta", 5, 300);

        double soldeIni = d1.getPortefeuille().getMontant();
        Transaction t1 = d1.acheterBoisson("Fanta", 2, 600);

        assertNotNull(t1);
        assertEquals(600, t1.getMontant(), 0.001);
        assertEquals(soldeIni + 600, d1.getPortefeuille().getMontant(), 0.001);
    }

    @Test
    public void testAcheterBoissonAvecMontantInsufisant(){
        d1.rechargerBoisson("Fanta", 5, 300);
        Transaction t1 = d1.acheterBoisson("Fanta", 2, 400);

        assertNull(t1);
        assertEquals(0, d1.getPortefeuille().getMontant(), 0.001);
    }

    @Test
    public void testAcheterBoissonEnRuptureDeStock(){
        d1.rechargerBoisson("Fanta", 1, 300);
        Transaction t1 = d1.acheterBoisson("Fanta", 1, 300);
        assertNotNull(t1);
        assertEquals(0, d1.getStock().getQuantite(new Boisson("Fanta", 300)));
        Transaction t2 = d1.acheterBoisson("Fanta", 1, 300);
        assertNull(t2);
        assertEquals(0, d1.getStock().getQuantite(new Boisson("Fanta", 300)));
    }

    @Test
    public void testAcheterBoissonNonExistante(){
        Transaction transaction = d1.acheterBoisson("Pepsi", 1, 500);
        assertNull(transaction);
        assertEquals(0, d1.getPortefeuille().getMontant(), 0.001);

    }

    @Test
    public void testAcheterBoissonAvecQuantiteNegative() {
        d1.rechargerBoisson("Fanta", 5, 300);
        Transaction t = d1.acheterBoisson("Fanta", -2, 600);
        assertNull(t);
        assertEquals(5, d1.getStock().getQuantite(new Boisson("Fanta", 300)));
    }
}
