package View;

import Libraries.GenericTablePanel;
import fileManager.Filter;
import fileManager.TaxiData;
import fileManager.TaxiDataTableFiller;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    Filter filter;
    public MainWindow(){
        filter = new Filter();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,600,600);

        //Everything needs to be placed here
        JPanel mainContentView = new JPanel();
        mainContentView.setLayout(new BorderLayout());

        /**
         * Setting up toolbar for refining search and misc.
         */
        JToolBar jtToolBar = new JToolBar();
        JButton jbExit = new JButton("Exit");
        JTextField tfSearchBar = new JTextField("Search Here");
        tfSearchBar.setColumns(8);
        JButton jbLoad = new JButton("Load");
        JButton jbDefineFilter = new JButton("Define Filter");
        JButton jbApplyFilter = new JButton("Apply Filter");

        jtToolBar.add(jbExit);
        jtToolBar.add(tfSearchBar);
        jtToolBar.add(jbLoad);
        jtToolBar.add(jbDefineFilter);
        jtToolBar.add(jbApplyFilter);
        mainContentView.add(jtToolBar,BorderLayout.NORTH);

        //Table of TaxiData class
        GenericTablePanel<TaxiData> taxiRides = new GenericTablePanel<>(TaxiData.class);
        TaxiDataTableFiller allRides = new TaxiDataTableFiller();
        mainContentView.add(taxiRides,BorderLayout.CENTER);

        JTextArea tfLogger = new JTextArea();
        Font font = new Font("Times New Roman",Font.PLAIN,12);
        tfLogger.setFont(font);
        tfLogger.setForeground(Color.gray);
        tfLogger.setEditable(false);
        tfLogger.setRows(4);
        mainContentView.add(tfLogger,BorderLayout.SOUTH);

        /**
         * Adding actions
         */
        jbLoad.addActionListener(e -> {
            jbLoad.setEnabled(false);
            String dataLoadMessage;
            String path = tfSearchBar.getText();
            try{
                allRides.setPath(path);
                taxiRides.update(allRides.getAllTaxiTrips());
                dataLoadMessage = "Successfully added all taxi rides.";
            }
            catch (Exception e1){
                dataLoadMessage = "Error whilst loading taxi rides";
            }
            jbLoad.setEnabled(true);

            tfLogger.append(dataLoadMessage + "\n");

        });
        jbDefineFilter.addActionListener(e ->{
            new FilterWindow(filter);
        });
        jbExit.addActionListener(e -> System.exit(0));

        add(mainContentView);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.pack();
            mainWindow.setVisible(true);
        });
    }
}
