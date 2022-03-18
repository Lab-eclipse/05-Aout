package com.etudiants;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class GestionAuthentification extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField usernamefield;
	private JPasswordField passwordfield;
	
	
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	private JTextField textField_1;
	private JPasswordField passwordField;
	
	int y = 0;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAuthentification frame = new GestionAuthentification();
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
	public GestionAuthentification() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenu mnOuvrir = new JMenu("Ouvrir");
		mnFichier.add(mnOuvrir);
		
		JMenuItem mntmMotDePasse = new JMenuItem("Mot de passe oublie");
		mnOuvrir.add(mntmMotDePasse);
		
		JMenu mnSource = new JMenu("Editer");
		menuBar.add(mnSource);
		
		JMenuItem mntmCopier = new JMenuItem("Copier");
		mnSource.add(mntmCopier);
		
		JMenuItem mntmColler = new JMenuItem("Coller");
		mnSource.add(mntmColler);
		
		JMenu mnSoucre = new JMenu("Soucre");
		menuBar.add(mnSoucre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.conneexioDB();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 1284, 671);
		contentPane.add(panel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
		lblUsername.setForeground(new Color(0, 0, 255));
		lblUsername.setBounds(416, 431, 168, 46);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
		lblPassword.setForeground(new Color(0, 0, 255));
		lblPassword.setBounds(416, 468, 168, 51);
		panel.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Arial", Font.BOLD, 20));
		textField_1.setBounds(603, 437, 198, 38);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Arial", Font.BOLD, 20));
		passwordField.setBounds(603, 477, 198, 37);
		panel.add(passwordField);
		
		JLabel label_1 = new JLabel("Mot de passe oublie");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				MotPasseIndicateur mdp = new MotPasseIndicateur();
				mdp.setVisible(true);
				mdp.setLocationRelativeTo(null);
				
			}
		});
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Baskerville Old Face", Font.BOLD, 16));
		label_1.setBounds(659, 514, 190, 26);
		panel.add(label_1);
		
		JButton button_1 = new JButton("Login");
		button_1.setBackground(new Color(255, 250, 205));
		button_1.setIcon(null);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = textField_1.getText().toString();
				String password = passwordField.getText().toString();
				
				String sql = "SELECT username, password FROM utilisateurs";
				
				try {
						
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					resultat = prepare.executeQuery();
					int i = 0;
					
					if(username.equals("") || password.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Remplissez les champs vide!");
					}else{
						
						while(resultat.next())
						{
							String username1 = resultat.getString("username");
							String password1 = resultat.getString("password");
							
							
							if (username1.equals(username) && password1.equals(password)) 
							{
								JOptionPane.showMessageDialog(null, "Connexion Reussue Bienvenue !!!");
								MenuAdminstrateur obj = new MenuAdminstrateur();
								obj.setVisible(true);
								obj.setLocationRelativeTo(null);
								fermer();
								i = 1;
					
							} 
							
						}
					if(i==0)
						JOptionPane.showMessageDialog(null, "Connexion Echoué!");;
					}
					
//					JOptionPane.showMessageDialog(null, i);
					
				} catch (SQLException e) {
					
				  e.printStackTrace();
				}
				
			}
		});
		button_1.setForeground(new Color(0, 0, 255));
		button_1.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		button_1.setBounds(468, 574, 125, 38);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Reset");
		button_2.setBackground(new Color(255, 250, 205));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				passwordfield.setText("");
				usernamefield.setText("");
			}
		});
		button_2.setIcon(null);
		button_2.setForeground(new Color(0, 0, 255));
		button_2.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		button_2.setBounds(605, 574, 125, 38);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Exit");
		button_3.setBackground(new Color(255, 250, 205));
		button_3.setIcon(null);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fermer();
			}
		});
		button_3.setForeground(new Color(0, 0, 255));
		button_3.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		button_3.setBounds(740, 574, 125, 38);
		panel.add(button_3);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.png"));
		btnNewButton.setBounds(615, 253, 148, 173);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setText("LOGICIEL DE GESTION");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLUE);
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(0, 0, 1121, 94);
		panel.add(textField);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\MyPicture\\a.jpg"));
		btnNewButton_1.setBounds(1116, 0, 168, 122);
		panel.add(btnNewButton_1);
		cnx = ConnexionMysql.conneexioDB();
		
	}
//	Creation de la methode fermer avec la fonction dispose
	public void fermer()
	{
		dispose();
	}
}
