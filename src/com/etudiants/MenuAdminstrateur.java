package com.etudiants;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import MesTest.GestionFiliere;

public class MenuAdminstrateur extends JFrame {
    
	private JPanel contentPane;
	private JButton button_1;
	private JButton btnNewButton_2;
	private JLabel lblGs;
	private JLabel lblNewLabel;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenu mnOuvrir;
	private JMenuItem mntmGestionFiliere;
	private JMenuItem mntmGestionEtudiants;
	private JMenuItem mntmGestionUtilisateurs;
	private JMenuItem mntmGestionAuthentification;
	private JMenu mnEditer;
	private JMenuItem mntmCopier;
	private JMenuItem mntmColler;
	private JMenu mnSource;
	private JMenuItem mntmFermer;
	private JTextField txtLogicielDeGestion;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdminstrateur frame = new MenuAdminstrateur();
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
	public MenuAdminstrateur() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mnOuvrir = new JMenu("Ouvrir");
		mnFichier.add(mnOuvrir);
		
		mntmGestionFiliere = new JMenuItem("Gestion Filieres");
		mntmGestionFiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_Filiere etu = new Gestion_Filiere();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionFiliere);
		
		mntmGestionEtudiants = new JMenuItem("Gestion Etudiants");
		mntmGestionEtudiants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEtudiants etu = new GestionEtudiants();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionEtudiants);
		
		mntmGestionUtilisateurs = new JMenuItem("Gestion Utilisateurs");
		mntmGestionUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUser etu = new GestionUser();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionUtilisateurs);
		
		mntmGestionAuthentification = new JMenuItem("Gestion Authentification");
		mntmGestionAuthentification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAuthentification etu = new GestionAuthentification();
				etu.setVisible(true);
				etu.setLocationRelativeTo(null);
				fermer();
			}
		});
		mnOuvrir.add(mntmGestionAuthentification);
		
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
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionUser obj = new GestionUser();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\Admin-icon.png"));
		button.setBounds(370, 180, 157, 149);
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				GestionEtudiants etud = new GestionEtudiants();
				etud.setVisible(true);
				etud.setLocationRelativeTo(null);
				fermer();
			
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement (23).jpg"));
		btnNewButton_1.setBounds(370, 439, 165, 149);
		contentPane.add(btnNewButton_1);
		
		button_1 = new JButton("");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Gestion_Filiere obj = new Gestion_Filiere();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
				
			}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement (22).jpg"));
		button_1.setBounds(589, 180, 157, 149);
		contentPane.add(button_1);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\images (3).png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAbsences obj = new GestionAbsences();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton_2.setBounds(589, 439, 157, 149);
		contentPane.add(btnNewButton_2);
		
		lblGs = new JLabel("Gestion des Utilisateurs");
		lblGs.setFont(new Font("Arial", Font.BOLD, 15));
		lblGs.setForeground(new Color(0, 0, 255));
		lblGs.setHorizontalAlignment(SwingConstants.CENTER);
		lblGs.setBounds(352, 340, 183, 14);
		contentPane.add(lblGs);
		
		lblNewLabel = new JLabel("Gestion des Etudiants");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(352, 599, 201, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des Fili\u00E8res");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(589, 340, 165, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des Absences");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(563, 599, 212, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Info obj = new Info();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton_5.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\absent-blue-icon.png"));
		btnNewButton_5.setBounds(1188, 544, 61, 71);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel_5 = new JLabel("Info ");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5.setForeground(new Color(0, 0, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(1188, 616, 62, 30);
		contentPane.add(lblNewLabel_5);
		
		txtLogicielDeGestion = new JTextField();
		txtLogicielDeGestion.setText("LOGICIEL DE GESTION");
		txtLogicielDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogicielDeGestion.setForeground(Color.BLUE);
		txtLogicielDeGestion.setFont(new Font("Algerian", Font.BOLD, 50));
		txtLogicielDeGestion.setEditable(false);
		txtLogicielDeGestion.setColumns(10);
		txtLogicielDeGestion.setBackground(Color.WHITE);
		txtLogicielDeGestion.setBounds(0, 0, 1121, 94);
		contentPane.add(txtLogicielDeGestion);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\MyPicture\\a.jpg"));
		btnNewButton.setBounds(1119, -10, 165, 138);
		contentPane.add(btnNewButton);
	}
//	Creation de la methode fermer avec la fonction dispose
	public void fermer()
	{
		dispose();
	}
}
