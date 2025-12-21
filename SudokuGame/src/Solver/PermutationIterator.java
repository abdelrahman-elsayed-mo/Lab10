
package Solver;

import java.util.Arrays;


public class PermutationIterator implements CustomIterator {
    
    private final int n;
    private final int maxValue;
    private final int[] current;
    private boolean hasNext;
    private long generated;
    private final long totalPermutations;
    
    public PermutationIterator(int n , int max)
    {
        this.n=n;
        this.maxValue=max;
        this.current=new int[n];
        Arrays.fill(current,1);
        this.hasNext=true;
        this.generated=0;
        this.totalPermutations=(long) Math.pow(max, n);
        
    }
    
    @Override 
    public boolean hasNext()
    {
        if (hasNext==true && generated<totalPermutations)
            return true ; 
        else 
            return false ;
    }
    
    @Override 
    public int[] next()
    {
        if(hasNext==false)
        {
            System.out.println("No more permutations");
            return null ;
        }
        else
        {
            int[] result = Arrays.copyOf(current, n);
            generated++;
            
            increment(0);
            
            return result; 
        }
      
    }
    
    private void increment(int index)
    {
        if(index>=n)
        {
            hasNext=false ;
            return ; 
        }
        
       current[index]++;
       
       if(current[index]>maxValue)
       {
           current[index]=1;
           increment(index+1);
       }  
        
    }
 
    
    
   public long getTotalPermutations(){
       return this.totalPermutations;
   }
   
   public long getGenerated()
   {
       return this.generated;
   }
   
   public void reset(){
       Arrays.fill(current,1);
       hasNext=true;
       generated=0;
   }
    
}
