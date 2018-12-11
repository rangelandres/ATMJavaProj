//Andres Rangel
//CS:3210
//Project1: ATM


package projectatm;

import java.util.*;
import java.util.regex.*;

//primary class ATM
class ATM{
    private int bills;               //Integer of the bills inside the ATM
    private String inputAccNo;       //User's input for Account Number
    private String inputPinNo;       //User's input for Account Pin
    private String inputMainScreen;  //User's input for Account Number
    private String inputWithdrawal;  //User's input withdrawal amount
    private String inputDeposit;     //User's input deposit amount
    private boolean bSwitch;         //if false; code will exit loop keeping user in mainScreen
    
    //Constructor
    public ATM(){
        bills = 500;
    }
    
    Scanner input = new Scanner(System.in);
    
    //"databases" for accounts/pins, and accounts/balance
    //To populate the database with new user check the method "populate"
    HashMap<String, String> accountMap = new HashMap<>();
    HashMap<String, Double> moneyMap = new HashMap<>();

    
    //Runs ATM program until bSwitch == false
    //When user is on main screen and exits from atm, bSwitch will turn false
    public void start(){
        
        //populates databases with accounts and pins
        accDatabase();
        moneyDatabase();
        
        System.out.println("\nWelcome!");
        
        promptAcc(input);      //Prompts user to input their account number
        checkAcc(inputAccNo);  //Checks user's input with "database"
                
        promptPin(input);      //Prompts user to input their pin number
        checkPin(inputPinNo);  //Checks user's input pin with "database"
        
        mainScreen();          //Prompts mainScreen after user has successfully logged in
        optionSelection();     //Takes user's input and selects corresponding action
        
        //keeps user in a loop until they "exit"
        while(bSwitch!=false){
            mainScreen();
            optionSelection();
        }
    }
    
    
    //getter method to retrieve the atm bills
    public int getBills(){
        return bills;
    }    
    
    //Prompts user for account number and returns user input as string
    public String promptAcc(Scanner input){
        System.out.println("\nEnter your account number: ");
        inputAccNo = input.nextLine();
        
        return inputAccNo;
    }

    //method checks if account number entered matches with "database"
    //returns a boolean if there is a match
    public Boolean checkAcc(String accNo){
        Boolean a = accountMap.containsKey(accNo);
        
        if ((accountMap.containsKey(accNo)) == false){
            while(accountMap.containsKey(accNo) == false){
            System.out.println("\nERROR: INVALID ACCOUNT NUMBER");
            System.out.println("Enter your account number: ");
            
            inputAccNo = accNo = input.nextLine();
            }
        }
    return a;
    }
    
    //Prompts user for account number and returns user input as string
    public String promptPin(Scanner input){
        System.out.println("\nEnter your PIN: ");
        inputPinNo = input.nextLine();
        
        return inputPinNo;
    }
    
    //method checks if pin number entered matches with account number
    public Boolean checkPin(String accPin){
        Boolean b = accountMap.containsKey(accPin);
    
        if(!accPin.equals(accountMap.get(inputAccNo))){
            while(!accPin.equals(accountMap.get(inputAccNo))){
            System.out.println("\nERROR: INVALID PIN");
            System.out.println("Enter your PIN: ");
            inputPinNo = accPin = input.nextLine();
            }            
        }
    return b;
    }        
    
    //method mainScreen promompts after user logs in
    //shows user the main screen options
    public void mainScreen(){
        bSwitch = true;
        System.out.println("\n");
        System.out.println("Main Screen: ");
        System.out.println("1 - Balance Inquiry");
        System.out.println("2 - Withdrawal");       
        System.out.println("3 - Deposit");             
        System.out.println("4 - Exit");
        inputMainScreen = input.nextLine();
        
        Pattern p = Pattern.compile("^[1-4]");
        Matcher m = p.matcher(inputMainScreen);
        
        //checks if user has typed an integer from 1-4
        if (m.find() != true){
            System.out.println("\nPLEASE ENTER A DIGIT FROM 1-4");
            pause();
            mainScreen();
        }
    }
    
    //takes input from user and depending on their input, chooses one of the options
    public void optionSelection(){
        try{
            int intValue = Integer.valueOf(inputMainScreen);
            
            switch (intValue) {
                //Balance Inquiry
                case 1:
                    getBalance(inputAccNo);
                    pause();
                    break;
                //Withdrawal
                case 2:
                    withdrawMoney();
                    break;
                //Deposit
                case 3:
                    depositMoney();
                    break;
                //Exit
                case 4:
                    System.out.println("\nTHANK YOU! GOODBYE.");
                    pause();
                    bSwitch = false;
                    break;
                //Checks if user typed a digit from 1-4
                default:
                    System.out.println("\nPlease Enter a digit from 1-4.");
                    pause();
                    break;
            }
        //Checks if user typed anything besides a digit from 1-4        
        }catch( NumberFormatException e ){
            System.out.println("\nPlease Enter a digit.");
            pause();
        }
    }

    //Getter method to retrieve balance from user's account
    public double getBalance(String input){
        System.out.println("\nYour balance is: ");
        System.out.printf("$" + "%.2f", moneyMap.get(input));
        return (moneyMap.get(input));

    }
    
    //method withdraws the amount chosen
    //ERROR 2929 is shown when there is insufficient finds
    //ERROR 4567 is shown when the ATM runs out of bills
    public void withdrawMoney(){
        System.out.println("\nWithdraw: ");
        System.out.println("1 - $20 ");
        System.out.println("2 - $40 ");
        System.out.println("3 - $60 ");
        System.out.println("4 - $100 ");
        System.out.println("5 - $200 ");
        System.out.println("6 - CANCEL ");
        inputWithdrawal = input.nextLine();
        int intValue2 = Integer.valueOf(inputWithdrawal);

        //within each case, checks if user has enough money to withdraw, if not error: 2929
        //also checks if there are enough bills in the atm to successfuly withdraw, if not error: 4567;
        switch (intValue2) {
            //$20
            case 1:
                if (moneyMap.get(inputAccNo)<20){
                    System.out.println("\nERROR 2929: INSUFFICIENT FUNDS.");
                    pause();
                }else if(bills < 1){
                    System.out.println("\nERROR 4567: NOT ENOUGH BILLS IN ATM. SORRY.");
                    pause();
                }else{
                    moneyMap.put(inputAccNo, moneyMap.get(inputAccNo) - 20);
                    bills = bills - 1;
                    System.out.println("\nYOU HAVE WITHDREW $20");
                    pause();
                }
                break;
            //$40
            case 2:
                if (moneyMap.get(inputAccNo)<40){
                    System.out.println("\nERROR 2929: INSUFFICIENT FUNDS.");
                    pause();
                }else if(bills < 2){
                    System.out.println("\nERROR 4567: NOT ENOUGH BILLS IN ATM. SORRY.");
                    pause();
                }else{
                    moneyMap.put(inputAccNo, moneyMap.get(inputAccNo) - 40);
                    bills = bills - 2;
                    System.out.println("\nYOU HAVE WITHDREW $40");
                    pause();
                }
                break;
            //$60
            case 3:
                if (moneyMap.get(inputAccNo)<60){
                    System.out.println("\nERROR 2929: INSUFFICIENT FUNDS.");
                    pause();
                }else if(bills < 3){
                    System.out.println("\nERROR 4567: NOT ENOUGH BILLS IN ATM. SORRY.");
                    pause();
                }else{
                    moneyMap.put(inputAccNo, moneyMap.get(inputAccNo) - 60);
                    bills = bills - 3;                    
                    System.out.println("\nYOU HAVE WITHDREW $60");
                    pause();
                }
                break;
            //$100
            case 4:
                if (moneyMap.get(inputAccNo)<100){
                    System.out.println("\nERROR 2929: INSUFFICIENT FUNDS.");
                    pause();
                }else if(bills < 5){
                    System.out.println("\nERROR 4567: NOT ENOUGH BILLS IN ATM. SORRY.");
                    pause();
                }else{
                    moneyMap.put(inputAccNo, moneyMap.get(inputAccNo) - 100);
                    bills = bills - 5;                    
                    System.out.println("\nYOU HAVE WITHDREW $100");
                    pause();
                }
                break;
            //$200
            case 5:
                if (moneyMap.get(inputAccNo)<200){
                    System.out.println("\nERROR 2929: INSUFFICIENT FUNDS.");
                    pause();
                }else if(bills < 10){
                    System.out.println("\nERROR 4567: NOT ENOUGH BILLS IN ATM. SORRY.");
                    pause();
                }else{
                    moneyMap.put(inputAccNo, moneyMap.get(inputAccNo) - 200);
                    bills = bills - 10;
                    System.out.println("\nYOU HAVE WITHDREW $200");
                    pause();
                }
                break;
            //Cancels transaction
            case 6:
                System.out.println("\nCANCELLED TRANSACTION");
                pause();
                break;
            //Checks if user has not typed a digit from 1-6
            default:
                System.out.println("\nPLEASE ENTER A DIGIT FROM 1-6");
                pause();
                withdrawMoney();
                break;
        }
        
    }
    
    //prompts user the option to deposit money according to specifications
    //divides input by 100 and converts it to a double value
    public void depositMoney(){
        System.out.println("\nEnter a deposit amount('0' to EXIT): ");
        System.out.println("$57.23 would be entered as 5723");
                
        inputDeposit = input.nextLine();
        Pattern num = Pattern.compile("\\D");
        Matcher a2 = num.matcher(inputDeposit);
        
        //checks if user has typed digits
        if (a2.find()){
            System.out.println("PLEASE ENTER DIGIT(S)");
            pause();
            depositMoney();
        //If user types in "0", transactin is cancelled
        }else if("0".equals(inputDeposit)){
            System.out.println("\nCANCELLING TRANSACTION");
            pause();
        //converts user's input from string into double and deposits it into their account
        }else{
            double value = Double.parseDouble(inputDeposit)/100;
            System.out.printf("YOU HAVE DEPOSITED "+"$" + "%.2f", value);
            moneyMap.put(inputAccNo, moneyMap.get(inputAccNo) + value);        
            pause();            
        }
    }  
    
    
    //method pauses code by 1.5 seconds
    public void pause(){
        try {
            Thread.sleep(1500);
            }catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
               }
    }
   

    //method creates databases of accounts
    //(string, string)
    // key = account#, value = pin#
    public void accDatabase(){
        accountMap.put("00001", "00011");
        accountMap.put("00002", "00022");
        accountMap.put("00003", "00033");
        accountMap.put("00004", "00044");       
    }

    
    //key = account#, value = account balance
    public void moneyDatabase(){
        moneyMap.put("00001", 40.50);
        moneyMap.put("00002", 500.00);
        moneyMap.put("00003", 1.00);
        moneyMap.put("00004", 2000.00);       
    }
    
    //Populates an account into bank "database"
    public void populate(String AccNo, String AccPin, Double money){
        moneyMap.put(AccNo, money);
        accountMap.put(AccNo, AccPin);
    }
}


    

public class ProjectATM {
    public static void main(String[] args) {
        ATM test = new ATM();
        test.populate("10000", "00001", 250.00);
        //runs ATM program
        test.start();
    }
}
