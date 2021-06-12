package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Application {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        //Manages All the Classes
        //Calls Loan Process Class
        LoanProcessInterface loanProcess = (LoanProcessInterface) context.getBean("loanProcess");

        int choice;
        do {
            System.out.println("Welcome to Loan Management System");
            System.out.println("1. Apply for New Loan");
            System.out.println("2. Existing Loan");
            System.out.println("3. Check Loan Status");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    Customer customer = (Customer) context.getBean("customer1");
                        loanProcess.start(customer);
                }
                case 2 -> {
                    System.out.print("Enter Application Number: ");
                    loanProcess.ApplicationStatus(sc.nextInt());
                }
                case 3-> {
                    System.out.print("Enter Application Number: ");
                    loanProcess.checkLoanStatus(sc.nextInt());
                }
                case 4 -> System.out.println("Thank you for using Loan Management System");
                default -> System.out.println("Invalid Choice");
            }
        } while (choice != 4);


    }
}
