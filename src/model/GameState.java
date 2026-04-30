package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;



public class GameState implements Serializable {
    private static final long serialVersionUID=1L;
    public List<Order> orders;
    
    public GameState(List<Order> orders){
        this.orders=orders;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void saveGame(String SAVE) throws IOException{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE))){
            out.writeObject(this);
        }

    }

    public static GameState loadGame(String SAVE) throws IOException,ClassNotFoundException{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE))){
            return (GameState) in.readObject();  
        }
    }

}
