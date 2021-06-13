package exceptions;

public class LongUsernameException extends Exception
{
    public LongUsernameException()
    {
        super("\nException: Username cannot contain digits and it cannot have more than 12 characters.");
    }
}