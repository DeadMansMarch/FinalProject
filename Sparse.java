package finalproject;
import java.util.HashMap;

public class Sparse implements ArrayInterface {
    HashMap Flexi = new HashMap<Integer,Integer>();
    int Length = 0;
    public int Get(int Index){

        if (Flexi.containsKey(Index)){
            return (int) Flexi.get(Index);
        }
        
        return 0;
    }
    
    public void Set(int Index,int Value){
        Flexi.put(Index,Value);
    }
    
    public int numberElements(){
        Integer Tot = (Integer) Flexi.entrySet()
                .stream()
                .reduce(0,(A,B)-> (int) A + 1);    
        return Tot;
    }
    
    public int Length(){
        return Length;
    }
    
    Sparse(int Length){
        super();
        this.Length = Length;
    }
}
