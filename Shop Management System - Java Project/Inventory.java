
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

//import com.sun.xml.internal.ws.Closeable;


public class Inventory extends JPanel{
	private String [] COLUMN_HEADER = {"ID", "Product Name", "Unit", "Per Unit Price"};
	private String [] [] COLUMN_VALUE = {};
	
	private JLabel lblMenuName = new JLabel("Inventory Menu", SwingConstants.CENTER);
	
	private JPanel panelLebel = new JPanel();
	private JPanel panelInput = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JPanel panelCenter=new JPanel();
	private JPanel paneltxtPanel = new JPanel();
	
	private JScrollPane scrollPane;
	
	private JLabel lblSearch = new JLabel("Search Text: ");
	private JLabel lblProductId = new JLabel("Search Type: ");
	
	private JTextField txtSearch = new JTextField();
	private JComboBox comboBoxCatagory = new JComboBox();
	
	private JButton btndelete = new JButton("Delete product");
	private JButton btnLoad = new JButton("Load Data");
	private JButton btnSave = new JButton("Save");
	
	private DefaultTableModel model = new DefaultTableModel(COLUMN_VALUE, COLUMN_HEADER);
	private JTable tableproductInfo = new JTable(model){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	
       public Inventory() {
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
		
		comboBoxCatagory.addItem("ID");
		comboBoxCatagory.addItem("Product Name");
		
txtSearch.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
			
				
			}
			
			public void keyReleased(KeyEvent arg0) {
				if (comboBoxCatagory.getSelectedItem().equals("ID")) {
					if(txtSearch.getText() == ""){
						txtSearch.setText("0");
					}
					
					String query = txtSearch.getText();
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `products` where id = " + Integer.parseInt(query) + "";     
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
						    String productName = rs.getString("name");
						    String Unit = rs.getString("unit");
						    String perUnitPrice = rs.getString("per_unit_price");
						    model.addRow(new Object[]{id, productName, Unit, perUnitPrice});
								
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
				}else if(comboBoxCatagory.getSelectedItem().equals("Product Name")) {
					
					if(txtSearch.getText() == ""){
						return;
					}
					
					String query = txtSearch.getText();
					
					model.setRowCount(0);
					String queryShow = "SELECT * FROM `products` where name like '%" + query +"%'";     
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
						    String productName = rs.getString("name");
						    String Unit = rs.getString("unit");
						    String perUnitPrice = rs.getString("per_unit_price");
						    model.addRow(new Object[]{id, productName, Unit, perUnitPrice});
								
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
				
				
			}
		});
		
		addToGUI();
		btnListeners();
	}

	private void btnListeners() {
		btnLoad.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				String query = "SELECT * FROM `products`";     
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
					    String productName = rs.getString("name");
					    String Unit = rs.getString("unit");
					    String perUnitPrice = rs.getString("per_unit_price");
					    model.addRow(new Object[]{id, productName, Unit, perUnitPrice});
							
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
		
		panelLebel.add(lblProductId);
		panelLebel.add(lblSearch);
		
		paneltxtPanel.add(comboBoxCatagory);
		paneltxtPanel.add(txtSearch);
		
		panelButtons.add(btnLoad);
		
		panelInput.add(panelLebel, BorderLayout.WEST);
		panelInput.add(paneltxtPanel, BorderLayout.CENTER);
		panelCenter.add(panelInput, BorderLayout.PAGE_START);
		panelCenter.add(scrollPane, BorderLayout.CENTER);
		panelCenter.add(panelButtons, BorderLayout.EAST);
		add(lblMenuName, BorderLayout.PAGE_START);
		add(panelCenter, BorderLayout.CENTER);
		
	}
}

