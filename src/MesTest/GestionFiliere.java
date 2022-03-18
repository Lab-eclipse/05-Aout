package MesTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.etudiants.ConnexionMysql;
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
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;

public class GestionFiliere extends JFrame {

	private JPanel contentPane;
	
	String userVB = null;
	
//	declaration des variable de la connexion dans la classe motPasseIndicateur
	Connection cnx = null;
	PreparedStatement prepare = null; 
	ResultSet resultat = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField nomField;
	private JComboBox typeComboBox;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFiliere frame = new GestionFiliere();
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
	public GestionFiliere() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		Activation de la connexion a la db
		cnx = ConnexionMysql.conneexioDB();
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setLocale((Locale) null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(255, 204, 255));
		panel.setBounds(0, 0, 1284, 671);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setText("GESTION DES ETUDIANTS");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(0, 0, 205));
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(230, 230, 250));
		textField.setBounds(161, 0, 941, 108);
		panel.add(textField);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.jpg"));
		button.setBounds(-14, 0, 183, 162);
		panel.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.jpg"));
		button_1.setBounds(1101, 0, 183, 162);
		panel.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setText("UNIVERSITE ESCT");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLUE);
		textField_1.setFont(new Font("Algerian", Font.BOLD, 25));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(230, 230, 250));
		textField_1.setBounds(480, 105, 334, 57);
		panel.add(textField_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nomfil = nomField.getText().toString();
				String combox = typeComboBox.getSelectedItem().toString();
				
//				Code sql pour le btn Ajouter
				String sql = "INSERT INTO filieres(nomfiliere, type) VALUES(?, ?)";
				
				try
				{
					prepare = (PreparedStatement) cnx.prepareStatement(sql);
					prepare.setString(1, nomfil);
					prepare.setString(2, combox);
					prepare.execute();
					
					nomField.setText("");
					typeComboBox.setSelectedItem("Selectionner");
					JOptionPane.showMessageDialog(null, "Filiere ajoutee");
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		button_2.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\userAdd.jpg"));
		button_2.setForeground(new Color(0, 0, 128));
		button_2.setFont(new Font("Arial", Font.BOLD, 20));
		button_2.setBounds(99, 537, 51, 57);
		panel.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\UserDelete1.jpg"));
		button_3.setForeground(new Color(0, 0, 128));
		button_3.setFont(new Font("Arial", Font.BOLD, 20));
		button_3.setBounds(432, 537, 51, 57);
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\UerModifier.jpg"));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_4.setForeground(new Color(0, 0, 128));
		button_4.setFont(new Font("Arial", Font.BOLD, 20));
		button_4.setBounds(265, 537, 51, 57);
		panel.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				fermer();
				
			}
		});
		button_5.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\back.png"));
		button_5.setForeground(new Color(0, 0, 128));
		button_5.setFont(new Font("Arial", Font.BOLD, 20));
		button_5.setBounds(10, 157, 56, 43);
		panel.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				updateTable();
				
			}
		});
		button_6.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\Apps-system-software-update-icon.png"));
		button_6.setForeground(Color.BLUE);
		button_6.setFont(new Font("Arial", Font.BOLD, 20));
		button_6.setBounds(1197, 221, 51, 46);
		panel.add(button_6);
		
		JLabel label_2 = new JLabel("AJOUTER");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_2.setBounds(55, 605, 135, 25);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("MODIFIER");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Arial", Font.BOLD, 16));
		label_3.setBounds(212, 603, 154, 27);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("SUPPRIMER");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("Arial", Font.BOLD, 16));
		label_4.setBounds(386, 603, 135, 27);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Tableau des Utilisateurs");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("Arial", Font.BOLD, 20));
		label_5.setBounds(508, 237, 267, 29);
		panel.add(label_5);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBackground(Color.RED);
		separator.setBounds(10, 208, 1265, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.RED);
		separator_1.setBackground(Color.RED);
		separator_1.setBounds(518, 208, 2, 452);
		panel.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(541, 304, 707, 326);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				permet d afficher dans textField un element selectionne dans la table
				int ligne = table.getSelectedRow();
				
//				permet d inserer respectivement les donnees dans la table avec  
				userVB = table.getModel().getValueAt(ligne, 1).toString();
				String values = table.getModel().getValueAt(ligne, 2).toString();
				
//				les donnees inserees dans la table seront afficher respectivement dans textField 
				nomField.setText(userVB);
				typeComboBox.setSelectedItem(values);
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNomDuFiliere = new JLabel("Nom du Filiere");
		lblNomDuFiliere.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblNomDuFiliere.setForeground(new Color(0, 0, 255));
		lblNomDuFiliere.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomDuFiliere.setBounds(10, 328, 203, 37);
		panel.add(lblNomDuFiliere);
		
		JLabel lblTypeDuFiliere = new JLabel("Type du Filiere");
		lblTypeDuFiliere.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblTypeDuFiliere.setForeground(new Color(0, 0, 255));
		lblTypeDuFiliere.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeDuFiliere.setBounds(10, 376, 203, 41);
		panel.add(lblTypeDuFiliere);
		
		typeComboBox = new JComboBox();
		typeComboBox.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		typeComboBox.setForeground(new Color(0, 0, 0));
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionner", "Licence Technique"}));
		typeComboBox.setBounds(212, 376, 271, 32);
		panel.add(typeComboBox);
		
		nomField = new JTextField();
		nomField.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		nomField.setBounds(209, 328, 274, 30);
		panel.add(nomField);
		nomField.setColumns(10);
		
//		Activation de la connexion a la db
		cnx = ConnexionMysql.conneexioDB();
	}
	
//	Creation de la methode mise a jour de la table
	public void updateTable(){
//		Code sql permettant de selectionner des elements dans la table utilisateurs de la db 
		String sql = "SELECT  id_filiere, nomfliere, type FROM filieres";
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
