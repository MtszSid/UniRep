package plansza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Board {

    static int N = 10;
    static int numberOfBeans = 80;
    static int maxValue = 100;

    int positionOf0;
    List<Thread> threads;
    AtomicReferenceArray<NumberBean> board;

    public Board(){
        board = new AtomicReferenceArray<>(N*N);
        List<Integer> permutation = new ArrayList<>();
        threads = new ArrayList<>();

        for(int i = 0; i < N*N; i++){
            permutation.add(i);
        }

        Collections.shuffle(permutation);

        NumberBean bean = new NumberBean();
        bean.setVal(0);
        bean.setX(permutation.get(0) % 10);
        bean.setY(permutation.get(0) / 10);
        board.set(permutation.get(0), bean);

        positionOf0 = permutation.get(0);

        Thread t = new Thread(new Mover(bean));
        threads.add(t);

        for(int i = 1; i < numberOfBeans; i++){
            bean = new NumberBean();
            bean.setVal((int)(Math.random() * maxValue) + 1);
            bean.setX(permutation.get(i) % 10);
            bean.setY(permutation.get(i) / 10);

            board.set(permutation.get(i), bean);

            t = new Thread(new Mover(bean));
            threads.add(t);
        }

        for (Thread i :threads) {
            i.start();
        }
    }

    public int getSize(){
        return N;
    }

    class Mover implements Runnable{

        public Mover(NumberBean b){
            super();
            bean = b;
        }

        NumberBean bean;
        @Override
        public void run() {
            int val = bean.getVal();
            int x, y;
            while(!bean.isDead()){
                x = bean.getX();
                y = bean.getY();
                if(val == 0){
                    int dir = ((int)(Math.random() * 4));
                    int dx = 0, dy = 0;
                    switch (dir){
                        case(0):
                            if(y > 0)
                                dy = - 1;
                            else
                                dy = 1;
                            break;
                        case(1):
                            if(x < Board.N - 1)
                                dx = 1;
                            else
                                dx = - 1;
                            break;
                        case(2):
                            if(y < Board.N - 1)
                                dy = 1;
                            else
                                dy = - 1;
                            break;
                        case(3):
                            if(x > 0)
                                dx = - 1;
                            else
                                dx = 1;
                            break;
                    }
                    int currentPosition = y * Board.N + x;
                    int updatedPosition = (y + dy) * Board.N + x + dx;

                    NumberBean tempBean = Board.this.board.getAndSet(updatedPosition, bean);
                    if(tempBean == null){
                        Board.this.board.compareAndSet(currentPosition, bean, null);
                    }
                    else if (!tempBean.isPrime()){
                        tempBean.setDead(true);
                        Board.this.board.compareAndSet(currentPosition, bean, null);
                    }
                    else{
                        Board.this.board.compareAndSet(currentPosition, bean, tempBean);
                        tempBean.trySetXY(x + dx, y + dy, x, y);
                    }
                    bean.setX(x + dx);
                    bean.setY(y + dy);
                    Board.this.positionOf0 = updatedPosition;
                    try {
                        Thread.sleep(500 + (int)(Math.random() * 200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    int updatedPosition = getUpdatedPosition(x, y);
                    int currentPosition = y * Board.N + x;

                    NumberBean tempBean = Board.this.board.get(updatedPosition);

                    if(tempBean != null){
                        updatedPosition = getUpdatedPosition(x, y);
                        tempBean = Board.this.board.get(updatedPosition);
                    }

                    if(tempBean == null){
                        if(Board.this.board.compareAndSet(updatedPosition, null, bean)){
                            if(Board.this.board.compareAndSet(currentPosition, bean, null)){
                                bean.trySetXY(x, y,updatedPosition % Board.N, updatedPosition / Board.N);
                                try {
                                    Thread.sleep(500 + (int)(Math.random() * 200));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                Board.this.board.compareAndSet(updatedPosition, bean, null);
                            }
                        }
                    }
                    else{
                        try {
                            Thread.sleep(500 + (int)(Math.random() * 200));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            x = bean.getX();
            y = bean.getY();
            Board.this.board.compareAndSet(y * Board.N + x, bean, null);
        }

        enum Direction{
            UP, RIGHT, LEFT, DOWN;
        }

        private int getUpdatedPosition(int x, int y) {
            int x0 = Board.this.positionOf0 % 10;
            int y0 = Board.this.positionOf0 / 10;

            List<Direction> towards  = new LinkedList<>();
            List<Direction> opposite = new LinkedList<>();

            if(x0 == x){
                opposite.add(Direction.LEFT);
                opposite.add(Direction.RIGHT);
            }
            else if(x > x0){
                opposite.add(Direction.RIGHT);
                towards.add(Direction.LEFT);
            }
            else {
                towards.add(Direction.RIGHT);
                opposite.add(Direction.LEFT);
            }

            if(y0 == y){
                opposite.add(Direction.UP);
                opposite.add(Direction.DOWN);
            }
            else if(y < y0){
                towards.add(Direction.UP);
                opposite.add(Direction.DOWN);
            }
            else {
                opposite.add(Direction.UP);
                towards.add(Direction.DOWN);
            }

            int dist = (int)Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));

            int dir = (int)(Math.random() * 2 * Math.sqrt(2) * Board.N);

            Direction direction;

            if(dist <= dir && towards.size() > 0){
                direction = towards.get((int)(Math.random() * towards.size()));
            }
            else{
                direction = opposite.get((int)(Math.random() * opposite.size()));
            }

            int dx = 0,dy = 0;
            switch (direction) {
                case UP     -> dy =  1;
                case DOWN   -> dy = -1;
                case LEFT   -> dx = -1;
                case RIGHT  -> dx =  1;
            }

            if(y == 0 && dy == -1)
                dy = 1;
            else if(y == Board.N - 1 && dy == 1)
                dy = -1;

            if(x == Board.N - 1 && dx == 1)
                dx = -1;
            else if (x == 0 && dx == -1)
                dx = 1;

            return (y + dy) * Board.N + x + dx;
        }
    }
}
