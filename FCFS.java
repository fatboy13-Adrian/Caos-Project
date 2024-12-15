import java.util.*;
class FCFS
{
public static void main(String args[])
{
Scanner in=new Scanner(System.in);
int p;
float t1=0,t2=0;
System.out.println("Enter number of processes");
p=in.nextInt();
int bt[]=new int[p];

System.out.println("Enter burst time");
for(int i=0;i<p;i++)
{
System.out.println("Burst time for P"+(i+1)+"=");
bt[i]=in.nextInt();
}
int wt[]=new int[p];
wt[0]=0;
for(int i=1;i<p;i++)
{
wt[i]=bt[i-1]+wt[i-1];
t1+=wt[i];
}
int tat[]=new int[p];
for(int i=0;i<p;i++)
{
tat[i]=bt[i]+wt[i];
t2+=tat[i];
}
System.out.println("Process\tBurst time\tWaiting time\tTurn Around time");
for(int i=0;i<p;i++)
{
System.out.println("P"+(i+1)+"\t\t"+bt[i]+"\t\t"+wt[i]+"\t\t"+tat[i]);
}
float awt,atat;
System.out.println("Average Waiting time="+t1/p);
System.out.println("Average Turn Around time="+t2/p);
}
}


