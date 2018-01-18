package View;

import fileManager.Filter;

import javax.swing.*;
import java.awt.*;

public class FilterWindow extends JFrame {
    public FilterWindow(Filter filter){
        setBounds(200,200,550,150);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JPanel mainContentView = new JPanel();
        mainContentView.setLayout(new BorderLayout());

        /**
         * Left part of the filter view
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

        /**
         * Middle part of the filter view
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


        /**
         * Setting up filter view values
         */
        jcSkipRecords.setSelected(filter.isSkipRecords());
        jcFilterByPayment.setSelected(filter.isFilterByPaymentType());
        jcLeaveRecords.setSelected(filter.isLeaveRecords());
        jcLimitDistance.setSelected(filter.isLimitDistance());
        tfLeave.setText(String.valueOf(filter.getHowManyToLeave()));
        tfSkip.setText(String.valueOf(filter.getHowManyToSkip()));
        switch (filter.getPaymentType()){
            case 0: jrbCash.setSelected(true);
                    break;
            case 1: jrbCard.setSelected(true);
                    break;
            default: jrbUnknown.setSelected(true);
                    break;
        }


        add(mainContentView);
        SwingUtilities.invokeLater(()->{
            //pack();
            setVisible(true);
        });


    }
}
