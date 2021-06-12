package org.example;


import org.example.Constants.LoanConstants;
import org.example.Constants.StageConstants;

import java.util.ArrayList;
import java.util.Scanner;

public class LoanProcess implements LoanProcessInterface, LoanConstants, StageConstants {

    private ArrayList<Customer> customers = new ArrayList<>();

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customer) {
        customers.add(customer);

    }

    //Sourcing
    public void start(Customer customer) {


        System.out.println("--------Stage 1/5(SOURCING)--------");

        //SOURCING =1
        customer.setStatus(SOURCING);

        Scanner sc = new Scanner(System.in);

        Validator v = new Validator();

        System.out.print("Enter First name: ");
        customer.getPersonal().setFname(sc.next());
        System.out.print("Enter Last name: ");
        customer.getPersonal().setLname(sc.next());
        System.out.print("Enter Age: ");
        customer.getPersonal().setAge(v.ageValidator(sc.nextInt()));
        System.out.print("Enter Phone Number: ");
        customer.getPersonal().setPhoneNumber(v.phoneNumberValidator(sc.nextLong()));
        System.out.print("Enter emailId: ");
        customer.getPersonal().setEmail(v.emailNumberValidator(sc.next()));
        System.out.println("Enter Address");

        // Values for Address
        System.out.print("Enter House number: ");
        String hno = sc.next();
        System.out.print("Enter Landmark: ");
        String landmark = sc.next();
        System.out.print("Enter City: ");
        String city = sc.next();
        System.out.print("Enter Pincode: ");
        int pinCode = sc.nextInt();
        customer.getPersonal().setAddress(hno, landmark, city, pinCode);

        //Aadhaar ID
        System.out.print("Enter AadhaarId: ");
        customer.getPersonal().setAdhaarId(v.aadharNumberValidator(sc.nextLong()));

        //Pan ID
        System.out.print("Enter PanId: ");
        customer.getPersonal().setPanId(v.pancardValidator(sc.next()));
        if (checkExisting(customer)) {
            System.out.println("User Already Exists");
            return;
        }

        customer.setId(customer);

        //Adding Customer to ArrayList
        setCustomers(customer);

        //STAGE 1 Completed
        System.out.println("Stage 1 (SOURCING) Completed");
        customer.setStatus(QDE);
        ApplicationStatus(customer.getId());
    }

    //Checks if the user already have account in Database
    public boolean checkExisting(Customer customer){

        for(Customer existing: customers) {

            if (customer.getPersonal().getAdhaarId().toString().compareTo(existing.getPersonal().getAdhaarId().toString()) == 0
                    && customer.getPersonal().getPanId().toString().compareTo(existing.getPersonal().getPanId().toString()) == 0)
                return true;

        }
        return false;
    }


    //Quick Data Entry
    public void qde(Customer customer) {


        Scanner s = new Scanner(System.in);
        Validator v = new Validator();
        //Printing Personal Details
        System.out.println("Application Number: " + customer.getId());
        System.out.println("Name: " + customer.getPersonal().getFname().toUpperCase()
                + " " + customer.getPersonal().getLname().toUpperCase());
        System.out.println("Age: " + customer.getPersonal().getAge());
        System.out.println("Phone number: " + customer.getPersonal().getPhoneNumber());
        System.out.println("Email Id: " + customer.getPersonal().getEmail());


        System.out.println("--------Stage 2/4(DATA ENTRY)---------");
        System.out.print("Enter Income: ");
        customer.setIncome(s.nextFloat());
        System.out.print("Enter Assets: ");
        customer.setAssets(s.nextFloat());
        System.out.print("Enter Liabilities: ");
        float totalIncome = customer.getIncome()+customer.getAssets();
        customer.setLiability(v.invalidLiabilityValidator(totalIncome,s.nextFloat()));
        System.out.print("Enter your Education:" +
                " Student(S) |" +
                " Graduated(G) |" +
                " None(N) |");
        customer.setEducation(s.next());
        System.out.println("Total Income: " + customer.actualIncome());
        System.out.println();
        System.out.println("Enter Loan Details");
        System.out.println("Enter Loan Type B-(Business Loan) |" +
                "E-(Education Loan) |" +
                " H-(Home Loan) ");
        customer.getLoan().setType(s.next());
        System.out.print("Enter Loan Amount: ");
        customer.getLoan().setAmount(s.nextDouble());
        customer.setStatus(DEDUPE);
        System.out.println("Your have Successfully Passed Stage 2(QUICK DATA ENTRY)");
    }

    public void dedupe(Customer customer) {
        System.out.println("--------Stage 3/5(DEDUPE)--------");

        for (Customer fraud : customer.getFraudCustomers()) {
            if (isFraud(customer, fraud)) {
                customer.setStatus(REJECT);
                customer.setRemarks("Negative Customer");
                System.out.println("Sorry Your Application is Rejected due to Negative Score.");
                return;
            }

        }
        System.out.println("Your have Successfully Passed Stage 3(DEDUPE)");
        customer.setStatus(SCORING);

    }

    private boolean isFraud(Customer customer, Customer fraud) {

        int haveNegative = 0;
        if (customer.getPersonal().getPhoneNumber().equals(fraud.getPersonal().getPhoneNumber()))
            haveNegative += 10;

        if (customer.getPersonal().getEmail().compareTo(fraud.getPersonal().getEmail()) == 0)
            haveNegative += 10;

        if (customer.getPersonal().getAdhaarId().equals(fraud.getPersonal().getAdhaarId()))
            haveNegative += 10;

        if (customer.getPersonal().getPanId().equals(fraud.getPersonal().getPanId()))
            haveNegative += 10;

        return haveNegative > 0;

    }

    public void scoring(Customer customer) {
        System.out.println("--------Stage 4/5(SCORING)--------");

        int positiveScore = 0;
        if (customer.getPersonal().getAge() < 21 && customer.getPersonal().getAge() > 17 && (customer.getEducation().compareTo(S)) == 0) {
            System.out.println("You have age advantage in Education Loan");
            positiveScore += 60;
        }

        if (customer.actualIncome() >= ((customer.getLoan().getAmount()) / 20)) {
            System.out.println("You have Income Advantage");
            positiveScore += 30;
        }

        customer.getLoan().setScore(positiveScore);
        System.out.println("Your have Successfully Passed Stage 4(SCORING)");
        customer.setStatus(APPROVAL);
    }

    public void approval(Customer customer) {
        System.out.println("--------Stage 5/5(APPROVAL)--------");

        System.out.println(customer.getLoan().getType());
        if (customer.getLoan().getType().compareTo("H") == 0) {  //Home Loan
            if (customer.actualIncome() >= (customer.getLoan().getAmount() / 50)) {
                customer.setRemarks("Home Loan Approved");
                getInstallments(customer.getLoan().getAmount(), customer);
                customer.setStatus(APPROVED);
            } else {
                customer.setRemarks("You don't have enough score to request this Loan");
                customer.setStatus(REJECT);

            }
        } else if (customer.getLoan().getType().compareTo("E") == 0) {
            if (customer.actualIncome() >= (customer.getLoan().getAmount() / 10)) {
                customer.setRemarks("Education Loan Approved");
                getInstallments(customer.getLoan().getAmount(), customer);
                customer.setStatus(APPROVED);
            } else {
                customer.setRemarks("You don't have enough score to request this Loan");
                customer.setStatus(REJECT);

            }

        } else if (customer.getLoan().getType().compareTo("B") == 0) {
            if (customer.actualIncome() >= (customer.getLoan().getAmount() / 20)) {
                customer.setRemarks("Business Loan Approved");
                getInstallments(customer.getLoan().getAmount(), customer);
                customer.setStatus(APPROVED);
            } else {
                customer.setRemarks("You don't have enough score to request this Loan");
                customer.setStatus(REJECT);

            }
        }

    }

    private void getInstallments(double amount, Customer customer) {
        System.out.println("Choose your Duration");
        System.out.println("1. 24 months  " + calculatePerMonth(amount, 24, customer) + " Amount Payable");
        System.out.println("2. 48 months  " + calculatePerMonth(amount, 48, customer) + " Amount Payable");
        System.out.println("3. 96 months  " + calculatePerMonth(amount, 96, customer) + " Amount Payable");
        //EMI CALCULATED
        System.out.println("EMI Calculated :" + amount * customer.getLoan().getRoi() * (Math.pow((customer.getLoan().getRoi() + 1), 24)) / Math.pow((customer.getLoan().getRoi() + 1), 23));
        System.out.print("Enter your choice: ");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        switch (choice) {
            case 1 -> customer.getLoan().setDuration(24);
            case 2 -> customer.getLoan().setDuration(48);
            case 3 -> customer.getLoan().setDuration(96);
            default -> System.out.println("Invalid Input");
        }

    }

    private double calculatePerMonth(double amount, int months, Customer customer) {
        if (customer.getLoan().getScore() >= 30) {
            customer.getLoan().setRoi(1.3);
            return ((amount * 1.3 * months) / 100) + amount;
        } else if (customer.getLoan().getScore() >= 60) {
            customer.getLoan().setRoi(1.1);
            return ((amount * 1.1 * months) / 100) + amount;
        }

        customer.getLoan().setRoi(1.5);
        return ((amount * 1.5 * months) / 100) + amount;
    }

    public void ApplicationStatus(int applicationNumber) {


        for (Customer customer : customers) {
            if (applicationNumber == customer.getId()) {
                if (customer.getStatus() == 2) {
                    qde(customer);
                    if(!cont())
                    {
                        break;
                    }
                } if (customer.getStatus() == 3) {
                    dedupe(customer);
                    if(!cont())
                    {
                        break;
                    }
                } if (customer.getStatus() == 4) {
                    scoring(customer);
                    if(!cont())
                    {
                        break;
                    }
                } if (customer.getStatus() == 5) {
                    approval(customer);

                } if (customer.getStatus() == 6) {
                    System.out.println("Your Application is rejected");
                }

            }
        }



    }

    public boolean cont(){
        System.out.println("Want to Continue Further?(Y/N)");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next().toUpperCase();
        if(choice.compareTo("Y")==0)
        {
            return true;
        }
        else if(choice.compareTo("N")==0)
        {
            return false;
        }
        else {
            System.out.println("Invalid Choice");
        }
        return cont();
    }

    public void checkLoanStatus(int applicationNumber) {
        for (Customer customer : customers) {
            if (applicationNumber == customer.getId()) {
                if (customer.getStatus() == 0) {
                    System.out.println("Your Loan is Approved");
                } else if (customer.getStatus() == 6) {
                    System.out.println("Loan Rejected");
                } else {
                    System.out.println("Loan in Progress");
                    System.out.println("Want to Continue Loan Process? (Y/N)");
                    Scanner s = new Scanner(System.in);
                    String cont = s.next().toUpperCase();
                    if (cont()) {
                        ApplicationStatus(applicationNumber);
                    } else {
                        break;
                    }
                }
            }
        }
    }



}
