
package finalproject;

import java.util.HashMap;
import java.util.Scanner;

public class Interactor {
    Scanner Scan = new Scanner(System.in);
    HashMap storage = new HashMap<>();
    colony Current = null;
    String Key = "";
    int MillisSet = 100; // default 100ms
    boolean Silent = false;
    
    private void Help(){
        
    }
    
    private boolean SetCol(){
        return !Key.equals("");
    }
    
    private void doScan(){
        
        String inp = "";
        while (!inp.equals("exit")){
            System.out.println("Input command : ");
            inp = Scan.nextLine();
            
            
            String[] Main = inp.split(" ");
            if (Main[0] == "q"){
                break;
            }
            switch(Main[0]){
                case "h":{
                    Help();
                }
                case "i":{ //Basic info
                    
                }
                case "a":{//alive x,y
                    if (!Key.equals("")){
                        Current.setCellAlive(Integer.parseInt(Main[1]) - 1, 
                                Integer.parseInt(Main[2]) - 1);
                        Current.Update();
                    }
                    break;
    
                }
                case "d":{
                    if (SetCol()){
                        Current.setCellDead(Integer.parseInt(Main[1]) - 1, 
                                Integer.parseInt(Main[2]) - 1);
                        Current.Update();
                    }
                    break;
                }
                case "e":{
                    try{
                        if (SetCol()){
                            for (int i = 1;i <=Integer.parseInt(Main[1]);i++){
                                Current.evolve();
                                Thread.sleep(MillisSet);
                            }
                        }
                        }
                    catch(Exception E){
                        System.out.println("error : " + E);
                    }
                    break;
                }
                case "r":{
                    if (SetCol()){
                        Current.ResetColony();
                    }
                    break;
                }
                case "s":{
                    Silent = !Silent;
                    break;
                }
                case "p":{
                    MillisSet = Integer.parseInt(Main[1]);
                    break;
                }
                case "c":{
                    Key = Main[1];
                    Current = new colony(Key,Integer.parseInt(Main[2]));
                    storage.put(Key,Current);
                    break;
                }
                case "u":{
                    Key = Main[1];
                    Current = (colony) storage.get(Key);
                    break;
                }
                case "w":{
                    if (SetCol()){
                        Current.ToggleWrap();
                    }
                    break;
                }
                case "rs":{
                    break;
                }
                case "ws":{
                    break;
                }
                case "rAll":{
                    break;
                }
                case "wAll":{
                    break;
                }
                default:{
                    
                }
                   
            }
        }
        System.out.println("Exiting the game of life.");
    }
    
    public Interactor(){
        doScan();
    }
}
