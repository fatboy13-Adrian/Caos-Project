import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CAOS 
{
	//declare all global String variables
	
	//declare & assign all global variables
	static int Start, Choice, DisplayLoop = 0, total;
	//create & declare all global arrays
	
	static Scanner input = new Scanner(System.in).useDelimiter("\r\n");
	static DecimalFormat fmt = new DecimalFormat("0.00");
	
	public static void main(String []args)
	{
		do
		{
			StartMenu();				//method to call for start menu
			Start = input.nextInt();	//capture user input
			System.out.println("");		//usage of this spacing is to ensure readability in the actual console
			
			switch(Start)
			{
				case 1:
				do
				{
					FCFS();						//method call for first in first out menu
					System.out.println("");		//usage of this spacing is to ensure readability in the actual console
					
				}
			}	
		}
	}

    
    
}