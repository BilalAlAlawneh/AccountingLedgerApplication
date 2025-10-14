
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.*;
public class Ledger {
    private final List<Transactions> transactionslist = new ArrayList<>();

    public void addDeposit(String description, String vendor, double amount) {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);

        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        Transactions newDeposit = new Transactions(amount, date, formattedTime, description, vendor);

        transactionslist.add(newDeposit);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(newDeposit.toString());
            writer.newLine(); // move to the next line
            System.out.println("Deposit added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void makePayment(String description, String vendor, double amount) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);

        if (amount >= 0) {
            System.out.println("Payment amount must be negative.");
            return;
        }

        Transactions newPayment = new Transactions(amount, date, formattedTime, description, vendor);
        transactionslist.add(newPayment);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(newPayment.toString());
            writer.newLine(); // move to the next line
            System.out.println("Payment payed successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void displayAll() {
        if (this.transactionslist.isEmpty()) {
            System.out.println("Sorry, your transaction list is empty");
        } else {
            System.out.println("Transaction List: ");
            for (Transactions t : this.transactionslist) {
                System.out.println(t);
            }
        }
    }

    public void displayDeposits() {
        if (this.transactionslist.isEmpty()) {
            System.out.println("Sorry, your transaction list is empty");
        } else {
            System.out.println("Deposit List: ");
            for (Transactions t : this.transactionslist) {
                if (t.getAmount() > 0) {
                    System.out.println(t);

                }
            }
        }
    }

    public void displayPayments() {
        if (this.transactionslist.isEmpty()) {
            System.out.println("Sorry, your transaction list is empty");
        } else {
            System.out.println("Payment List: ");
            for (Transactions t : this.transactionslist) {
                if (t.getAmount() < 0) {
                    System.out.println(t);
                }
            }
        }
    }

    public void MonthToDate(){
        LocalDate today = LocalDate.now();
        LocalDate FirstofMonth = today.withDayOfMonth(1);

        for(Transactions t: this.transactionslist){
            LocalDate getDate = t.getDate();
            if((getDate.isEqual(FirstofMonth) || getDate.isAfter(FirstofMonth) && getDate.isBefore(today.plusDays(1)))){
                System.out.println(t);
            }
        }
    }
}

