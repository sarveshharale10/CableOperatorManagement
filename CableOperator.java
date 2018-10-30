import javax.swing.*;
import javax.swing.event.*;

class ContainerFrame extends JFrame implements ChangeListener{
	JTabbedPane tabbedPane;
	JPanel customerPanel,collectionPanel,complaintPanel;
	ContainerFrame(){
		super("Cable Operator Management - Customer");
		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
			if ("Windows".equals(info.getName())) {
				try{
	            	UIManager.setLookAndFeel(info.getClassName());
	            	break;
				}catch(Exception e){
					System.err.println("Error: "+e.toString());
				}
        	}
		}
		JTabbedPane tabbedPane = new JTabbedPane();

		customerPanel = new CustomerPanel();
		collectionPanel = new CollectionPanel();
		complaintPanel = new ComplaintPanel();

		tabbedPane.add("Customer",customerPanel);
		tabbedPane.add("Collection",collectionPanel);
		tabbedPane.add("Complaints",complaintPanel);

		tabbedPane.addChangeListener(this);

		add(tabbedPane);

		pack();
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void stateChanged(ChangeEvent e){
		JTabbedPane pane = (JTabbedPane) e.getSource();
		int sel = pane.getSelectedIndex();
				
		switch(sel){
			case 0:
			setTitle("Cable Operator Management - Customer");
			break;

			case 1:
			setTitle("Cable Operator Management - Collection");
			break;

			case 2:
			setTitle("Cable Operator Management - Complaints");
			break;

		}
				
	}
}

class CableOperator{
	public static void main(String[] args) {
		new ContainerFrame();
	}
}