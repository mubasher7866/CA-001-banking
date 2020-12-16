// Name : Mubasher Zeb Khan
// Student Number : 21694

package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingApplication {

    public static void main(String[] args) throws IOException{
        // TODO code application logic here

        // please change the path according to system.
        FileWriter file=new FileWriter("C:\\Users\\Mubasher\\Desktop\\Customers.txt",true);

        Scanner readUserType=new Scanner(System.in);
        Scanner readEmployeePin=new Scanner(System.in);
        Scanner readEmployeeAction=new Scanner(System.in);
        Scanner readCustomerDetails=new Scanner(System.in);
        ArrayList<Customer> list=new ArrayList<>();
        ArrayList list1=new ArrayList<>();

        int userType,employeeAction,loop = 0;
        String pin,firstName = null,lastName,email,line = null,an = null;
        Employee employee=new Employee();
        while(loop!=-1){

            System.out.println("-------------------------------------------");
            System.out.println("PLease login to you account!");
            System.out.println("1: Employee");
            System.out.println("2: Customer");
            System.out.println("3: Close");
            System.out.println("-------------------------------------------");
            System.out.print("Enter 1 or 2 to select user type: ");

            userType=readUserType.nextInt();
            if(userType==1){
                System.out.println("Account Type: Employee");
                System.out.print("Enter your pin: ");
                pin=readEmployeePin.nextLine();
                if(!pin.isEmpty()){
                    if(employee.pin.contentEquals(pin)){
                        System.out.println("Login Successful!\n");
                        while(userType!=3){

                            System.out.println("---- Menu ----");
                            System.out.println("1: Create Customer Account");
                            System.out.println("2: Delete Customer Account");
                            System.out.println("3: View Customer Details");
                            System.out.println("4: Add money to customer's account");
                            System.out.println("5: Withdraw money from customer's account");
                            System.out.println("6: View Account Details");
                            System.out.println("7: Log Out");

                            System.out.print("Select the action you want to perfotm: ");
                            employeeAction=readEmployeeAction.nextInt();
                            if(employeeAction==1){
                                System.out.println("Create Customer Account!");
                                System.out.println("---- Enter Customer Details ----");
                                System.out.print("Enter First Name: ");
                                firstName=readCustomerDetails.nextLine();
                                System.out.print("Enter Last Name: ");
                                lastName=readCustomerDetails.nextLine();
                                System.out.print("Enter Email: ");
                                email=readCustomerDetails.nextLine();

                                String f=firstName.substring(0,1);
                                String l=lastName.substring(0,1);

                                int position1=findPosition(f.charAt(0));
                                int position2=findPosition(l.charAt(0));

                                an=firstName.substring(0,1)+lastName.substring(0,1)+"-"+Math.addExact(firstName.length(), lastName.length())+"-"+String.valueOf(position1)+"-"+String.valueOf(position2);
                                pin=String.valueOf(position1)+String.valueOf(position2);
                                // please change the path according to system.
                                FileWriter file1=new FileWriter("C:\\Users\\Mubasher\\Desktop\\Customers.txt",true);
                                FileWriter file2=new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+an+"-savings.txt",true);
                                FileWriter file3=new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+an+"-current.txt",true);

                                list.add(new Customer(firstName,lastName,email,pin,an));
                                file1.write("\nName: "+firstName+" "+lastName+"\nEmail: "+email+"\nPin: "+pin+"\nAccount Number: "+an+"\n");
                                file1.write("-----------------------\n");
                                file2.write(0);
                                file3.write(0);
                                file1.close();
                                file2.close();
                                file3.close();
                                System.out.println("---- Account Created Successfully! ----");
                            }else if(employeeAction==2){
                                System.out.println("Delete Customer Account!");
                            }else if(employeeAction==3){

                                System.out.println("View Customer Details!");
                                System.out.println("---- Details ----");
                                FileReader fr =
                                        // please change the path according to system.
                                        new FileReader("C:\\Users\\Mubasher\\Desktop\\Customers.txt");
                                BufferedReader br=new BufferedReader(fr);
                                StringBuilder sb = new StringBuilder();
                                line = br.readLine();

                                while (line != null) {
                                    sb.append(line);
                                    sb.append(System.lineSeparator());
                                    line = br.readLine();
                                    if(line!=null){
                                        System.out.println(line);
                                    }else{
                                        break;
                                    }
                                }


                            }else if(employeeAction==4){
                                System.out.println("1: Add Money to savings account");
                                System.out.println("2: Add money to current account");
                                int choice;
                                Scanner choice1=new Scanner(System.in);
                                choice=choice1.nextInt();
                                Scanner number=new Scanner(System.in);

                                if(choice==1){
                                    System.out.print("Enter the account Number: ");
                                    String number1=number.nextLine();

                                    try{
                                        // please change the path according to system.
                                        try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt",true)) {
                                            System.out.print("Enter the amount to add: ");
                                            Scanner amount=new Scanner(System.in);
                                            int amount1=amount.nextInt();
                                            fileNew.write("\n"+String.valueOf(amount1));
                                            FileReader fr =
                                                    // please change the path according to system.
                                                    new FileReader("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt");
                                            BufferedReader br=new BufferedReader(fr);
                                            StringBuilder sb = new StringBuilder();
                                            line = br.readLine();

                                            while (line != null) {
                                                sb.append(line);
                                                sb.append(System.lineSeparator());
                                                line = br.readLine();
                                                if(line!=null){
                                                    int total=Integer.valueOf(line)+amount1;
                                                    fileNew.write("\n"+String.valueOf(total));
                                                }else{
                                                    break;
                                                }

                                            }
                                            // please change the path according to system.
                                            Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt");
                                            List<String> allLines = Files.readAllLines(path);

                                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                            LocalDateTime now = LocalDateTime.now();

                                            // please change the path according to system.
                                            FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-savings.txt",true);
                                            transactions.write("\n"+dtf.format(now)+"\t"+"Add\t"+amount1+"\t"+allLines.get(allLines.size()-2));
                                            transactions.close();
                                        }




                                    }catch(Exception ie){
                                        System.err.println("No such file found!");
                                    }
                                }else if(choice==2){
                                    System.out.print("Enter the account Number: ");
                                    String number1=number.nextLine();

                                    try{
                                        // please change the path according to system.
                                        try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt",true)) {
                                            System.out.print("Enter the amount to add: ");
                                            Scanner amount=new Scanner(System.in);
                                            int amount1=amount.nextInt();
                                            fileNew.write("\n"+String.valueOf(amount1));
                                            FileReader fr =
                                                    // please change the path according to system.
                                                    new FileReader("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt");
                                            BufferedReader br=new BufferedReader(fr);
                                            StringBuilder sb = new StringBuilder();
                                            line = br.readLine();

                                            while (line != null) {
                                                sb.append(line);
                                                sb.append(System.lineSeparator());
                                                line = br.readLine();
                                                if(line!=null){
                                                    int total=Integer.valueOf(line)+amount1;
                                                    fileNew.write("\n"+String.valueOf(total));
                                                }else{
                                                    break;
                                                }

                                            }
                                            // please change the path according to system.
                                            Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt");
                                            List<String> allLines = Files.readAllLines(path);
                                            System.out.println(allLines.get(allLines.size()-1));

                                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                            LocalDateTime now = LocalDateTime.now();

                                            // please change the path according to system.
                                            FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-current.txt",true);
                                            transactions.write("\n"+dtf.format(now)+"\t"+"Add\t"+amount1+"\t"+allLines.get(allLines.size()-1));

                                        }

                                    }catch(Exception ie){
                                        System.err.println("No such file found!");
                                    }

                                }else{
                                    System.out.println("No such option present");

                                }

                            }else if(employeeAction==5){

                                System.out.println("1: Withdraw money from savings account");
                                System.out.println("2: Withdraw money from current account");
                                int choice;
                                Scanner choice1=new Scanner(System.in);
                                choice=choice1.nextInt();
                                if(choice==1){
                                    Scanner number=new Scanner(System.in);
                                    System.out.print("Enter the account Number you waht to withdraw from: ");
                                    String number1=number.nextLine();
                                    try{
                                        // please change the path according to system.
                                        try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt",true)) {
                                            System.out.print("Enter the amount to withdraw: ");
                                            Scanner amount=new Scanner(System.in);
                                            int amount1=amount.nextInt();
                                            // please change the path according to system.
                                            Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt");
                                            List<String> allLines = Files.readAllLines(path);
                                            int withdrawl=Integer.valueOf(allLines.get(allLines.size()-1))-amount1;
                                            fileNew.write("\n"+String.valueOf(withdrawl));
                                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                            LocalDateTime now = LocalDateTime.now();
                                            //System.out.println(dtf.format(now));
                                            // please change the path according to system.
                                            FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-savings.txt",true);
                                            transactions.write("\n"+dtf.format(now)+"\t"+"Withdrawl\t"+amount1+"\t"+withdrawl);
                                        }




                                    }catch(Exception ie){
                                        System.err.println("No such file found!");
                                    }

                                }else if(choice==2){
                                    Scanner number=new Scanner(System.in);
                                    System.out.print("Enter the account Number you waht to withdraw from: ");
                                    String number1=number.nextLine();
                                    try{
                                        // please change the path according to system.
                                        try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt",true)) {
                                            System.out.print("Enter the amount to withdraw: ");
                                            Scanner amount=new Scanner(System.in);
                                            int amount1=amount.nextInt();
                                            // please change the path according to system.
                                            Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt");
                                            List<String> allLines = Files.readAllLines(path);
                                            int withdrawl=Integer.valueOf(allLines.get(allLines.size()-1))-amount1;
                                            fileNew.write("\n"+String.valueOf(withdrawl));
                                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                            LocalDateTime now = LocalDateTime.now();

                                            // please change the path according to system.
                                            FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-current.txt",true);
                                            transactions.write("\n"+dtf.format(now)+"\t"+"Withdrawl\t"+amount1+"\t"+withdrawl);

                                        }




                                    }catch(Exception ie){
                                        System.err.println("No such file found!");
                                    }

                                }


                            }

                            else if(employeeAction==6){
                                Scanner number=new Scanner(System.in);
                                System.out.print("Enter the account Number: ");
                                String number1=number.nextLine();
                                // please change the path according to system.
                                Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt");
                                List<String> allLines = Files.readAllLines(path);
                                // please change the path according to system.
                                Path path1 = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt");
                                List<String> allLines1 = Files.readAllLines(path1);


                                System.out.println("Savings Account: "+allLines.get(allLines.size()-1));
                                System.out.println("Current Account:"+allLines1.get(allLines1.size()-1));

                            }else if(employeeAction==7){
                                System.out.println("You have logged out!");
                                break;

                            }

                        }


                    }else{
                        System.out.println("Your pin is incorrect!");
                    }
                }

            }else if(userType==2){

                loginCustomer(firstName,line,list1);

            }else if(userType==3){
                file.close();
                System.out.println("Changes Saved Successfully!");
                break;

            }

        }


    }
    public static int findPosition(char inputLetter)
    {
        // converting input letter in to uniform case.
        char inputLetterToLowerCase= Character.toLowerCase(inputLetter);
        // COnverting chat in to its ascii value
        int asciiValueOfinputChar= (int)inputLetterToLowerCase;
        // ASCII value of lower case letters starts from 97
        int position= asciiValueOfinputChar-96;
        return position;

    }
    public static void loginCustomer(String firstName,String line, ArrayList list1) throws FileNotFoundException, IOException{
        Scanner readCustomerDetails=new Scanner(System.in);
        System.out.print("Enter First Name: ");
        firstName=readCustomerDetails.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber=readCustomerDetails.nextLine();

        System.out.print("Enter Pin Code: ");
        String pin1=readCustomerDetails.nextLine();

        FileReader fr =
                // please change the path according to system.
                new FileReader("C:\\\\Users\\\\Mubasher\\Desktop\\Customers.txt");
        BufferedReader br=new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();


        while ((line=br.readLine()) != null) {
            String s[]=line.split(" ");
            for(int i=0;i<s.length;i++){
                list1.add(s[i]);
            }
            if(list1.contains(firstName)&& list1.contains(accountNumber) && list1.contains(pin1)){
                Scanner input=new Scanner(System.in);
                System.out.println("Welcome "+firstName+"\n");
                System.out.println("Select the action you want to perform: \n");
                System.out.println("1: VIew transaction history");
                System.out.println("2: Add Money");
                System.out.println("3: Withdraw Money");
                System.out.print("Enter the option:");
                int option=input.nextInt();
                if(option==1){
                    transactionHistory();
                }else if(option==2){
                    addmoney();
                }else if(option==3){
                    withdrawMoney();
                }else {
                    System.out.println("Invalid Option entered!");
                }


                break;
            }
        }

    }
    public static int filereader(Scanner textfile)
    {
        int sum = 0;

        while (textfile.hasNextInt()) {
            sum += textfile.nextInt();
        }


        return sum;
    }
    public static void transactionHistory() throws IOException{
        String line,line1;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the account Number:");
        String an=input.nextLine();
        // please change the path according to system.
        FileReader transactionsCurrent = new FileReader("C:\\Users\\Mubasher\\Desktop\\"+an+"-transactions-current.txt");



        BufferedReader br=new BufferedReader(transactionsCurrent);
        StringBuilder sb = new StringBuilder();
        line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
            if(line!=null){
                System.out.println(line);
            }else{
                break;
            }

            // please change the path according to system.
            FileReader transactionsSavings = new FileReader("C:\\Users\\Mubasher\\Desktop\\"+an+"-transactions-savings.txt");



            BufferedReader br1=new BufferedReader(transactionsSavings);
            StringBuilder sb1 = new StringBuilder();
            line1 = br1.readLine();

            while (line1 != null) {
                sb1.append(line1);
                sb1.append(System.lineSeparator());
                line1 = br1.readLine();
                if(line1!=null){
                    System.out.println(line1);
                }else{
                    break;
                }

            }


        }
    }
    public static void addmoney(){
        String line;
        System.out.println("1: Add Money to savings account");
        System.out.println("2: Add money to current account");
        int choice;
        Scanner choice1=new Scanner(System.in);
        choice=choice1.nextInt();
        Scanner number=new Scanner(System.in);

        if(choice==1){
            System.out.print("Enter the account Number: ");
            String number1=number.nextLine();

            try{
                // please change the path according to system.
                try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt",true)) {
                    System.out.print("Enter the amount that you want to add: ");
                    Scanner amount=new Scanner(System.in);
                    int amount1=amount.nextInt();
                    fileNew.write("\n"+String.valueOf(amount1));
                    FileReader fr =
                            // please change the path according to system.
                            new FileReader("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt");
                    BufferedReader br=new BufferedReader(fr);
                    StringBuilder sb = new StringBuilder();
                    line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                        if(line!=null){
                            int total=Integer.valueOf(line)+amount1;
                            fileNew.write("\n"+String.valueOf(total));
                        }else{
                            break;
                        }

                    }
                    // please change the path according to system.
                    Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt");
                    List<String> allLines = Files.readAllLines(path);

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    // please change the path according to system.
                    FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-savings.txt",true);
                    transactions.write("\n"+dtf.format(now)+"\t"+"Add\t"+amount1+"\t"+allLines.get(allLines.size()-2));
                    transactions.close();
                }




            }catch(Exception ie){
                System.err.println("No such file found!");
            }
        }else if(choice==2){
            System.out.print("Enter the account Number: ");
            String number1=number.nextLine();

            try{
                // please change the path according to system.
                try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt",true)) {
                    System.out.print("Enter the amount that you want to add: ");
                    Scanner amount=new Scanner(System.in);
                    int amount1=amount.nextInt();
                    fileNew.write("\n"+String.valueOf(amount1));
                    FileReader fr =
                            // please change the path according to system.
                            new FileReader("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt");
                    BufferedReader br=new BufferedReader(fr);
                    StringBuilder sb = new StringBuilder();
                    line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                        if(line!=null){
                            int total=Integer.valueOf(line)+amount1;
                            fileNew.write("\n"+String.valueOf(total));
                        }else{
                            break;
                        }

                    }
                    // please change the path according to system.
                    Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt");
                    List<String> allLines = Files.readAllLines(path);
                    System.out.println(allLines.get(allLines.size()-1));

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    // please change the path according to system.
                    FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-current.txt",true);
                    transactions.write("\n"+dtf.format(now)+"\t"+"Add\t"+amount1+"\t"+allLines.get(allLines.size()-1));

                }

            }catch(Exception ie){

            }

        }else{
            System.out.println("No such option present");

        }
    }

    private static void withdrawMoney() {

        System.out.println("1: Withdraw money from savings account");
        System.out.println("2: Withdraw money from current account");
        int choice;
        Scanner choice1=new Scanner(System.in);
        choice=choice1.nextInt();
        if(choice==1){
            Scanner number=new Scanner(System.in);
            System.out.print("Enter the account Number you waht to withdraw from: ");
            String number1=number.nextLine();
            try{
                // please change the path according to system.
                try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt",true)) {
                    System.out.print("Enter the amount to withdraw: ");
                    Scanner amount=new Scanner(System.in);
                    int amount1=amount.nextInt();
                    // please change the path according to system.
                    Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-savings.txt");
                    List<String> allLines = Files.readAllLines(path);
                    int withdrawl=Integer.valueOf(allLines.get(allLines.size()-1))-amount1;
                    fileNew.write("\n"+String.valueOf(withdrawl));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    // please change the path according to system.
                    FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-savings.txt",true);
                    transactions.write("\n"+dtf.format(now)+"\t"+"Withdrawl\t"+amount1+"\t"+withdrawl);
                }




            }catch(Exception ie){
                System.err.println("No such file found!");
            }

        }else if(choice==2){
            Scanner number=new Scanner(System.in);
            System.out.print("Enter the account Number you waht to withdraw from: ");
            String number1=number.nextLine();
            try{
                // please change the path according to system.
                try (FileWriter fileNew = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt",true)) {
                    System.out.print("Enter the amount to withdraw: ");
                    Scanner amount=new Scanner(System.in);
                    int amount1=amount.nextInt();
                    // please change the path according to system.
                    Path path = Paths.get("C:\\Users\\Mubasher\\Desktop\\"+number1+"-current.txt");
                    List<String> allLines = Files.readAllLines(path);
                    int withdrawl=Integer.valueOf(allLines.get(allLines.size()-1))-amount1;
                    fileNew.write("\n"+String.valueOf(withdrawl));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    
                    // please change the path according to system.
                    FileWriter transactions = new FileWriter("C:\\Users\\Mubasher\\Desktop\\"+number1+"-transactions-current.txt",true);
                    transactions.write("\n"+dtf.format(now)+"\t"+"Withdrawl\t"+amount1+"\t"+withdrawl);

                }




            }catch(Exception ie){
                System.err.println("No such file found!");
            }

        }


    }
}




