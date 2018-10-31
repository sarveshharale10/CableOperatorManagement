import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

class AddPaymentDialog extends JDialog implements ActionListener{
    JLabel lblCustomerNo,lblCustomerName,lblDueAmount,lblAmountReceived;
    JLabel lblCustomerNoValue,lblCustomerNameValue,lblDueAmountValue;
    JTextField txtAmount;
    JButton btnAdd,btnCancel;

    DefaultTableModel collectionModel;

    PaymentDao paymentDao;

    int selectedRowIndex;

    AddPaymentDialog(int selectedRowIndex){
        setTitle("Add Payment");

        this.selectedRowIndex = selectedRowIndex;

        lblCustomerNo = new JLabel("Customer No:");
        lblCustomerName = new JLabel("Customer Name:");
        lblDueAmount = new JLabel("Due Amount:");
        lblAmountReceived = new JLabel("Amount Received:");

        txtAmount = new JTextField(10);

        btnAdd = new JButton("Add");
        btnCancel = new JButton("Cancel");

        paymentDao = new PaymentDao();

        collectionModel = CollectionViewModelFactory.getInstance();
        lblCustomerNoValue = new JLabel((String)collectionModel.getValueAt(selectedRowIndex,0));
        lblCustomerNameValue = new JLabel((String)collectionModel.getValueAt(selectedRowIndex,1));
        lblDueAmountValue = new JLabel((String)collectionModel.getValueAt(selectedRowIndex,2));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10,10,10,10);
        add(lblCustomerNo,gc);
        
        gc.gridx = 1;
		gc.gridy = 0;		
		add(lblCustomerNoValue,gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(lblCustomerName,gc);

		gc.gridx = 1;
		gc.gridy = 1;
        add(lblCustomerNameValue,gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        add(lblDueAmount,gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(lblDueAmountValue,gc);

        gc.gridx = 0;
        gc.gridy = 3;
        add(lblAmountReceived,gc);

        gc.gridx = 1;
        gc.gridy = 3;
		add(txtAmount,gc);

		gc.gridx = 0;
        gc.gridy = 4;
		add(btnAdd,gc);
		
		gc.gridx = 1;
        gc.gridy = 4;
		add(btnCancel,gc);
        
        btnAdd.addActionListener(this);
        btnCancel.addActionListener(this);

		pack();

    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();

        if(src.equals(btnAdd)){
            int customerNo = Integer.parseInt(lblCustomerNoValue.getText());
            Date date = new Date(System.currentTimeMillis());
            int amount = Integer.parseInt(txtAmount.getText());
            paymentDao.addPayment(customerNo,date,amount);
            int newDue = Integer.parseInt(collectionModel.getValueAt(selectedRowIndex,2).toString()) - amount;
            if(newDue == 0){
                collectionModel.removeRow(selectedRowIndex);
            }
            else{
                collectionModel.setValueAt(Integer.toString(newDue),selectedRowIndex,2);
            }

            JOptionPane.showMessageDialog(this,"Payment Added Successfully");
        }
        else if(src.equals(btnCancel)){
            setVisible(false);
			dispose();
        }
    }
}