package exceptions;

public class PriceException extends Exception
{
    public PriceException()
    {
        super("\nException: Price cannot be less than or equal to zero.");
    }
}