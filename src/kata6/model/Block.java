package kata6.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Block {
    private int x;
    private int y;
    public static int MAX = 9;
    private final Timer timer;
    private List<Observer> observers;
    
    public Block(int x, int y){
        this.y = y;
        this.x = x;
        timer = new Timer();
        //timer.schedule(task(), 0, 50); //automatic random movement
        observers = new ArrayList<>();
    }
    
    public void left(){
        if(x == 0) return;
        x--;
        updated();
    }
    
    public void right(){
        if(x+1== MAX) return;
        x++;
        updated();
    }
    
    public void up(){
        if(y+1 == MAX) return;
        y++;
        updated();
    }
    
    public void down(){
        if(y == 0) return;
        y--;
        updated();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private TimerTask task() {
        return new TimerTask() {
            @Override
            public void run() {
                double r = Math.random();
                if (r >= 0.2) return;
                System.out.println(r);
                if (r >= 0.15) left();
                else if (r >= 0.1) right();
                else if (r >= 0.05) up();
                else if (r >= 0.00) down();
                updated();
            }
        };
    }
    
    private void updated(){
        for (Observer observer : observers) observer.updated();
    }
    
    public void register(Observer observer) {
        observers.add(observer);
    }

    public void pos(int x, int y) {
        this.x = x;
        this.y = y;
        updated();
    }
    
    public interface Observer {
        void updated();
    }
}
