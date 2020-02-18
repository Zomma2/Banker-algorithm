package bankerAlgorithm;

import java.util.Random;
import java.util.Scanner;

public class Bankeralgorithm {

	Scanner sc = new Scanner(System.in) ;
	int m ;// number of resources types 
	int n; // number of process (p0,p1,...etc)
    int max[][];

int need[][]=needCalc();

int available[];

int allocation[][];
int[] request  ; // representing the request we can get 
    Random rand = new Random();
    private void RandMatrix() {
    	

Scanner sc=new Scanner(System.in);
System.out.println("Please note that all resources instance are limited to 10 ");
System.out.println("Please enter no. of Customers and no. of Resource ");

n=sc.nextInt();

m=sc.nextInt();

max=new int[n][m];

need=new int[n][m];

available=new int[m];

allocation=new int[n][m];




System.out.println("Allocation Matrix Randomized successfully");

for(int i=0;i<m;i++){

char c= (char) ((char) 65+i);

System.out.print(c+"\t");

}

System.out.println();

for(int i=0;i<n;i++){

for(int j=0;j<m;j++){

allocation[i][j]=rand.nextInt(2);
System.out.print(allocation[i][j]+"\t");
}
System.out.println();
}




System.out.println(" Max Matrix Randomized successfully");

for(int i=0;i<m;i++){

char c= (char) ((char) 65+i);

System.out.print(c+"\t");

}

System.out.println();

for(int i=0;i<n;i++){

for(int j=0;j<m;j++){
do {
max[i][j]=rand.nextInt(50);}while(max[i][j]<allocation[i][j]);
System.out.print(max[i][j]+"\t");
}
System.out.println();
}

System.out.println("Available Matrix Randomized successfully");

for(int i=0;i<m;i++){

char c= (char) ((char) 65+i);

System.out.print(c+"\t");

}

System.out.println();

for(int i=0;i<m;i++){

available[i]=rand.nextInt(50)+10;
System.out.print(available[i]+"\t");
}
System.out.println();
sc.close();

}
 
    
    public int[][]  needCalc()
    {
    	boolean calc = true ;
    	
    	for(int i=0;i<n;i++){
if(calc) {
    		for(int j=0;j<m;j++){
if(max[i][j]>=allocation[i][j])
    		need[i][j]=max[i][j]-allocation[i][j]+10;
else {
	calc= false ;
	System.out.println("Can't calculate need :allocation is bigger than maximun");
break ;
	
}	}

}}
    	
    	
    	
    	if(calc)
    	{
    	needprint() ;
    	}
    	return need ; 
    }
    
     public void needprint() {
    	 
    	 System.out.println("Here is the need calculated ");
    	 for (int i= 0 ; i<m;i++)
     	{

     	char c= (char) ((char) 65+i);

 		System.out.print(c+"\t");
 	

     	}
     	System.out.println(); 
     	
    	 
    	 for(int i=0;i<n;i++){

     		for(int j=0;j<m;j++){

     		System.out.print(need[i][j]+"\t") ;

     		}
    	 System.out.println();
    	 }
    	 System.out.println();
    	 
     }
    
    
    
    public int[] generateRequest() 
    
    {
    	request = new int [m] ;
    	
    	System.out.println("the request is ");
    	

    	for(int i=0;i<m;i++){

    	char c= (char) ((char) 65+i);

    	System.out.print(c+"\t");

    	}

    	System.out.println();

    	for(int i=0;i<m;i++){

    		request[i]=rand.nextInt(2);
    	System.out.print(request[i]+"\t");
    	}
    	System.out.println();
		
    	
    	
    return request;
    	
    
    }

    public int getWaitingProcessNumber() {
    	int waitprocessnumber = 0 ;
    	
   	 for(int i=0;i<n;i++){

 		for(int j=0;j<m;j++){
 			
 			
 			waitprocessnumber++ ;
 			
 		
 		}
 		
    	
    
    }
 	return waitprocessnumber ;
    }
    
    public boolean avilableNeedLoop(int need[][])
    {
    	int r=n ;
    	
  boolean wait = false ;
        int	waiting[][] = new int[getWaitingProcessNumber()][m];
    	 
   	 for(int i=0;i<n;i++){

    		for(int j=0;j<m;j++){
    			
    		if(available[i]>=need[i][j] &&!wait)
    		{
    			available[i]+=need[i][j] ; 
    		}
    		else 
    		{
    			wait=true ;
    			waiting[i][j]=need[i][j] ;
    		
    		}
    		}
    		while(getWaitingProcessNumber()!=0 && --r!=0) {
    			avilableNeedLoop(waiting) ;
    		}

    		
    		
    		
    		
    	
   	 }
    	
   	if(n==0) {
   		return false ;
   		
   	}

   	else 
   	{
   		return true ;
   		}
    }
    
    
    public boolean safe()
    {
    	
    	boolean isSafe = avilableNeedLoop(need);
    	if(!isSafe)
    	{System.out.println("the system is unsafe")
    	;	return false ;
    		}
    	else 
    	{
    		System.out.println("the system is safe") ;
    	return true ;
    	}
    
    }    
    
    public void processRequest()
    {
    	request = generateRequest() ; 
    	int proceesMakeRequest=rand.nextInt(n);
    	System.out.println("the process that make request is " +  proceesMakeRequest);
    	boolean reject = false ;
    	for(int i =0 ;i<m ; i++)
    		
    	{
    		
    		if(request[i]>need[proceesMakeRequest][i])
    		{
    		 reject = true ;
    		 System.out.println("process request is bigger than need , request is denied ");
    		 break ;
    			
    			}
    		if(request[i]>available[i])
    		{
    			reject = true ; 
    			System.out.println("process request is bigger than available , request is denied ");
    			break ; 
    		
    		}
    	}
    	
    	if(!reject)
    	{
    		
    		for(int i = 0 ; i<m ; i++ )
    		{
    			available[i]=available[i]-request[i] ;
    			allocation[proceesMakeRequest][i]=allocation[proceesMakeRequest][i]+request[i] ;
    		need[proceesMakeRequest][i]=need[proceesMakeRequest][i]-request[i] ;
    		}
    		safe() ;
    		
    	}
    }
    
public static void main(String[] args) {
	Bankeralgorithm s1 = new Bankeralgorithm() ;
	s1.RandMatrix();
s1.needCalc() ;

	
		while(true) {
		s1.processRequest();
		}
	
	}



}
