package validate;
import exceptions.*;

public class Validate
{
    public boolean valid = true;

    public boolean pwdValidator(String password)
    {
        String pwd;
        pwd = password;
        char ch;
        int ctr = 0;

        valid = true;

        if(pwd.length()<8 || pwd.length()>12)
        {
            valid = false;
        }

        for(int i=0; i<pwd.length(); i++)
        {
            ch=pwd.charAt(i);

            if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch =='9')
            {
                ctr++; 
                break;
            }
        }

        if(ctr == 0)
        {
            valid = false;
        }

        return valid;

    }

    public boolean priceValidator(int price)
    {
        boolean valid = true;

        if(price<=0)
        {
            valid = false;
        }

        return valid;
    }

    public boolean expenseValidator(String expense)
    {
        String exp;
        exp = expense;

        char ch;
        int ctr = 0;

        valid = true;
        for(int i=0; i<exp.length(); i++)
        {
            ch = exp.charAt(i);

            if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch =='9')
            {
                ctr++; 
            }
        }

        if(ctr != 0)
        {
            valid = false;
        }

        return valid;
    }

    public boolean usernameValidator(String username)
    {
        String user = username;

        char ch;
        int ctr = 0;

        valid = true;
        for(int i=0; i<user.length(); i++)
        {
            ch = user.charAt(i);

            if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch =='9')
            {
                ctr++; 
            }
        }

        if(user.length()>12)
        {
            valid = false;
        }

        if(ctr != 0)
        {
            valid = false;
        }

        return valid;
    }

}
