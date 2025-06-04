package distributeur;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StockTest {

    private Stock stock;
    private  Boisson b1;
    private  Boisson b2;


    @Before
    public void initialiser(){
         stock = new Stock();
         b1 = new Boisson("Fanta", 250);
         b2 = new Boisson("Coca-Cola", 300);
    }
    @Test
    public void testAjouterBoissonStock(){
        stock.ajouterBoisson(b1, 10);
        assertEquals(10, stock.getQuantite(b1));
    }

    @Test
    public void testAjouterQuantiteSupBoisson(){
        stock.ajouterBoisson(b1, 10);
        stock.ajouterBoisson(b1, 2);
        assertEquals(12, stock.getQuantite(b1));
    }

    @Test
    public void testRetirerBoisson(){
        stock.ajouterBoisson(b1, 10);
        boolean retrait = stock.retirerBoisson(b1, 1);
        assertTrue(retrait);
    }

    @Test
    public void testRetiterQuantiteTropGrande(){
        stock.ajouterBoisson(b1, 1);
        boolean retrait = stock.retirerBoisson(b1, 3);
        assertFalse(retrait);
    }

    @Test
    public void testVoirQuantiteBoisson(){
        stock.ajouterBoisson(b1, 12);
        assertEquals(12, stock.getQuantite(b1));
    }

    @Test
    public void testListeBoissonDisponible(){
        stock.ajouterBoisson(b1, 2);
        stock.ajouterBoisson(b2, 3);

        List<Boisson> listeBoissons = stock.listerBoissons();

        assertEquals(2, listeBoissons.size());
        assertTrue(listeBoissons.contains(b1));
        assertTrue(listeBoissons.contains(b1));
    }

    @Test
    public void testAjouterQuantiteNegative(){
        stock.ajouterBoisson(b1, 12);
        stock.ajouterBoisson(b1, -1);
        assertEquals(12, stock.getQuantite(b1));
    }

    @Test
    public void testVoirQteBoissonNonExistante(){
        assertEquals(0, stock.getQuantite(b2));
    }
}
