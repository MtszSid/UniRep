package plansza;

import java.io.Serializable;

public class NumberBean implements Serializable {
    private int x;
    private int y;
    private int val;
    private boolean prime;
    private boolean dead;

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVal(int val) {
        this.val = val;
        prime = testIfPrime(val);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVal() {
        return val;
    }

    public boolean isPrime() {
        return prime;
    }

    public synchronized boolean trySetXY(int prevX, int prevY, int newX, int newY){
        if(x == prevX && y == prevY){
            x = newX;
            y = newY;
            return true;
        }
        return false;
    }

    private boolean testIfPrime(int val) {
        int lim = (int) Math.floor(Math.sqrt(val));
        if(val == 0 || val == 1){
            return false;
        }
        if(val == 2){
            return true;
        }
        if(val % 2  == 0){
            return false;
        }
        for(int i = 3; i <= lim; i += 2){
            if(val % i  == 0){
                return false;
            }
        }
        return true;
    }
}
