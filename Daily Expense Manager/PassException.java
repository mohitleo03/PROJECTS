package exceptions;

public class PassException extends Exception
{
    public PassException()
    {
        super("\nException: Password must have more than 8 and less than 12 characters");
    }
}