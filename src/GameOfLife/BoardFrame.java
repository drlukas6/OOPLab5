package GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPanel bottomPanel = new JPanel();
        JLabel aliveFields = new JLabel("Make Certain Fields Alive: ");
        JButton diagAlive = new JButton("Diagonals");
        JButton borderAlive = new JButton("Borders");
        JButton alive = new JButton("Make Alive");
        JTextField coordinatesX = new JTextField("0");
        coordinatesX.setColumns(2);
        JTextField coordinatesY = new JTextField("0");
        coordinatesY.setColumns(2);

        JTextField refreshRatems = new JTextField("500");
        refreshRatems.setColumns(4);

        bottomPanel.add(aliveFields);
        bottomPanel.add(diagAlive);
        bottomPanel.add(borderAlive);
        bottomPanel.add(coordinatesX);
        bottomPanel.add(coordinatesY);
        bottomPanel.add(alive);

        JToolBar toolBar = new JToolBar();
        JButton start = new JButton("Start");
        JButton iteration = new JButton("One Iteration");
        JButton stop = new JButton("Stop");
        toolBar.add(refreshRatems);
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
        content.add(bottomPanel,BorderLayout.SOUTH);
        add(content);
        setSize(700,700);

        //Actions:
        start.addActionListener(e -> {
            start.setEnabled(false);
            stop.setEnabled(true);
            iteration.setEnabled(false);
            if(refreshRatems.getText().length()==0){
                simulationThread = new SimulationThread(500);
                simulationThread.start();
            }
            else {
                try{
                simulationThread = new SimulationThread(Integer.parseInt(refreshRatems.getText()));
                simulationThread.start();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Unsuccessfully parsed lenght.\n Please try again.\n");
                }


            }


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

        diagAlive.addActionListener(e -> {
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    if (i == j) fields[i][j].setSelected(true);
                }
            }
        });

        borderAlive.addActionListener(e -> {
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    if (i==0 || j == 0 || i==x-1 || j==y-1) fields[i][j].setSelected(true);
                }
            }
        });



        alive.addActionListener(e -> {
            try{
                int coordinateX = Integer.parseInt(coordinatesX.getText());
                int coordinateY  = Integer.parseInt(coordinatesY.getText());

                fields[coordinateY][coordinateX].setSelected(true);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Unsuccessfully parsed coordinates.\nPlease try again.\n");
            }
        });


    }


    class SimulationThread extends Thread{

        private boolean stopped = false;
        private int refreshRate;
        public SimulationThread(int refreshRate){
            this.refreshRate = refreshRate * 1000;
        }

        @Override
        public void run() {

            while(!stopped){
                board.playOneIteration();

                try{
                    Thread.sleep(refreshRate);
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
