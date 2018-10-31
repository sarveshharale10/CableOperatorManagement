import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

class CloseComplaintDialog extends JDialog implements ActionListener{
    JLabel lblComplaintNo,lblDescription,lblRemarks;
    JLabel lblComplaintNoValue;
    JTextArea txtAreaDescription,txtAreaRemarks;
    JButton btnCloseComplaint,btnCancel;

    int selectedRowIndex;
    DefaultTableModel complaintModel;
    ComplaintDao complaintDao;

    CloseComplaintDialog(int selectedRowIndex){
        setTitle("Close Complaint");
        this.selectedRowIndex = selectedRowIndex;

        lblComplaintNo = new JLabel("Complaint No:",SwingConstants.RIGHT);
        lblDescription = new JLabel("Description:",SwingConstants.LEFT);
        lblRemarks = new JLabel("Remarks:",SwingConstants.LEFT);

        lblComplaintNoValue = new JLabel();
        txtAreaDescription = new JTextArea(10,40);
        txtAreaDescription.setEditable(false);
        txtAreaRemarks = new JTextArea(10,40);

        btnCloseComplaint = new JButton("Close Complaint");
        btnCancel = new JButton("Cancel");

        complaintModel = ComplaintViewModelFactory.getInstance();
        complaintDao = new ComplaintDao();

        String complaintNo = (String)complaintModel.getValueAt(selectedRowIndex,0);
        String description = (String)complaintModel.getValueAt(selectedRowIndex,3);
        
        lblComplaintNoValue.setText(complaintNo);
        txtAreaDescription.setText(description);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10,10,10,10);
        add(lblComplaintNo,gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(lblComplaintNoValue,gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        add(lblDescription,gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        add(txtAreaDescription,gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        add(lblRemarks,gc);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        add(txtAreaRemarks,gc);

        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 1;
        add(btnCloseComplaint,gc);

        gc.gridx = 1;
        gc.gridy = 5;
        add(btnCancel,gc);

        pack();

        btnCloseComplaint.addActionListener(this);
        btnCancel.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();

        if(src.equals(btnCloseComplaint)){
            int complaintNo = Integer.parseInt(lblComplaintNoValue.getText());
            Date dateClosed = new Date(System.currentTimeMillis());
            String remarks = txtAreaRemarks.getText();
            complaintDao.closeComplaint(complaintNo, dateClosed, remarks);
            complaintModel.removeRow(selectedRowIndex);
            JOptionPane.showMessageDialog(this,"Complaint Registered");
        }
        else if(src.equals(btnCancel)){
            setVisible(false);
            dispose();
        }
    }

}