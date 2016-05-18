package finalproject;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class colony {
    private GraphicsWindow Window;
    private Array2D ColonyListing;
    private int BoxSize;
    private int Gen = 0;
    private String ColonyName = "";
    private boolean sparse = false;
    private boolean Wrap = false;
    
    private void Save() throws IOException{
        try (BufferedWriter O = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(ColonyName + ".txt"), "utf-8"))) {
            for (int i = 0;i<= BoxSize;i++){
                for (int k = 0;i<= BoxSize;i++){
                    if (isCellAlive(i,k)){
                        O.write("1");
                    }else{
                        O.write("0");
                    }
                }
                O.write(" ");
            }
            O.close();
        }
    }
    
    public void save(){
        try {
            Save();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    } 
    
    public void ToggleWrap(){
        Wrap = !Wrap;
    }
    
    public void Load(){
        Stream<String> fileInst = null;
        try{
            fileInst = Files.lines(Paths.get(ColonyName + ".txt"));
        }catch(Exception FileNotFound){
            System.out.println("Save file not found.");
        }
        if (fileInst != null){
            int x = 0;
            int y = 0;
            for (String i : fileInst.toArray(String[]::new)){
                x++;
                if (i == "1"){
                    setCellAlive(x,y);
                }else if (i == "0"){
                    setCellDead(x,y);
                }else{
                    y++;
                    x = 0;
                }
                System.out.println(x + " " + y);
                
            }
        }else{
            System.out.println("File not found.");
        }
    }
    
    public int getColonySize(){
        return BoxSize;
    }
    
    public int getGenerationNumber(){
        return Gen;
    }
    
    public String getName(){
        return ColonyName;
    }
    
    public void ResetColony(){
        ColonyListing = new Array2D(BoxSize + 2,BoxSize + 2,true);
        Update();
    }
    
    public void setCellAlive(Array2D Table,int Col,int Row){
        Table.Set(GetX(Col), GetY(Row), 1);
    }
    public void setCellAlive(int Col,int Row){
        setCellAlive(ColonyListing,Col,Row);
    }
    
    public void setCellDead(Array2D Table,int Col,int Row){
        Table.Set(GetX(Col), GetY(Col), 0);
    }
    public void setCellDead(int Col,int Row){
        setCellDead(ColonyListing,Col,Row);
    }
    
    public final boolean isCellAlive(int Col,int Row){
        if (ColonyListing.Get(Col + 1, Row + 1) == 1){
            return true;
        }
        return false;
    }
    
    private int Get(int Col,int Row){
        return ColonyListing.Get(GetX(Col),GetY(Row));
    }
    
    private int GetX(int Col){
        if (Col == 0 && Wrap){
            Col = BoxSize - 1;
        }else{
            Col = Col + 1;
        }   
        return Col;
    }
    
    private int GetY(int Row){
        if (Row == 0 && Wrap){
            Row = BoxSize - 1;
        }else{
            Row = Row + 1;
        }
        return Row;
    }
    
    public int GetSurround(int Col,int Row){
        int Tot = 0;
        Tot = Tot + Get(Col - 1,Row - 1);
        Tot = Tot + Get(Col - 1,Row + 1);
        Tot = Tot + Get(Col + 1,Row - 1);
        Tot = Tot + Get(Col + 1,Row + 1);
        Tot = Tot + Get(Col,Row + 1);
        Tot = Tot + Get(Col,Row - 1);
        Tot = Tot + Get(Col + 1,Row);
        Tot = Tot + Get(Col - 1,Row);
        return Tot;
    }
    
    public void evolve(){
       Array2D New = new Array2D(BoxSize + 2,BoxSize + 2,true);
       for (int x = 0;x<= BoxSize;x++){ // Col
            for (int y = 0;y<= BoxSize;y++){ // Row
                boolean CellAlive = isCellAlive(x,y);
                int Surround = GetSurround(x,y);
                
                if (CellAlive == false && Surround == 3){
                    setCellAlive(New,x,y);
                }else if ((Surround < 2 || Surround > 3) && CellAlive == true){
                    setCellDead(New,x,y);
                }else if ((Surround == 2 || Surround == 3) && CellAlive == true){
                    setCellAlive(New,x,y);
                }
               
            }
        }
        ColonyListing = New;
        Update();
    }
    
    public String toString(){
        String St = "";
        for (int x = 0;x<= BoxSize;x++){
            for (int y = 0;y<= BoxSize;y++){
                if (isCellAlive(x,y)){
                    St = St + "*";
                }else{
                    St = St + "-";
                }
                St = St + " ";
            }
            St = St + "\n";
        }
        return St;
    }
    
    public int getNumberLivingCells(){
        int T = 0;
        for (int x = 0;x<= BoxSize;x++){
            for (int y = 0;y<= BoxSize;y++){
                if (isCellAlive(x,y)){
                    T++;
                }
            }
        }
        return T;
    }
    
    public void Update(){
        this.Window.UpdateColony();
    }
    
    public colony(String Name, int SquareSize){
        BoxSize = SquareSize;
        ColonyName = Name;
        this.ColonyListing = new Array2D(SquareSize + 2,SquareSize + 2,true);
        this.Window = new GraphicsWindow(Name,this);
    }
}
