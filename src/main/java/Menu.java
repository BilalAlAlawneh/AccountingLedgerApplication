//Menu Class

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.lang.Thread;


public class Menu {
    public static void showMenu()throws InterruptedException {


        Scanner scanner = new Scanner(System.in);
        Ledger Ledger = new Ledger();
        boolean running = true;
        Ledger.loadTransactionsFromFile();


        while (running) {
            System.out.println("⚽   Welcome to the ⚽Soccer⚽ Store Ledger System");
            System.out.println("⚽   S) Record Sales Only");
            System.out.println("⚽   E) Record an Expense / Inventory Purchase");
            System.out.println("⚽   L) Ledger");
            System.out.println("⚽   X) Exit");
            System.out.printf("⚽    Please choose one of the options: ");
            String inputLine = scanner.nextLine();
            String UpperCase = inputLine.toUpperCase();
            char choice = UpperCase.charAt(0);//grabbing the first char only


            switch (choice) {
                case 'S':
                    System.out.print("Loading ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print("⚽ ");
                        Thread.sleep(500); // 1 second per ball
                    }
                    System.out.println("\nReady!");

                    System.out.print("Please enter the Sales Amount: ");
                    double Depositamount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.printf("Please enter the Sales Description: ");
                    String DepositDescription = scanner.nextLine();

                    System.out.printf("Please enter the Buyer Name: ");
                    String DepositVendor = scanner.nextLine();

                    Ledger.addDeposit(DepositDescription, DepositVendor, Depositamount);
                    break;

                case 'E':
                    System.out.print("Loading ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print("⚽ ");
                        Thread.sleep(500); // 1 second per ball
                    }
                    System.out.println("\nReady!");


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
                    System.out.print("Loading ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print("⚽ ");
                        Thread.sleep(500); // 1 second per ball
                    }
                    System.out.println("\nReady!");

                    boolean LedgerRunning = true;
                    while (LedgerRunning) {
                        System.out.println("⚽️Soccer⚽ Store Ledger: ");
                        System.out.println("A) All transactions history");
                        System.out.println("D) All Payments to Store");
                        System.out.println("P) All Expenses / Inventory purchases");
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
                                System.out.print("Loading ");
                                for (int i = 0; i < 5; i++) {
                                    System.out.print("⚽ ");
                                    Thread.sleep(500); // 1 second per ball
                                }
                                System.out.println("\nReady!");
                                boolean ReportsChoiceRunning = true;
                                while(ReportsChoiceRunning){

                                System.out.println("Reports Screen");
                                System.out.println("1) Month to Date Transactions");
                                System.out.println("2) Previous Month Transactions");
                                System.out.println("3) Year to Date Transactions");
                                System.out.println("4) Previous Year Transactions");
                                System.out.println("5) Search by Vendor/Buyer");
                                System.out.println("6) Custom Search");
                                System.out.println("0) Go back to ledger page");
                                System.out.printf("Please choose one the options above: ");

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
                                        System.out.print("Loading ");
                                        for (int i = 0; i < 5; i++) {
                                            System.out.print("⚽ ");
                                            Thread.sleep(500); // 1 second per ball
                                        }
                                        System.out.println("\nReady!");

                                        System.out.println("Custom Search");

                                        System.out.printf("Please enter the start Date(YYYY-MM-DD) or press Enter to skip: ");
                                        String StartDateChoice = scanner.nextLine();
                                        System.out.printf("Please enter the end Date(YYYY-MM-DD) or press Enter to skip: ");
                                        String EndDateChoice = scanner.nextLine();
                                        System.out.printf("Please enter the description of transaction or press Enter to skip: ");
                                        String descriptionChoice = scanner.nextLine();
                                        System.out.printf("Please enter the vendor of transaction or press Enter to skip: ");
                                        String vendorChoice = scanner.nextLine();
                                        System.out.printf("Please enter the amount of transaction or press Enter to skip: ");
                                        String amountChoice = scanner.nextLine();

                                        Ledger.CustomSearch(StartDateChoice, EndDateChoice, descriptionChoice, vendorChoice, amountChoice);
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
