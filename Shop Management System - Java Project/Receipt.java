import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class Receipt extends JFrame{
	private String [] COLUMN_HEADER = {"Product Name", "Quantity", "Per Unit Price","Total Price"};
	private String [] [] COLUMN_VALUE = {};
	private JButton btnClose = new JButton("Close");
	private JLabel lblReceipt = new JLabel("Payment Receipt", SwingConstants.CENTER);
	private JLabel lblcustomername;
	private JLabel lbldate;
	private JLabel lbltotalbillamount;
	private JLabel lblcashrecieve;
	private JLabel lblchangedamount;
	private JLabel lblServedBy;
	
	private JPanel receiptPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel topPanel=new JPanel();
	private JPanel endPanel=new JPanel();
	
	private String customerName;
	private String servedBy;
	private String buyDate;
	private String totalBill;
	private String cashReceived;
	private String changedAmount;
	private JScrollPane scrollPane;
	
	private DefaultTableModel model = new DefaultTableModel(COLUMN_VALUE, COLUMN_HEADER);
	private JTable tableproductInfo = new JTable(model){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	

	public Receipt()
		 {
		super();
		//setUndecorated(true);
		setMinimumSize(new Dimension(600, 600));
		setVisible(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		
	}
	
	public void showReceipt(String customerName, String servedBy, String buyDate,
			String totalBill, String cashReceived, String changedAmount,
			DefaultTableModel model, JTable tableproductInfo)
	{
		this.customerName = customerName;
		this.servedBy = servedBy;
		this.buyDate = buyDate;
		this.totalBill = totalBill;
		this.cashReceived = cashReceived;
		this.changedAmount = changedAmount;
		this.model = model;
		this.tableproductInfo = tableproductInfo;
		this.scrollPane = scrollPane;
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tableproductInfo);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
	    lblReceipt.setFont(new Font("Courier new", Font.BOLD, 22));
		receiptPanel.setLayout(new BorderLayout(10, 10));
		centerPanel.setLayout(new BorderLayout());
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS));
		receiptPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 10));
		
		init_Labels();
		
		///Align Labels
		lblcustomername.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblServedBy.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lbldate.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lbltotalbillamount.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblcashrecieve.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		lblchangedamount.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		addToGUI();
		
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		this.setVisible(true);
	}

	private void init_Labels() {
		
		lblcustomername = new JLabel("Customer Name: " + customerName, SwingConstants.CENTER);
		lbldate = new JLabel("Buy Date: " + buyDate, SwingConstants.CENTER);
		lbltotalbillamount=new JLabel("Total Bill Amount: " + totalBill, SwingConstants.CENTER);
		lblcashrecieve=new JLabel("Cash Received: " + cashReceived, SwingConstants.CENTER);
		lblchangedamount=new JLabel("Changed Amount: " + changedAmount, SwingConstants.CENTER);
		lblServedBy = new JLabel("Served By: " + servedBy, SwingConstants.CENTER);
	}

	private void addToGUI() {
		
		topPanel.add(lblcustomername);
		topPanel.add(lblServedBy);
		topPanel.add(lbldate);
		
		endPanel.add(lbltotalbillamount);
		endPanel.add(lblcashrecieve);
		endPanel.add(lblchangedamount);
		
		centerPanel.add(topPanel,BorderLayout.PAGE_START);
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		centerPanel.add(endPanel,BorderLayout.PAGE_END);
		
		
		receiptPanel.add(btnClose, BorderLayout.PAGE_END);
		receiptPanel.add(lblReceipt, BorderLayout.PAGE_START);
		receiptPanel.add(centerPanel, BorderLayout.CENTER);
		add(receiptPanel, BorderLayout.CENTER);
	}
}
