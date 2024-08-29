import java.util.List;

public class BankAccount {


    private String firstName;

    private String lastName;

    private String address;

    private String salutation;

    private double balance;

    private Integer accountNumber;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void displayInfo() {
        System.out.println("Account Holder : "+salutation+firstName+lastName);
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Available Balance :"+balance);
    }

    public boolean withdraw(double withdrawAmount){
        if(withdrawAmount>0){
            if(withdrawAmount<=balance){
                balance=balance-withdrawAmount;
                System.out.println(String.format("%s%s, Withdrawl of Rs.%s is successful.",salutation,firstName,withdrawAmount));
                System.out.println("Available Balance now is: Rs."+balance);
                return true;
            }else {
                System.out.println("Not enough Fund");
            }
        }else {
            System.out.println("Negative amount not allowed");
        }
      return false;
    }

    public boolean deposit(double depositAmount){
        if(depositAmount>0){
            balance = balance+depositAmount;
            System.out.println(String.format("%s%s, Deposit of Rs.%s is successful.",salutation,firstName,depositAmount));
            System.out.println("Available Balance now is: Rs."+balance);
            return  true;
        }else {
            System.out.println("Negative Amount Not Allowed");
        }
        return false;
    }

    public void viewTxnHistory(List<String> txnHistory) {
        System.out.println("Action\t\tAmount\t\tAvailable Balance");
        txnHistory.forEach(System.out::println);
    }
}
