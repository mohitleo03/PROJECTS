package org.example;

public interface LoanProcessInterface {
    //Sourcing Function
    public void start(Customer customer);

    //Continue the Process
    public void ApplicationStatus(int applicationNumber);

    //Returns
    public void checkLoanStatus(int applicationNumber);
}
