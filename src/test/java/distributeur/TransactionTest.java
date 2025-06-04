package distributeur;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionTest {
    private Transaction t1;
    private Boisson b1;
    private Date date;
    private double montant;

    @Before
    public void initialiser(){
        b1 = new Boisson("vimto", 400);
        date = new Date();
        montant = 450;
        t1 = new Transaction(date, b1, montant);
    }

    @Test
    public void testCreerTransaction(){
        assertNotNull(t1);
    }

    @Test
    public void testDateNonNull(){
        assertNotNull(t1.getDate());
    }

    @Test
    public void testVerifierAttributs(){
        assertEquals(date, t1.getDate());
        assertEquals(b1, t1.getBoisson());
        assertEquals(montant, t1.getMontant(), 0.001);
    }
}
