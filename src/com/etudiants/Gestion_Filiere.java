package com.etudiants;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Color;
import java.util.Locale;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Gestion_Filiere extends JFrame {

	private JPanel contentPane;
//	declaration des variable de la connexion dans la classe motPasseIndicateur
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	private JTextField nomfield;
	private JTable table;
	private JComboBox typecomboBox;
	
	String userVB = null;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_Filiere frame = new Gestion_Filiere();
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
	public Gestion_Filiere() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		Activation de la connexion a la db
		cnx = ConnexionMysql.conneexioDB();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setLocale((Locale) null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(0, 0, 1284, 671);
		contentPane.add(panel);
		
		JLabel lblNomDuFiliere = new JLabel("Nom du Filiere");
		lblNomDuFiliere.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomDuFiliere.setForeground(Color.BLUE);
		lblNomDuFiliere.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblNomDuFiliere.setBounds(6, 337, 184, 46);
		panel.add(lblNomDuFiliere);
		
		JLabel lblTypeDuFiliere = new JLabel("Type du Filiere");
		lblTypeDuFiliere.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeDuFiliere.setForeground(Color.BLUE);
		lblTypeDuFiliere.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblTypeDuFiliere.setBounds(6, 401, 184, 43);
		panel.add(lblTypeDuFiliere);
		
		nomfield = new JTextField();
		nomfield.setHorizontalAlignment(SwingConstants.CENTER);
		nomfield.setFont(new Font("Arial", Font.BOLD, 20));
		nomfield.setColumns(10);
		nomfield.setBounds(212, 337, 267, 37);
		panel.add(nomfield);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setIcon(null);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				Declaration des variables 
				String nomfil = nomfield.getText().toString();
				String combox1 = typecomboBox.getSelectedItem().toString();
				
//				Code sql pour le btn Ajouter
				String sql = "INSERT INTO filieres (nom_filiere, type) VALUES(?, ?)";
				
				try
				{
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, nomfil);
					prepare.setString(2, combox1);
					
					if(nomfield.getText().equals("") && typecomboBox.getSelectedItem().equals("Selectionner"))
						JOptionPane.showMessageDialog(null, "Remplisser les champs vides");
					
					if(nomfield.getText().equals("") || typecomboBox.getSelectedItem().equals("Selectionner"))
					{	
						if (nomfield.getText().equals("") && !typecomboBox.getSelectedItem().equals("Selectionner")) 
							JOptionPane.showMessageDialog(null, "Entree le nom de la filiere");
						
						if (!nomfield.getText().equals("") && typecomboBox.getSelectedItem().equals("Selectionner")) 
							JOptionPane.showMessageDialog(null, "Entree le type de la filiere");
						
						
					}else {
						int i = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter une filiere", "Ajouter une filiere", JOptionPane.YES_NO_OPTION);
						if (i == 0)
						{
						
						prepare.execute();			
						JOptionPane.showMessageDialog(null, "Filiere ajoutee");
						nomfield.setText("");
						typecomboBox.setSelectedItem("Selectionner");
						updateTable();
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAjouter.setForeground(new Color(0, 0, 128));
		btnAjouter.setFont(new Font("Arial", Font.BOLD, 20));
		btnAjouter.setBounds(277, 449, 135, 37);
		panel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				Insertion du code sql permettant de supprimer un utilisateur dans la table utilisateurs du db
				String sql =" DELETE FROM filieres WHERE nom_filiere = ? AND type = ? ";
//				try/catch obligatoire 
				try {
					
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, nomfield.getText().toString());
					prepare.setString(2, typecomboBox.getSelectedItem().toString());
					if(!nomfield.getText().equals("") && !typecomboBox.getSelectedItem().equals(""))
					{
						int i = JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Supprimer ?", "Supprimer la Filiere", JOptionPane.YES_NO_OPTION);
						
						if (i == 0)
						{
							prepare.execute();
							JOptionPane.showMessageDialog(null, "Filiere supprimee !!!");
							updateTable();
							nomfield.setText("");
							typecomboBox.setSelectedItem("Selectionner");
						}
							
					}else {
						JOptionPane.showMessageDialog(null, "Selectionner une filiere !!!");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSupprimer.setIcon(null);
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setFont(new Font("Arial", Font.BOLD, 20));
		btnSupprimer.setBounds(826, 577, 144, 37);
		panel.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomfil = nomfield.getText().toString();
//				Insertion du code sql permettant le update
				String sql = "UPDATE filieres SET nom_filiere = ?, type = ? where nom_filiere = '"+userVB+"'";
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(2, typecomboBox.getSelectedItem().toString());
					prepare.setString(1, nomfield.getText().toString());
					if(!nomfield.getText().equals("") && !typecomboBox.getSelectedItem().equals(""))
					{
						int i = JOptionPane.showConfirmDialog(null, "Voulez-vous Modifier cette filiere", "Modifier la Filiere", JOptionPane.YES_NO_OPTION);
						if (i == 0) 
							
						{
						prepare.execute();
						JOptionPane.showMessageDialog(null, "Modification reussit");
						updateTable();
						nomfield.setText("");
						typecomboBox.setSelectedItem("Selectionner");
						}
						}else
						{
							JOptionPane.showMessageDialog(null, "Remplisser les champs vides");
						}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnModifier.setIcon(null);
		btnModifier.setForeground(new Color(0, 0, 128));
		btnModifier.setFont(new Font("Arial", Font.BOLD, 20));
		btnModifier.setBounds(607, 577, 165, 37);
		panel.add(btnModifier);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuAdminstrateur menu = new MenuAdminstrateur();
				menu.setVisible(true);
				menu.setLocationRelativeTo(null);
				fermer();
				
			}
		});
		button_5.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\back.png"));
		button_5.setForeground(new Color(0, 0, 128));
		button_5.setFont(new Font("Arial", Font.BOLD, 20));
		button_5.setBounds(0, 130, 56, 43);
		panel.add(button_5);
		
		JLabel lblTableauDesFilieres = new JLabel("Tableau des Fili\u00E8res");
		lblTableauDesFilieres.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableauDesFilieres.setForeground(Color.BLUE);
		lblTableauDesFilieres.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblTableauDesFilieres.setBounds(770, 249, 267, 29);
		panel.add(lblTableauDesFilieres);
		
		typecomboBox = new JComboBox();
		typecomboBox.setBackground(new Color(255, 255, 240));
		typecomboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 22));
		typecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionner", "Licence Technique", "Licence Gestion", "Licence Mangement", "Master Technique", "Master Gestion", "Master Magement"}));
		typecomboBox.setBounds(210, 401, 273, 37);
		panel.add(typecomboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(498, 293, 776, 273);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLUE);
		table.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				permet d afficher dans textField un element selectionne dans la table
				int ligne = table.getSelectedRow();
				
//				permet d inserer respectivement les donnees dans la table avec  
			    userVB  = table.getModel().getValueAt(ligne, 1).toString();
				String values = table.getModel().getValueAt(ligne, 2).toString();
				
//				les donnees inserees dans la table seront afficher respectivement dans textField 
				nomfield.setText(userVB);
				typecomboBox.setSelectedItem(values);
			}
		});
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 1284, 25);
		panel.add(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier  \r\n");
		mnFichier.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnFichier.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnFichier);
		
		JMenu mnOuvrir = new JMenu("Ouvrir       \r\n\r\n");
		mnFichier.add(mnOuvrir);
		
		JMenuItem mntmGestionDesEtudiants = new JMenuItem("Gestion Etudiants");
		mntmGestionDesEtudiants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEtudiants etu = new GestionEtudiants();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionDesEtudiants);
		
		JMenuItem mntmGestionDesUtilisateurs = new JMenuItem("Gestion Utilisateurs");
		mntmGestionDesUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUser user = new GestionUser();
				user.setVisible(true);
				user.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionDesUtilisateurs);
		
		JMenuItem mntmGestionDesFilieres = new JMenuItem("Gestion Authentification                 \r\n");
		mntmGestionDesFilieres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAuthentification auth = new GestionAuthentification();
				auth.setVisible(true);
				auth.setLocationRelativeTo(null);
				fermer();
			}
		});
		
		JMenuItem mntmMenuDadministration = new JMenuItem("Menu Administration");
		mntmMenuDadministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuAdminstrateur admin = new MenuAdminstrateur();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmMenuDadministration);
		mnOuvrir.add(mntmGestionDesFilieres);
		
		JMenuItem mntmEnregistrer = new JMenuItem("Fermer");
		mntmEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				fermer();
				
			}
		});
		mntmEnregistrer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnFichier.add(mntmEnregistrer);
		
		JMenu mnEditer = new JMenu("Editer ");
		mnEditer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnEditer.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnEditer);
		
		JMenuItem mntmColler = new JMenuItem("Coller                  ");
		mnEditer.add(mntmColler);
		
		JMenuItem mntmCopier = new JMenuItem("Copier                      ");
		mnEditer.add(mntmCopier);
		
		JMenu mnSource = new JMenu("Source");
		mnSource.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnSource.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnSource);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setIcon(null);
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnActualiser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateTable();
			}
		});
		btnActualiser.setForeground(Color.BLUE);
		btnActualiser.setFont(new Font("Arial", Font.BOLD, 20));
		btnActualiser.setBounds(1014, 577, 135, 37);
		panel.add(btnActualiser);
		
		textField = new JTextField();
		textField.setText("LOGICIEL DE GESTION");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLUE);
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(0, 25, 1099, 94);
		panel.add(textField);
		
		JButton button = new JButton("New button");
		button.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\MyPicture\\a.jpg"));
		button.setBounds(1102, 25, 172, 122);
		panel.add(button);
	
	}
	
//	Creation de la methode mise a jour de la table
	public void updateTable(){
//		Code sql permettant de selectionner des elements dans la table utilisateurs de la db 
		String sql = "SELECT * FROM filieres";
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
