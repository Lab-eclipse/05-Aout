package MesTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import com.etudiants.ConnexionMysql;
import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GestionAdmin1 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
//	private JTextField textField_3;
	
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAdmin1 window = new GestionAdmin1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionAdmin1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		cnx = ConnexionMysql.conneexioDB();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 1084, 671);
		frame.getContentPane().add(panel);
		
		textField = new JTextField();
		textField.setText("GESTION DES DONNEES");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(0, 0, 205));
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(230, 230, 250));
		textField.setBounds(161, 0, 738, 108);
		panel.add(textField);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.jpg"));
		button.setBounds(-14, 0, 183, 162);
		panel.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.jpg"));
		button_1.setBounds(901, 0, 183, 162);
		panel.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setText("UNIVERSITE ESCT");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(new Color(0, 0, 128));
		textField_1.setFont(new Font("Algerian", Font.BOLD, 25));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(230, 230, 250));
		textField_1.setBounds(341, 95, 334, 57);
		panel.add(textField_1);
		
		JLabel label = new JLabel("Ajouter un utilisateur");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Arial", Font.BOLD, 32));
		label.setBounds(59, 173, 359, 46);
		panel.add(label);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement (1).png"));
		button_2.setBounds(168, 230, 166, 179);
		panel.add(button_2);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(0, 0, 139));
		label_1.setFont(new Font("Arial", Font.BOLD, 20));
		label_1.setBounds(-14, 436, 144, 46);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(0, 0, 139));
		label_2.setFont(new Font("Arial", Font.BOLD, 20));
		label_2.setBounds(-14, 493, 144, 43);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.BOLD, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(129, 436, 267, 46);
		panel.add(textField_2);
		
		JButton button_3 = new JButton("Ajouter");
		button_3.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
				
				String sql = " insert into utilisateurs (username, password) values( ? , ? )";
				
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, textField_2.getText().toString());
					prepare.setString(2, textField_3.getText().toString());
					prepare.execute();
					
//					if (resultat.next()) {
//						String password = resultat.getString("password");
//						textField_3.setText(password);
//					}
					
					
					JOptionPane.showMessageDialog(null, "User a ete ajoute avec succes !!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		button_3.setForeground(new Color(0, 0, 128));
		button_3.setFont(new Font("Arial", Font.BOLD, 20));
		button_3.setBounds(10, 598, 109, 31);
		panel.add(button_3);
		
		textField_3 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String sql = "select password from utilisateurs where username = ?";
				
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, textField_2.getText().toString());
					resultat = prepare.executeQuery();
					
					if (resultat.next()) 
					{
						String password = resultat.getString("password");
						textField_3.setText(password);	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textField_3.setFont(new Font("Arial", Font.BOLD, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(129, 493, 267, 43);
		panel.add(textField_3);
		
		JButton button_4 = new JButton("Supprimer");
		button_4.setForeground(new Color(0, 0, 128));
		button_4.setFont(new Font("Arial", Font.BOLD, 20));
		button_4.setBounds(129, 598, 140, 31);
		panel.add(button_4);
		
		JButton button_5 = new JButton("Modifier");
		button_5.setForeground(new Color(0, 0, 128));
		button_5.setFont(new Font("Arial", Font.BOLD, 20));
		button_5.setBounds(279, 598, 119, 31);
		panel.add(button_5);
		
		JButton button_6 = new JButton("Quitter");
		button_6.setForeground(new Color(0, 0, 128));
		button_6.setFont(new Font("Arial", Font.BOLD, 20));
		button_6.setBounds(408, 598, 109, 30);
		panel.add(button_6);
	}

}
