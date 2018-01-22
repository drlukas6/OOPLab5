package GameOfLife;


import java.util.LinkedList;
import java.util.List;

public class Board  {
    private int size_x, size_y;
    private boolean[][] fieldActivity;
    private List<BoardListener> listeners;

    public Board(int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;
        listeners = new LinkedList<>();
        fieldActivity = new boolean[size_x][size_y];
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                fieldActivity[i][j] = false;
            }
        }
    }

    /*
    private class BoardThread extends Thread{
        private boolean stopped = false;
        @Override
        public void run() {
            while(!stopped){

            }
        }

        @Override
        public void interrupt() {
            stopped = true;
        }

    }

    */

    public boolean isCellAlive(int x, int y) {
        return fieldActivity[x][y];
    }

    public int getWidth() {
        return size_x;
    }

    public int getHeight() {
        return size_y;
    }

    public void setCell(int x, int y, boolean alive){
        this.fieldActivity[x][y] = alive;
    }

    public int countAliveNeighbors(int x, int y) {

        int cnt = 0;

        int fix_x, fix_y;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                if(i == 0 && j == 0) continue;
                fix_x = (x + i) % size_x;
                fix_y = (y + j) % size_y;
                if(fix_x < 0) fix_x += size_x;
                if(fix_y < 0) fix_y += size_y;
                if(fieldActivity[fix_x][fix_y]) cnt++;
            }
        }
        return cnt;
    }

    public void playOneIteration() {
        for(int i = 0; i < size_x; i++){
            for(int j = 0; j < size_y; j++){
                int cntAlive = countAliveNeighbors(i, j);
                //System.err.println(String.format("%d %d", i,j));
                if(isCellAlive(i,j) && cntAlive > 3) fieldActivity[i][j] = false;
                else if(isCellAlive(i,j) && cntAlive < 2) fieldActivity[i][j] = false;
                else if(!isCellAlive(i,j) && cntAlive == 3) fieldActivity[i][j] = true;
            }
        }
        for(BoardListener boardListener : listeners) boardListener.boardChanged(this);
    }

    public void addListener(BoardListener listener) {
        listeners.add(listener);
    }


}
