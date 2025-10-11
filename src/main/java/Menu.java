//Menu Class

import java.util.Scanner;

public class Menu {
    public static void showMenu(){
        Scanner scanner = new Scanner(System.in);
        Ledger Ledger = new Ledger();
        boolean running = true;

        while(running){
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
                    System.out.print("Please enter your Deposit amount: ");
                    double Depositamount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Please enter the deposit description");
                    String DepositDescription = scanner.nextLine();

                    System.out.println("Please enter the deposit vendor");
                    String DepositVendor = scanner.nextLine();

                    Ledger.addDeposit(DepositDescription, DepositVendor, Depositamount);
                    break;
                case 'P':
                    System.out.print("Please enter your Deposit amount: ");
                    double Payamount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Please enter the deposit description");
                    String PayDescription = scanner.nextLine();

                    System.out.println("Please enter the deposit vendor");
                    String PayVendor = scanner.nextLine();

                    Ledger.addDeposit(PayDescription, PayVendor, Payamount);
                    break;
                case 'L':
                    System.out.println("Ledger: ");
                    System.out.println("A) All transaction history");
                    System.out.println("D) Deposits into Account");
                    System.out.println("P) Payments out of Account");
                    System.out.println("R) Reports Screen");
                    String LedgerInput = scanner.nextLine();
                    String LedgerUpperCase = LedgerInput.toUpperCase();
                    char LedgerChoice = LedgerUpperCase.charAt(0);

                    switch (LedgerChoice){
                        case 'A':
                            Ledger.displayAll();
                            break;
                        case 'D':
                            System.out.println("Deposits");
                            break;
                        case 'P':
                            System.out.println("Payments");
                            break;
                        case 'R':
                            System.out.println("Reports Screen");
                    }

                    break;
                case 'X':
                    System.out.println("Exit");
                    running = false;
                    break;
                default:
                    System.out.println("⚠️ Invalid choice, try again");
            }
        }
    }
}
