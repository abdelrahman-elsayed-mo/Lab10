
package Solver;


public class PermutationIteratorFactory {
    
    public PermutationIterator createIterator(int n ,int maxvalue)
    {
        return new PermutationIterator(n,maxvalue);
    }
    
    public PermutationIterator createFiveCellIterator()
    {
        return new PermutationIterator(5,9);
    }
    
}
