import java.util.*;
class Sjf
{
	//declare all the global variables needed for this program
	static int p;					//number of processes
	static int bt[]=new int[p];		//burst time for each processes
	static int at[]=new int[p];		//arrival time for each processes 
	static int tat[]=new int[p];	//turn around time for each processes 
	static float t1 = 0, t2 = 0, aat, atat; 
	
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		//prompt users to enter the amount of processes he/ she wants
		System.out.print("Enter Number Of Processes You Want:");
		
		//capture user input
		p = in.nextInt();
		
		//prompt users to enter the burst time of each processes
		System.out.println("Enter burst time Of Each Process:");
		for(int i=0; i < p; i++)
		{
			System.out.println("Burst time for P"+(i+1)+"=");
			bt[i]=in.nextInt();
		}
		
		//declare the initial arrival time as 0
		at[0]=0;
		
		for(int i = 1; i < p; i++)
		{
				at[i] = bt[i-1] + at[i-1];
				t1 += at[i];
		}
		
		for(int i=0;i<p;i++)
		{
			tat[i] = bt[i] + at[i];
			t2 += tat[i];
		}
		
		//dlsiplay a chart that outputs the user's burst time, arrival time & turn around time
		System.out.println("Process\tBurst time\tArrival time\tTurn Around time");
		
		for(int i = 0; i < p; i++)
		{
			//display all the values for each processes with this data: burst time, arrival time & 
			System.out.println("P"+(i+1)+"\t\t"+bt[i]+"\t\t"+at[i]+"\t\t"+tat[i]);
}

System.out.println("Average Waiting time = "+t1/p);
System.out.println("Average Turn Around time =" +t2/p);
}
}
