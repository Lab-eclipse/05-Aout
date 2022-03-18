package MesTest;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JSeparator;

import com.etudiants.ConnexionMysql;
import com.etudiants.MenuAdminstrateur;
import com.mysql.jdbc.PreparedStatement;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification extends JFrame {

	private JFrame frame;
	private JTextField usernamefield;
	private JPasswordField passwordfield;
	
	
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
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
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.setBounds(100, 100, 639, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		cnx = ConnexionMysql.conneexioDB();
		
		JLabel lblNewLabel = new JLabel("AUTHENTIFICATION");
		lblNewLabel.setForeground(new Color(0, 0, 205));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 30));
		lblNewLabel.setBounds(144, 11, 358, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(94, 131, 163, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_2.setBounds(94, 209, 137, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		usernamefield = new JTextField();
		usernamefield.setFont(new Font("Arial", Font.BOLD, 25));
		usernamefield.setBounds(267, 131, 235, 37);
		frame.getContentPane().add(usernamefield);
		usernamefield.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = usernamefield.getText().toString();
				String password = passwordfield.getText().toString();
				
				String sql = "select username, password from utilisateurs";
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					resultat = prepare.executeQuery();
					int i = 0;
					
					if (username.equals("") || password.equals("")) 
					{
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}else   
					 {
						while(resultat.next())
						{
							String username1 = resultat.getString("username");
							String password1 = resultat.getString("password");
							
							if (username1.equals(username) && password1.equals(password)) 
							{
								JOptionPane.showMessageDialog(null, "Connexion reussite");
								MenuAdminstrateur obj = new MenuAdminstrateur();
								obj.setVisible(true);
								i = 1;
							} 
//							MenuAdminstrateur obj = new MenuAdminstrateur();
//							obj.setVisible(true);
						}
					
						if(i==0)
							JOptionPane.showMessageDialog(null, "Connexion Echoue, Information incorrect :( ");;
					 }
//					JOptionPane.showMessageDialog(null, i);
					
				} catch (SQLException e) {
					
				  e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Algerian", Font.BOLD, 20));
		btnNewButton.setBounds(67, 344, 107, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setFont(new Font("Algerian", Font.BOLD, 20));
		btnNewButton_1.setBounds(266, 344, 107, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setForeground(new Color(0, 0, 128));
		btnNewButton_2.setFont(new Font("Algerian", Font.BOLD, 20));
		btnNewButton_2.setBounds(467, 344, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		passwordfield = new JPasswordField();
		passwordfield.setFont(new Font("Arial", Font.BOLD, 25));
		passwordfield.setBounds(267, 208, 235, 37);
		frame.getContentPane().add(passwordfield);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.MAGENTA);
		separator.setBounds(0, 78, 623, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.MAGENTA);
		separator_1.setBounds(0, 303, 623, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe oublie");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IndicationMotPass pass = new IndicationMotPass();
				pass.setVisible(true);
				pass.setLocationRelativeTo(null);
				
			}
		});
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setBounds(351, 255, 190, 26);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
