package org.example;

import org.example.ExceptionClasses.*;
import org.example.Sourcing.SourcingData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator{
    Properties properties = new Properties();
    FileInputStream input=null;
    Scanner sc= new Scanner(System.in);
    public Validator()
    {

        try {
           this.input = new FileInputStream("D:\\College-Programs\\ORM-Practice\\LoanManagementSystem-2021\\src\\main\\java\\org\\example\\Constants\\exception.properties");
           properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int ageValidator(int age)
    {
        try{
            ageValidation(age);
        } catch(AgeValidationException e) {
            String s = properties.getProperty("ageException");
            System.out.println(s);
            System.out.print("Enter Age: ");
            age = sc.nextInt();
            ageValidator(age);
        }
     return age;
    }

    public long phoneNumberValidator(long phoneNumber){
        try {
        phoneNumValidation(phoneNumber);
        } catch (PhoneNumberValidationException e) {
            String s = properties.getProperty("phoneException");
            System.out.println(s);
            System.out.print("Enter Phone Number: ");
            phoneNumber = sc.nextLong();
            phoneNumberValidator(phoneNumber);
        }
        return phoneNumber;
    }

    public String emailNumberValidator(String email){
        try {
            emailIDValidation(email);
        } catch (EmailIdValidationException e) {
            String s = properties.getProperty("emailException");
            System.out.println(s);
            System.out.print("Enter Email Id: ");
            email = sc.next();
            emailNumberValidator(email);
        }
        return email;
    }

    public long aadharNumberValidator(long aadharNo){
        try {
            aadharNumberValidation(aadharNo);
        } catch (AadharNumberValidationException e) {
            String s = properties.getProperty("aadharException");
            System.out.println(s);
            System.out.print("Enter AadharNo: ");
            aadharNo = sc.nextLong();
            aadharNumberValidator(aadharNo);
        }
        return aadharNo;
    }

    public String pancardValidator(String panId){
        try {
            pancardValidation(panId);
        } catch (PanNumberValidationException e) {
            String s = properties.getProperty("panException");
            System.out.println(s);
            System.out.print("Enter panId: ");
            panId = sc.next();
            pancardValidator(panId);
        }
        return panId;
    }

    public float invalidLiabilityValidator(float income , float liability){
        try {
            invalidLiabilityValidationException(income,liability);
        } catch (InvalidLiabilityValidationException e) {
            String s = properties.getProperty("liabilityException");
            System.out.println(s);
            System.out.print("Enter Liability: ");
            liability = sc.nextFloat();
            invalidLiabilityValidator(income,liability);
        }
        return liability;
    }



    public void ageValidation(int age) throws AgeValidationException {

        if (age < 18) {
            throw new AgeValidationException("Underage");
        }
    }


    public void phoneNumValidation(Long phoneNumber)throws PhoneNumberValidationException {

        if (!phoneNumber.toString().matches("\\d{10}"))
            throw new PhoneNumberValidationException("Invalid Phone Number");

    }

    public  void emailIDValidation(String email) throws EmailIdValidationException {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            throw new EmailIdValidationException("Empty Email");
        if(!pat.matcher(email).matches())
            throw new EmailIdValidationException("Invalid Email");
    }


    public  void aadharNumberValidation(Long adhaar) throws AadharNumberValidationException{

        String regex = "^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$";


        Pattern p = Pattern.compile(regex);


        if (adhaar == null) {
            throw new AadharNumberValidationException("Empty Adhhaar");
        }


        Matcher m = p.matcher(adhaar.toString());

        if(!m.matches())
        throw new AadharNumberValidationException("Invalid Aadhaar Number");
    }


    public  void pancardValidation(String pancardNumber) throws PanNumberValidationException {
        if (!pancardNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            throw new PanNumberValidationException("Invalid Pan Number");
        }
    }

    public void invalidLiabilityValidationException(float income , float liability) throws InvalidLiabilityValidationException
    {
        if(liability > income){
            throw new InvalidLiabilityValidationException("Invalid Liability");
        }
    }

}
