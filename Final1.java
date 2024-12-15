//import java utilities for this project
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class Final1
{
	//declare all variables & arrays as global variables & arrays for first come first serve
	static int pfcfs, start;				//pfcfs = number of process
	static int atfcfs[] = new int[pfcfs];	//arrival time for each process	
	static int btfcfs[] = new int[pfcfs];	//burst time for each process
	static int tatfcfs[] = new int[pfcfs];	//turn around time for each process
	static int pid[] = new int[pfcfs+1];	//process id sorting
	static int wtsum, tatsum;
	
	
	//awtfcfs = average waiting time for fcfs, aatfcfs = average arrival time for fcfs, atatfcfs = average turn around time for fcfs
	static double t1fcfs = 0.0, t2fcfs = 0.0, atatfcfs, awtfcfs, aatfcfs;
	
	//declare all global variables & arrays for shortest job first
	static int psjf;
	static int atsjf[] = new int[psjf];
	static int btsjf[] = new int[psjf];
	static int tatsjf[] = new int[psjf];
	
	//awtsjf = average waiting time for sjf, aatsjf = average arrival time for sjf, atatsjf = average turn around time for sjf
	static double t1sjf = 0, t2sjf = 0, atatsjf, awtsjf, aatsjf;			
	
	//declare all global variables & arrays for worst fit memory management
	static int Mprocess[],Aprocess[],Mblock[],Ablock[],Pusage[],Ausage[];//Wmemory
    static int process,block,free,used,readfree,readused,d,y;//Wmemory
    
    //declare all other variables used in this program
    static int i, j, tempA, tempB, tempC;
    
    static BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
    static Scanner in = new Scanner(System.in).useDelimiter("\r\n");
    static DecimalFormat fmt = new DecimalFormat("0.00");
    
	public static void main(String args[]) throws IOException
	{
		do
		{
			StartMenu();			//Start Menu For Program		
			start = in.nextInt();//capture user input 
			System.out.println("");	//spacing to ensure readability in the actual console program
			
			switch(start)
			{
				case 1:
					fcfs();		//function call for FCFS
				break;
				
				case 2:
					sjf();		//fucntion call for SJF
				break;
				
				case 3:
					 displayWorstFitMemory();	//function call for worst fit
				break;
			}
		}
		while(start > 3);
	}//end of main program

//methods for first come first serve
/*-----------------------------------------------------------------------------------------------------------------*/
static void fcfs()
{	
	Process();							//prompt user to enter number of processes
	pfcfs = in.nextInt();				//capture user input for number of processes
	int btfcfs[] = new int[pfcfs+1];	//store user input into an array
	int pid[] = new int[pfcfs+1];		//sort by process id
	
	BurstTime();						//prompt user to enter burst time of each process
	for(int i = 0; i < pfcfs; i++)		
	{
		System.out.println("Burst time for P"+(i+1)+":");
		btfcfs[i] = in.nextInt();
		pid[i] = i + 1;
	}
					
	btfcfs[pfcfs] = 999;
	
	ArrivalTime();					//prompt user to enter arrival time for each process
	int atfcfs[] = new int[pfcfs+1];			
	for(int i = 0; i < pfcfs; i++)
	{
		System.out.println("Arrival Time for P"+(i + 1)+": ");
		atfcfs[i] = in.nextInt();
	}
	
	atfcfs[pfcfs] = 999;
	
	for(i=0; i<pfcfs; i++)
		for(j=i+1; j<pfcfs+1; j++)
			if( atfcfs[j] < atfcfs[i])
			{
				tempA = atfcfs[j];
				atfcfs[j] = atfcfs[i];
				atfcfs[i] = tempA;
				
				tempB = btfcfs[j];
				btfcfs[j] = btfcfs[i];
				btfcfs[i] = tempB;	
				
				tempC = pid[j];
				pid[j] = pid[i];
				pid[i] = tempC;
			}
				
	//calculate waiting time for each process
	int endtime = 0;
	int wtfcfs[] = new int[pfcfs];
	int tatfcfs[] = new int[pfcfs];
	
	for(int i = 0; i < pfcfs; i++)
	{		
		wtfcfs[i] = endtime - atfcfs[i];
		endtime = endtime + btfcfs[i];
		tatfcfs[i] = endtime - atfcfs[i];
	}
	
	for(int i = 0; i < pfcfs; i++)
	{	
		wtsum += wtfcfs[i];
		tatsum += tatfcfs[i];		
	}
	
	t1fcfs = 1.0 * wtsum / pfcfs;
	t2fcfs = 1.0 * tatsum / pfcfs;
						
	DisplayHeader();		//display title header
					
	//display output for number of processes, arrival time, burst time, waiting time & turn around time of each process
	for(int i = 0; i < pfcfs; i++)
	{
		System.out.println("P"+pid[i]+"\t\t"+atfcfs[i]+"\t\t"+btfcfs[i]+"\t\t"+wtfcfs[i]+"\t\t"+tatfcfs[i]);
	}
	
	System.out.println("Average Waiting time = "+t1fcfs);
	System.out.println("Average Turn Around time = "+t2fcfs);
}

//methods for shortest job first
/*-------------------------------------------------------------------------------------------------------------------*/
static void sjf()
{
	Process();							//prompt user to enter number of processes
	psjf = in.nextInt();				//capture user input for number of processes
	int btsjf[] = new int[psjf+1];		//store user input into an array
	int pid[] = new int[psjf+1];		//sort by process id for sjf
	
	BurstTime();						//prompt user to enter burst time of each process
	for(int i = 0; i < psjf; i++)		
	{
		System.out.println("Burst time for P"+(i+1)+":");
		btsjf[i] = in.nextInt();
		pid[i] = i + 1;
	}
					
	btsjf[psjf] = 999;
	
	ArrivalTime();						//prompt user to enter arrival time for each process
	int atsjf[] = new int[psjf+1];		//capture user input and store it in an array		
	for(int i = 0; i < psjf; i++)
	{
		System.out.println("Arrival Time for P"+(i + 1)+": ");	//prompt user to enter number of processes for each process
		atsjf[i] = in.nextInt();		//capture user input 
	}
	
	atsjf[psjf] = 999;
	
	//method used for sorting 
	for(i=0; i<psjf; i++)
		for(j=i+1; j<psjf+1; j++)
			if( btsjf[j] < btsjf[i])
			{
				tempA = atsjf[j];
				atsjf[j] = atsjf[i];
				atsjf[i] = tempA;
				
				tempB = btsjf[j];
				btsjf[j] = btsjf[i];
				btsjf[i] = tempB;	
				
				tempC = pid[j];
				pid[j] = pid[i];
				pid[i] = tempC;
			}
				
	//calculate waiting time & arrival time for each process
	int endtime = 0;
	int wtsjf[] = new int[psjf];
	int tatsjf[] = new int[psjf];
	
	for(int i = 0; i < psjf; i++)
	{		
		wtsjf[i] = endtime - atsjf[i];
		endtime = endtime + btsjf[i];
		tatsjf[i] = endtime - atsjf[i];
	}
	
	for(int i = 0; i < psjf; i++)
	{	
		wtsum += wtsjf[i];
		tatsum += tatsjf[i];		
	}
	
	//calculation of average waiting & turn around time for sjf
	t1sjf = 1.0 * wtsum / psjf;
	t2sjf = 1.0 * tatsum / psjf;
						
	DisplayHeader();		//display title header
					
	//display output for number of processes, arrival time, burst time, waiting time & turn around time of each process
	for(int i = 0; i < psjf; i++)
	{
		System.out.println("P"+pid[i]+"\t\t"+atsjf[i]+"\t\t"+btsjf[i]+"\t\t"+wtsjf[i]+"\t\t"+tatsjf[i]);
	}
	
	System.out.println("Average Waiting time = "+t1sjf);
	System.out.println("Average Turn Around time = "+t2sjf);
	
}
	
//methods for worst fit memory management
/*-------------------------------------------------------------------------------------------------------------------*/
static void displayWorstFitMemory() throws IOException
{
	Wmenu();				//worstFit-menu
    readWMemory();			//worstFit-read memory
    resetWMemory();			//worstFit-reset memory
    displayMemoryoutput();	//worstFit-display output for memory
    worstFitmemory();		//worstFit
}

static void Wmenu() throws IOException
{
	line();
    blocks();				//prompt user's to enter number of block
    block = Integer.parseInt(in2.readLine());
    
    Process();				//prompt user's to enter number of process
    process = Integer.parseInt(in2.readLine());
    Mprocess=new int[process];
    Aprocess = new int[process];
    Mblock = new int[block];
    Ablock = new int[block];
    Pusage = new int[block];
    Ausage = new int[block];
    d = 0;
}

static void readWMemory() throws IOException
{
	int i;
     
    for(i = 0; i < block; i++)
    {
    	line();
     	System.out.print("Enter the size for B"+(i+1)+" : ");//block size that prompt the user
     	Ablock[i] = Integer.parseInt(in2.readLine());
    }
    
    for(i=0;i<block;i++)
    {
    	line();
     	System.out.println("Enter the block usage scenerio");
     	System.out.print("Is B"+(i+1)+" used(0) or free(1)?");//check the block whether is free or not free
     	Ausage[i] = Integer.parseInt(in2.readLine());
     	if(Ausage[i] == 1)
     	{
     	  readused = readused + Ablock[i];
     	  y++;	
     	}
     	
     	else
     	{
     	  readfree = readfree + Ablock[i];	
     	}
     }
     
     for(i = 0;i < process; i++)
     {
     	line();
     	System.out.print("Enter the process demand for P"+(i+1)+":");//prompt user to enter number of process demand
     	Aprocess[i] = Integer.parseInt(in2.readLine());
     }
}

static void resetWMemory() throws IOException
{
	int i;
    for(i = 0;i < block; i++)
    {
    	Mblock[i] = Ablock[i];	
    	Pusage[i] = Ausage[i];
    }
    
    for(i = 0; i < process; i++)
    {
    	Mprocess[i] = Aprocess[i];
    	used = readused;
    	free = readfree;
    	d = y;	
    }
}

static void displayMemoryoutput() throws IOException
{
/**	int eMemory,imemory;
eMemory=**/
    	
	int total;
	total = readused + readfree;
	System.out.println("Blocks used = "+d);						//display the block that being used
	System.out.println("Total used space = "+used);				//display the memory space that being used
	System.out.println("Blocks free = "+(block - d));			//display the output when the block is free
	System.out.println("Total free space = "+(total - used));	//display the total output of free space
}

static void worstFitmemory() 
{	
	int i,j,size,worst;
    for(i = 0; i < process; i++)
    {
    	size = 0;
    	worst = -1;
    	for(j = 0; j < block; j++)
    	{
    		if(Mprocess[i] <= Mblock[j] && Pusage[j] == 0 && (Mblock[j] - Mprocess[i]) > size)
    		{
    			size = Mblock[j] - Mprocess[i];
    			worst = j;
    		}
    	}
    	
    	if(worst != -1) //Ensuring a worst fit.
		{
			Pusage[worst]=1;
			used = used + Mblock[worst];
			d++;
			System.out.println("Process "+(i+1)+" is in block "+(worst+1));//display the output for worst-fit memory block
		}
    }
}

//methods for the entire programs
/*-------------------------------------------------------------------------------------------------------------------*/
static void StartMenu()
	{
	//all users must take note of the NOTE
	System.out.println("NOTE: ALL VALUES THAT ARE DISPLAYED IN THIS CONSOLE ARE IN NON - PREEMPTIVE FORMAT");
	line();
	System.out.println("Please Make Your Choice:");	//prompt user to make selection between these 3 choices
	System.out.println("1) FCFS");					//first come first serve
	System.out.println("2) SJF");					//shortest job first
	System.out.println("3) Worst Fit");				//worst fit memory management
	line();
}
	
static void Process()
{
	System.out.println("Enter Number Of Processes: ");
}
	
static void BurstTime()
{
	System.out.println("Enter Burst Time For Each Process: ");
}
	
static void ArrivalTime()
{
	System.out.println("Enter Arrival Time For Each Process: ");
}

static void blocks()
{
	System.out.print("Enter number of blocks:");
}
	
static void DisplayHeader()
{
	System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurn Around Time");
}

static void line()
{
	System.out.println("----------------------------------------------------------------------------------");
}
}//end of class Final1
