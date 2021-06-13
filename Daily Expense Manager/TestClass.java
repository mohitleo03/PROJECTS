package dailyexp;
import exceptions.*;
import validate.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class TestClass 
{
    public Validate v = new Validate();
    public boolean val = true;

	public void menu() throws Exception
	{
		int ch;
		char ans = 'y';

		Scanner input = new Scanner(System.in);

		TestClass t = new TestClass();

		do
		{
			System.out.print("\nChoose the operation you want to perform on your record: \n1. Add\n2. Update\n3. Delete\n4. Display Expenses\n5. Calculate Expenses\n6. Exit\n\nEnter choice: ");

			ch=input.nextInt();
            try
            {
    			switch(ch)
    			{
    				case 1: t.append();
    						break;

    				case 2: t.update();
    						break;

    				case 3: t.del();
    						break;

    				case 4: t.displayExpenses();
    						break;

    				case 5: t.calculateExpenses();
    						break; 

                    case 6: System.exit(0);

    				default: System.out.print("\nWrong Choice");
    			}
            }
            catch(Exception e)
            {
                System.out.print(e + "Program terminated");
                System.exit(0);

            }

			System.out.print("\nDo you want to continue?(y/n)");

       		ans = input.next().charAt(0);

		}while(ans=='y' || ans=='Y');

	}


	public void add() throws Exception, ExpenseException, PriceException
	{
        Scanner input = new Scanner(System.in);

		FileWriter fw = new FileWriter("display_expenses.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        String scanexp;
        String expense = " ";
        int price = 1;
 
	    System.out.print("\nAdd your expenses\nHow many expenses do you want to add? ");
    	int n=input.nextInt();

        boolean val = false;
    	for(int i=0; i<n; i++)
    	{
            val = false;
            while(!val)
            {                   
        		System.out.print("\nEnter expense name: ");
        		expense = input.next();

                val = v.expenseValidator(expense);

                if(!val)
                {
                    throw new ExpenseException();
                }
            }

            val = false;

            while(!val)
            {                
        		System.out.print("\nEnter the money spent on this expense: ");
        		price = input.nextInt();

                val = v.priceValidator(price);

                if(!val)
                {
                    throw new PriceException();
                }
            }

    		bw.write(expense + "," + price + "|");

    	}	

		bw.write(System.lineSeparator());

	    bw.close();

	}

    public void append() throws Exception, ExpenseException, PriceException
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nEnter the name to search:");
        String name;
       
        name = input.next();

        String scanexp;
        String expense = " ";
        int price = 1;

        String []rec;
        String []exp;
        
        try
        {
            File file1 = new File("temp.txt");
            File file2 = new File("display_expenses.txt");

            file1.createNewFile(); 

            BufferedWriter br1 = new BufferedWriter(new FileWriter("temp.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("display_expenses.txt"));
            String line;
        
            while((line = br2.readLine())!=null)
            {
                rec=line.split(";");
                if(!(name.equals(rec[0])))
                {
                    br1.write(rec[0] + ";" + rec[1] + ";");

                    exp=rec[2].split("[|]");
                    for(String s : exp)
                    {
                        expense = s.split(",")[0];
                        price = Integer.parseInt(s.split(",")[1]);

                        br1.write(expense + "," + price + "|");                
                    }                
                }

                else if((name.equals(rec[0])))
                {
                    br1.write(rec[0] + ";" + rec[1] + ";");                    
                    exp=rec[2].split("[|]");
                    for(String s : exp)
                    {
                        expense = s.split(",")[0];
                        price = Integer.parseInt(s.split(",")[1]);
                        br1.write(expense + "," + price + "|");
                    }       

                    System.out.print("\nHow many expenses do you want to add? ");
                    int n=input.nextInt();
                    for(int i=0; i<n; i++)
                    {   
                        val = false;
                        while(!val)
                        {    
                            System.out.print("\nEnter expense name: ");
                            expense = input.next();

                            val = v.expenseValidator(expense);

                            if(!val)
                            {
                                throw new ExpenseException();
                            }
                        }

                        val = false;

                        while(!val)
                        {
                            System.out.print("\nEnter the money spent on this expense: ");
                            price = input.nextInt();

                            val = v.priceValidator(price);
                            
                            if(!val)
                            {
                                throw new ExpenseException();
                            }
                        }

                        br1.write(expense + "," + price + "|");
                    }                           
                }

                br1.write(System.lineSeparator());
            }

            br1.close();
            br2.close();

            File dump = new File("display_expenses.txt");
            file2.delete();
            file1.renameTo(dump);
        }

        catch(Exception e)
        {
            System.out.print(e);
        } 

    }

	public void update() throws Exception, ExpenseException, PriceException
	{
		Scanner input = new Scanner(System.in);
        
        System.out.println("\nEnter the name to search:");
        String name;
       
        name = input.next();

        String scanexp;
        String expense;
        int price;

        String []rec;
        String []exp;
        
        try
        {
            File file1 = new File("temp.txt");
            File file2 = new File("display_expenses.txt");

            file1.createNewFile();

        	BufferedWriter br1 = new BufferedWriter(new FileWriter("temp.txt"));
        	BufferedReader br2 = new BufferedReader(new FileReader("display_expenses.txt"));
        	String line;
        
        	while((line = br2.readLine())!=null)
        	{
            	rec=line.split(";");
            	if(!(name.equals(rec[0])))
            	{
            		br1.write(rec[0] + ";" + rec[1] + ";");

               		exp=rec[2].split("[|]");
                	for(String s : exp)
                	{
                    	expense = s.split(",")[0];
                    	price = Integer.parseInt(s.split(",")[1]);

                    	br1.write(expense + "," + price + "|");                
                	}                
            	}

            	else if((name.equals(rec[0])))
            	{
            		br1.write(rec[0] + ";" + rec[1] + ";");

            		System.out.print("\nEnter the expense you want to update: ");
            		scanexp = input.next(); 

               		exp=rec[2].split("[|]");
                	for(String s : exp)
                	{
                    	expense = s.split(",")[0];
                    	price = Integer.parseInt(s.split(",")[1]);

                    	if(expense.equals(scanexp))
                    	{
                            val = false;
                            while(!val)
                            {    
                                System.out.print("\nEnter expense name: ");
                                expense = input.next();

                                val = v.expenseValidator(expense);

                                if(!val)
                                {
                                    throw new ExpenseException();
                                }
                            }

                            val = false;

                            while(!val)
                            {
                                System.out.print("\nEnter the money spent on this expense: ");
                                price = input.nextInt();

                                val = v.priceValidator(price);

                                if(!val)
                                {
                                    throw new PriceException();
                                }
                            }
            			}

                       	br1.write(expense + "," + price + "|");
                    }
                    	
            	}
                br1.write(System.lineSeparator());
 			}

 			br1.close();
 			br2.close();

            File dump = new File("display_expenses.txt");
            file2.delete();
            file1.renameTo(dump);

    	}

        catch(ExpenseException e)
        {
        	System.out.println(e);
		}

        catch(PriceException e)
        {
            System.out.println(e);
        }

        catch(Exception e)
        {
            System.out.println();
        }
	}

	public void del() 
	{
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nEnter the name to search: ");
        String name;
       
        name = input.next();

        String scanexp;
        String expense;
        int price;

        String []rec;
        String []exp;
        
        try
        {
            File file1 = new File("temp.txt");
            File file2 = new File("display_expenses.txt");

            file1.createNewFile();

        	BufferedWriter br1 = new BufferedWriter(new FileWriter("temp.txt"));
        	BufferedReader br2 = new BufferedReader(new FileReader("display_expenses.txt"));
        	String line;
        
        	while((line = br2.readLine())!=null)
        	{
            	rec=line.split(";");
            	if(!(name.equals(rec[0])))
            	{
            		br1.write(rec[0] + ";" + rec[1] + ";");

               		exp=rec[2].split("[|]");
                	for(String s : exp)
                	{
                    	expense = s.split(",")[0];
                    	price = Integer.parseInt(s.split(",")[1]);

                    	br1.write(expense + "," + price + "|");
             
                	}                
            	}
            	else if((name.equals(rec[0])))
            	{
            		br1.write(rec[0] + ";" + rec[1] + ";");

            		System.out.print("\nEnter the expense you want to delete: ");
            		scanexp = input.next(); 

               		exp=rec[2].split("[|]");
                	for(String s : exp)
                	{
                    	expense = s.split(",")[0];
                    	price = Integer.parseInt(s.split(",")[1]);

                    	if(!(expense.equals(scanexp)))
                    	{
                       		br1.write(expense + "," + price + "|");
            			}
                    }    	
            	}

                br1.write(System.lineSeparator());
 			}

 			br1.close();
 			br2.close();

 		    File dump = new File("display_expenses.txt");
            file2.delete();
            file1.renameTo(dump);
    	}

    	catch(Exception e)
        {
        	System.out.println();
		}
	}

	public void calculateExpenses()
	{
		Scanner input = new Scanner(System.in);
        
        System.out.println("\nEnter the name to search:");
        String name;
       
        name = input.next();

        try
        {
        	BufferedReader br2 = new BufferedReader(new FileReader("display_expenses.txt"));
        	String line;
        	String []rec;
        	String []exp;
        	
        	int sum = 0;
        
        	while((line = br2.readLine())!=null)
        	{
            	rec=line.split(";");
            	if(name.equals(rec[0]))
            	{
               		 exp=rec[2].split("[|]");
                	 for(String s : exp)
                	 {
                    	sum+= Integer.parseInt(s.split(",")[1]);
                	 }                
            	}

 			}
            System.out.println("\nYour total expenditure is " +sum);
        	br2.close();
    	}

        catch(Exception e)
        {
        	System.out.println(e);
		}

	}

	public void displayExpenses()
	{
		Scanner input = new Scanner(System.in);
        
        System.out.println("\nEnter the name to search:");
        String name;
       
        name = input.next();

        String expense;
        int price;

        String []rec;
        String []exp;
        
        try
        {
        	BufferedReader br2 = new BufferedReader(new FileReader("display_expenses.txt"));
        	String line;
        
        	while((line = br2.readLine())!=null)
        	{
            	rec=line.split(";");
            	if(name.equals(rec[0]))
            	{
            		System.out.print("\nName: " + rec[0]);

            		System.out.print("\nExpense" + "\tPrice");

               		exp=rec[2].split("[|]");
                	for(String s : exp)
                	{
                    	expense = s.split(",")[0];
                    	price = Integer.parseInt(s.split(",")[1]);

            			System.out.print("\n" + expense + "\t" + price);
                	}                
            	}
 			}
 			br2.close();
    	}

        catch(Exception e)
        {
        	System.out.println(e);
		}
	}

  	public void newuser() throws Exception, LongUsernameException, PassException
	{
	    val = false;
        String username = " ", password = " ";

		TestClass t1 = new TestClass();
        
		Scanner input = new Scanner(System.in);

		FileWriter fw = new FileWriter("display_expenses.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        
        do
        {
        		System.out.print("\nEnter new username: ");
                username=input.next();

                val = v.usernameValidator(username);

                if(!val)
                {
                    throw new LongUsernameException();
                }
        }while(!val);

        val = false;

        do
        {
            System.out.print("\nEnter new password: ");
            password=input.next();

            val = v.pwdValidator(password);

            if(!val)
            {
                throw new PassException();   
            }

        }while(!val);

        bw.write(username + ";" + password + ";");

        bw.close();

	    t1.add();
	}    

	public void login() throws Exception
	{
		int ch, j = 0, ctr = 1;
		Scanner input = new Scanner(System.in);

		TestClass t2 = new TestClass();
	
		System.out.print("\nPress 1 for signup\nPress 2 for login: ");
		
		String uname, pwd;
	
		ch=input.nextInt();

		switch(ch)
		{
			case 1: try
                    {
                        t2.newuser();
                    }
                    catch(Exception e)
                    {
					   System.out.print(e + "\nProgram terminated");
                       System.exit(0); 
                    }

                    break;
			
			case 2:	FileReader fr =new FileReader("display_expenses.txt");
         			BufferedReader br = new BufferedReader(fr);

         			String line;
         			String []user;

					System.out.print("\nEnter username: ");
	        		uname = input.next();
    					
	        		while((line = br.readLine()) != null)
	        		{	 
	        				try
	        				{	
	        					user = line.split(";");
    							String scanuser = user[0]; String scanpass = user[1];
   
    							if(uname.equals(scanuser))
    							{
									for(j=0; j<3; j++)
									{
										System.out.print("\nEnter password: ");
				    					pwd=input.next();

				    					if((pwd.equals(scanpass)))
				    					{
				    						System.out.print("\nLOGIN Successful");
				    						ctr = 0;
		                   					break;
				    					}
				    					else
				    					{
		               						System.out.print(2-j + "attempt(s) left");
				    					}
									}

									if(j==3)
		    						{
		    							System.out.print("\nLOGIN Blocked, create new account");
            							t2.newuser();
		    						}
		                   		}
		                   	}

		                   	catch(ArrayIndexOutOfBoundsException e)
		                   	{
		                   		System.out.print(" ");
		                   	}
					} 

					if(ctr == 1)
            		{
            			System.out.print("\nUsername didn't match with existing record");
            			t2.newuser();
					}

					br.close();
					break;

  		}
	}
}