
package Solver;


public class EmptyCell {
    
    final int row;
    final int column;
    
    public EmptyCell(int r , int c)
    {
        this.row=r;
        this.column=c;
    }
    
    @Override
    public boolean equals (Object obj){
        if (this==obj) 
            return true;
        else if (obj==null || getClass()!=obj.getClass())
            return false; 
        else
        {
            EmptyCell ec = (EmptyCell) obj;
            if(row==ec.row && column==ec.column)
                return true; 
            else
                return false; 
              
        }
        
    }
    
    @Override
    public int hashCode()
    {
        return 31*row + column;
    }
    
    
}
