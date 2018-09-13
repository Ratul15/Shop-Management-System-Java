import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javafx.scene.shape.Box;

import javax.swing.*;
import javax.swing.SwingConstants;


public class AddProducts extends JPanel{
	
	private JLabel lblMenuName = new JLabel("Add Products Menu", SwingConstants.CENTER);
	
	private JPanel panelLabel = new JPanel();
	private JPanel panelTextBox = new JPanel();
	private JPanel panelButtons = new JPanel();
	
	private JLabel lblProductName = new JLabel("Product Name: ");
	private JLabel lblProductUnit = new JLabel("Product Unit: ");
	private JLabel lblPerUnitPrice = new JLabel("Per Unit Price: ");
	
	private JTextField txtProductName = new JTextField();
	private JTextField txtProductUnit = new JTextField();
	private JTextField txtPerUnitPrice = new JTextField();
	
	private JButton btnAdd = new JButton("Add");
	private JButton btnReset = new JButton("Reset");
	
	
	
	public AddProducts() {
		setVisible(true);
		setLayout(new BorderLayout());
		panelLabel.setBackground(Color.YELLOW);
		panelTextBox.setBackground(Color.GRAY);
		
		panelLabel.setLayout(new BoxLayout(panelLabel, BoxLayout.Y_AXIS));
		panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		txtProductName.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtProductName.getMinimumSize().height));
		txtProductUnit.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtProductUnit.getMinimumSize().height));
		txtPerUnitPrice.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtPerUnitPrice.getMinimumSize().height));
		
		addToGUI();
		btnListeners();
	}

	private void btnListeners() {
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if (txtProductName.getText().equals("") || txtProductUnit.getText().equals("") || txtPerUnitPrice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields.");
					return;
				}
				
				String productName=txtProductName.getText();
				String productUnit=txtProductUnit.getText();
				String perUnitPrice = txtPerUnitPrice.getText();
				
				String query = "INSERT INTO `products` (`Name`, `Unit`, `Per_unit_Price`) VALUES ('" + productName + "', '" + productUnit + "', " + Float.parseFloat(perUnitPrice) +  ")";     
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
		            
		            JOptionPane.showMessageDialog(null, "Product added.");
		        }
			}
			}
		);
		
		btnReset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				txtProductName.setText("");
				txtProductUnit.setText("");
				txtPerUnitPrice.setText("");
			}
		});
	}

	private void addToGUI() {
		
		panelLabel.add(lblProductName);
		panelLabel.add(lblProductUnit);
		panelLabel.add(lblPerUnitPrice);
		
		panelTextBox.add(txtProductName);
		panelTextBox.add(txtProductUnit);
		panelTextBox.add(txtPerUnitPrice);
		
		panelButtons.add(btnReset);
		panelButtons.add(btnAdd);
		
		add(lblMenuName, BorderLayout.PAGE_START);
		add(panelLabel, BorderLayout.WEST);
		add(panelTextBox, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.EAST);
	}
	
}
