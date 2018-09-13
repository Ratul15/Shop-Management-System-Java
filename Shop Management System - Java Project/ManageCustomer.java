import java.awt.*;
import java.awt.event.*;
import java.sql.*;

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


public class ManageCustomer extends JPanel{
	private JLabel lblMenuName = new JLabel("Manage Customers Menu", SwingConstants.CENTER);
	private String [] COLUMN_HEADER = {"ID", "Customer Name", "Customer Address", "Contact Number"};
	private String [] [] COLUMN_VALUE = {};
	
	private JPanel panelLebel = new JPanel();
	private JPanel panelInput = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JPanel panelCenter=new JPanel();
	private JPanel paneltxtPanel = new JPanel();
	
	private JScrollPane scrollPane;
	
	private JLabel lblSearch = new JLabel("Search Text: ");
	private JLabel lblCustomerId = new JLabel("Search Type: ");
	
	private JTextField txtSearch = new JTextField();
	private JComboBox comboBoxCustomer = new JComboBox();
	
	private JButton btndeletecustomer = new JButton("Delete Customer");
	private JButton btnLoad = new JButton("Load Data");
	private JButton btnSave = new JButton("Save");
	
	private DefaultTableModel model = new DefaultTableModel(COLUMN_VALUE, COLUMN_HEADER);
	private JTable tableproductInfo = new JTable(model){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
	public ManageCustomer()
	{
		setVisible(true);
		setLayout(new BorderLayout());
		
		panelCenter.setLayout(new BorderLayout());
		panelInput.setLayout(new BorderLayout());
		panelLebel.setLayout(new BoxLayout(panelLebel, BoxLayout.Y_AXIS));
		paneltxtPanel.setLayout(new BoxLayout(paneltxtPanel, BoxLayout.Y_AXIS));
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tableproductInfo);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		comboBoxCustomer.addItem("ID");
		comboBoxCustomer.addItem("Customer Name");
		
		txtSearch.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent arg0) {
				if (comboBoxCustomer.getSelectedItem().equals("ID")) {
					if(txtSearch.getText() == ""){
						txtSearch.setText("0");
					}
					
					String query = txtSearch.getText();
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `customer` where id = " + Integer.parseInt(query) + "";     
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
							String id = rs.getString("id");
						    String Name = rs.getString("name");
						    String address = rs.getString("address");
						    String contactNumber = rs.getString("contact_number");
						    model.addRow(new Object[]{id, Name, address, contactNumber});
								
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
				}else if(comboBoxCustomer.getSelectedItem().equals("Customer Name")) {
					
					if(txtSearch.getText() == ""){
						return;
					}
					
					String query = txtSearch.getText();
					
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `customer` where name like '%" + query +"%'";     
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
							String id = rs.getString("id");
						    String Name = rs.getString("name");
						    String address = rs.getString("address");
						    String contactNumber = rs.getString("contact_number");
						    model.addRow(new Object[]{id, Name, address, contactNumber});
								
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
			}
			
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addToGUI();
		btnListeners();
		
	}

	private void btnListeners() {
		btndeletecustomer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(tableproductInfo.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Select a row for delete product");
					return;
				}
				
				int selectedRow = tableproductInfo.getSelectedRow();
				selectedRow = tableproductInfo.convertRowIndexToModel(selectedRow);
				String value = (String) tableproductInfo.getModel().getValueAt(selectedRow, 0);
				String query = "delete from `Customer` where id = '"+ value +"'";    
				String queryLoad = "SELECT * FROM `customer`";
				Connection con=null;//for connection
		        Statement st = null;//for query execution
		        ResultSet rs = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st = con.createStatement();//create statement
					st.executeUpdate(query);
					model.setRowCount(0);
					rs = st.executeQuery(queryLoad);//getting result
					
					while(rs.next())
					{
						String id = rs.getString("id");
					    String Name = rs.getString("name");
					    String address = rs.getString("address");
					    String contactNumber = rs.getString("contact_number");
					    model.addRow(new Object[]{id, Name, address, contactNumber});
							
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
		            catch(Exception ex){}
		            
		            JOptionPane.showMessageDialog(null, "Product Deleted.");
				}
			}
		});
		
		btnLoad.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				String query = "SELECT * FROM `customer`";     
		        Connection con=null;//for connection
		        Statement st = null;//for query execution
		        ResultSet rs = null; /// for getting data
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st = con.createStatement();//create statement
					rs = st.executeQuery(query);//getting result
					
					while(rs.next())
					{
					    String id = rs.getString("id");
					    String Name = rs.getString("name");
					    String address = rs.getString("address");
					    String contactNumber = rs.getString("contact_number");
					    model.addRow(new Object[]{id, Name, address, contactNumber});
							
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
		            catch(Exception ex){}
		            
		            JOptionPane.showMessageDialog(null, "Product Show In Table.");
		        }
			}
		});
	}

	private void addToGUI() {
		
		panelLebel.add(lblCustomerId);
		panelLebel.add(lblSearch);
		
		lblCustomerId.setFont(new Font("Times New Roamn", Font.BOLD, 18));
		lblSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		paneltxtPanel.add(comboBoxCustomer);
		paneltxtPanel.add(txtSearch);
		
		panelButtons.add(btndeletecustomer);
		panelButtons.add(btnLoad);
		
		panelInput.add(panelLebel, BorderLayout.WEST);
		panelInput.add(paneltxtPanel, BorderLayout.CENTER);
		panelCenter.add(panelInput, BorderLayout.PAGE_START);
		panelCenter.add(scrollPane, BorderLayout.CENTER);
		panelCenter.add(panelButtons, BorderLayout.EAST);
		
		add(lblMenuName, BorderLayout.PAGE_START);
		add(panelCenter,BorderLayout.CENTER);
		
	}
	
	
	
	
	
}
