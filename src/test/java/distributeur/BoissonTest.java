package distributeur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BoissonTest {
    private Boisson boisson;

    @Before
    public void initialisation(){
        boisson = new Boisson("coca-cola", 1200);
    }

    @Test
    public void testInstanciationBoisson(){
        assertNotNull("L'intance n'est pas crée", boisson);
    }

    @Test
    public void testGetNom(){
        assertEquals("le prix est incorrect", 1200, boisson.getPrix(), 0.001);
    }

    @Test
    public void testGetPrix(){
        assertEquals("le prix est incorrect", 1200, boisson.getPrix(), 0.001);
    }

    @Test
    public void testSetNom(){
        boisson.setNom("coca-cola");
        assertEquals("le nom est incorect", "coca-cola", boisson.getNom());
    }

    @Test
    public void testSetPrix(){
        boisson.setPrix(1200);
        assertEquals("le prix est incorrect", 1200, boisson.getPrix(), 0.001);
    }
}
