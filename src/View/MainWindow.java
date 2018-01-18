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
        JScrollPane jsBotton = new JScrollPane(tfLogger);

        mainContentView.add(jsBotton,BorderLayout.SOUTH);

        /**
         * Adding actions
         */
        jbLoad.addActionListener(e -> {
            jbLoad.setEnabled(false);
            jbDefineFilter.setEnabled(false);
            jbApplyFilter.setEnabled(false);
            String dataLoadMessage;
            String path = tfSearchBar.getText();
            try{
                allRides.setPath(path);
                taxiRides.update(allRides.getAllTaxiTrips());
                dataLoadMessage = "Successfully added all taxi rides.";
                System.out.println(allRides.getAllTaxiTrips().size());
            }
            catch (Exception e1){
                dataLoadMessage = "Error occurred while loading taxi rides";
                e1.printStackTrace();
            }
            jbLoad.setEnabled(true);
            jbDefineFilter.setEnabled(true);
            jbApplyFilter.setEnabled(true);

            tfLogger.append(dataLoadMessage + "\n");

        });
        jbDefineFilter.addActionListener(e ->{
            new FilterWindow(filter);
        });
        jbExit.addActionListener(e -> System.exit(0));
        jbApplyFilter.addActionListener(e -> {
            filter = FilterWindow.getFilter();
            tfLogger.append("Obtained filter from filter selection view\n");
            tfLogger.append(filter.toString());
            allRides.setFilterPresent(true);
            allRides.setTripFilter(filter);

            jbLoad.setEnabled(false);
            jbDefineFilter.setEnabled(false);
            jbApplyFilter.setEnabled(false);
            String dataLoadMessage;
            String path = tfSearchBar.getText();
            try{
                allRides.setPath(path);
                taxiRides.update(allRides.getAllTaxiTrips());
                dataLoadMessage = "Successfully added all taxi rides.";
                System.out.println(allRides.getAllTaxiTrips().size());
            }
            catch (Exception e1){
                dataLoadMessage = "Error occurred while loading taxi rides";
                e1.printStackTrace();
            }
            jbLoad.setEnabled(true);
            jbDefineFilter.setEnabled(true);
            jbApplyFilter.setEnabled(true);

            tfLogger.append(dataLoadMessage + "\n");
        });

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
