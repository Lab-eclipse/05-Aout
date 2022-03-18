package com.etudiants;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.util.Locale;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Info extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Info() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setLocale((Locale) null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(255, 218, 185));
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
		button.setBounds(-14, 0, 176, 162);
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
		
		JButton button_5 = new JButton("");
		button_5.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\back.png"));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuAdminstrateur menu = new MenuAdminstrateur();
				menu.setVisible(true);
				menu.setLocationRelativeTo(null);
				fermer();
				
			}
		});
		button_5.setForeground(new Color(0, 0, 128));
		button_5.setFont(new Font("Arial", Font.BOLD, 20));
		button_5.setBounds(16, 234, 56, 43);
		panel.add(button_5);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBackground(Color.RED);
		separator.setBounds(6, 221, 1265, 2);
		panel.add(separator);
		
		JLabel lblApplicationDeGestion = new JLabel("Application de Gestion Des Donnees Developpee en java");
		lblApplicationDeGestion.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		lblApplicationDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplicationDeGestion.setForeground(new Color(0, 0, 255));
		lblApplicationDeGestion.setBounds(258, 245, 921, 93);
		panel.add(lblApplicationDeGestion);
		
		JLabel lblParIbrahimSogore = new JLabel("Par Ibrahim Sogore pour un texte de niveau en java");
		lblParIbrahimSogore.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		lblParIbrahimSogore.setForeground(new Color(0, 0, 255));
		lblParIbrahimSogore.setHorizontalAlignment(SwingConstants.CENTER);
		lblParIbrahimSogore.setBounds(312, 276, 802, 117);
		panel.add(lblParIbrahimSogore);
	}
//	Creation de la methode fermer avec la fonction dispose
	public void fermer()
	{
		dispose();
	}

}
