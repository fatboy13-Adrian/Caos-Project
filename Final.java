//import java utilities for this project
import java.text.DecimalFormat;
import java.util.*;

public class Final 
{
	//declare all the global variables needed for this program
	static int processF, Start;				//number of processes for fcfs
	static int burstF[] = new int[processF];	//burst time for each processes for fcfs
	static int arrivalF[]  = new int[processF];	//arrival time for each processes  for fcfs
	static int turnaroundF[] = new int[processF];	//turn around time for each processes for fcfs
	static float f1 = 0, f2 = 0, aarrivalF, aturnaroundF;
	
	static Random rn = new Random();
	
	static Scanner input = new Scanner(System.in).useDelimiter("\r\n");
	static DecimalFormat fmt = new DecimalFormat("0.00");
		
	public static void main(String args[])
	{		
		do
		{
			
			StartMenu();					//Main Menu Function
			Start = input.nextInt();		//capture user input 
			System.out.println("");			//spacing to ensure readability in the actual console program
				
			switch(Start)
			{
				case 1:			
				System.out.println("FCFS");			//FCFS
				Fcfs();
				break;
					
/*				case 2:			//SJF
				Sjf();
				break;
					
				case 3:			//Worst Fit
				WrostFit();
				break;				*/
			}//end of switch(Start)
			}//end of do
			while(Start > 3);
		}//end of main program
	/*------------------------------------------------------------------------------------------------------------------------------------------------*/

		static void StartMenu()
		{
			//all users must take note of the NOTE
//			System.out.println("NOTE: ALL VALUES THAT ARE DISPLAYED IN THIS CONSOLE ARE IN NON - PREEMPTIVE FORMAT");
			System.out.println("------------------------------------");
			System.out.println("Please Make Your Choice:");	//prompt user to make selection between these 3 choices
			System.out.println("1) FCFS");					//first come first serve
/*			System.out.println("2) SJF");					//shortest job first
			System.out.println("3) Worst Fit");				//worst fit memory management    */
			System.out.println("------------------------------------");
		}

		static void Fcfs()
		{
			Scanner in=new Scanner(System.in);
			
			Processes();
			processF = in.nextInt();
			
			int burstF[] = new int[processF]; //burst time
			BurstTime();
			for(int i = 0; i < processF; i++)
			{
				burstF[i] =(1 + rn.nextInt((15-1)+1));
			}
			
			int arrivalF[] = new int[processF];
			arrivalTime();
			for(int i = 0; i < processF; i++)
			{
				arrivalF[i] = rn.nextInt(5);
				arrivalF[i] = arrivalF[i];
			}
			
			int waitF[] = new int[processF]; //wait time
			for(int i = 1; i < processF; i++)
			{
				waitF[i-1] = waitF[i-1] - arrivalF[i-1];
				waitF[i] =	burstF[i-1] + waitF[i-1];
				f1 += waitF[i] +waitF[i-1];
			}
						

			
			
			int turnaroundF[] = new int[processF]; //turnaround time
			for(int i = 0; i < processF; i++)
			{
				turnaroundF[i] = (arrivalF[i]+burstF[i] + waitF[i]) - arrivalF[i];
				f2 += turnaroundF[i];
			}
			
			DisplayOutputHeader();
			for(int i = 0; i < processF; i++)
			{
				System.out.println("P"+(i)+"\t\t"+arrivalF[i]+"\t\t"+burstF[i]+"\t\t"+waitF[i]+"\t\t"+turnaroundF[i]);
			}
			
			float awaitF,aturnaroundF;
			AvergeTimeFcfs();
		}
		static void Sjf()
		{
			
		}
		
		static void WrostFit()
		{
			
		}
		
		static void Processes()
		{
			System.out.println("Enter Number Of Processes: ");
		}
		
		static void arrivalTime()
		{
		}
		
		static void BurstTime()
		{
		}
		
		static void DisplayOutputHeader()
		{
			System.out.println("Process\tArrival time\tBurst time\tWaiting time\tTurnAround time");
		}
		
		static void AvergeTimeFcfs()
		{
			System.out.println("Average Waiting time = "+(f1 / processF));
			System.out.println("Average Turn Around time = "+(f2 / processF));
		}
	}//end of class CAOS_project