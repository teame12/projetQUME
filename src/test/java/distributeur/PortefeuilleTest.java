package distributeur;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PortefeuilleTest {
    private Portefeuille p1;

    @Before
    public void initialiser(){
        p1 = new Portefeuille();
    }

    @Test
    public void testAjouterMontantPositif(){
        p1.insererMontant(2000);
        assertEquals(2000, p1.getMontant(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAjouterMontantNegatifOuNul(){
        p1.insererMontant(-500);
        p1.insererMontant(0);
    }

    @Test
    public void testRetirerMontant(){
        p1.setMontant(10000);
        boolean retrait = p1.retirerMontant(3000);

        assertTrue(retrait);
        assertEquals(7000, p1.getMontant(), 0.001);
    }

    @Test
    public void testRetirerMontantSupSolde(){
        p1.setMontant(100);
        boolean retrait = p1.retirerMontant(200);
        assertFalse(retrait);
        assertEquals(100, p1.getMontant(), 0.001);
    }

    @Test
    public void testVoirMontantDispo(){
        p1.setMontant(1000);

        p1.insererMontant(1200);

        assertEquals(2200, p1.getMontant(), 0.001);
    }
}
