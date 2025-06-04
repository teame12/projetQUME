package distributeur;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JournalDeVenteTest {

    private JournalDeVente j1;
    private Boisson b1;
    private  Transaction t1;
    private int taille;
    private int tailleApres;
    List<Transaction> transactions;

    @Before
    public void initialiser(){
        j1 = new JournalDeVente();
        b1 = new Boisson("Fanta", 300);
        t1 = new Transaction(new Date(), b1, 300);
        taille = j1.listerTransactions().size();
        j1.ajouterTransaction(t1);
        transactions = j1.listerTransactions();
        tailleApres = transactions.size();
    }

    @Test
    public void testAjouterTransaction(){
        assertEquals(taille + 1, tailleApres);
        assertTrue(transactions.contains(t1));
    }

    @Test
    public void testVoirListeTransactions(){
        assertEquals(1, transactions.size());
        assertTrue(transactions.contains(t1));
    }
}
