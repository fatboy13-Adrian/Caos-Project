/**
 * @(#)wF.java
 *
 *
 * @author:Xue Ting :) 
 * @version 1.00 2014/1/24
 */
//import java.util.*;
import java.io.*;
public class wF {
        //static Scanner in=new Scanner(System.in).useDelimiter("\r\n");
        static BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        static int Mprocess[],Aprocess[],Mblock[],Ablock[],Pusage[],Ausage[];
        static int process,block,free,used,readfree,readused,d,y;
        
    /**
     * Creates a new instance of <code>wF</code>.
     */
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here

        menu();//worstFit-menu
        readMemory();//worstFit-read memory
        resetMemory();//worstFit-reset memory
        displayMemory();//worstFit-display output for memory
        worstFitmemory();//worstFit
    }
    static void menu() throws IOException
    {
             System.out.println("=====================================");
             System.out.print("Enter number of blocks:");//prompt user's to enter number of block
             block=Integer.parseInt(in.readLine());
             System.out.print("Enter number of process:");//prompt user's to enter number of process
             process=Integer.parseInt(in.readLine());
             Mprocess=new int[process];
             Aprocess=new int[process];
             Mblock=new int[block];
             Ablock=new int[block];
             Pusage=new int[block];
             Ausage=new int[block];
             d=0;
    }
    static void readMemory() throws IOException
    {
     int i;
     
     for(i=0;i<block;i++)
     {
     	System.out.println("-------------------------------------");
     	System.out.print("Enter the size for B"+(i+1)+" : ");//block size that prompt the user
     	Ablock[i]=Integer.parseInt(in.readLine());
     }
     for(i=0;i<block;i++)
     {
     	System.out.println("--------------------------------------");
     	System.out.println("Enter the block usage scenerio");
     	System.out.print("Is B"+(i+1)+" used(1) or free(0)?");//check the block whether is free or not free
     	Ausage[i]=Integer.parseInt(in.readLine());
     	if(Ausage[i]==1)
     	{
     	  readused=readused+Ablock[i];
     	  y++;	
     	}
     	else
     	{
     	  readfree=readfree+Ablock[i];	
     	}
     }
     	for(i=0;i<process;i++)
     	{
     		System.out.println("---------------------------------------");
     		System.out.print("Enter the process demand for P"+(i+1)+":");//prompt user to enter number of process demand
     		Aprocess[i]=Integer.parseInt(in.readLine());
     	}
     
     
 
    }
    static void resetMemory() throws IOException
    {
    	int i;
    	for(i=0;i<block;i++)
    	{
    	  Mblock[i]=Ablock[i];	
    	  Pusage[i]=Ausage[i];
    	}
    	for(i=0;i<process;i++)
    	{
    		Mprocess[i]=Aprocess[i];
    		used=readused;
    		free=readfree;
    		d=y;
    		
    	}
      
    
    }
    static void displayMemory() throws IOException
    {
    /**	int eMemory,imemory;
    	eMemory=**/
    	
    	int total;
		total=readused+readfree;
		System.out.println("Blocks used = "+d);//display the block that being used
		System.out.println("Total used space = "+used);//display the memory space that being used
		System.out.println("Blocks free = "+(block-d));//display the output when the block is free
		System.out.println("Total free space = "+(total-used));//display the total output of free space
    	
      	
    }
    static void worstFitmemory() 
    {
    	
    	int i,j,size,worst;
    	for(i=0;i<process;i++)
    	{
    		size=0;
    		worst=-1;
    			for(j=0;j<block;j++)
    			{
    				if(Mprocess[i]<=Mblock[j]&&Pusage[j]==0&&(Mblock[j]-Mprocess[i])>size)
    				{
    					size=Mblock[j]-Mprocess[i];
    					worst=j;
    				}
    				
    					
    					
    			}
    			if(worst!=-1) //Ensuring a worst fit.
				{
					Pusage[worst]=1;
					used=used+Mblock[worst];
					d++;
					System.out.println("Process "+(i+1)+" is in block "+(worst+1));//display the output for worst-fit memory block
				
				}
    	}
    		
  
    }
}
