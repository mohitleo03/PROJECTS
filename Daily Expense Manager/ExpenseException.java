package exceptions;

public class ExpenseException extends Exception
{
    public ExpenseException()
    {
        super("\nException: Expense name cannot contain digits.");
    }
}

