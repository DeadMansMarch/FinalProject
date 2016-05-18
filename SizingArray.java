
package finalproject;

public class SizingArray extends FlexiArray {
    
    public void Set(int index,int value){
        if (index >= super.length){
            int Ilength = (int) (index * 1.2);
            int[] NewSet = new int[Ilength];
            for (int i = 0;i <= super.length - 1;i++){
                NewSet[i] = super.Flexi[i];
            }
            
            super.length = Ilength;
            super.Flexi = NewSet;
        }
        System.out.println(super.length + " " + index);
        super.Flexi[index] = value;
    }
    
    public int Get(int index){
        if (index > super.length){
            return 0;
        }
        
        return super.Flexi[index];
    }

    public SizingArray(int length){
       super(length); 
    }
}
