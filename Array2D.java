
package finalproject;

public class Array2D {
    protected ArrayInterface Array = null;
    protected int RowLength = 0;
    protected int ColumnLength = 0;
    
    protected int MemoryKey(int Column, int Row){
        return (Array.Length() / RowLength) * Row + Column;
    }
    
    public void Set(int Column,int Row,int Value){
        if (Column <= ColumnLength && Row <= RowLength){
            Array.Set(MemoryKey(Column,Row),Value);
        }else{
            System.out.println("Index out of bounds exception:"
                    + " Column: " + Column + " Row: " + Row + " Max: Column: "
                    + ColumnLength + " Row: " + RowLength);
            System.exit(-1);
        }
    }
    
    public int Get(int Column,int Row){
        return Array.Get(MemoryKey(Column,Row));
    }

    
    public Array2D(int Columns, int Rows,boolean IsSparce){
        this.RowLength = Rows;
        this.ColumnLength = Columns;
        if (IsSparce){
            Array = new Sparse(Columns * Rows);
        }else{
            Array = new FlexiArray(Columns * Rows);
        }
    }
}
