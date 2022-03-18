package MesTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.etudiants.ConnexionMysql;
import com.mysql.jdbc.PreparedStatement;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSeparator;

public class IndicationMotPass extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	private JSeparator separator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndicationMotPass obj = new IndicationMotPass();
					obj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IndicationMotPass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 249);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		cnx = ConnexionMysql.conneexioDB();
		
		textField = new JTextField();
		textField.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		textField.setBounds(198, 96, 189, 29);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String username = textField.getText().toString();
				String sql = "select password from utilisateurs where username = ?";
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, username);
					resultat = prepare.executeQuery();
					if(resultat.next())
					{
						String pass = resultat.getString("password");
						String pass1 = pass.substring(0, 3);
						textField_1.setText("Les 3 derniers mots de passe sont: "+pass1+"**** ");
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(198, 151, 392, 29);
		textField_1.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
				
			}
		});
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Userneme");
		lblNewLabel.setBounds(40, 96, 148, 29);
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe oublie");
		lblNewLabel_1.setFont(new Font("Algerian", Font.BOLD, 30));
		lblNewLabel_1.setBounds(135, 11, 351, 40);
		contentPane.add(lblNewLabel_1);
		
		separator = new JSeparator();
		separator.setBackground(Color.MAGENTA);
		separator.setBounds(0, 64, 611, 2);
		contentPane.add(separator);
	}
}
