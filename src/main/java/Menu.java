//Menu Class

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    public static void showMenu() {


        Scanner scanner = new Scanner(System.in);
        Ledger Ledger = new Ledger();
        boolean running = true;
        Ledger.loadTransactionsFromFile();


        while (running) {
            System.out.println("Welcome to the Accounting Ledger Application");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.printf("Please choose one of the options: ");
            String inputLine = scanner.nextLine();
            String UpperCase = inputLine.toUpperCase();
            char choice = UpperCase.charAt(0);//grabbing the first char only


            switch (choice) {
                case 'D':
                    System.out.printf("Please enter your Deposit amount: ");
                    double Depositamount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.printf("Please enter the deposit description: ");
                    String DepositDescription = scanner.nextLine();

                    System.out.printf("Please enter the deposit vendor: ");
                    String DepositVendor = scanner.nextLine();

                    Ledger.addDeposit(DepositDescription, DepositVendor, Depositamount);
                    break;

                case 'P':
                    System.out.print("Please enter your Payment amount: ");
                    double PayAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Please enter the Payment description: ");
                    String PayDescription = scanner.nextLine();

                    System.out.printf("Please enter the Payment vendor: ");
                    String PayVendor = scanner.nextLine();

                    Ledger.makePayment(PayDescription, PayVendor, PayAmount);
                    break;

                case 'L':
                    boolean LedgerRunning = true;
                    while (LedgerRunning) {
                        System.out.println("Ledger: ");
                        System.out.println("A) All transaction history");
                        System.out.println("D) Deposits into Account");
                        System.out.println("P) Payments out of Account");
                        System.out.println("R) Reports Screen");
                        System.out.println("H) Go back to Home Page");
                        System.out.printf("Please choose one of the options above: ");
                        String LedgerInput = scanner.nextLine();
                        String LedgerUpperCase = LedgerInput.toUpperCase();
                        char LedgerChoice = LedgerUpperCase.charAt(0);

                        switch (LedgerChoice) {
                            case 'A':
                                Ledger.displayAll();
                                break;

                            case 'D':
                                Ledger.displayDeposits();
                                break;

                            case 'P':
                                Ledger.displayPayments();
                                break;

                            case 'R':
                                boolean ReportsChoiceRunning = true;
                                while(ReportsChoiceRunning){

                                System.out.println("Reports Screen");
                                System.out.println("1) Month to Date: ");
                                System.out.println("2) Previous Month");
                                System.out.println("3) Year to Date");
                                System.out.println("4) Previous Year");
                                System.out.println("5) Search by Vendor");
                                System.out.println("6) Custom Search");
                                System.out.println("0) Go back to ledger page");
                                System.out.printf("Please choose on the options above: ");

                                int ReportsChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (ReportsChoice) {
                                    case 1:
                                        System.out.println("Month to date");
                                        Ledger.MonthToDate();
                                        break;
                                    case 2:
                                        System.out.println("Previous month");
                                        Ledger.LastMonth();
                                        break;
                                    case 3:
                                        System.out.println("Year to Date");
                                        Ledger.YearToDate();
                                        break;
                                    case 4:
                                        System.out.println("Previous year");
                                        Ledger.LastYear();
                                        break;
                                    case 5:
                                        System.out.printf("Please enter vendor name: ");
                                        String vendor = scanner.nextLine();

                                        Ledger.SearchByVendor(vendor);
                                        break;
                                    case 6:
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
                                        System.out.println("Custom Search");

                                        System.out.printf("Please enter the start Date(YYYY-MM-DD) or press Enter to skip: ");
                                        String StartDateChoice = scanner.nextLine();
                                        System.out.printf("Please enter the end Date(YYYY-MM-DD) or press Enter to skip: ");
                                        String EndDateChoice = scanner.nextLine();
                                        System.out.printf("Please enter the description of transaction or press Enter to skip: ");
                                        String descriptionChoice = scanner.nextLine();
                                        System.out.println("Please enter the vendor of transaction or press Enter to skip: ");
                                        String vendorChoice = scanner.nextLine();
                                        System.out.printf("Please enter the amount of transaction or press Enter to skip: ");
                                        double amountChoice = scanner.nextDouble();

                                        break;
                                    case 0:
                                        System.out.println("Back to ledger page");
                                        ReportsChoiceRunning = false;
                                        break;

                                    default:
                                        System.out.println("Invalid choice, try again");
                                    }
                                }
                                break;
                            case 'H':
                                System.out.println("Back to home page");
                                LedgerRunning = false;
                                break;
                        }
                    }
                        break;
                        case 'X':
                            System.out.println("Exit");
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice, try again please");
            }
        }
    }
}
