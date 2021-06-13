import java.util.*;
import java.io.*;
import java.lang.*;

import dailyexp.*;

class MainMenu  
{
	public static void main(String args[]) throws Exception
	{
        try
        {
            TestClass t = new TestClass();
            
            System.out.print("Welcome to home screen. Have a good day :)");

            t.login();

            t.menu();
        }

        catch(Exception e)
        {
            System.out.print(e);
        }	
	}
}