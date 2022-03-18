package com.etudiants;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSeparator;

import net.proteanit.sql.DbUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JMenuBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;

import java.awt.GridLayout;


public class GestionEtudiants extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField prenomField;
	private JTextField nomField;
	private JTextField adresseField;
	private JTextField dateNaissField;
	private JTextField emailField;
	private JLabel lblEmail;
	private JLabel lblDateDe;
	private JLabel lblAdresse;
	private JLabel lblTelephone;
	private JTextField tellField;
	private JLabel lblFiliere;
	private JComboBox filiereBox;
	private JButton btnNewButton;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton button_3;
	private JLabel lblTableauDesEtudiants;
	private JButton btnActualiser;
	private String s;
	
//	declaration des variables de connexion
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenu mnOuvrir;
	private JMenuItem mntmGestionFilieres;
	private JMenuItem mntmGestionUtilisateurs;
	private JMenuItem mntmMenuAdminitractions;
	private JMenuItem mntmGestionAuthentifications;
	private JMenuItem mntmFermer;
	private JMenu mnEditer;
	private JMenu mnSource;
	private JMenuItem mntmCopier;
	private JMenuItem mntmColler;
	private JLabel labelimg;
	private JTextField textField;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEtudiants frame = new GestionEtudiants();
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
	public GestionEtudiants() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mnOuvrir = new JMenu("Ouvrir");
		mnFichier.add(mnOuvrir);
		
		mntmGestionFilieres = new JMenuItem("Gestion Filieres");
		mntmGestionFilieres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_Filiere admin = new Gestion_Filiere();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionFilieres);
		
		mntmGestionUtilisateurs = new JMenuItem("Gestion Utilisateurs");
		mntmGestionUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUser admin = new GestionUser();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionUtilisateurs);
		
		mntmMenuAdminitractions = new JMenuItem("Menu Adminitractions");
		mntmMenuAdminitractions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuAdminstrateur admin = new MenuAdminstrateur();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmMenuAdminitractions);
		
		mntmGestionAuthentifications = new JMenuItem("Gestion Authentifications");
		mntmGestionAuthentifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAuthentification admin = new GestionAuthentification();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionAuthentifications);
		
		mntmFermer = new JMenuItem("Fermer");
		mntmFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fermer();
			}
		});
		mnFichier.add(mntmFermer);
		
		mnEditer = new JMenu("Editer");
		menuBar.add(mnEditer);
		
		mntmCopier = new JMenuItem("Copier");
		mnEditer.add(mntmCopier);
		
		mntmColler = new JMenuItem("Coller");
		mnEditer.add(mntmColler);
		
		mnSource = new JMenu("Source");
		menuBar.add(mnSource);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		Activation de la connexion a la db
		cnx = ConnexionMysql.conneexioDB();
//		Appel de la methode Remplircombobox
//		Remplircombobox();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 1284, 671);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(479, 250, 795, 323);
		panel.add(scrollPane);
		
//		La table
		table = new JTable();
		table.setFillsViewportHeight(true);
//		Creation du bouton clicked dans la table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
//				La fonction getSelectRow permet de select les elements de la ligne clicker
				int ligne = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql = "SELECT * FROM etudiants where id = '"+id+"'";
				
				try {
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					resultat = prepare.executeQuery();
					if (resultat.next()) 
					{
//						Permet de mettre tout les donnees selectionner de la table dans les textfeilds qui respondant
						prenomField.setText(resultat.getString("prenom"));
						nomField.setText(resultat.getString("nom"));
						emailField.setText(resultat.getString("email"));
						dateNaissField.setText(resultat.getString("date_naiss"));
						adresseField.setText(resultat.getString("adresse"));
						tellField.setText(resultat.getString("tell"));
						filiereBox.setSelectedItem(resultat.getString("filiere"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		prenomField = new JTextField();
		prenomField.setHorizontalAlignment(SwingConstants.CENTER);
		prenomField.setFont(new Font("Arial", Font.BOLD, 18));
		prenomField.setBounds(10, 326, 230, 37);
		panel.add(prenomField);
		prenomField.setColumns(10);
		
		nomField = new JTextField();
		nomField.setHorizontalAlignment(SwingConstants.CENTER);
		nomField.setFont(new Font("Arial", Font.BOLD, 18));
		nomField.setColumns(10);
		nomField.setBounds(254, 325, 220, 38);
		panel.add(nomField);
		
		adresseField = new JTextField();
		adresseField.setHorizontalAlignment(SwingConstants.CENTER);
		adresseField.setFont(new Font("Arial", Font.BOLD, 18));
		adresseField.setColumns(10);
		adresseField.setBounds(10, 447, 230, 37);
		panel.add(adresseField);
		
		dateNaissField = new JTextField();
		dateNaissField.setHorizontalAlignment(SwingConstants.CENTER);
		dateNaissField.setFont(new Font("Arial", Font.BOLD, 18));
		dateNaissField.setColumns(10);
		dateNaissField.setBounds(44, 392, 395, 35);
		panel.add(dateNaissField);
		
		emailField = new JTextField();
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setFont(new Font("Arial", Font.BOLD, 18));
		emailField.setColumns(10);
		emailField.setBounds(254, 448, 220, 35);
		panel.add(emailField);
		
		JLabel lblNewLabel = new JLabel("Prenom");
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 300, 208, 30);
		panel.add(lblNewLabel);
		
		JLabel nomFeild = new JLabel("Nom");
		nomFeild.setHorizontalAlignment(SwingConstants.CENTER);
		nomFeild.setForeground(Color.BLUE);
		nomFeild.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		nomFeild.setBounds(261, 300, 208, 30);
		panel.add(nomFeild);
		
		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.BLUE);
		lblEmail.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblEmail.setBounds(260, 429, 202, 20);
		panel.add(lblEmail);
		
		lblDateDe = new JLabel("Lieu et Date Naissance");
		lblDateDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateDe.setForeground(Color.BLUE);
		lblDateDe.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblDateDe.setBounds(0, 374, 487, 20);
		panel.add(lblDateDe);
		
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setForeground(Color.BLUE);
		lblAdresse.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblAdresse.setBounds(17, 429, 202, 20);
		panel.add(lblAdresse);
		
		lblTelephone = new JLabel("Telephone");
		lblTelephone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelephone.setForeground(Color.BLUE);
		lblTelephone.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblTelephone.setBounds(260, 495, 208, 20);
		panel.add(lblTelephone);
		
		tellField = new JTextField();
		tellField.setHorizontalAlignment(SwingConstants.CENTER);
		tellField.setFont(new Font("Arial", Font.BOLD, 18));
		tellField.setColumns(10);
		tellField.setBounds(270, 516, 204, 35);
		panel.add(tellField);
		
		lblFiliere = new JLabel("Filiere");
		lblFiliere.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiliere.setForeground(Color.BLUE);
		lblFiliere.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblFiliere.setBounds(17, 495, 202, 20);
		panel.add(lblFiliere);
		
		filiereBox = new JComboBox();
		filiereBox.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		filiereBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionner une Filiere"}));
		filiereBox.setBounds(10, 517, 255, 35);
		panel.add(filiereBox);
//		Appel de la methode Remplircombobox
		Remplircombobox();
		
		btnNewButton = new JButton("Ajouter");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Initialisation des variables Exp: prenom doit recevoir les donnees de prenomFeild
				String prenom = prenomField.getText().toString();
				String nom = nomField.getText().toString();
				String dateNaiss = dateNaissField.getText().toString();
				String adress = adresseField.getText().toString();
				String email = emailField.getText().toString();
				String tell = tellField.getText().toString();
				
//				Code sql permettant d inserer les donnees saissis dans les Feild dans la db
				String sql = "INSERT INTO etudiants(prenom, nom, email, date_naiss, adresse, tell, filiere, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
				
//				try/catch obligatoire
				try {
					
					FileInputStream img = new FileInputStream(new File(s));
					
//					La condition pour pour eviter les champs vides
					if(!prenom.equals("") && !nom.equals("") && !dateNaiss.equals("") && !adress.equals("")
							&& !email.equals("") && !tell.equals("") )
					{
//						Preparation de la requete
						prepare = (PreparedStatement) cnx.prepareStatement(sql);
//						Prenom de toucher a id selectionner le 1 represente prenom
						prepare.setString(1, prenom);
						prepare.setString(2, nom);
						prepare.setString(3, email);
						prepare.setString(4, dateNaiss);
						prepare.setString(5, adress);
						
//						Condition permettant d'empecher les lettres dans tell //marche pas
//						if (tell.equals("a")) {
//							JOptionPane.showMessageDialog(null, "Entrer des chiffres dans tell");	
//							}else {
								prepare.setString(6, tell);
//							}
						
						prepare.setString(7, filiereBox.getSelectedItem().toString());
						prepare.setBlob(8, img);
						int p = JOptionPane.showConfirmDialog(null,"Voulez-vous Ajouter cet Etudiant ? ", "Ajout d'Etudiant", JOptionPane.YES_NO_OPTION);
						if( p == 0){
//							Execution de la requete
							prepare.execute();
							
//							filiereBox.setSelectedItem("");
							JOptionPane.showMessageDialog(null, "Etudiant ajoute avec succes");
							updateTable();
							
							prenomField.setText("");
							nomField.setText("");
							dateNaissField.setText("");
							adresseField.setText("");
							emailField.setText("");
							tellField.setText("");
						}

					}else {
						JOptionPane.showMessageDialog(null, "Remplisser les champs vides");
					}
		
				} catch (SQLException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(171, 574, 166, 37);
		panel.add(btnNewButton);
		
//		Bouton modification
		btnModifier = new JButton("Modifier");
		btnModifier.setForeground(Color.BLUE);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				cette fonction permette de recuperer la valeur selectionner dans le tableau
				int ligne = table.getSelectedRow();
				
				if (ligne == -1) 
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner un etudiant");
				}else 
				{
					String id = table.getModel().getValueAt(ligne, 0).toString();
//					Code sql permettant de modifier les etudiants selectionnes dans db
					String sql = " UPDATE etudiants SET prenom = ?, nom = ?, email = ?, date_naiss = ?, adresse = ?, tell = ?, filiere = ? where id = '"+id+"'";
					
//					
					try {
						prepare = (PreparedStatement) cnx.prepareStatement(sql);
						prepare.setString(1, prenomField.getText().toString());
						prepare.setString(2, nomField.getText().toString());
						prepare.setString(3, emailField.getText().toString());
						prepare.setString(4, dateNaissField.getText().toString());
						prepare.setString(5, adresseField.getText().toString());
						prepare.setString(6, tellField.getText().toString());
						prepare.setString(7, filiereBox.getSelectedItem().toString());
						
					if(!prenomField.equals("") && !nomField.equals("") && !dateNaissField.equals("") && !emailField.equals("")
							&& !adresseField.equals("") && !tellField.equals("") )
					{
						int o = JOptionPane.showConfirmDialog(null, "Voulez-vous Modifier ?", "Modification d'etudiant", JOptionPane.YES_NO_OPTION);
						if (o == 0)
						{
							prepare.execute();
							JOptionPane.showMessageDialog(null, "L'etudiant a ete modifier");
							updateTable();	
							prenomField.setText("");
							nomField.setText("");
							dateNaissField.setText("");
							adresseField.setText("");
							emailField.setText("");
							tellField.setText("");
						}
					
					}else 
					{
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
		btnModifier.setIcon(null);
		btnModifier.setBounds(542, 584, 136, 33);
		panel.add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(Color.BLUE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int ligne = table.getSelectedRow();
				if (ligne == -1) 
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner un Etudiant !!");
				}else
				{
					String id = table.getModel().getValueAt(ligne, 0).toString();
					
//					Code sql permettant de supprimer un donnee dans la table etudiants en selectionnant id 
					String sql = "DELETE FROM etudiants WHERE id = '"+id+"'";
					
					try {
						prepare = (PreparedStatement) cnx.prepareStatement(sql);
						
						int s = JOptionPane.showConfirmDialog(null, "Voulez-vous Supprimer !", "Supprimer l'Etudiant", JOptionPane.YES_NO_OPTION);
						if (s == 0)
						{
							prepare.execute();
							
							JOptionPane.showMessageDialog(null, "L'etudiant supprimer !");
							updateTable();
							prenomField.setText("");
							nomField.setText("");
							dateNaissField.setText("");
							adresseField.setText("");
							emailField.setText("");
							tellField.setText("");	
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnSupprimer.setIcon(null);
		btnSupprimer.setBounds(731, 582, 144, 35);
		panel.add(btnSupprimer);
		
		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAdminstrateur menu = new MenuAdminstrateur();
				menu.setVisible(true);
				menu.setLocationRelativeTo(null);
				fermer();
				
			}
		});
		button_3.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\back.png"));
		button_3.setForeground(new Color(0, 0, 128));
		button_3.setFont(new Font("Arial", Font.BOLD, 20));
		button_3.setBounds(0, 105, 49, 37);
		panel.add(button_3);
		
		lblTableauDesEtudiants = new JLabel("Tableau des Etudiants");
		lblTableauDesEtudiants.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableauDesEtudiants.setForeground(Color.BLUE);
		lblTableauDesEtudiants.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblTableauDesEtudiants.setBounds(747, 214, 267, 29);
		panel.add(lblTableauDesEtudiants);
		
		btnActualiser = new JButton("Actualiser");
		btnActualiser.setIcon(null);
		btnActualiser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				updateTable();
			}
		});
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnActualiser.setForeground(Color.BLUE);
		btnActualiser.setFont(new Font("Arial", Font.BOLD, 20));
		btnActualiser.setBounds(1090, 584, 144, 33);
		panel.add(btnActualiser);
		
//		Bouton imprimer
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnImprimer.setForeground(Color.BLUE);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				On cree un obj de la classe document
				Document doc = new Document();
				
//				Code sql permettant de selectionner tout les donnees dans db
				String sql = "SELECT * FROM etudiants";

				try {
					
//					Creation de la connexion
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					int v = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer cet document ?", "Imprimer cet document", JOptionPane.YES_NO_OPTION);
					if (v == 0)
					{
						resultat = prepare.executeQuery();	
					
//					Methode permettant d editer le fichier pdf
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\sogor\\Desktop\\etudiantsList.pdf"));
					doc.open();
					
//					Ajout d un image 
//					NB: les extension png jpeg jpg sont tres important
					Image img = Image.getInstance("C:\\Users\\sogor\\Desktop\\MyPicture\\a.jpg");
					img.scaleAbsoluteWidth(100);
					img.scaleAbsoluteHeight(92);
					img.setAlignment(Image.ALIGN_LEFT);
					doc.add(img);
					
//					Ajouter un paragraph dans le pdf
//					doc.add(new Paragraph(" "));
					doc.add(new Paragraph("Liste des Etudiants de l'universite ESCT : "));

					doc.add(new Paragraph(" "));
					doc.add(new Paragraph(" "));
					
//					Creation du tableau dans le fichier pdf, 6 represent le nbr de ligne
					PdfPTable table = new PdfPTable(7);
					table.setWidthPercentage(100);
					
//					Creation des colonnes et declaration de la variable cell
					PdfPCell cell;
//					\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
					
					cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Arial","bold", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
//					Ajout des colonne dans la table
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Arial","bold", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
//					Ajout des colonne dans la table
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Email", FontFactory.getFont("Arial","bold", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
//					Ajout des colonne dans la table
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Lieu et Date Naissance", FontFactory.getFont("Arial","bold", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
//					Ajout des colonne dans la table
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Adresse", FontFactory.getFont("Arial","bold", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
//					Ajout des colonne dans la table
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Tell", FontFactory.getFont("Arial", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
//					Ajout des colonne dans la table
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Filiere", FontFactory.getFont("Arial", 12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
//					Ajout des colonne dans la table
					table.addCell(cell);
					
					
					
//					\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
					
//					NB:Cette fonction nomField.getText().toString() permet de recuperer les donnes saisis dans ce champs
					//////////////////////////////////////////////////////////////////////////////////////////////////////////
					while (resultat.next()) 
					{
					
						cell = new PdfPCell(new Phrase(resultat.getString("prenom").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						Ajout des colonne dans la table
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("nom").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						Ajout des colonne dans la table
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("email").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						Ajout des colonne dans la table
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("date_naiss").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						Ajout des colonne dans la table
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("adresse").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						Ajout des colonne dans la table
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("tell").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						Ajout des colonne dans la table
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("filiere").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						Ajout des colonne dans la table
						table.addCell(cell);
						
					}
					
					
					
					//////////////////////////////////////////////////////////////////////////////////////////////////////////
//					Ajout de la table dans le doc pdf
					doc.add(table);
					

					
					
					doc.close();
					
//					Ouverture du fichier sur le bureau
					Desktop.getDesktop().open(new File("C:\\Users\\sogor\\Desktop\\etudiantsList.pdf"));

					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});
		btnImprimer.setIcon(null);
		btnImprimer.setBounds(927, 584, 122, 33);
		panel.add(btnImprimer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(171, 136, 149, 139);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 1));
		
		labelimg = new JLabel("");
		panel_1.add(labelimg);
		
		JButton btnParcouri = new JButton("Parcourir");
		btnParcouri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("C:\\Users\\sogor\\Desktop"));
				FileNameExtensionFilter filtrer = new FileNameExtensionFilter("IMAGE ", "jpg ", "png ", "gif ");
				fc.addChoosableFileFilter(filtrer);
				int resultat = fc.showSaveDialog(null);
				
//				Affiche de l'image 
				if (resultat == fc.APPROVE_OPTION) 
				{
					File selectFile = fc.getSelectedFile();
					String path = selectFile.getAbsolutePath();
					ImageIcon myImg = new ImageIcon(path);
					java.awt.Image img = myImg.getImage();
					java.awt.Image newImg = img.getScaledInstance(labelimg.getWidth(), labelimg.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon finalImg = new ImageIcon(newImg);
					labelimg.setIcon(finalImg);
					s = path;
				}else {
					if (resultat == fc.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(null, "T'as pas choisi d'image!");
					}
				}
			}
		});
		btnParcouri.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		btnParcouri.setBounds(171, 276, 149, 29);
		panel.add(btnParcouri);
		
		textField = new JTextField();
		textField.setText("LOGICIEL DE GESTION");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLUE);
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(0, 0, 1115, 94);
		panel.add(textField);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\MyPicture\\a.jpg"));
		btnNewButton_1.setBounds(1118, 0, 166, 126);
		panel.add(btnNewButton_1);
	}
	
//	Creation de la methode mise a jour de la table
	public void updateTable(){
//		Code sql permettant de selectionner des elements dans la table utilisateurs de la db 
		String sql = "SELECT * FROM etudiants";
		try {
			prepare = (PreparedStatement) cnx.prepareStatement(sql);
			resultat = prepare.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	Methode pour remplir combobox
	private void Remplircombobox() {
		// Requete sql permettant de selectionner la table filieres
		String sql = "SELECT * FROM filieres";
		
		try {
			prepare = (PreparedStatement) cnx.prepareStatement(sql);
			resultat = prepare.executeQuery();
			while (resultat.next()) {
				String nom = resultat.getString("nom_filiere").toString();
				filiereBox.addItem(nom);
				
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
}
