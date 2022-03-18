package com.etudiants;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;


public class MotPasseIndicateur extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	private JButton button;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MotPasseIndicateur frame = new MotPasseIndicateur();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MotPasseIndicateur() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 808, 288);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.conneexioDB();
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 25));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(53, 139, 133, 43);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD, 18));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String username = textField.getText().toString();
				String sql = "select password from utilisateurs where username = ? ";
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, username);
					resultat = prepare.executeQuery();
					
					if(resultat.next())
					{
						String passe = resultat.getString("password");
						String passe1 = passe.substring(0, 4);
						textField_1.setText("Les 4 premiers mots sont: " + passe1 + "***");
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				
			}
		});
		textField.setBounds(211, 145, 242, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Arial", Font.BOLD, 18));
		textField_1.addKeyListener(new KeyAdapter() {
			
		});
		textField_1.setBounds(211, 192, 488, 41);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.jpg"));
		button.setBounds(619, 0, 144, 108);
		contentPane.add(button);
		
		textField_2 = new JTextField();
		textField_2.setText("LOGICIEL DE GESTION");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.BLUE);
		textField_2.setFont(new Font("Algerian", Font.BOLD, 50));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(0, 0, 617, 94);
		contentPane.add(textField_2);
	}
//	Creation de la methode fermer avec la fonction dispose
	public void fermer()
	{
		dispose();
	}

}
