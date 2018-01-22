package GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BoardFrame extends JFrame{

    private Board board;
    SimulationThread simulationThread;

    public BoardFrame(int x, int y){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        board = new Board(x, y);

        JPanel content = new JPanel(new BorderLayout());
        JPanel buttonSpace = new JPanel(new GridLayout(x,y));
        JPanel toolBarSpace = new JPanel();

        JToolBar toolBar = new JToolBar();
        JButton start = new JButton("Start");
        JButton iteration = new JButton("One Iteration");
        JButton stop = new JButton("Stop");
        toolBar.add(start);
        toolBar.add(iteration);
        toolBar.add(stop);
        toolBarSpace.add(toolBar);

        JToggleButton[][] fields = new JToggleButton[y][x];
        for(int i=0; i < x; i++){
            for(int j=0; j < y; j++){
                Random rn = new Random();
                JToggleButton toAdd = new JToggleButton();

                fields[i][j] = toAdd;
                buttonSpace.add(toAdd);

                final int xx = i, yy = j;
                fields[i][j].addActionListener(e -> {
                   // if(board.isCellAlive(xx,yy)) fields[xx][yy].setSelected(true);
                   // else fields[xx][yy].setSelected(false);
                    board.setCell(xx,yy,!board.isCellAlive(xx,yy));
                });
                
                board.addListener(new BoardListener(fields[i][j], i, j));
            }
        }


        content.add(buttonSpace,BorderLayout.CENTER);
        content.add(toolBarSpace,BorderLayout.NORTH);
        add(content);
        setSize(500,500);

        //Actions:
        start.addActionListener(e -> {
            start.setEnabled(false);
            stop.setEnabled(true);
            iteration.setEnabled(false);
            simulationThread = new SimulationThread();
            simulationThread.start();

        });

        stop.addActionListener(e -> {
            start.setEnabled(true);
            stop.setEnabled(false);
            iteration.setEnabled(true);
            simulationThread.interrupt();
        });

        iteration.addActionListener(e -> {
            board.playOneIteration();
        });


    }


    class SimulationThread extends Thread{

        private boolean stopped = false;

        @Override
        public void run() {

            while(!stopped){
                board.playOneIteration();

                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void interrupt() {
            stopped = true;
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            BoardFrame game = new BoardFrame(30,30);
            game.setVisible(true);
        });
    }

}
