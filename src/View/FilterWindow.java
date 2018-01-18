package View;

import fileManager.Filter;

import javax.swing.*;
import java.awt.*;

public class FilterWindow extends JFrame {
    static Filter filterToApply;



    public FilterWindow(Filter filter){
        filterToApply = filter;
        setBounds(200,200,550,150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel mainContentView = new JPanel();
        mainContentView.setLayout(new BorderLayout());

        /*
        Left part of the filter view
        */
        JPanel leftView = new JPanel();
        leftView.setLayout(new BorderLayout());
        JCheckBox jcSkipRecords = new JCheckBox("Skip Records?");
        JPanel leftMiddle = new JPanel();
        leftMiddle.setLayout(new GridLayout(0,1));
        JCheckBox jcLeaveRecords = new JCheckBox("Leave Records?");
        JCheckBox jcFilterByPayment = new JCheckBox("Filter By Payment Type?");
        leftMiddle.add(jcLeaveRecords);
        leftMiddle.add(jcFilterByPayment);
        JCheckBox jcLimitDistance = new JCheckBox("Limit Distance?");
        leftView.add(jcSkipRecords,BorderLayout.NORTH);
        leftView.add(leftMiddle,BorderLayout.CENTER);
        leftView.add(jcLimitDistance,BorderLayout.SOUTH);
        mainContentView.add(leftView,BorderLayout.WEST);

        /*
        Middle part of the filter view
        */
        JPanel centerView = new JPanel();
        JLabel lblRecordSkip = new JLabel("Number of records to skip: ");
        JPanel jpmiddle = new JPanel();
        jpmiddle.setLayout(new GridLayout(0,1));
        JLabel lblRecordLeave = new JLabel("Number of records to leave: ");
        String[] choices = {"<",">"};
        JComboBox<String> dropDown = new JComboBox<>(choices);
        centerView.add(lblRecordSkip,BorderLayout.NORTH);
        jpmiddle.add(lblRecordLeave);
        centerView.add(jpmiddle,BorderLayout.CENTER);
        centerView.add(dropDown,BorderLayout.SOUTH);
        mainContentView.add(centerView,BorderLayout.CENTER);

        /**
         * Right part of the filter view
         */
        JPanel rightView = new JPanel();
        rightView.setLayout(new BorderLayout());
        JPanel rightCenter = new JPanel();
        rightCenter.setLayout(new GridLayout(0,1));
        JTextField tfSkip = new JTextField();
        JTextField tfLeave = new JTextField();
        JTextField tfDistance = new JTextField();
        JLabel lblpayment = new JLabel("Payment Type?");
        ButtonGroup paymentGroup = new ButtonGroup();
        JRadioButton jrbCash = new JRadioButton("Cash(CHI)");
        JRadioButton jrbCard = new JRadioButton("Card(CRD)");
        JRadioButton jrbUnknown = new JRadioButton("Unknown(UNK)");
        paymentGroup.add(jrbCash);
        paymentGroup.add(jrbCard);
        paymentGroup.add(jrbUnknown);
        rightCenter.add(tfSkip);
        rightCenter.add(tfLeave);
        rightCenter.add(lblpayment);
        rightCenter.add(jrbCash);
        rightCenter.add(jrbCard);
        rightCenter.add(jrbUnknown);
        rightView.add(tfDistance,BorderLayout.NORTH);
        rightView.add(rightCenter,BorderLayout.CENTER);
        rightCenter.add(tfDistance,BorderLayout.SOUTH);
        mainContentView.add(rightCenter,BorderLayout.EAST);


        /*
        Setting up filter view values when initiating view
        */
        jcSkipRecords.setSelected(filterToApply.isSkipRecords());
        jcFilterByPayment.setSelected(filterToApply.isFilterByPaymentType());
        jcLeaveRecords.setSelected(filterToApply.isLeaveRecords());
        jcLimitDistance.setSelected(filterToApply.isLimitDistance());
        tfLeave.setText(String.valueOf(filterToApply.getHowManyToLeave()));
        tfSkip.setText(String.valueOf(filterToApply.getHowManyToSkip()));
        switch (filterToApply.getPaymentType()){
            case 0: jrbCash.setSelected(true);
                    break;
            case 1: jrbCard.setSelected(true);
                    break;
            default: jrbUnknown.setSelected(true);
                    break;
        }
        switch (filter.getDistanceOperator()){
            case 0: dropDown.setSelectedIndex(0);
                    break;
            default: dropDown.setSelectedIndex(1);
                     break;
        }
        tfDistance.setText(String.valueOf(filterToApply.getDistance()));
        tfLeave.setText(String.valueOf(filterToApply.getHowManyToLeave()));
        tfSkip.setText(String .valueOf(filterToApply.getHowManyToSkip()));

        /*
        Setting up returning filter values to the main window
         */
        jcSkipRecords.addActionListener(e -> {
            filterToApply.setSkipRecords(jcSkipRecords.isSelected());
            filterToApply.setHowManyToSkip(Integer.valueOf(tfSkip.getText()));
            jcLeaveRecords.setSelected(false);
            filterToApply.setLeaveRecords(false);
        });
        jcFilterByPayment.addActionListener(e ->{
            filterToApply.setFilterByPaymentType(jcFilterByPayment.isSelected());
        });
        jcLeaveRecords.addActionListener(e -> {
            filterToApply.setLeaveRecords(jcLeaveRecords.isSelected());
            filterToApply.setHowManyToLeave(Integer.valueOf(tfLeave.getText()));
            jcSkipRecords.setSelected(false);
            filterToApply.setSkipRecords(false);

        });
        jcLimitDistance.addActionListener(e -> {
            filterToApply.setLimitDistance(jcLimitDistance.isSelected());
            filterToApply.setDistance(Double.valueOf(tfDistance.getText()));
            filterToApply.setDistanceOperator(dropDown.getSelectedIndex());
        });

        jrbCard.addActionListener(e -> {
            filterToApply.setPaymentType(1);
        });
        jrbCash.addActionListener(e -> {
            filterToApply.setPaymentType(0);
        });
        jrbUnknown.addActionListener(e -> {
            filterToApply.setPaymentType(2);
        });



        add(mainContentView);
        SwingUtilities.invokeLater(()->{
            //pack();
            setVisible(true);
        });
    }

        public static Filter getFilter(){
            return filterToApply;
        }
}
