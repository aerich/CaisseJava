package Vue;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import caisse.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.*;

public class Vue implements ActionListener
{
	private JPanel contentPane,panelTop,panel;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenuItem mntmConnexion,mntmDeconnexion;
	private JLabel lblConnexion,ldlEtatCon,lnom,lprenom,lblNom_1,lblPrenom,lblProduir;
	

	private boolean connexion=false;
	private GroupLayout gl_contentPane;
	private JPanel pClient;
	private JLabel lblNom;
	private JComboBox comboBox,comboBox_1,CBBStock;
	private JButton btnOk,btnOk_1,btnValider,btnAjoutUt,btnAjoutProduit,btValideTable;
	private Users tabuser;
	private Produit prod;
	private Connexion BDD;
	private String user[][];
	private String userSelect[];
	private String art[][];
	private Object[][] data = new Object[0][0];
	private NewModel model;
	
	private Fenetre fenPrinc,fenAjoutUt,fenlist;
	private JPanel CPFenAjout,CPFenList;
	private JTextField tfAjNom,tfAjPrenom,tfAjMail,tfAjCp,tfAjVille,tfAjPhone,quantstock;
	private JLabel lblAjNom,lblAjPrenom,lblAjMail,lblAjAdresse,lblAjCp,lblAjVille,lblAjphone,lblValeurtotal;
	private JButton btnAjValider,btnAjAnnuler,btnListeProduit;
	private JTextPane tpAjAdresse;
	private GroupLayout GL_CPFenAjout;
	private JTable table;
	private JScrollPane scrollPane;
	private Fenetre fenAjoutProduit;
	private JPanel CPFajoutProd;
	private JLabel lblCatgorie,lblNomDeLartique,lblPrixDeLartique,lblQuantit,lblDescription,lblTotal;
	private JComboBox CBCateg;
	private JTextField tfNomArt,tfPrixArt,tfQuantArt;
	private JTextPane textPane;
	private JSpinner spQuantAjoutart;
	private int max;
	
	/**
	 * Constructeur par défaut, affiche la page principal
	 */
	public Vue()
	{
		fenPrinc=new Fenetre("Caisse enregistreuse",653,466);
		
		menuBar = new JMenuBar();
		fenPrinc.setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmConnexion = new JMenuItem("Connexion");
		
		mnFichier.add(mntmConnexion);
		
		mntmDeconnexion = new JMenuItem("Deconnexion");
		mntmDeconnexion.setEnabled(false);
		mnFichier.add(mntmDeconnexion);
		
		mntmQuitter = new JMenuItem("Quitter");
		mnFichier.add(mntmQuitter);
		
		mnPrfrence = new JMenu("Pr\u00E9f\u00E9rence");
		menuBar.add(mnPrfrence);
		
		mntmConfig = new JMenuItem("Config");
		mnPrfrence.add(mntmConfig);
		
		mntmConnexion.addActionListener(this);
		mntmQuitter.addActionListener(this);
		
		contentPane = new JPanel();
		fenPrinc.setContentPane(contentPane);
		
		panelTop = new JPanel();
		panelTop.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
		
		lblConnexion = new JLabel("Etat de la Base de donn\u00E9e : ");
		panelTop.add(lblConnexion);
		
		ldlEtatCon = new JLabel("Non connecté");
		ldlEtatCon.setBackground(Color.RED);
		panelTop.add(ldlEtatCon);
		
		this.initjpClient();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(pClient, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, 0, 0, Short.MAX_VALUE))
					.addComponent(panelTop, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(pClient, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		btnAjoutUt = new JButton("Ajout utilisateur");
		btnAjoutUt.setEnabled(false);
		
		btnAjoutProduit = new JButton("Ajout produit");
		btnAjoutProduit.setEnabled(false);
		
		btnListeProduit = new JButton("liste produit");
		btnListeProduit.setEnabled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAjoutProduit, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAjoutUt, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnListeProduit, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAjoutProduit, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAjoutUt, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnListeProduit, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(153))
		);
		panel.setLayout(gl_panel);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		
		lblNom_1 = new JLabel("nom :");
		lblNom_1.setEnabled(false);
		
		lnom = new JLabel("");
		
		lblPrenom = new JLabel("prenom :");
		lblPrenom.setEnabled(false);
		 
		lprenom = new JLabel("");
		
		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		
		lblProduir = new JLabel("Produir :");
		lblProduir.setEnabled(false);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		
		
		btnOk_1 = new JButton("OK");
		btnOk_1.setEnabled(false);
		
		
		btnValider = new JButton("Valider");
		btnValider.setEnabled(false);
		btnValider.addActionListener(this);

		
		JPanel panel_1 = new JPanel();
		
		lblTotal = new JLabel("Total :");
		lblTotal.setEnabled(false);
		
		lblValeurtotal = new JLabel("");
		
		CBBStock = new JComboBox();
		CBBStock.setEnabled(false);
		
		GroupLayout gl_pClient = new GroupLayout(pClient);
		gl_pClient.setHorizontalGroup(
			gl_pClient.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pClient.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProduir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(CBBStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnOk_1)
					.addGap(101))
				.addGroup(gl_pClient.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pClient.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pClient.createSequentialGroup()
							.addComponent(lblNom_1)
							.addGap(18)
							.addComponent(lnom)
							.addGap(54)
							.addComponent(lblPrenom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lprenom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addGap(101))
				.addGroup(gl_pClient.createSequentialGroup()
					.addContainerGap(363, Short.MAX_VALUE)
					.addComponent(lblValeurtotal)
					.addGap(103))
				.addGroup(gl_pClient.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pClient.createSequentialGroup()
					.addContainerGap(318, Short.MAX_VALUE)
					.addComponent(btnValider, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
				.addGroup(Alignment.TRAILING, gl_pClient.createSequentialGroup()
					.addContainerGap(279, Short.MAX_VALUE)
					.addComponent(lblTotal)
					.addGap(147))
		);
		gl_pClient.setVerticalGroup(
			gl_pClient.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pClient.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pClient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNom)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOk))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pClient.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pClient.createSequentialGroup()
							.addGroup(gl_pClient.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrenom)
								.addComponent(lblNom_1))
							.addGap(15))
						.addGroup(gl_pClient.createSequentialGroup()
							.addComponent(lprenom)
							.addGap(24))
						.addGroup(gl_pClient.createSequentialGroup()
							.addComponent(lnom)
							.addGap(26)))
					.addGroup(gl_pClient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduir)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOk_1)
						.addComponent(CBBStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pClient.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pClient.createSequentialGroup()
							.addComponent(lblValeurtotal)
							.addGap(32)
							.addComponent(btnValider, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTotal))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		/**
		 * Ici je crée le Jtable, qui contiendra les articles choisi par un client
		 */
		//titre du Tableau
		String  title[] = {"supprimer","id","Nom ", "Prix", "Quantité","total/euro"};
		//instancie un nouveau modèle
		model=new NewModel(data,title);
		//instancie la table avec les données et les titres
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(439, 374));
		//Le scrollpane nous permet de voir les titres
		scrollPane = new JScrollPane(table);
		scrollPane.setVisible(false);
		//modifie la taille 
		scrollPane.setPreferredSize(new Dimension(430, 120));
		panel_1.add(scrollPane);
		supp.setVisible(false);
		panel_1.add(supp);
		supp.addActionListener(this);
		pClient.setLayout(gl_pClient);
		contentPane.setLayout(gl_contentPane);
		
//////////////////////////////////////////////////////
/////////////////////Action bouton / autre////////////
//////////////////////////////////////////////////////
		
		btnOk_1.addActionListener(this);
		btnOk.addActionListener(this);
		btnAjoutProduit.addActionListener(this);
		btnAjoutUt.addActionListener(this);
		btnListeProduit.addActionListener(this);
		/**
		 * Dès que l'on sélectionne un autre article dans le combobox
		 * on réactualise le stock dans la seconde combobox
		 * comme ceci un utilisateur ne peut prendre plus d'article
		 * qu'il en existe dans la base
		 */
		comboBox_1.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				for(int i=0;i<art.length;i++)
				{
					if(arg0.getItem().toString()==art[i][1])
					{
						
						max=Integer.parseInt(art[i][3]);
						AjoutCCBStock(max,CBBStock);	
						break;
					}
				}
				
			}
		});		
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		
	}
	
	/**
	 * Crée la fenètre qui vas permettre l'ajout d'un produit
	 */
	public void initAjoutProduit()
	{
		
		fenAjoutProduit=new Fenetre("Admin produit",620,300);
		CPFajoutProd = new JPanel();
		CPFajoutProd.setBorder(new EmptyBorder(5, 5, 5, 5));
		fenAjoutProduit.setContentPane(CPFajoutProd);
		
		lblCatgorie = new JLabel("Cat\u00E9gorie :");
		
		CBCateg = new JComboBox();
		CBCateg.addItem("Soutien gorge");
		CBCateg.addItem("Porte jartelle");
		CBCateg.addItem("maillot de bain");
		CBCateg.addItem("lingerie de nuit");
		CBCateg.addItem("ensemble sexy");
		CBCateg.addItem("lingerie de mariage");
		
		
		lblNomDeLartique = new JLabel("Nom de l'artique:");
		
		tfNomArt = new JTextField();
		tfNomArt.setColumns(10);
		
		lblPrixDeLartique = new JLabel("Prix de L'artique :");
		
		tfPrixArt = new JTextField();
		tfPrixArt.setColumns(10);
		
		lblQuantit = new JLabel("Quantit\u00E9 :");
		
		tfQuantArt = new JTextField();
		tfQuantArt.setColumns(5);
		
		
		lblDescription = new JLabel("Description");
		
		textPane = new JTextPane();
		
		
		JButton btnValider = new JButton("Valider");
		/**
		 * Quand on valide la sélection, je place les infos dans un tableau
		 * puis j'envoie le tableau à la méthode "ajoutProduit"
		 * qui va ajouter l'article dans la base de donnée
		 */
		btnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Object []tab={tfNomArt.getText().toString(),
						textPane.getText().toString(),
						tfPrixArt.getText().toString(),
						tfQuantArt.getText().toString(),
						CBCateg.getSelectedIndex()+""
				};
				prod.AjoutProduit(tab);
				fenAjoutProduit.dispose();
			}
		});
		JButton btnAnnuler = new JButton("Annuler");
		/**
		 * quand on clique sur annuler on quitte tout simplement la fenetre
		 */
		btnAnnuler.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				fenAjoutProduit.dispose();
			}
		});	
	
		GroupLayout gl_CPFajoutProd = new GroupLayout(CPFajoutProd);
		gl_CPFajoutProd.setHorizontalGroup(
			gl_CPFajoutProd.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CPFajoutProd.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CPFajoutProd.createSequentialGroup()
							.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomDeLartique)
								.addComponent(lblPrixDeLartique))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.LEADING)
								.addComponent(tfNomArt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfPrixArt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_CPFajoutProd.createSequentialGroup()
							.addComponent(lblQuantit)
							.addGap(12)
							.addComponent(tfQuantArt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_CPFajoutProd.createSequentialGroup()
							.addComponent(lblCatgorie)
							.addGap(12)
							.addComponent(CBCateg, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addGap(84)
					.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescription)
						.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_CPFajoutProd.createSequentialGroup()
					.addContainerGap(321, Short.MAX_VALUE)
					.addComponent(btnValider)
					.addGap(18)
					.addComponent(btnAnnuler)
					.addGap(31))
		);
		gl_CPFajoutProd.setVerticalGroup(
			gl_CPFajoutProd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CPFajoutProd.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCatgorie)
							.addComponent(CBCateg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_CPFajoutProd.createSequentialGroup()
							.addComponent(lblDescription)
							.addGap(18)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_CPFajoutProd.createSequentialGroup()
							.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomDeLartique)
								.addComponent(tfNomArt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrixDeLartique)
								.addComponent(tfPrixArt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(37)
							.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQuantit)
								.addComponent(tfQuantArt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_CPFajoutProd.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnValider)
						.addComponent(btnAnnuler))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		CPFajoutProd.setLayout(gl_CPFajoutProd);
	}

	
	public void initjpClient()
	{
		pClient = new JPanel();
		pClient.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNom = new JLabel("Nom :");
		lblNom.setEnabled(false);
	}
	/**
	 * fonction qui crée la fenêtre pour ajouter un utilisateur
	 */
	public void initfenAjoutUt()
	{
		CPFenAjout= new JPanel();
		CPFenAjout.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		lblAjNom=new JLabel("Entrer nom:");
		
		tfAjNom = new JTextField();
		tfAjNom.setColumns(10);
		
		lblAjPrenom = new JLabel("Entrer le pr\u00E9nom");
		
		tfAjPrenom = new JTextField();
		tfAjPrenom.setColumns(10);
		
		lblAjMail = new JLabel("Entrer l'adresse mail:");
		
		tfAjMail = new JTextField();
		tfAjMail.setColumns(10);
		
		lblAjAdresse = new JLabel("Adresse:");
		
		tpAjAdresse = new JTextPane();
		
		lblAjCp = new JLabel("Entrer le code Postal :");
		
		lblAjVille = new JLabel("Ville :");
		
		tfAjCp = new JTextField();
		tfAjCp.setColumns(10);
		
		tfAjVille = new JTextField();
		tfAjVille.setColumns(10);
		
		lblAjphone = new JLabel("T\u00E9l\u00E9phone :");
		
		tfAjPhone = new JTextField();
		tfAjPhone.setColumns(10);
		
		btnAjValider = new JButton("Valider");
		btnAjValider.addActionListener(this);
		
		btnAjAnnuler = new JButton("Annuler");
		btnAjAnnuler.addActionListener(this);
		
		GL_CPFenAjout = new GroupLayout(CPFenAjout);
		GL_CPFenAjout.setHorizontalGroup(
				GL_CPFenAjout.createParallelGroup(Alignment.LEADING)
				.addGroup(GL_CPFenAjout.createSequentialGroup()
					.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.LEADING)
						.addGroup(GL_CPFenAjout.createSequentialGroup()
							.addContainerGap()
							.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.LEADING)
								.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(GL_CPFenAjout.createSequentialGroup()
										.addComponent(lblAjNom)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(tfAjNom, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblAjPrenom, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfAjPrenom, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblAjAdresse)
									.addGroup(GL_CPFenAjout.createSequentialGroup()
										.addComponent(lblAjCp)
										.addGap(2)
										.addComponent(tfAjCp, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblAjVille)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(tfAjVille, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(tpAjAdresse))
								.addGroup(GL_CPFenAjout.createSequentialGroup()
									.addComponent(lblAjMail)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tfAjMail, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
								.addGroup(GL_CPFenAjout.createSequentialGroup()
									.addComponent(lblAjphone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfAjPhone, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))
						.addGroup(GL_CPFenAjout.createSequentialGroup()
							.addGap(122)
							.addComponent(btnAjValider)
							.addGap(18)
							.addComponent(btnAjAnnuler)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		GL_CPFenAjout.setVerticalGroup(
				GL_CPFenAjout.createParallelGroup(Alignment.LEADING)
				.addGroup(GL_CPFenAjout.createSequentialGroup()
					.addContainerGap()
					.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAjNom)
						.addComponent(tfAjNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAjPrenom)
						.addComponent(tfAjPrenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAjAdresse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tpAjAdresse, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAjCp)
						.addComponent(tfAjCp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAjVille)
						.addComponent(tfAjVille, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAjMail)
						.addComponent(tfAjMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAjphone)
						.addComponent(tfAjPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(GL_CPFenAjout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAjValider)
						.addComponent(btnAjAnnuler)))
		);
		CPFenAjout.setLayout(GL_CPFenAjout);
		fenAjoutUt.setContentPane(CPFenAjout);
	}
	float totalFacture=0;
	int quantProduit=0;
	float reduc=0;
	float total=0;
	private JMenuItem mntmQuitter;
	private JMenu mnPrfrence;
	private JMenuItem mntmConfig;
	private JButton supp=new JButton("supprimer la dernière ligne");
	
	/**
	 * La fonction addligne permet quand l'utilisateur valide un article
	 * de l'ajouter dans le jtable.
	 */
	public void addligne()
	{
		for(int i=0;i<art.length;i++)
		{
			//Des que l'article dans la base correspond à l'article sélectionné
			if(comboBox_1.getSelectedItem().toString()==art[i][1])
			{
				//on récupère le total de l'article sélectionné avec la quantité
				total=(Float.parseFloat(art[i][2])*(Float.parseFloat(CBBStock.getSelectedItem().toString())));
				//on met à jour le total de l'achet
				totalFacture+=total;
				//converti le nombre d'article selectionne en int
				int quant=Integer.parseInt(CBBStock.getSelectedItem().toString());
				//on place les données dans un Object
				Object[] donnee = new Object[]{"supp",art[i][0],art[i][1], art[i][2], CBBStock.getSelectedItem(), total};
				//on ajoute la ligne au jtable
				((NewModel)table.getModel()).addRow(donnee);
				//Après on actualise le stock de l'article
				 art[i][3]= (Integer.parseInt(art[i][3])-quant)+"";
				 AjoutCCBStock(Integer.parseInt(art[i][3]),CBBStock);
				 if(Integer.parseInt(art[i][3])<1)
					{
						JOptionPane.showMessageDialog(fenAjoutUt, "Stock vide","Attention",JOptionPane.WARNING_MESSAGE);
					}	
				break;
			}
		}
		//on affiche la facture
		lblValeurtotal.setText(totalFacture+"");
		lblValeurtotal.repaint();
		
	}
	/**
	 * fonction qui modifie une ligne du jtable si un article est déja présent dans le jtable
	 * @param ligne
	 */
	public void modifligne(int ligne)
	{
		//on récupère la quantité (stock) contenu dans le jtable
		int quantTable=Integer.parseInt(table.getValueAt(ligne, 4).toString());
		//on récupère la quantité que le client souhaite rajouter
		int quant=Integer.parseInt(CBBStock.getSelectedItem().toString());
		//on additionne le tout
		int nouvquant=quantTable+quant;
		//contient la nouvelle quantité du stock disponnible pour la combobox
		int nouvquantCBOX=CBBStock.getItemCount()-quant;
		
		/*Réactualise le total*/
		totalFacture+=(Float.parseFloat(table.getValueAt(ligne, 3).toString())*quant);
		total+=(Float.parseFloat(table.getValueAt(ligne, 3).toString())*quant);
		lblValeurtotal.setText(totalFacture+"");
		lblValeurtotal.repaint();
		
		//réactualise le combobox des stock
		AjoutCCBStock(nouvquantCBOX,CBBStock);
		//on réactualise le stock de l'article sélectionné
		for(int i=0;i<art.length;i++)
		{
			if(comboBox_1.getSelectedItem().toString()==art[i][1])
			{
				art[i][3]= (Integer.parseInt(art[i][3])-quant)+"";
			}
		}
		//on modifie les champ dans la jtable 5=>total et 4 => stock
		((NewModel)table.getModel()).SetValueAt(ligne, 5,(Object)(total));
		((NewModel)table.getModel()).SetValueAt(ligne, 4,(Object)(nouvquant));
	}
	/**
	 * fonctione qui supprime la dernière ligne ajouté.
	 * une fois la ligne supprimée, il faut réactualisé les stocks
	 * @param ligne
	 */
	public void enlèvelgne(int ligne)
	{
		//Réactualise le total de la facture
		totalFacture-=(Float.parseFloat(table.getValueAt(ligne, 3).toString()));
		lblValeurtotal.setText(totalFacture+"");
		lblValeurtotal.repaint();

		//récupère l'id de l'article dans la jtable de la ligne supprimé
		String idart=(table.getValueAt(ligne, 1).toString());
		//récupère la quantité d'un article contenu dans la jtable de la ligne supprimé
		int quant=(Integer.parseInt(table.getValueAt(ligne, 4).toString()));
		for(int i=0;i<art.length;i++)
		{
			//dés que l'article de la base correspond à l'id de celui contenue dans la jtable
			//on le réactualise
			if(art[i][0]==idart)
			{
				int nouvstock=(Integer.parseInt(art[i][3])+quant);
				art[i][3]=nouvstock+"";
				AjoutCCBStock(nouvstock,CBBStock);
				break;
			}
		}
		//on supprime enfin la ligne
		((NewModel)table.getModel()).removeRow(table.getRowCount()-1);
	}
	/**
	 * Fonction qui remplie les combobox
	 * @param el => la variable (art ou user)
	 * @param id => la colonne
	 * @param comboBox2
	 */
	public void AjoutComboboxtest(String [][]el, int id,JComboBox comboBox2)
	{
		for(int i=0;i < el.length;i++)
		{
			comboBox2.addItem(el[i][id]);
		}
		//réactualise la fenètre
		fenPrinc.validate();
	}
	/**
	 * idem que la fonction ci-dessus sauf que l'on ajoute 2 champs
	 * @param el
	 * @param id
	 * @param champ
	 * @param comboBox2
	 */
	public void AjoutComboboxtest(String [][]el, int id,int champ,JComboBox comboBox2)
	{
		for(int i=0;i < el.length;i++)
		{
			comboBox2.addItem(el[i][id]+" "+el[i][champ]);
		}
		fenPrinc.validate();
	}
	/**
	 * idem mais pour les stocks
	 * @param Stock
	 * @param comboBox2
	 */
	public void AjoutCCBStock(int Stock,JComboBox comboBox2)
	{
		comboBox2.removeAllItems();
		for(int i=0;i < Stock;i++)
		{
			comboBox2.addItem(i+1);
		}
		fenPrinc.validate();
	}
	
	/**
	 * fonction appelé pour l'ajout d'un utilisateur
	 */
	public void ajoutUtilisateur()
	{
		//pour vérifié si les champ sont bien rempli
		boolean veriftext=true;
		boolean verifchif=true;
		//tableau pour vérifié que les champs textes
		String []donnéeText={tfAjMail.getText().toString(),
						tfAjNom.getText().toString(),
						tfAjPrenom.getText().toString(),
						tpAjAdresse.getText().toString(),
						tfAjVille.getText().toString(),
						};
		//tableau pour vérifié que les champs numérique
		String []donnéeChif={tfAjCp.getText().toString(),
						tfAjPhone.getText().toString()};
		Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
		Pattern pattern2 = Pattern.compile("^[0-9 ]+$");
		//je parcour tout le tableau ne contenant que les champs textes, je les vérifie un par un
		//si il y a une erreur on arrète tout
				for(String str : donnéeText)
				{
					
					 if(!pattern.matcher(str).matches())
					 {
						 veriftext=false;
					 	 break;
					 }
				}
		//idem boucle précédent
				for(String str : donnéeChif)
				{
					 if(!pattern2.matcher(str).matches())
					 {
						 veriftext=false;
					 	 break;
					 }
				}
				//je vérifie si tout est ok, si c'est le cas alors j'enregistre le nouveau client
				if(veriftext && verifchif)
				{
					if(tabuser.AjoutUser2(donnéeText,donnéeChif))
					{
						user=tabuser.ListUser();
						comboBox.removeAllItems();
						JOptionPane.showMessageDialog(fenAjoutUt, "Le client a bien été inscrit","inscription",JOptionPane.PLAIN_MESSAGE);
						fenAjoutUt.dispose();
						AjoutComboboxtest(user,2,comboBox);
					}else
					{
						JOptionPane.showMessageDialog(fenAjoutUt, "Le client n'a pas pu être inscrit","Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}else
					{
						JOptionPane.showMessageDialog(fenAjoutUt, "Tout les champs ne sont pas remplis correctement","Erreur",JOptionPane.ERROR_MESSAGE);
					}
	}
	/**
	 * Fonction regroupant les différents boutons/actions
	 */
	public void actionPerformed(ActionEvent e) 
	{
		/*Quant on valide un article */
		if(e.getSource()==btnOk_1)
		{
			btnValider.setEnabled(true);
			lblTotal.setEnabled(true);
			boolean exist=false;
			int i=0;
			//on vérifie si l'article n'est pas déjas présent dans la jtable
			while(i<table.getRowCount())
			{
				if(comboBox_1.getSelectedItem().toString()==table.getValueAt(i, 2))
				{
					exist=true;
					//on modifie la quantité de l'article tant qu'il y en a en stock
					if(CBBStock.getItemCount()>0)
						modifligne(i);
					else
						JOptionPane.showMessageDialog(fenAjoutUt, "Stock vide","Attention",JOptionPane.ERROR_MESSAGE);			
					break;
				}
				i++;
			}
			//si l'article n'existe pas le rajoute simplement
			if(!exist)
			{
				this.addligne();
			}
			

		}else
		/*Quant on clique sur le bouton qui valide le nom du client sélectionner dans la comboBox*/
		if(e.getSource()==btnOk)
		{
					/*On active les différents composants*/
			comboBox_1.setEnabled(true);
			lnom.setEnabled(true);
			lprenom.setEnabled(true);
			scrollPane.setVisible(true);
			CBBStock.setEnabled(true);
			btnOk_1.setEnabled(true);
			lblPrenom.setEnabled(true);
			lblNom_1.setEnabled(true);
			lblProduir.setEnabled(true);
			btnAjoutProduit.setEnabled(true);
			btnAjoutUt.setEnabled(true);
			supp.setVisible(true);
			btnListeProduit.setEnabled(true);
			comboBox_1.removeAllItems();
					/*On ajoute les articles dans la comboBox dédier aux articles*/
			AjoutComboboxtest(art,1,comboBox_1);
					/*Variable qui définira le client qui est sélectionné, pour connaitre le nombre de colonne
					 * je me sers d'une méthode appartenant à la classe User*/
			userSelect=new String[tabuser.getnombreCol()];
			//stocke le nom prenom selectionné
			String userbox=comboBox.getSelectedItem().toString();
			String verifnom = lnom.getText().toString()+" "+lprenom.getText().toString();
			
			/*je teste si on a changé de client
			 * Quand on change de client sans valider le pannier, on vide le Jtable*/
			if(userbox.compareTo(verifnom)<0)
			{
				((NewModel)table.getModel()).update(data);
				lblValeurtotal.setText("");
			}	
			/*Ici je place le nom choisie dans une seconde variable fixe*/
			for(int i=0;i<user.length;i++)
			{
				String nom =user[i][2]+" "+user[i][3];
				String nomcombo=comboBox.getSelectedItem().toString();
				if(nomcombo.equals(nom))
				{
					for(int j=0;j<tabuser.getnombreCol();j++)
					{
						userSelect[j]=user[i][j];
					}
					lnom.setText(userSelect[2]);
					lprenom.setText(userSelect[3]);
					break;
				}
			}
			
			
			
		}else
			//quant on clique sur le bouton ajout produit
		if(e.getSource()==btnAjoutProduit)
		{
			initAjoutProduit();
		}else
			//quant on clique sur le bouton ajout utilisateur
		if(e.getSource()==btnAjoutUt)
		{
			fenAjoutUt=new Fenetre("Ajout utilisateur",461,339);
			initfenAjoutUt();
		}else
		if(e.getSource()==btnAjAnnuler)
		{
			fenAjoutUt.dispose();
		}else
			//quand on valide l'ajout d'un utilisateur
		if(e.getSource()==btnAjValider)
		{
			this.ajoutUtilisateur();
			
		}else
			//Quand on clique sur connexion
		if(e.getSource()==mntmConnexion)
		{
			BDD=new Connexion("localhost:3306/caisse","root","");
			connexion=BDD.getEtat();
			//si la connection fonctionne
				if(connexion)
				{
					ldlEtatCon.setText("Connecté");
					mntmDeconnexion.setEnabled(true);
					mntmConnexion.setEnabled(false);
					//on instance
					tabuser = new Users(BDD.getCon());
					prod=new Produit(BDD.getCon());
					user=tabuser.ListUser();
					art=prod.Article();
					//on ajoute les utilisateurs dans la combobox
					AjoutComboboxtest(user,2,3,comboBox);
					lblNom.setEnabled(true);
					comboBox.setEnabled(true);
					btnOk.setEnabled(true);
					
				}
		}else
			//quand on clique sur quitter
		if(e.getSource()==mntmQuitter)
		{
			System.exit(0);
		}else
			//Affiche l'inventaire des produit
		if(e.getSource()==btnListeProduit)
		{
			
			fenlist=new Fenetre("Listing produit",600,600);
			CPFenList = new JPanel();
			CPFenList.setLayout(new BorderLayout(0,0));
			final JTextField quantstock = new JTextField();
			quantstock.setColumns(5);
			//produit classé par stock
			String [][]listart=prod.Article("stock");
			//on crée un nouvel jtabl ou l'on va placer tout les produits
			NewModel model2=new NewModel(listart,new String[]{"id","Nom","prix","stock","catégorie"});
			JTable table2 = new JTable(model2);
			table2.setPreferredScrollableViewportSize(new Dimension(300, 150));
			JScrollPane scrollPane2 = new JScrollPane(table2);
			scrollPane2.setPreferredSize(new Dimension(300, 150));
			JLabel texte = new JLabel("Augmenter la quantité :");
			JPanel top = new JPanel();
			JButton btokstock = new JButton("ok"); 
			//quand on valide la nouvelle quantité, on met la base à jour
			btokstock.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					for(int i=0;i<art.length;i++)
					{
						if(comboBox_1.getSelectedItem()==art[i][1])
						{
							int id=Integer.parseInt(art[i][0]);
							int valeur=Integer.parseInt(quantstock.getText())+prod.getstock(id);
							prod.updatestock(id,valeur);
							break;
						}
					}
				}
			});
			top.add(texte);
			top.add(comboBox_1);
			top.add(quantstock);
			top.add(btokstock);
			CPFenList.add(top,BorderLayout.NORTH);
			CPFenList.add(scrollPane2,BorderLayout.CENTER);
			fenlist.setContentPane(CPFenList);
			
			
		}else
			//quand on valide l'achat on peut avoir un petit appercu 
		if(e.getSource()==btnValider)
		{//fonction appercu d'achat
			crfact();
		  	
		}else
			//quand on valide le panier pour de bon, on met la base de donnée à jour
		if(e.getSource()==btValideTable)
		{
			//si l'ajout est réussit
			if(nouv.ajoutFacture(Integer.parseInt(userSelect[0]), totalFacture))
			{
				JOptionPane.showMessageDialog(fenAjoutUt, "La facture "+(nouv.getlastfact()+1)+" a bien été confirmer....patienter pour l'impression","Facture",JOptionPane.PLAIN_MESSAGE);
				//pour chaque article dans la jtable, on met à jour son stock
				for(int i=0;i<table.getRowCount();i++)
				{
					int idart=Integer.parseInt(table.getValueAt(i, 1).toString());
					int quanttab=Integer.parseInt(table.getValueAt(i, 4).toString());
					int nouvStock=(prod.getstock(idart))-quanttab;
					this.prod.updatestock(idart, nouvStock);
				}
				//on imprime la facture en pdf
				this.print();
				//on efface le tableau
				((NewModel)table.getModel()).update(data);
				//réactualise les article
				art=this.prod.Article();
				//data = new Object[0][0];
				table.setVisible(false);
				//pour continuer l'achat il faudra resélectionné un client
				comboBox_1.setEnabled(false);
				supp.setEnabled(false);
				lblValeurtotal.setText("");
				testfen.dispose();
				
			}
		}else
			//quand on supprime une ligne
			if(e.getSource()==supp)
			{
				this.enlèvelgne(table.getRowCount()-1);
			}
			
	}
	
	Facture nouv;
	Fenetre testfen; 
	JTextPane testpane;
	int numfact=0;
	
	/**
	 * Fonction qui affiche une page d'appercu de la facture
	 */
	public void crfact()
	{
		testfen = new Fenetre("test",500,500);
		JPanel testpan=new JPanel();
		testfen.setContentPane(testpan);
		testpane = new JTextPane();
		btValideTable=new JButton("Imprimer");
		btValideTable.addActionListener(this);
		
		testpan.add(testpane);testpan.add(btValideTable);
		testpane.setPreferredSize(new Dimension(450,400));
		testpane.setEditable(false);
		String facttable=new String("Id \t| Nom Produit \t| prix\t| quantité\t| total\n\n");
		nouv=new Facture(BDD.getCon());
		numfact=nouv.getlastfact()+1;
		for(int i=0;i<table.getRowCount();i++)
		{
			for(int j=1;j<table.getColumnCount();j++)
			{
				if(j<table.getColumnCount()-1)
				{
					facttable+=""+table.getValueAt(i, j)+"\t| ";
				}else
					{
						facttable+=""+table.getValueAt(i, j);
					}
				
			}facttable+="\n";
			
		}
		testpane.setText(
				"Numéro de la facture : "+numfact+"\n\nIdentité\n" +
				"Nom : "+userSelect[2]+"\tPrenom : "+userSelect[3]+
				"\nmail : "+userSelect[1]+
				"\n\n\nAchat\n" +
				""+facttable+
				"\n\t\t Total : "+total+" euro");
		
	}
	/**
	 * Imprime la facture
	 */
	private void print() 
	{
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("DD_MM_yyy_HH-mm-ss");
	    String date = sdf.format(new Date());
	    Document document = new Document(PageSize.A4.rotate());
	    try 
	    {
	      PdfWriter writer = PdfWriter.getInstance(document,
	      new FileOutputStream("Facture de "+userSelect[2]+" "+userSelect[3]+"du "+date+".pdf"));

	      document.open();
	      PdfContentByte cb = writer.getDirectContent();
	      
	      cb.saveState();
	      Graphics2D g2 = cb.createGraphicsShapes(500, 500);

	      Shape oldClip = g2.getClip();
	      g2.clipRect(0, 0, 500, 500);
	      document.add(new Paragraph("Voici la facture"));
	      document.add(new Paragraph("Numéro de la facture : "+numfact+"\n\nIdentité\n" +
					"Nom : "+userSelect[2]+"\tPrenom : "+userSelect[3]+
					"\nmail : "+userSelect[1]+""));
	      //testpane.print();
	      table.print(g2);
	      g2.setClip(oldClip);

	      g2.dispose();
	      cb.restoreState();
	      document.add(new Paragraph("Le montant total  est de "+total+" euro "));
		  document.newPage();
	      //////////////
	      
	    } catch (Exception e) {
	      System.err.println(e.getMessage());
	    }
	    document.close();
	  }
}
