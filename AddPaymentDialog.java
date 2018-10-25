import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class AddPaymentDialog{
	JLabel lblCustomerNo,lblCustomerName,lblDue,lblAmount;
	JLabel lblCustomerNoValue,lblCustomerNameValue,lblDueValue;
	JTextField txtAmount;

	int selectedRowIndex;

	AddPaymentDialog(){

	}

	void setSelectedRowIndex(int selectedRowIndex){
		this.selectedRowIndex = selectedRowIndex;
	}
}