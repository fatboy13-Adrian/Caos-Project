import java.util.*;
public class FCFSAlgorithms
{
 //Static Scanner Input=new Scanner(System.in).useDelimiter("\r\n");
 //Global variable
 //Static int CPU_burst,Arrival_Time,waiting_time,turnaround_time,process;
 //Static float time1=0,time2=0;
  public static void main(String[] args)
  {
           FCFS();
        
  }

  
  static void FCFS()
  {
	  Scanner Input=new Scanner(System.in).useDelimiter("\r\n");
	  int process;
	  float time1=0,time2=0;
      int start_FCFS;
	  System.out.println("This is FCFS scheduling"); 
      System.out.print("Enter number of process:");
      process=Input.nextInt();
      int Arrival_Time[]=new int[process];//new process that being created for Arrival time
      int CPU_burst[]=new int[process];//new process that being created for CPU burst time
   
			for(int i=0;i<process;i++)
			{
			   System.out.print("Arrival time for the P"+(i+1)+"=");//user's Arrival time for process
			   Arrival_Time[i]=Input.nextInt();
			   System.out.print("CPU burst time for the P"+(i+1)+"=");//user's CPU burst time for process
			   CPU_burst[i]=Input.nextInt();
			 
			}
      
     
      int waiting_time[]=new int[process];
      for(int i=0;i<process;i++)
      {
       waiting_time[i]=CPU_burst[i]+Arrival_Time[i];
       time1+=waiting_time[i];
      }
      int turnaround_time[]=new int[process];
      for(int i=0;i<process;i++)
      {
        turnaround_time[i]=CPU_burst[i]+waiting_time[i];
        time2+=turnaround_time[i];
      }
	     System.out.println("-------------------------------------------------------------------------");
         System.out.println("||Process\tCPU Burst\tArrival Time\tWaiting time\tTurnaround time\t||");
         for(int i=0; i<process;i++)
         {
          System.out.println("||P"+(i+1)+"\t\t\t"+CPU_burst[i]+"\t\t\t"+Arrival_Time[i]+"\t\t\t\t"+waiting_time[i]+"\t\t\t\t\t"+turnaround_time[i]+"\t\t||");
          System.out.println("-------------------------------------------------------------------------");
		 }//display the output of the user's FCFS scheduling
      float average_waiting_time, average_turnaround_time;
      System.out.println("Average Waiting time="+time1/process);
      System.out.println("Average turnaround time="+time2/process);

      
       
    
  }
} 