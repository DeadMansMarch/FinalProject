
package finalproject;


public class FlexiArray implements ArrayInterface{
    protected int length = 0;
    protected int[] Flexi;
    
    public void Set(int index, int value){
        Flexi[index] = value;
    }
    
    public int Get(int index){
        return Flexi[index];
    }
    
    public int Length(){
       return length;
    }
    
    FlexiArray(int Length){
        length = Length;
        Flexi = new int[Length];
    }
}
