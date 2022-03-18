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

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;
import com.toedter.components.JLocaleChooser;

import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;

import java.awt.Window.Type;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GestionAbsences extends JFrame {

	private JPanel contentPane;
	
//	declaration des variables de connexion
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	private JTable table;
	
	String AbsentVB = null;
	private JComboBox nomcomboBox;
	private JDateChooser dateChooser;
	private JButton btnAjouter;
	private JComboBox raisonComboBox;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAbsences frame = new GestionAbsences();
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
	public GestionAbsences() {
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Gestion des Absences");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenu mnOuvrir = new JMenu("Ouvrir");
		mnFichier.add(mnOuvrir);
		
		JMenuItem mntmGestionFiliere = new JMenuItem("Gestion Filieres");
		mntmGestionFiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_Filiere etu = new Gestion_Filiere();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionFiliere);
		
		JMenuItem mntmGestionEtudiant = new JMenuItem("Gestion Etudiants");
		mntmGestionEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEtudiants etu = new GestionEtudiants();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionEtudiant);
		
		JMenuItem mntmGestionUtilisateurs = new JMenuItem("Gestion Utilisateurs");
		mntmGestionUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUser etu = new GestionUser();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionUtilisateurs);
		
		JMenuItem mntmGestionAuthentifications = new JMenuItem("Gestion Authentifications");
		mntmGestionAuthentifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAuthentification etu = new GestionAuthentification();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionAuthentifications);
		
		JMenuItem mntmMenuAdministrateur = new JMenuItem("Menu Administrateurs");
		mntmMenuAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuAdminstrateur etu = new MenuAdminstrateur();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmMenuAdministrateur);
		
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		Activation de la connexion
		cnx = ConnexionMysql.conneexioDB();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setLocale((Locale) null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(240, 255, 240));
		panel.setBounds(0, 0, 1284, 650);
		contentPane.add(panel);
		
		JLabel lblNomDeLetudiant = new JLabel("Nom de l'Etudiant");
		lblNomDeLetudiant.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomDeLetudiant.setForeground(Color.BLUE);
		lblNomDeLetudiant.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblNomDeLetudiant.setBounds(58, 338, 200, 46);
		panel.add(lblNomDeLetudiant);
		
		JLabel lblDateDabsence = new JLabel("Date d'Absence");
		lblDateDabsence.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateDabsence.setForeground(Color.BLUE);
		lblDateDabsence.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblDateDabsence.setBounds(50, 395, 183, 43);
		panel.add(lblDateDabsence);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setIcon(null);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				Declaration des variable
				String nomEtu = nomcomboBox.getSelectedItem().toString();
				String dateAbsent = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				String raison = raisonComboBox.getSelectedItem().toString();
				
//				Insertion du code sql
				String sql = "INSERT INTO absent(nom_Absent, date_Absent, raison_Absent) VALUES(?,?,?)";
				
				try {
					int i = JOptionPane.showConfirmDialog(null, "Voulez-vous Ajouter cet Absent", "Ajout des Absents", JOptionPane.YES_NO_OPTION);
					if (i == 0) {
					if (nomcomboBox.getSelectedItem().equals("Selectionner") || dateChooser.getDateFormatString().equals("") || raisonComboBox.getSelectedItem().equals("Selectionner")) 
					{
						JOptionPane.showMessageDialog(null, "Completez les information");
					}
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, nomEtu);
					prepare.setString(2, dateAbsent);
					prepare.setString(3, raison);
					prepare.execute();
					
					JOptionPane.showMessageDialog(null, "Absent ajoute avec succes");
//					Permet la mise a jour
					updateTable();
//					Pour rendre ls champs vides apres l'execution
					nomcomboBox.setSelectedItem("Selectionner");
					dateChooser.setDateFormatString("");
					raisonComboBox.setSelectedItem("Selectionner");
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAjouter.setForeground(new Color(0, 0, 128));
		btnAjouter.setFont(new Font("Arial", Font.BOLD, 20));
		btnAjouter.setBounds(283, 518, 145, 33);
		panel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une Absence !");
				}else {
					String id = table.getModel().getValueAt(ligne, 0).toString();
					
					String sql = "DELETE FROM absent WHERE id_Absent = '"+id+"'";
					
					try {
						int i = JOptionPane.showConfirmDialog(null, "Voulez-vous Supprimer cet Absent ?", "Supprimer des Absents", JOptionPane.YES_NO_OPTION);
						if (i == 0) {
						prepare = (PreparedStatement) cnx.prepareStatement(sql);
						prepare.execute();
						
						JOptionPane.showMessageDialog(null, "Absent Suppprimer avec succes !");
						updateTable();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		btnSupprimer.setIcon(null);
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setFont(new Font("Arial", Font.BOLD, 20));
		btnSupprimer.setBounds(884, 584, 135, 33);
		panel.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setIcon(null);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				int ligne = table.getSelectedRow();
				
				String nomAbs = nomcomboBox.getSelectedItem().toString();
				String dateAbs = dateChooser.getDateEditor().getUiComponent().toString();
				String raiAbs = raisonComboBox.getSelectedItem().toString();
				
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez un absent");
				}else {
					
					String id = table.getModel().getValueAt(ligne, 0).toString();
					
					String sql = "UPDATE absent SET nom_Absent = ?, date_Absent = ?, raison_Absent = ? WHERE id_Absent = '"+id+"'";
					
					try {
						int i = JOptionPane.showConfirmDialog(null, "Voulez-vous Modifier cet Absent ?", "Modifier une Absence", JOptionPane.YES_NO_OPTION);
						if (i == 0) {
							
						prepare = (PreparedStatement) cnx.prepareStatement(sql);
						prepare.setString(1, nomAbs);
						prepare.setString(2, dateAbs);
						prepare.setString(3, raiAbs);
						prepare.execute();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnModifier.setForeground(new Color(0, 0, 128));
		btnModifier.setFont(new Font("Arial", Font.BOLD, 20));
		btnModifier.setBounds(674, 584, 145, 33);
		panel.add(btnModifier);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuAdminstrateur obj = new MenuAdminstrateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button_4.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\back.png"));
		button_4.setForeground(new Color(0, 0, 128));
		button_4.setFont(new Font("Arial", Font.BOLD, 20));
		button_4.setBounds(0, 105, 56, 43);
		panel.add(button_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(573, 257, 711, 303);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				permet d afficher dans textField un element selectionne dans la table
				int ligne = table.getSelectedRow();
				
//				permet d inserer respectivement les donnees dans la table avec  
				AbsentVB  = table.getModel().getValueAt(ligne, 1).toString();
				String values = table.getModel().getValueAt(ligne, 2).toString();
				String values1 = table.getModel().getValueAt(ligne, 3).toString();
				
//				les donnees inserees dans la table seront afficher respectivement dans textField 
				nomcomboBox.setSelectedItem(AbsentVB);
				dateChooser.setDateFormatString(values);
				raisonComboBox.setSelectedItem(values1);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTable();
			}
		});
		btnActualiser.setIcon(null);
		btnActualiser.setForeground(Color.BLUE);
		btnActualiser.setFont(new Font("Arial", Font.BOLD, 20));
		btnActualiser.setBounds(1083, 585, 135, 33);
		panel.add(btnActualiser);
		
		JLabel lblTableauDesAbsences = new JLabel("Tableau des Absences");
		lblTableauDesAbsences.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableauDesAbsences.setForeground(Color.BLUE);
		lblTableauDesAbsences.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblTableauDesAbsences.setBounds(758, 223, 340, 29);
		panel.add(lblTableauDesAbsences);
		
		JLabel lblRaisonDabsences = new JLabel("Raison d'Absences");
		lblRaisonDabsences.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaisonDabsences.setForeground(Color.BLUE);
		lblRaisonDabsences.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblRaisonDabsences.setBounds(37, 453, 248, 43);
		panel.add(lblRaisonDabsences);
		
		nomcomboBox = new JComboBox();
		nomcomboBox.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		nomcomboBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionner"}));
		nomcomboBox.setBounds(280, 340, 263, 44);
		panel.add(nomcomboBox);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd MMM yyyy");
		dateChooser.setForeground(Color.BLACK);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setBounds(280, 395, 263, 43);
		panel.add(dateChooser);
		
		JLocaleChooser localeChooser = new JLocaleChooser();
		localeChooser.setBounds(280, 288, 87, -32);
		panel.add(localeChooser);
		
		raisonComboBox = new JComboBox();
		raisonComboBox.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		raisonComboBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionner", "Malade", "Reatart", "Panne", "Moyen de Transport", "Volontaire"}));
		raisonComboBox.setBounds(280, 453, 263, 43);
		panel.add(raisonComboBox);
		
		textField = new JTextField();
		textField.setText("LOGICIEL DE GESTION");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLUE);
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(0, 0, 1119, 94);
		panel.add(textField);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\MyPicture\\a.jpg"));
		btnNewButton.setBounds(1120, 0, 164, 126);
		panel.add(btnNewButton);
		remplirCombobox();
	}
	
//	Methode permettant de remplir combobox
	
	public void remplirCombobox(){
//		Code sql 
		String sql = "SELECT * FROM etudiants";
		
			try {
				prepare = (PreparedStatement) cnx.prepareStatement(sql);
				resultat = prepare.executeQuery();
				
				while (resultat.next()) {
					String nom = resultat.getString("nom").toString();
					String prenom = resultat.getString("prenom").toString();
					
					nomcomboBox.addItem(prenom +"  "+nom);
				}
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
	
//		Creation de la methode mise a jour de la table
	public void updateTable(){
//		Code sql permettant de selectionner des elements dans la table utilisateurs de la db 
		String sql = "SELECT id_Absent, nom_Absent, date_Absent, raison_Absent FROM absent";
		try {
			prepare = (PreparedStatement) cnx.prepareStatement(sql);
			resultat = prepare.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}


