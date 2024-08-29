import java.util.*;

public class BankingSystem {

    private static Map<Integer, List<String>> txnHistory = new HashMap<>();
    private static Map<Integer,BankAccount> accountListMap = new HashMap<>();


    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n");
                System.out.println("*****************************************************************");
                System.out.println("1. Add Account Details");
                System.out.println("2. View Account Details");
                System.out.println("3. Deposit Amount");
                System.out.println("4. Withdraw Amount");
                System.out.println("5. View TXn History");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                BankAccount account = null;
                int accountNumber = 0;
                switch (choice) {
                    case 1:
                        account = grabAccountDetails(scanner);
                        accountListMap.put(account.getAccountNumber(), account);
                        System.out.printf("Congratulations %s%s, your account has been created with account number: %s", account.getSalutation(),
                                account.getFirstName(), account.getAccountNumber());
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("*****************************************************************");
                        System.out.println("Enter account number to view details: ");
                        accountNumber = scanner.nextInt();
                        account = accountListMap.get(accountNumber);
                        if (account == null) {
                            System.out.println("Entered Bank Account Not available");

                        } else account.displayInfo();
                        break;

                    case 3:
                        System.out.println("*****************************************************************");
                        System.out.println("Enter account number : ");
                        accountNumber = scanner.nextInt();
                        account = accountListMap.get(accountNumber);
                        if (account == null) {
                            System.out.println("Entered Bank Account Not available");

                        } else {
                            System.out.println("Enter deposit Amount: ");
                            double depositAmount = scanner.nextDouble();
                            if (account.deposit(depositAmount)) {
                                List<String> txnHistoryList = txnHistory.get(accountNumber);
                                if (txnHistoryList != null) {
                                    txnHistoryList.add(String.format("%s\t\t\t%s\t\t\t%s", "CR", depositAmount, account.getBalance()));
                                    txnHistory.put(accountNumber, txnHistoryList);
                                } else {
                                    List<String> newHistory = new ArrayList<>();
                                    newHistory.add(String.format("%s\t\t\t%s\t\t\t%s", "CR", depositAmount, account.getBalance()));
                                    txnHistory.put(accountNumber, newHistory);
                                }
                            }

                        }

                        break;
                    case 4:
                        System.out.println("*****************************************************************");
                        System.out.println("Enter account number : ");
                        accountNumber = scanner.nextInt();
                        account = accountListMap.get(accountNumber);
                        if (account == null) {
                            System.out.println("Entered Bank Account Not available");

                        } else {
                            System.out.println("Enter withdraw Amount: ");
                            double withdrawAmount = scanner.nextInt();
                            if (account.withdraw(withdrawAmount)) {
                                List<String> txnHistoryList = txnHistory.get(accountNumber);
                                txnHistoryList.add(String.format("%s\t\t\t%s\t\t\t%s", "DR", withdrawAmount, account.getBalance()));
                                txnHistory.put(accountNumber, txnHistoryList);
                            }

                        }
                        break;
                    case 5:
                        System.out.println("*****************************************************************");
                        System.out.println("Enter account number : ");
                        accountNumber = scanner.nextInt();
                        account = accountListMap.get(accountNumber);
                        if (account == null) {
                            System.out.println("Entered Bank Account Not available");

                        } else {
                            List<String> txnHistoryList = txnHistory.get(accountNumber);
                            if (txnHistoryList != null) {
                                account.viewTxnHistory(txnHistoryList);
                            } else {
                                System.out.println("No Transaction Till Now");
                            }

                        }
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);

            scanner.close();
        } catch (Exception exception) {

            System.out.println("Something Went Wrong. Please re-run your application");
        }
    }

    private static BankAccount grabAccountDetails(Scanner scanner) {
        BankAccount bankAccount = new BankAccount();
        System.out.println("*****************************************************************");
        scanner.nextLine();
        System.out.print("Enter your First  name: ");
        bankAccount.setFirstName(scanner.nextLine().trim().toUpperCase()+" ");
        System.out.print("Enter your Last  name: ");
        bankAccount.setLastName(scanner.nextLine().trim().toUpperCase());
        System.out.print("Enter Salutation(Mr/Ms/Mrs) ");
        bankAccount.setSalutation(scanner.nextLine().trim().toUpperCase()+". ");
        Integer accountNumber = grabIntegerRelatedInfo(scanner, " Your Desired AccountNumber");
        bankAccount.setAccountNumber(accountNumber);
        return bankAccount;
    }

    private static int grabIntegerRelatedInfo(Scanner input, String type) {
        int number;
        while (true) {
            System.out.print("Please enter " + type + ": ");
            while (!input.hasNextInt()) {
                System.out.println("Character not allowed, please enter an integer number only.");
                input.next();
                System.out.print("Please Enter" + type + ": ");
            }
            number = input.nextInt();

            if (accountListMap.containsKey(number)) {
                System.out.println(type + " Account Number Already Exist , Please enter unique one.");
            } else {
                break;
            }
        }
        return number;
    }

}