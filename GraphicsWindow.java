package finalproject;

import java.awt.*;
import java.awt.event.*;

public class GraphicsWindow extends Frame{
    private String Name = "";
    private int Size = 500;
    private int BoxSize = 0;
    private int ColonySize = 0;
    private colony Colony;
    
    
    public void paint(Graphics G){
        setSize(Size + 6,Size + 28);
        for (int x = 0;x< ColonySize;x++){
            for (int y = 0;y< ColonySize;y++){
                if (Colony.isCellAlive(x, y) == true){
                    G.fillRect(3 + (2 * x) + (BoxSize * x),1 + 25 + (2 * y) + (BoxSize * y), BoxSize, BoxSize);
                    
                }else{
                    G.drawRect(3 + (2 * x) + (BoxSize * x),1 + 25 + (2 * y) + (BoxSize * y), BoxSize, BoxSize);
                }
            }
        };
    }
    
    public void UpdateColony(){
        repaint();
    }
    
    private void Setup(){
        addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
           System.out.println("Colony " + Name + " has been destroyed.");
           setEnabled(false);
           dispose();
         }
        });
        setSize(Size + 6,Size + 28);
        super.setVisible(true);
    }
    
    public GraphicsWindow(String Name,colony Col){
        super(Name);
        this.Name = Name;
        this.Colony = Col;
        this.ColonySize = Colony.getColonySize();
        this.BoxSize = ((Size - (2 * ColonySize)) / ColonySize);
        System.out.println(BoxSize);
        Setup();
        
    }
}