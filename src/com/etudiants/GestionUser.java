package com.etudiants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GestionUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	
//	declaration d une variable pour permet la modification du textField_3 dans le bouton modifier 
	String userVB = null;
	
//	declaration des variable de la connexion dans la classe motPasseIndicateur
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	
	private JTextField textField_3;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUser frame = new GestionUser();
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
	public GestionUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenu mnOuvrir = new JMenu("Ouvrir");
		mnOuvrir.setBackground(Color.WHITE);
		mnFichier.add(mnOuvrir);
		
		JMenuItem mntmMenuDadministration = new JMenuItem("Menu Administration");
		mntmMenuDadministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuAdminstrateur admin = new MenuAdminstrateur();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		
		JMenuItem mntmGestionDesFilieres = new JMenuItem("Gestion des filieres");
		mntmGestionDesFilieres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_Filiere admin = new Gestion_Filiere();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionDesFilieres);
		
		JMenuItem mntmGestionDesEtudiants = new JMenuItem("Gestion des Etudiants");
		mntmGestionDesEtudiants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEtudiants admin = new GestionEtudiants();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionDesEtudiants);
		mnOuvrir.add(mntmMenuDadministration);
		
		JMenuItem mntmGestionDesAuthentification = new JMenuItem("Gestion des Authentifications");
		mntmGestionDesAuthentification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAuthentification admin = new GestionAuthentification();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionDesAuthentification);
		
		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mntmFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fermer();
			}
		});
		mnFichier.add(mntmFermer);
		
		JMenu mnEditer = new JMenu("Editer");
		menuBar.add(mnEditer);
		
		JMenuItem mntmCopier = new JMenuItem("Copier");
		mnEditer.add(mntmCopier);
		
		JMenuItem mntmColler = new JMenuItem("Coller");
		mnEditer.add(mntmColler);
		
		JMenu mnSource = new JMenu("Source");
		menuBar.add(mnSource);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLocale(null);
		
//		Activation de la connexion a la db
		cnx = ConnexionMysql.conneexioDB();
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 317, 144, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 395, 144, 43);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				String sql = "select password from utilisateurs where username = ?";
//				
//				try {
//					prepare = (PreparedStatement) cnx.prepareStatement(sql);
//					prepare.setString(1, textField_2.getText().toString());
//					resultat = prepare.executeQuery();
//					
//					if (resultat.next()) 
//					{
//						String password = resultat.getString("password");
//						textField_3.setText(password);	
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		});
		textField_2.setFont(new Font("Arial", Font.BOLD, 20));
		textField_2.setBounds(183, 317, 300, 46);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
//		Creation du bouton ajouter
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setIcon(null);
		btnAjouter.addActionListener(new ActionListener() {
//			Creation d un actionEvent dans key
			public void actionPerformed(ActionEvent arg0) {
//				Code sql permettant de inserer les donnee dans la table utilisateurs dans db
				String sql = " insert into utilisateurs (username, password) values( ? , ? )";
//				try/catch obligatoire
				try {
//					declaration d un variable et initiation du variable par l activation de la connexion d la db
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, textField_2.getText().toString());
					prepare.setString(2, textField_3.getText().toString());
					
//					Condition if/else permettant d eviter l insertion des champs vides
					if(!textField_2.getText().equals("")&& !textField_3.getText().equals(""))
					{
						int d = JOptionPane.showConfirmDialog(null, "Voulez-vous Ajouter cet User ?", "Ajouter User!", JOptionPane.YES_NO_OPTION);
						if (d == 0) 
						{
//							Execution de la requete sql
							prepare.execute();
//							Appel de la methode updateTable permettant la mise a jour
							updateTable();
//							Pour le champs vide
							textField_2.setText("");
							textField_3.setText("");
							JOptionPane.showMessageDialog(null, "User ajoute avec succes !!");
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides!!");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnAjouter.setForeground(new Color(0, 0, 128));
		btnAjouter.setFont(new Font("Arial", Font.BOLD, 20));
		btnAjouter.setBounds(183, 477, 162, 34);
		contentPane.add(btnAjouter);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Arial", Font.BOLD, 20));
		textField_3.setBounds(183, 393, 300, 46);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
//		Creation du bouton supprimer
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIcon(null);
		btnSupprimer.addActionListener(new ActionListener() {
//			Creation d un actionEvent dans key 
			public void actionPerformed(ActionEvent arg0) {
//				Insertion du code sql permettant de supprimer un utilisateur dans la table utilisateurs du db
				String sql =" DELETE FROM utilisateurs WHERE username = ? AND password = ? ";
//				try/catch obligatoire 
				try {
					if(!textField_2.getText().equals("") && !textField_3.getText().equals(""))
					{
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, textField_2.getText().toString());
					prepare.setString(2, textField_3.getText().toString());
					
					int a = JOptionPane.showConfirmDialog(null, "Voulez-vous Supprimer cet User ?", "Supprimer le User", JOptionPane.YES_NO_OPTION);
					if (a==0) 
					{
						prepare.execute();
//						Appel de la methode updateTable permettant la mise a jour permettant d afficher la table
						updateTable();
						JOptionPane.showMessageDialog(null, "User a ete supprimer avec succes ");
						textField_2.setText("");
						textField_3.setText("");
					}
							
					}else {
						JOptionPane.showMessageDialog(null, "Selectionner un User");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSupprimer.setFont(new Font("Arial", Font.BOLD, 20));
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setBounds(835, 605, 154, 34);
		contentPane.add(btnSupprimer);
		
//		Bouton Modifier
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = textField_2.getText().toString();
//				Insertion du code sql permettant le update
				String sql = "UPDATE utilisateurs SET username = ?, password = ? WHERE username = '"+userVB+"' ";
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(2, textField_3.getText().toString());
					prepare.setString(1, textField_2.getText().toString());
					if(!textField_3.getText().equals("") && !textField_2.getText().equals(""))
						{
						int i = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier cet User", "Modifier User", JOptionPane.YES_NO_OPTION);
						if (i == 0) 
						{
							prepare.execute();
							JOptionPane.showMessageDialog(null, "Modification reussit");
							updateTable();
//							Pour le champs vide
							textField_2.setText("");
							textField_3.setText("");
						}
						
						}else {
							JOptionPane.showMessageDialog(null, "Remplisser les champs vides");
						}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBounds(639, 605, 132, 34);
		contentPane.add(btnNewButton);
		
//		Creation du bouton quitter
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\back.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuAdminstrateur menu = new MenuAdminstrateur();
				menu.setVisible(true);
				menu.setLocationRelativeTo(null);
				fermer();
				
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				MenuAdminstrateur obj = new MenuAdminstrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
//				Appel de la methode fermer qui permet de fermer la fenetre
				fermer();
			}
		});
		
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setBounds(0, 110, 56, 43);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(503, 257, 781, 337);
		contentPane.add(scrollPane);
		
//		Creation de la table
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.setForeground(Color.BLACK);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				permet d afficher dans textField un element selectionne dans la table
				int ligne = table.getSelectedRow();
				
//				permet d inserer respectivement les donnees dans la table avec  
				userVB = table.getModel().getValueAt(ligne, 1).toString();
				String password = table.getModel().getValueAt(ligne, 2).toString();
				
//				les donnees inserees dans la table seront afficher respectivement dans textField 
				textField_2.setText(userVB);
				textField_3.setText(password);
				
			}
		});
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Actualiser");
		btnNewButton_2.setIcon(null);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				Appel de la methode updateTable qui permet la mise a jour
				updateTable();
				
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_2.setForeground(new Color(0, 0, 255));
		btnNewButton_2.setBounds(1055, 605, 135, 34);
		contentPane.add(btnNewButton_2);
		
		JLabel lblLaTableDes = new JLabel("Tableau des Administrateurs");
		lblLaTableDes.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblLaTableDes.setForeground(new Color(0, 0, 255));
		lblLaTableDes.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaTableDes.setBounds(739, 227, 340, 29);
		contentPane.add(lblLaTableDes);
		
		textField = new JTextField();
		textField.setText("LOGICIEL DE GESTION");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLUE);
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(0, 0, 1123, 94);
		contentPane.add(textField);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\MyPicture\\a.jpg"));
		btnNewButton_3.setBounds(1122, 0, 162, 133);
		contentPane.add(btnNewButton_3);
		
		
	}
	
//	Creation de la methode mise a jour de la table
	public void updateTable(){
//		Code sql permettant de selectionner des elements dans la table utilisateurs de la db 
		String sql = "select id, username, password from utilisateurs";
		try {
			prepare = (PreparedStatement) cnx.prepareStatement(sql);
			resultat = prepare.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
//	Creation de la methode fermer avec la fonction dispose
	public void fermer()
	{
		dispose();
	}
}
