
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.*;
import java.util.stream.Stream;
import static java.util.Locale.filter;

public class Ledger {

    private final List<Transactions> transactionslist = new ArrayList<>();
    private static final String FILE_PATH = "src/main/resources/transactions.csv";

    public void loadTransactionsFromFile() {
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            lines
                    .filter(line -> !line.trim().isEmpty())
                    .map(line -> line.split("\\|"))
                    .filter(parts -> parts.length == 5)
                    .map(parts -> new Transactions(
                            Double.parseDouble(parts[4].trim()),
                            LocalDate.parse(parts[0].trim()),
                            parts[1].trim(), parts[2].trim(), parts[3].trim()))
                    .forEach(transactionslist::add);

            System.out.println("Loaded " + transactionslist.size() + " transactions from file.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
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
            amount *= -1;
        }

        Transactions newPayment = new Transactions(amount, date, formattedTime, description, vendor);
        transactionslist.add(newPayment);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(newPayment.toString());
            writer.newLine(); // move to the next line
            System.out.println("Payment payed successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void displayAll() {
        if (transactionslist.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            transactionslist.forEach(System.out::println);
        }
    }

    public void displayDeposits() {
        transactionslist.stream()
                .filter(t -> t.getAmount() > 0)
                .forEach(System.out::println);
    }

    public void displayPayments() {
        transactionslist.stream()
                .filter(t -> t.getAmount() < 0)
                .forEach(System.out::println);
    }

    public void MonthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate FirstofMonth = today.withDayOfMonth(1);

        for (Transactions t : transactionslist) {
            LocalDate getDate = t.getDate();
            if ((getDate.isEqual(FirstofMonth) || getDate.isAfter(FirstofMonth) && getDate.isBefore(today.plusDays(1)))) {
                System.out.println(t);
            }
        }
    }

    public void LastMonth() {
        LocalDate LastMonth = LocalDate.now().minusMonths(1);
        LocalDate FirstofLastmonth = LastMonth.withDayOfMonth(1);
        LocalDate LastDay = LastMonth.withDayOfMonth(LastMonth.lengthOfMonth());

        for (Transactions t : transactionslist) {
            LocalDate getDate = t.getDate();
            if (((getDate.isEqual(FirstofLastmonth) || getDate.isAfter(FirstofLastmonth)) && getDate.isBefore(LastDay.plusDays(1)))) {
                System.out.println(t);
            }
        }
    }

    public void YearToDate() {
        LocalDate today = LocalDate.now();
        LocalDate FirstofYear = today.withDayOfYear(1);

        for (Transactions t : transactionslist) {
            LocalDate getDate = t.getDate();
            if ((getDate.isEqual(FirstofYear) || getDate.isAfter(FirstofYear) && getDate.isBefore(today.plusDays(1)))) {
                System.out.println(t);
            }
        }
    }

    public void LastYear() {
        LocalDate LastYear = LocalDate.now().minusYears(1);
        LocalDate FirstofLastYear = LastYear.withDayOfYear(1);
        LocalDate LastDay = LastYear.withDayOfYear(LastYear.lengthOfYear());

        for (Transactions t : transactionslist) {
            LocalDate getDate = t.getDate();
            if (((getDate.isEqual(FirstofLastYear) || getDate.isAfter(FirstofLastYear)) && getDate.isBefore(LastDay.plusDays(1)))) {
                System.out.println(t);
            }
        }
    }

    public void SearchByVendor(String VendorChoice) {
        transactionslist.stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(VendorChoice))
                .forEach(System.out::println);
    }

    public void CustomSearch(String StartDate, String EndDate, String Description, String Vendor, String Amount) {
        LocalDate start = null;
        LocalDate end = null;
        String desc = null;
        String ven = null;
        String amnt = null;


        if (!StartDate.trim().isEmpty()) {
            start = LocalDate.parse(StartDate.trim());
        }

        if (!EndDate.trim().isEmpty()) {
            end = LocalDate.parse(EndDate.trim());

        }

        if (!Description.trim().isEmpty()) {
            desc = Description.trim();
        }

        if (!Vendor.trim().isEmpty()) {
            ven = Vendor.trim();
        }

        if (!Amount.trim().isEmpty()) {
            amnt = Amount.trim();
        }

        final LocalDate fstart = start;
        final LocalDate fend = end;
        final String fdesc = desc;
        final String fven = ven;
        final String famnt = amnt;


        transactionslist.stream()
                .filter(t -> fstart == null || !t.getDate().isBefore(fstart))
                .filter(t -> fend == null || !t.getDate().isAfter(fend))
                .filter(t -> fdesc == null || t.getDescription().equalsIgnoreCase(fdesc))
                .filter(t -> fven == null || t.getVendor().equalsIgnoreCase(fven))
                .filter(t -> famnt == null || String.valueOf(t.getAmount()).equals(famnt))
                .forEach(System.out::println);

    }
}

