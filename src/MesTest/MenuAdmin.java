package MesTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdmin {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin window = new MenuAdmin();
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
	public MenuAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 1084, 671);
		frame.getContentPane().add(panel);
		
		textField = new JTextField();
		textField.setText("GESTION DES DONNEES");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(0, 0, 205));
		textField.setFont(new Font("Algerian", Font.BOLD, 50));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(230, 230, 250));
		textField.setBounds(171, 0, 734, 108);
		panel.add(textField);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.jpg"));
		button.setBounds(901, 0, 183, 162);
		panel.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionAdmin1 ad = new GestionAdmin1();
				
							
			}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement (2).png"));
		button_1.setBounds(137, 193, 183, 177);
		panel.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setText("Gestions des utilisateurs");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLUE);
		textField_1.setFont(new Font("Arial", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(230, 230, 250));
		textField_1.setBounds(137, 363, 183, 31);
		panel.add(textField_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button_2.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\images (2).jpg"));
		button_2.setBounds(456, 193, 183, 177);
		panel.add(button_2);
		
		textField_2 = new JTextField();
		textField_2.setText("Gestion des Etudiants");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.BLUE);
		textField_2.setFont(new Font("Arial", Font.BOLD, 15));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(230, 230, 250));
		textField_2.setBounds(456, 364, 186, 28);
		panel.add(textField_2);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement (3).png"));
		button_3.setBounds(770, 193, 183, 177);
		panel.add(button_3);
		
		textField_3 = new JTextField();
		textField_3.setText("Gestion des filieres");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.BLUE);
		textField_3.setFont(new Font("Arial", Font.BOLD, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(230, 230, 250));
		textField_3.setBounds(767, 363, 186, 29);
		panel.add(textField_3);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\images (3).png"));
		button_4.setBounds(137, 430, 183, 185);
		panel.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement.jpg"));
		button_5.setBounds(-11, 0, 183, 162);
		panel.add(button_5);
		
		textField_4 = new JTextField();
		textField_4.setText("Gestion des absences");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setForeground(Color.BLUE);
		textField_4.setFont(new Font("Arial", Font.BOLD, 14));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(230, 230, 250));
		textField_4.setBounds(137, 609, 183, 31);
		panel.add(textField_4);
		
		JButton button_6 = new JButton("");
		button_6.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement (1).jpg"));
		button_6.setBounds(456, 430, 183, 185);
		panel.add(button_6);
		
		textField_5 = new JTextField();
		textField_5.setText("UNIVERSITE ESCT");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setForeground(Color.BLUE);
		textField_5.setFont(new Font("Algerian", Font.BOLD, 25));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(230, 230, 250));
		textField_5.setBounds(427, 101, 242, 46);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("Gestion des matieres");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setForeground(new Color(0, 0, 205));
		textField_6.setFont(new Font("Arial", Font.BOLD, 15));
		textField_6.setColumns(10);
		textField_6.setBackground(new Color(230, 230, 250));
		textField_6.setBounds(456, 610, 183, 28);
		panel.add(textField_6);
		
		JButton button_7 = new JButton("");
		button_7.setIcon(new ImageIcon("C:\\Users\\sogor\\Desktop\\ProjetGestionEtudiants\\MesLogo\\t\u00E9l\u00E9chargement (5).png"));
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_7.setBounds(770, 430, 183, 185);
		panel.add(button_7);
		
		textField_7 = new JTextField();
		textField_7.setText("Gestions des notes");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setForeground(new Color(0, 0, 205));
		textField_7.setFont(new Font("Arial", Font.BOLD, 15));
		textField_7.setColumns(10);
		textField_7.setBackground(new Color(230, 230, 250));
		textField_7.setBounds(770, 609, 183, 31);
		panel.add(textField_7);
		
	}

}
