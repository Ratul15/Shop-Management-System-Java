import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class SellProduct extends JPanel {
	private Receipt receipt = new Receipt();
	private double grandTotalPrice = 0;
	private int itemCount = 0;
	private String [] COLUMN_HEADER = {"Product Name", "Quantity", "Per Unit Price","Total Price"};
	private String [] [] COLUMN_VALUE = {};
	
	private JLabel lblMenuName = new JLabel("Sell Product  Menu", SwingConstants.CENTER);
	private JPanel panelCenter=new JPanel();
	private JPanel panelLabels = new JPanel();
	private JPanel paneltxtPanel = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JComboBox comboBoxCustomerName = new JComboBox();
	private JComboBox comboBoxProductName = new JComboBox();
	
	private JLabel lblcustomername = new JLabel("Customer Name: ");
	private JLabel lblproductcname= new JLabel("Product Name: ");
	private JLabel lblproductquantity = new JLabel("Product Quantity: ");
	private JLabel lbltotalprice = new JLabel("Grand Total: ");
	private JLabel lblpaidamount = new JLabel("Paid Amount: ");
	private JLabel lblbackmoney = new JLabel("Changed Amount: ");
	
	private JTextField txtproductquantity = new JTextField();
	private JLabel txttotalprice= new JLabel("0");
	private JTextField txtpaidamount= new JTextField(SwingConstants.CENTER);
	private JLabel txtbackamount = new JLabel("0");
	
	private JScrollPane scrollPane;
	private String employeeName;
	
	private JButton btnadditem = new JButton("Add Item");
	private JButton btndeleteitem = new JButton("Delete Item");
	private JButton btncustomerlistrefresh = new JButton("Refresh Customer List");
	private JButton btnproductlistrefresh = new JButton("Refresh Product List");
	private JButton btnshowreceive = new JButton("Show Receipt");
	
	private DefaultTableModel model = new DefaultTableModel(COLUMN_VALUE, COLUMN_HEADER);
	private JTable tableproductInfo = new JTable(model){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	private String userName;
	
	 public SellProduct(String userName) {
		this.userName = userName;
		setVisible(true);
		setLayout(new BorderLayout());
		
		panelCenter.setLayout(new BorderLayout());
		
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
		paneltxtPanel.setLayout(new BoxLayout(paneltxtPanel, BoxLayout.Y_AXIS));
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tableproductInfo);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		comboBoxCustomerName.setMaximumSize(new Dimension(Integer.MAX_VALUE, comboBoxCustomerName.getMinimumSize().height+5));
		comboBoxProductName.setMaximumSize(new Dimension(Integer.MAX_VALUE, comboBoxProductName.getMinimumSize().height+5));
		txtproductquantity.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtproductquantity.getMinimumSize().height+5));
		txttotalprice.setMaximumSize(new Dimension(Integer.MAX_VALUE, txttotalprice.getMinimumSize().height+5));
		txtpaidamount.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtpaidamount.getMinimumSize().height+5));
		txtbackamount.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtbackamount.getMinimumSize().height));
		
		///Adding Customer List
		String queryShow = "SELECT * FROM `customer`";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
        ResultSet rs = null; /// for getting data
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
			st = con.createStatement();//create statement
			rs = st.executeQuery(queryShow);//getting result
			
			while(rs.next())
			{
			    String Name = rs.getString("name");
			    comboBoxCustomerName.addItem(Name);		
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
            {
            	if(rs != null)
            	{
            		rs.close();
            	}
            	
                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex1){}
		}
		///Adding Products List
        String queryShow1 = "SELECT * FROM `products`";     
        Connection con1=null;//for connection
        Statement st1 = null;//for query execution
        ResultSet rs1 = null; /// for getting data
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
			st1 = con1.createStatement();//create statement
			rs1 = st1.executeQuery(queryShow1);//getting result
			
			while(rs1.next())
			{
			    String Name = rs1.getString("name");
			    comboBoxProductName.addItem(Name);		
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
            {
            	if(rs1 != null)
            	{
            		rs1.close();
            	}
            	
                if(st1!=null)
					st1.close();

                if(con1!=null)
					con1.close();
            }
            catch(Exception ex1){}
		}
        
        
        txtpaidamount.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					txtbackamount.setText(Double.toString(Double.parseDouble(txtpaidamount.getText()) - grandTotalPrice));
				} catch (Exception e) {
					txtbackamount.setText("0");
					JOptionPane.showMessageDialog(null, "Enter Cash received amount");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
        
        
		addToGUI();
		buttonListeners();
	}

	private void buttonListeners() {
		btncustomerlistrefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboBoxCustomerName.removeAllItems();
				String queryShow = "SELECT * FROM `customer`";     
		        Connection con=null;//for connection
		        Statement st = null;//for query execution
		        ResultSet rs = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st = con.createStatement();//create statement
					rs = st.executeQuery(queryShow);//getting result
					
					while(rs.next())
					{
					    String Name = rs.getString("name");
					    comboBoxCustomerName.addItem(Name);		
					}
				}
		        catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
		        }
		        finally
				{
		            try
		            {
		            	if(rs != null)
		            	{
		            		rs.close();
		            	}
		            	
		                if(st!=null)
							st.close();

		                if(con!=null)
							con.close();
		            }
		            catch(Exception ex1){}
				}
			}
		});
		
		btnproductlistrefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboBoxProductName.removeAllItems();
		        String queryShow1 = "SELECT * FROM `products`";     
		        Connection con1=null;//for connection
		        Statement st1 = null;//for query execution
		        ResultSet rs1 = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st1 = con1.createStatement();//create statement
					rs1 = st1.executeQuery(queryShow1);//getting result
					
					while(rs1.next())
					{
					    String Name = rs1.getString("name");
					    comboBoxProductName.addItem(Name);		
					}
				}
		        catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
		        }
		        finally
				{
		            try
		            {
		            	if(rs1 != null)
		            	{
		            		rs1.close();
		            	}
		            	
		                if(st1!=null)
							st1.close();

		                if(con1!=null)
							con1.close();
		            }
		            catch(Exception ex1){}
				}
			}
		});
		
		btnadditem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtproductquantity.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter quantity value.");
					return;
				}
				
				String productName = comboBoxProductName.getSelectedItem().toString();
				String quantity = txtproductquantity.getText();
				String perUnitPrice = "";
				///getting per Unit price for that product
				String queryShow1 = "SELECT * FROM `products` where `name` = '" + productName + "'";     
		        Connection con1=null;//for connection
		        Statement st1 = null;//for query execution
		        ResultSet rs1 = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st1 = con1.createStatement();//create statement
					rs1 = st1.executeQuery(queryShow1);//getting result
					
					while(rs1.next()){
					    perUnitPrice = rs1.getString("per_unit_price");		
					}
				}
		        catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
		        }
		        finally
				{
		            try
		            {
		            	if(rs1 != null)
		            	{
		            		rs1.close();
		            	}
		            	
		                if(st1!=null)
							st1.close();

		                if(con1!=null)
							con1.close();
		            }
		            catch(Exception ex1){}
				}
		        
		        String totalPrice = Double.toString(Double.parseDouble(quantity) * Double.parseDouble(perUnitPrice));
		        model.addRow(new Object [] {productName, quantity, perUnitPrice, totalPrice});
		        grandTotalPrice += Double.parseDouble(quantity) * Double.parseDouble(perUnitPrice);
		        txttotalprice.setText(Double.toString(grandTotalPrice));
		        itemCount++;
		        JOptionPane.showMessageDialog(null, "Product Added.");
			}
		});
		
		btndeleteitem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(tableproductInfo.getSelectionModel().isSelectionEmpty()){
					JOptionPane.showMessageDialog(null, "Select a row for delete product");
					return;
				}
				
				int selectedRow = tableproductInfo.getSelectedRow();
				selectedRow = tableproductInfo.convertRowIndexToModel(selectedRow);
				model.removeRow(selectedRow);
				
				int selectedRow1 = tableproductInfo.getSelectedRow();
				selectedRow1 = tableproductInfo.convertRowIndexToModel(selectedRow1);
				String value = (String) tableproductInfo.getModel().getValueAt(selectedRow1, 3);
				
		        grandTotalPrice -= Double.parseDouble(value);
		        txttotalprice.setText(Double.toString(grandTotalPrice));
		        itemCount--;
		        JOptionPane.showMessageDialog(null, "Product Removed.");
			}
		});
		
		btnshowreceive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtpaidamount.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please enter paid amount value");
					return;
				}
				
				String customerName;
				String buyDate;
				String totalBill;
				String cashReceived;
				String changedAmount;
					
				///Adding to sales Report
				for (int i = 0; i < itemCount; i++) {
					String query = "INSERT INTO `items` (`product_name`, `quantity`, `per_unit_price`, `total_price`) VALUES ('"+ tableproductInfo.getValueAt(i, 0) + "', '"+ tableproductInfo.getValueAt(i, 1) + "', '"+ tableproductInfo.getValueAt(i, 2) + "', '"+ tableproductInfo.getValueAt(i, 3) + "')";     
			        Connection con=null;//for connection
			        Statement st = null;//for query execution
			        try
					{
						Class.forName("com.mysql.jdbc.Driver");//load driver
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
						st = con.createStatement();//create statement
						st.executeUpdate(query);//getting result
								
					}
			        catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
			        }
			        finally
					{
			            try
			            {
			                if(st!=null)
								st.close();

			                if(con!=null)
								con.close();
			            }
			            catch(Exception ex){}
			        }
			        
			        String query1 = "INSERT INTO `sales_report` (`customer_name`, `employee_name`, `buy_date`) VALUES ('"+comboBoxCustomerName.getSelectedItem().toString() +"', '"+userName+"', '"+new SimpleDateFormat("yyyy MM dd_HH mm ss").format(Calendar.getInstance().getTime())+"')";     
			        Connection con1=null;//for connection
			        Statement st1 = null;//for query execution
			        try
					{
						Class.forName("com.mysql.jdbc.Driver");//load driver
						con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
						st1 = con1.createStatement();//create statement
						st1.executeUpdate(query1);//getting result
								
					}
			        catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
			        }
			        finally
					{
			            try
			            {
			                if(st1!=null)
								st1.close();

			                if(con1!=null)
								con1.close();
			            }
			            catch(Exception ex){}
			        }
				}
				
				String queryShow = "SELECT * FROM `person` where username = '" + userName +"'";     
		        Connection con2=null;//for connection
		        Statement st2 = null;//for query execution
		        ResultSet rs2 = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st2 = con2.createStatement();//create statement
					rs2 = st2.executeQuery(queryShow);//getting result
					
					while(rs2.next())
					{
						employeeName = rs2.getString("name");	
					}
				}
		        catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
		        }
		        finally
				{
		            try
		            {
		            	if(rs2 != null)
		            	{
		            		rs2.close();
		            	}
		            	
		                if(st2!=null)
							st2.close();

		                if(con2!=null)
							con2.close();
		            }
		            catch(Exception ex1){}
				}
				
		        receipt.showReceipt(comboBoxCustomerName.getSelectedItem().toString(), employeeName, new SimpleDateFormat("yyyy-MMM-dd    HH:mm:ss").format(Calendar.getInstance().getTime()), txttotalprice.getText(), txtpaidamount.getText(), txtbackamount.getText(), model, tableproductInfo);
				
			}
		});
		
	}

	private void addToGUI() {
		panelLabels.add(lblcustomername);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 9)));
		paneltxtPanel.add(comboBoxCustomerName);
		panelLabels.add(lblproductcname);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 9)));
		paneltxtPanel.add(comboBoxProductName);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 9)));
		panelLabels.add(lblproductquantity);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 9)));
		panelLabels.add(lbltotalprice);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 5)));
		panelLabels.add(lblpaidamount);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 5)));
		panelLabels.add(lblbackmoney);
		
		
		paneltxtPanel.add(txtproductquantity);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 9)));
		paneltxtPanel.add(txttotalprice);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 9)));
		paneltxtPanel.add(txtpaidamount);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 9)));
		paneltxtPanel.add(txtbackamount);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 9)));
		
		lblcustomername.setFont(new Font("Times New Roamn", Font.BOLD, 18));
		lblproductcname.setFont(new Font("Times New Roamn", Font.BOLD, 18));
		lblproductquantity.setFont(new Font("Times New Roamn", Font.BOLD, 18));
		lbltotalprice.setFont(new Font("Times New Roamn", Font.BOLD, 18));
		lblpaidamount.setFont(new Font("Times New Roamn", Font.BOLD, 18));
		lblbackmoney.setFont(new Font("Times New Roamn", Font.BOLD, 18));
		txtbackamount.setFont(new Font("Times New Roamn", Font.BOLD, 22));
		txttotalprice.setFont(new Font("Times New Roamn", Font.BOLD, 22));
		
		
		panelButtons.add(btncustomerlistrefresh);
		panelButtons.add(Box.createRigidArea(new Dimension(0, 5)));
		panelButtons.add(btnproductlistrefresh);
		panelButtons.add(Box.createRigidArea(new Dimension(0, 10)));
		panelButtons.add(btnadditem);
		panelButtons.add(Box.createRigidArea(new Dimension(0, 10)));
		panelButtons.add(btndeleteitem);
		panelButtons.add(Box.createRigidArea(new Dimension(0, 25)));
		panelButtons.add(btnshowreceive);
		
		
		
		
		panelCenter.add(panelLabels, BorderLayout.WEST);
		panelCenter.add(paneltxtPanel, BorderLayout.CENTER);
		panelCenter.add(scrollPane, BorderLayout.PAGE_END);
		panelCenter.add(panelButtons, BorderLayout.EAST);
		
		
		add(lblMenuName, BorderLayout.PAGE_START);
		add(panelCenter, BorderLayout.CENTER);
	}
}
	 

