package test;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;

public class Payment {
    public static void main(String[] args) {
// Set your secret key here
        Stripe.apiKey = "sk_test_51Oot3iRv3sfH9WMzrcEL8HyS1HFAWVjrKjuK1EsEaXZp2ZWZQe5Rqqz3Yiau2c2MySbZHCDFF8C3pyc24vBna2wi00mT4IIAmb";

        try {
// Retrieve your account information
            Account account = Account.retrieve();
            System.out.println("Account ID: " + account.getId());
// Print other account information as needed
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}