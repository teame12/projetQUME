package distributeur;

import java.util.*;

public class JournalDeVente {
    private List<Transaction> transactions = new ArrayList<>();

    public void ajouterTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> listerTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
