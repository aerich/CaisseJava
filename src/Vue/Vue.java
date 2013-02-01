package Vue;

import java.awt.*;

import javax.swing.Box.Filler;
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
import com.itextpdf.text.BaseColor;
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

//import com.itextpdf.forms.layout.FormLayout;
//import com.itextpdf.forms.layout.ColumnSpec;
//import com.itextpdf.forms.layout.RowSpec;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormXObject;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.pdf.Pfm2afm;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.*;
import java.awt.Toolkit; 
import java.awt.event.ComponentListener;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.text.JTextComponent;

public class Vue implements ActionListener
{
	private JPanel panelTop,panel;
        private JPanel pClient;
        private JPanel CPFenAjout,CPFenList;
        private JPanel CPFajoutProd;
        
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenuItem mntmConnexion,mntmDeconnexion;
	private JLabel lblConnexion,ldlEtatCon,lnom,lprenom,lblNom_1,lblPrenom,lblProduit;
	private JLabel lblNom;

	private boolean connexion=false;
        private DecimalFormat df = new DecimalFormat("#######0.00");
	
	private JComboBox comboBox,cmbArticlesFenPrinc,CBBStock;
        private JComboBox CBCateg;
        private JComboBox cmbListeProduit;
        
	private JButton btnOkClient,btnOkArticle,btnValider,btnAjoutUt,btnAjoutProduit,btValideTable,btnListeProduit;
        private JButton btnValiderAjProduit,btnAnnulerAjProduit,btnFermerListeProduit;
        
	private Users tabuser;
	private Produit prod;
	private Connexion BDD;
	private String user[][];
	private String userSelect[];
	private String art[][];
	private Object[][] data = new Object[0][0];
	private NewModel model;
        
	private JFrame fenPrinc;
	
        private Fenetre fenAjoutUt,fenlist;
	private Fenetre fenAjoutProduit;
        
	private JTextField tfAjNom,tfAjPrenom,tfAjMail,tfAjCp,tfAjVille,tfAjPhone;
        private JTextField tfNomArt,tfPrixArt,tfQuantArt;
        
	private JLabel lblValeurtotal;
        private JLabel lblTotal;
        
	private JTextPane tpAjAdresse;
        private JTextPane textPaneDescription;
        
	private JTable table;
        private JTable table2;
	private JScrollPane scrollPane;
	
	private int max;
        
        float totalFacture=0;
	int quantProduit=0;
	float reduc=0;
	float total=0;
	private JMenuItem mntmQuitter;
	private JMenu mnPrfrence;
	private JMenuItem mntmConfig;
	private JButton supp=new JButton("supprimer la dernière ligne");
	
        ActionsEcouteur ecouteur;
	/**
	 * Constructeur par defaut, affiche la page principal
	 */
	public Vue()
	{
               
                
                Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                
                
		fenPrinc=new JFrame("Caisse enregistreuse");
                
                fenPrinc.setSize(dimension);
                fenPrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenPrinc.setUndecorated(true);
               
                fenPrinc.setVisible(true);
                
		menuBar = new JMenuBar();
		fenPrinc.setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Action");
		menuBar.add(mnFichier);
		
		mntmConnexion = new JMenuItem("Connexion");
		
		mnFichier.add(mntmConnexion);
		
		mntmDeconnexion = new JMenuItem("D\u00E9connexion");
                mntmDeconnexion.addActionListener(this);
		mntmDeconnexion.setEnabled(false);
		mnFichier.add(mntmDeconnexion);
		
		mntmQuitter = new JMenuItem("Quitter");
		mnFichier.add(mntmQuitter);
		
		/*mnPrfrence = new JMenu("Pr\u00E9f\u00E9rence");
		menuBar.add(mnPrfrence);
		
		mntmConfig = new JMenuItem("Config");
		mnPrfrence.add(mntmConfig);*/
		
		mntmConnexion.addActionListener(this);
		mntmQuitter.addActionListener(this);
		
		
		panelTop = new JPanel();
		panelTop.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
		
		lblConnexion = new JLabel("État de la Base de donn\u00E9e : ");
		panelTop.add(lblConnexion);
		
		ldlEtatCon = new JLabel("Non connecté");
		ldlEtatCon.setBackground(Color.RED);
		panelTop.add(ldlEtatCon);
		
                // Jpanel avec le client
		pClient = new JPanel();
                pClient.setLayout(new BoxLayout(pClient, BoxLayout.X_AXIS));
		pClient.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNom = new JLabel("Nom :");
		lblNom.setEnabled(false);
                btnOkClient = new JButton("OK");
		btnOkClient.setEnabled(false);
                comboBox = new JComboBox();
		comboBox.setEnabled(false);
		pClient.add(lblNom);
		pClient.add(comboBox);
		pClient.add(btnOkClient);
                
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
                //
                JPanel panGauche=new JPanel();
                GridBagLayout layoutGauche = new GridBagLayout();
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                panGauche.setLayout(layoutGauche);
                panGauche.setBorder(new EmptyBorder(10, 10, 10, 10)); 
                
                
                JPanel panCentre = new JPanel();
                panCentre.setBorder(new EmptyBorder(10, 10, 10, 10)); 
                BoxLayout layoutCentre = new BoxLayout(panCentre, BoxLayout.Y_AXIS);
                
                panCentre.setLayout(layoutCentre);
                panCentre.add(pClient);
                               
                //
                
		btnAjoutUt = new JButton("Ajout utilisateur");
                btnAjoutUt.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		btnAjoutProduit = new JButton("Ajout produit");
                btnAjoutProduit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnListeProduit = new JButton("liste produit");
                btnListeProduit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
                              
                panGauche.add(Box.createVerticalGlue());
                c.gridx = 0;
                c.ipady = 10; // largeur suplémentaire
                panGauche.add(btnAjoutProduit,c);
                c.insets = new Insets(10,0,0,0);  //top padding
                panGauche.add(new Box.Filler(new Dimension(0,10), new Dimension(0,10), new Dimension(0,10)));
                panGauche.add(btnAjoutUt,c);
                panGauche.add(new Box.Filler(new Dimension(0,10), new Dimension(0,10), new Dimension(0,10)));
                panGauche.add(btnListeProduit,c);
		panGauche.add(Box.createVerticalGlue());
                //
		// Panel de l'utilisateur en cours
		JPanel panUtilisateur = new JPanel();
                
                panUtilisateur.setLayout(new FlowLayout(FlowLayout.LEFT));
		lblNom_1 = new JLabel("nom :");
		lblNom_1.setEnabled(false);
		panUtilisateur.add(lblNom_1);
                
		lnom = new JLabel("");
                panUtilisateur.add(lnom);
		
                Filler espace = new Box.Filler(new Dimension(10, 0),new Dimension(10, 0),new Dimension(10, 0));
                
                panUtilisateur.add(espace);
                
		lblPrenom = new JLabel("prénom :");
		panUtilisateur.add(lblPrenom);
		 
		lprenom = new JLabel("");
		panUtilisateur.add(lprenom);
		
                panCentre.add(panUtilisateur);
                
		// Panel des articles à ajouter
                JPanel panArticles = new JPanel();
                panArticles.setLayout(new BoxLayout(panArticles, BoxLayout.X_AXIS));
		lblProduit = new JLabel("Produit :");
		panArticles.add(lblProduit);
		
		cmbArticlesFenPrinc = new JComboBox();
		panArticles.add(cmbArticlesFenPrinc);
                
		CBBStock = new JComboBox();
		panArticles.add(CBBStock);
                
		btnOkArticle = new JButton("OK");		
		panArticles.add(btnOkArticle);
                                
                panCentre.add(panArticles);
                panCentre.add(new Box.Filler(new Dimension(0, 10),new Dimension(0, 10),new Dimension(0, 10)));
                
		// Total et prix
                JPanel panTotal = new JPanel();
                panTotal.setLayout(new FlowLayout(FlowLayout.RIGHT));
                panClavierVirtuel panClavier = new panClavierVirtuel();
                panTotal.add(panClavier);
                panTotal.add(new Box.Filler(new Dimension(300, 0),new Dimension(300, 0),new Dimension(300, 0)));
                
		lblTotal = new JLabel("Total :");
		lblValeurtotal = new JLabel("");
                
                panTotal.add(lblTotal);
                panTotal.add(lblValeurtotal);
		panTotal.add(new Box.Filler(new Dimension(100, 0),new Dimension(100, 0),new Dimension(100, 0)));
                // Bouton valider en dessous
		btnValider = new JButton("Valider");
		btnValider.addActionListener(this);

                /**
		 * Ici je cree le Jtable, qui contiendra les articles choisi par un client
		 */
                
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		table = new JTable(model) {

                    public boolean isCellEditable(int row, int column)
                    {
                        boolean editable=false;
                        if((row==this.getRowCount()-1)&&(column==4))
                        {
                            editable=true;
                        }
                        
                        return editable;
                        }
                public void changeSelection(
                    int row, int column, boolean toggle, boolean extend)
                {
                    super.changeSelection(row, column, toggle, extend);

                    if (editCellAt(row, column))
                    {
                        Component editor = getEditorComponent();
                        editor.setBackground(Color.LIGHT_GRAY);
                        ((JTextComponent) editor).selectAll();
                        Bouton.editor=((JTextComponent) editor);
                        editor.requestFocusInWindow();
                        
                    }
                }
                };
		//titre du Tableau
		String  title[] = {"Supprimer","id","Nom ", "Prix", "Quantité","total/euro"};
		//instancie un nouveau modele
		model=new NewModel(data,title);
		//instancie la table avec les donnees et les titres
		table.setModel(model);
              
                Bouton.setTarget(table);
                
		table.setPreferredScrollableViewportSize(new Dimension(439, 374));
		//Le scrollpane nous permet de voir les titres
		scrollPane = new JScrollPane(table);
		scrollPane.setVisible(false);
		//modifie la taille 
		//scrollPane.setPreferredSize(new Dimension(430, 120));
		panel_1.add(scrollPane);
		panel_1.add(supp);
		supp.addActionListener(this);
                //
		panCentre.add(panel_1);
                panCentre.add(panTotal);
                panCentre.add(btnValider);
                panCentre.add(new Box.Filler(new Dimension(0,100), new Dimension(0,500), new Dimension(0,100)));
		//
                JPanel fond = new JPanel(new BorderLayout());
                fond.add("North",panelTop);
                fond.add("Center", panCentre);
                fond.add(BorderLayout.EAST, panGauche);
                
                activerDesactiverElementGraphPrincipaux(false);
                fenPrinc.setContentPane(fond);
          
                fenPrinc.doLayout();
//////////////////////////////////////////////////////
/////////////////////Action bouton / autre////////////
//////////////////////////////////////////////////////
		
		btnOkArticle.addActionListener(this);
		btnOkClient.addActionListener(this);
		btnAjoutProduit.addActionListener(this);
		btnAjoutUt.addActionListener(this);
		btnListeProduit.addActionListener(this);
		/**
		 * Des que l'on selectionne un autre article dans le combobox
		 * on reactualise le stock dans la seconde combobox
		 * comme ceci un utilisateur ne peut prendre plus d'article
		 * qu'il en existe dans la base
		 */
		cmbArticlesFenPrinc.addItemListener(new ItemListener() 
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
		// Ecouteur global
                /////////////////////////////////////////////////////////
		ecouteur = new ActionsEcouteur(lblValeurtotal, btnValider);
                
                
                
	}
	
	/**
	 * Cree la fenetre qui vas permettre l'ajout d'un produit
	 */
	public void initAjoutProduit()
	{
		
		fenAjoutProduit=new Fenetre("Admin produit",620,300,fenPrinc,true);
                
		CPFajoutProd = new JPanel();
		CPFajoutProd.setBorder(new EmptyBorder(5, 5, 5, 5));
		fenAjoutProduit.setContentPane(CPFajoutProd);
                		
		CBCateg = new JComboBox();
                Categorie cat = new Categorie(BDD.getCon());
                
                AjoutElementCombobox(cat.listeCategorie(), CBCateg);
		/*CBCateg.addItem("Boisson chaude");
		CBCateg.addItem("viennoiserie");
		CBCateg.addItem("Boisson gazeuse");
		CBCateg.addItem("Boisson alcoolisée");*/
		
		
		tfNomArt = new JTextField();
		tfNomArt.setColumns(10);
				
		tfPrixArt = new JTextField();
		tfPrixArt.setColumns(10);
				
		tfQuantArt = new JTextField();
		tfQuantArt.setColumns(5);
				
		textPaneDescription = new JTextPane();
				
		JButton btnValider = new JButton("Valider");
		/**
		 * Quand on valide la selection, je place les infos dans un tableau
		 * puis j'envoie le tableau e la methode "ajoutProduit"
		 * qui va ajouter l'article dans la base de donnee
		 */
		btnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Object []tab={tfNomArt.getText().toString(),
						textPaneDescription.getText().toString(),
						tfPrixArt.getText().toString(),
						tfQuantArt.getText().toString(),
						CBCateg.getSelectedIndex()+""
				};
				prod.AjoutProduit(tab);
				fenAjoutProduit.dispose();
                                
                                art=prod.listeArticle();
                                cmbArticlesFenPrinc.removeAllItems();
					/*On ajoute les articles dans la comboBox dedier aux articles*/
                                AjoutElementCombobox(art,1,cmbArticlesFenPrinc);
                                
			}
		});
		JButton btnAnnulerProduit = new JButton("Annuler");
		/**
		 * quand on clique sur annuler on quitte tout simplement la fenetre
		 */
		btnAnnulerProduit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				fenAjoutProduit.dispose();
			}
		});	
                // Mise en forme de la fenêtre Ajout produit            
                JPanel panGaucheAJart = new JPanel();
                GridLayout layoutGauche = new GridLayout();
                layoutGauche.setColumns(2);
                layoutGauche.setRows(12);
               
                panGaucheAJart.setLayout(layoutGauche);
                panGaucheAJart.add(new JLabel("Cat\u00E9gorie:"));
                panGaucheAJart.add(CBCateg);
                panGaucheAJart.add(new Espace());
                panGaucheAJart.add(new Espace());
                panGaucheAJart.add(new JLabel("Nom de l'article:"));
                panGaucheAJart.add(tfNomArt);
                panGaucheAJart.add(new Espace());
                panGaucheAJart.add(new Espace());
                panGaucheAJart.add(new JLabel("Prix de l'article:"));
                panGaucheAJart.add(tfPrixArt);
                panGaucheAJart.add(new Espace());
                panGaucheAJart.add(new Espace());
                panGaucheAJart.add(new JLabel("Quantit\u00E9:"));
                panGaucheAJart.add(tfQuantArt);
                panGaucheAJart.add(new Espace());
                panGaucheAJart.add(new Espace());
                
                
                JPanel panDroitAJart = new JPanel();
                BoxLayout layoutDroit = new BoxLayout(panDroitAJart,BoxLayout.Y_AXIS);
                panDroitAJart.setLayout(layoutDroit);
                panDroitAJart.add(new JLabel("Description:"));
                panDroitAJart.add(textPaneDescription);
                FlowLayout layoutbtn = new FlowLayout(FlowLayout.RIGHT);
                JPanel panBtn = new JPanel();
                panBtn.setLayout(layoutbtn);
                panBtn.add(btnValider);
                panBtn.add(btnAnnulerProduit);
                panDroitAJart.add(panBtn);
                
                JSplitPane panAjoutarticles = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panGaucheAJart,panDroitAJart);
                
                fenAjoutProduit.setContentPane(panAjoutarticles);
                fenAjoutProduit.setVisible(true);
	}

	
	/**
	 * fonction qui cree la fenetre pour ajouter un utilisateur
	 */
	public void initfenAjoutUt()
	{
		CPFenAjout= new JPanel();
		CPFenAjout.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		tfAjNom = new JTextField();
		tfAjNom.setColumns(10);
		
		tfAjPrenom = new JTextField();
		tfAjPrenom.setColumns(10);
		
		tfAjMail = new JTextField();
		tfAjMail.setColumns(10);
		
		tpAjAdresse = new JTextPane();
		
		tfAjCp = new JTextField();
		tfAjCp.setColumns(10);
		
		tfAjVille = new JTextField();
		tfAjVille.setColumns(10);
		
		
		tfAjPhone = new JTextField();
		tfAjPhone.setColumns(10);
		
		btnValiderAjProduit = new JButton("Valider");
		btnValiderAjProduit.addActionListener(this);
		
		btnAnnulerAjProduit = new JButton("Annuler");
		btnAnnulerAjProduit.addActionListener(this);
                
               
                JPanel panAjUtil = new JPanel();
                GridBagLayout layoutAjUtilisateur= new GridBagLayout();
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.insets = new Insets(10,0,0,0); 
                panAjUtil.setLayout(layoutAjUtilisateur);
                c.gridx = 0;
                c.gridy = 0;
                panAjUtil.add(new JLabel("Nom:"),c);
                c.gridx = 1;
                panAjUtil.add(tfAjNom,c);
                c.gridx = 2;
                panAjUtil.add(new JLabel("Pr\u00E9nom:"),c);
                c.gridx = 3;
                panAjUtil.add(tfAjPrenom,c);
                
                c.gridy = 1;
                c.gridx = 0;
                panAjUtil.add(new JLabel("Adresse:"),c);              
                c.gridy = 2;
                c.gridwidth = 4;
                c.ipady = 40;
                panAjUtil.add(tpAjAdresse,c);
                c.ipady = 0;
                c.gridy = 3;
                c.gridwidth = 1;
                panAjUtil.add(new JLabel("Code Postal:"),c);
                c.gridx = 1;
                c.gridwidth = 3;
                panAjUtil.add(tfAjCp,c);
                c.gridy = 4;
                c.gridx = 0;
                c.gridwidth = 1;
                panAjUtil.add(new JLabel("Ville:"),c);
                c.gridx = 1;
                c.gridwidth = 3;
                panAjUtil.add(tfAjVille,c);
                c.gridy = 5;
                c.gridx = 0;
                c.gridwidth = 1;
                panAjUtil.add(new JLabel("E-mail:"),c);
                c.gridx = 1;
                c.gridwidth = 3;
                panAjUtil.add(tfAjMail,c);
                c.gridy = 6;
                c.gridx = 0;
                c.gridwidth = 1;
                panAjUtil.add(new JLabel("T\u00E9l\u00E9phone:"),c);
                c.gridx = 1;
                c.gridwidth = 3;
                panAjUtil.add(tfAjPhone,c);
                c.gridy = 7;
                c.gridx = 0;
                c.gridwidth = 2;
                panAjUtil.add(btnValiderAjProduit,c);
                c.gridx = 2;
                panAjUtil.add(btnAnnulerAjProduit,c);
                                                            
                fenAjoutUt.setContentPane(panAjUtil);
                fenAjoutUt.setVisible(true);
	}
	
	
	/**
	 * La fonction addligne permet quand l'utilisateur valide un article
	 * de l'ajouter dans le jtable.
	 */
	public void addligne()
	{
		for(int i=0;i<art.length;i++)
		{
			//Des que l'article dans la base correspond e l'article selectionne
			if(cmbArticlesFenPrinc.getSelectedItem().toString().equals(art[i][1]))
			{
				//on recupere le total de l'article selectionne avec la quantite
				total=(Float.parseFloat(art[i][2])*(Float.parseFloat(CBBStock.getSelectedItem().toString())));
				//on met e jour le total de l'achet
				totalFacture+=total;
				//converti le nombre d'article selectionne en int
				int quant=Integer.parseInt(CBBStock.getSelectedItem().toString());
				//on place les donnees dans un Object
				Object[] donnee = new Object[]{"supp",art[i][0],art[i][1], art[i][2], CBBStock.getSelectedItem(), total};
				//on ajoute la ligne au jtable
				((NewModel)table.getModel()).addRow(donnee);
				//Apres on actualise le stock de l'article
				 art[i][3]= (Integer.parseInt(art[i][3])-quant)+"";
				 AjoutCCBStock(Integer.parseInt(art[i][3]),CBBStock);
				 if(Integer.parseInt(art[i][3])<1)
					{
						JOptionPane.showMessageDialog(fenAjoutUt, "Stock vide","Attention",JOptionPane.WARNING_MESSAGE);
					}	
				break;
			}
		}
		//on catualise le total de la facture
                
                
		lblValeurtotal.setText(df.format(totalFacture));
		lblValeurtotal.repaint();
		
	}
	/**
	 * fonction qui modifie une ligne du jtable si un article est deja present dans le jtable
	 * @param ligne
	 */
	public void modifligne(int ligne)
	{
		//on recupere la quantite (stock) contenu dans le jtable
		int quantTable=Integer.parseInt(table.getValueAt(ligne, 4).toString());
		//on recupere la quantite que le client souhaite rajouter
		int quant=Integer.parseInt(CBBStock.getSelectedItem().toString());
		//on additionne le tout
		int nouvquant=quantTable+quant;
		//contient la nouvelle quantite du stock disponnible pour la combobox
		int nouvquantCBOX=CBBStock.getItemCount()-quant;
		
		/*Reactualise le total*/
		totalFacture+=(Float.parseFloat(table.getValueAt(ligne, 3).toString())*quant);
		total+=(Float.parseFloat(table.getValueAt(ligne, 3).toString())*quant);
                
		 
		lblValeurtotal.setText(df.format(totalFacture));
		lblValeurtotal.repaint();
		
		//reactualise le combobox des stock
		AjoutCCBStock(nouvquantCBOX,CBBStock);
		//on reactualise le stock de l'article selectionne
		for(int i=0;i<art.length;i++)
		{
			if(cmbArticlesFenPrinc.getSelectedItem().toString()==art[i][1])
			{
				art[i][3]= (Integer.parseInt(art[i][3])-quant)+"";
			}
		}
		//on modifie les champ dans la jtable 5=>total et 4 => stock
		((NewModel)table.getModel()).SetValueAt(ligne, 5,(Object)(total));
		((NewModel)table.getModel()).SetValueAt(ligne, 4,(Object)(nouvquant));
	}
	/**
	 * fonctione qui supprime la derniere ligne ajoute.
	 * une fois la ligne supprimee, il faut reactualise les stocks
	 * @param ligne
	 */
	public void enlevelgne(int ligne)
	{
		//Réactualise le total de la facture
                //
		totalFacture-=(Float.parseFloat(table.getValueAt(ligne, 3).toString())*Float.parseFloat(table.getValueAt(ligne, 4).toString()));
		               
                if(totalFacture<=0)
                {
                    totalFacture=0.0f;
                }
                lblValeurtotal.setText(df.format(totalFacture));
		lblValeurtotal.repaint();

		//Récupère l'id de l'article dans la jtable de la ligne à retrancher ou à supprimer
		String idart=(table.getValueAt(ligne, 1).toString());
		//Récupère la quantité d'un article contenu dans la jtable de la ligne supprime
		int quant=(Integer.parseInt(table.getValueAt(ligne, 4).toString()));
		for(int i=0;i<art.length;i++)
		{
			//Dès que l'article de la base correspond à l'id de celui contenu dans la jtable
			//on le reactualise
			if(art[i][0].equals(idart))
			{
				int nouvstock=(Integer.parseInt(art[i][3])+quant);
				art[i][3]=Integer.toString(nouvstock);
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
	public void AjoutElementCombobox(String [][]el, int id,JComboBox comboBox2)
	{
		for(int i=0;i < el.length;i++)
		{
			comboBox2.addItem(el[i][id]);
		}
		//reactualise la fenetre
		fenPrinc.validate();
	}
        
        public void AjoutElementCombobox(String []el,JComboBox comboBox2)
	{
		for(int i=0;i < el.length;i++)
		{
			comboBox2.addItem(el[i]);
		}
		//reactualise la fenetre
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
	 * fonction appele pour l'ajout d'un utilisateur
	 */
	public void ajoutUtilisateur(String strMail,String strNom, String strPrenom,String strAdresse,String strVille,String strCp,String strPhone)
	{
		//pour verifie si les champ sont bien rempli
		boolean veriftext=true;
		boolean verifchif=true;
		boolean verifmail=true;
		//tableau pour verifie que les champs textes
		String []donneeText={strMail,strNom,strPrenom,strAdresse,strVille,};
                String []donneeTextAVerifier={strNom,strPrenom,strVille,};
		//tableau pour verifie que les champs numerique
		String []donneeChif={strCp,strPhone};
		Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
		Pattern pattern2 = Pattern.compile("^[0-9 ]+$");
                Pattern patternEmail = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
		//je parcour tout le tableau ne contenant que les champs textes, je les verifie un par un
		//si il y a une erreur on arrete tout
                
 			
                                for(String str : donneeTextAVerifier)
				{
					
					 if(!pattern.matcher(str).matches())
					 {
						 veriftext=false;
                                                 JOptionPane.showMessageDialog(fenAjoutUt, "Le client n'a pas pu être inscrit\nVérifier nom, prénom et ville.","Erreur!",JOptionPane.ERROR_MESSAGE);
					 	 break;
					 }
				}
                                if (!patternEmail.matcher(strMail).matches()) 
                                {
                                        verifmail=false;
                                        JOptionPane.showMessageDialog(fenAjoutUt, "Le client n'a pas pu être inscrit\nVérifier votre adresse E-mail.","Erreur!",JOptionPane.ERROR_MESSAGE);
                                }
		//Boucle vérification des entrées chiffrées
				for(String str : donneeChif)
				{
					 if(!pattern2.matcher(str).matches())
					 {
						 verifchif=false;
                                                 JOptionPane.showMessageDialog(fenAjoutUt, "Le client n'a pas pu être inscrit\nVérifier Code postal et numéro de téléphone.","Erreur!",JOptionPane.ERROR_MESSAGE);
					 	 break;
					 }
				}
				//je verifie si tout est ok, si c'est le cas alors j'enregistre le nouveau client
				if(veriftext && verifchif && verifmail)
				{
					if(tabuser.AjoutUser2(donneeText,donneeChif))
					{
						user=tabuser.ListeUser();
						comboBox.removeAllItems();
						JOptionPane.showMessageDialog(fenAjoutUt, "Le client a bien été inscrit","inscription",JOptionPane.PLAIN_MESSAGE);
						fenAjoutUt.dispose();
						AjoutElementCombobox(user,2,comboBox);
					}else
					{
						
					}
				}
	}
	/**
	 * Fonction regroupant les differents boutons/actions
	 */
	public void actionPerformed(ActionEvent e) 
	{
		/*Quant on valide un article */
		if(e.getSource()==btnOkArticle)
		{
			
			lblTotal.setEnabled(true);
			boolean exist=false;
			int i=0;
			//on verifie si l'article n'est pas dejàs présent dans la jtable
			while(i<table.getRowCount())
			{
				if(cmbArticlesFenPrinc.getSelectedItem().toString()==table.getValueAt(i, 2))
				{
					exist=true;
					//on modifie la quantite de l'article tant qu'il y en a en stock
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
		/*Quant on clicque sur le bouton qui valide le nom du client selectionné dans la comboBox*/
		if(e.getSource()==btnOkClient)
		{
                        
					/*On active les differents composants*/
			activerDesactiverElementGraphPrincipaux(true);
			lblValeurtotal.setText("0,00");
                        cmbArticlesFenPrinc.removeAllItems();
					/*On ajoute les articles dans la comboBox dedier aux articles*/
			AjoutElementCombobox(art,1,cmbArticlesFenPrinc);
					/*Variable qui definira le client qui est selectionne, pour connaitre le nombre de colonne
					 * je me sers d'une methode appartenant e la classe User*/
			userSelect=new String[tabuser.getnombreCol()];
			//stocke le nom prenom selectionne
			String userbox=comboBox.getSelectedItem().toString();
			String verifnom = lnom.getText().toString()+" "+lprenom.getText().toString();
			
			/*je teste si on a change de client
			 * Quand on change de client sans valider le pannier, on vide la Jtable*/
			if(userbox.compareTo(verifnom)<0)
			{
				((NewModel)table.getModel()).update(data);
				lblValeurtotal.setText("0,00");
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
			fenAjoutUt=new Fenetre("Ajout utilisateur",461,339,fenPrinc,true);
			initfenAjoutUt();
		}else
		if(e.getSource()==btnAnnulerAjProduit)
		{
			fenAjoutUt.dispose();
		}else
                if(e.getSource()==btnFermerListeProduit)
		{
			fenlist.dispose();
		}else
			//quand on valide l'ajout d'un utilisateur
		if(e.getSource()==btnValiderAjProduit)
		{
			this.ajoutUtilisateur(tfAjMail.getText(),
						tfAjNom.getText(),
						tfAjPrenom.getText(),
						tpAjAdresse.getText(),
						tfAjVille.getText(),tfAjCp.getText(),
						tfAjPhone.getText());
			
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
					user=tabuser.ListeUser();
					art=prod.listeArticle();
					//on ajoute les utilisateurs dans la combobox
					AjoutComboboxtest(user,2,3,comboBox);
					lblNom.setEnabled(true);
					comboBox.setEnabled(true);
					btnOkClient.setEnabled(true);
                                        
				}
		}else
			//quand on clique sur quitter
		if(e.getSource()==mntmQuitter)
		{
			System.exit(0);
		}
                else
                if(e.getSource()==mntmDeconnexion)
                {
                        if(BDD.deconnecter())
                        {
                        ldlEtatCon.setText("Déconnecté");
			mntmDeconnexion.setEnabled(false);
			mntmConnexion.setEnabled(true);
                        activerDesactiverElementGraphPrincipaux(false);
                        lblNom.setEnabled(false);
					comboBox.setEnabled(false);
					btnOkClient.setEnabled(false);
                        }
                        
                }
                else
			//Affiche l'inventaire des produit
		if(e.getSource()==btnListeProduit)
		{
                        cmbListeProduit = new JComboBox();
                        art=prod.listeArticle();
                        AjoutElementCombobox(art, 1, cmbListeProduit);
                        
			btnFermerListeProduit = new JButton("Fermer");
                        btnFermerListeProduit.addActionListener(this);
                        
			fenlist=new Fenetre("Listing produit",600,600,fenPrinc,true);
			CPFenList = new JPanel();
			CPFenList.setLayout(new BorderLayout(0,0));
			final JTextField quantstock = new JTextField();
			quantstock.setColumns(5);
			//produit classe par stock
			String [][]listart=prod.Article("stock");
			//on cree un nouvel jtabl ou l'on va placer tout les produits
			NewModel model2=new NewModel(listart,new String[]{"id","Nom","prix","stock","categorie"});
			table2 = new JTable(model2);
		//	table2.setPreferredScrollableViewportSize(new Dimension(300, 150));
			JScrollPane scrollPane2 = new JScrollPane(table2);
			//scrollPane2.setPreferredSize(new Dimension(300, 150));
			JLabel texte = new JLabel("Augmenter la quantité:");
			JPanel top = new JPanel();
                        top.setLayout(new GridBagLayout());
			JButton btnStockAjout = new JButton("Ok"); 
			//Quand on valide la nouvelle quantité, on met la base à jour
			btnStockAjout.addActionListener(new ActionListener() 
			{
                @Override
				public void actionPerformed(ActionEvent e) 
				{
					for(int i=0;i<art.length;i++)
					{
						if(cmbListeProduit.getSelectedItem()==art[i][1])
						{
							int id=Integer.parseInt(art[i][0]);
							int valeur=Integer.parseInt(quantstock.getText())+prod.getstock(id);
							prod.updatestock(id,valeur);
                                                        table2.setModel(new NewModel(prod.Article("stock"),new String[]{"id","Nom","prix","stock","categorie"}));
							break;
						}
					}
				}
			});
                        
                        GridBagConstraints contraintes = new GridBagConstraints();
                        contraintes.fill = GridBagConstraints.HORIZONTAL;
                        contraintes.gridx = 0;
                        contraintes.gridy = 0;
                        contraintes.weightx = 0.5;
			top.add(texte,contraintes);
			
                        contraintes.gridx = 1;
                        contraintes.gridy = 0;
                        top.add(cmbListeProduit,contraintes);
                        
                        contraintes.gridx = 2;
                        contraintes.gridy = 0;
			top.add(quantstock,contraintes);
                        
                        contraintes.gridx = 0;
                        contraintes.gridy = 1;
                        contraintes.gridwidth = 3;
			contraintes.weightx = 0.0;
                        top.add(btnStockAjout,contraintes);
			CPFenList.add(top,BorderLayout.NORTH);
			CPFenList.add(scrollPane2,BorderLayout.CENTER);
                        CPFenList.add(btnFermerListeProduit,BorderLayout.SOUTH);
			fenlist.setContentPane(CPFenList);
                        fenlist.setVisible(true);
			
			
		}else
			//quand on valide l'achat on peut avoir un petit appercu 
		if(e.getSource()==btnValider)
		{//fonction appercu d'achat
                    System.out.println("Facture Demandée!");
			crfact();
		  	
		}else
			//quand on valide le panier pour de bon, on met la base de donnee e jour
		if(e.getSource()==btValideTable)
		{
			//si l'ajout est reussit
			if(nouv.ajoutFacture(Integer.parseInt(userSelect[0]), totalFacture))
			{
				JOptionPane.showMessageDialog(fenAjoutUt, "La facture "+(nouv.getlastfact())+" a bien été confirmée....patienter pour l'impression","Facture",JOptionPane.PLAIN_MESSAGE);
				//pour chaque article dans la jtable, on met e jour son stock
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
				//reactualise les article
				art=this.prod.listeArticle();
				//data = new Object[0][0];
				table.setVisible(false);
				//pour continuer l'achat il faudra resélectionner un client
				cmbArticlesFenPrinc.setEnabled(false);
				supp.setEnabled(false);
				lblValeurtotal.setText("0,00");
				testfen.dispose();
				
			}
		}else
			//quand on supprime une ligne
			if(e.getSource()==supp)
			{
                            if(table.getRowCount()>0)
                            {
				this.enlevelgne(table.getRowCount()-1);
                            }
			}
			
	}
	public void activerDesactiverElementGraphPrincipaux(boolean etat)
        {
                        cmbArticlesFenPrinc.setEnabled(etat);
			lnom.setEnabled(etat);
			lprenom.setEnabled(etat);
			scrollPane.setVisible(etat);
			CBBStock.setEnabled(etat);
			btnOkArticle.setEnabled(etat);
			lblPrenom.setEnabled(etat);
			lblNom_1.setEnabled(etat);
			lblProduit.setEnabled(etat);
			btnAjoutProduit.setEnabled(etat);
			btnAjoutUt.setEnabled(etat);
			supp.setVisible(etat);
			btnListeProduit.setEnabled(etat);
                        btnValider.setEnabled(etat);
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
		testfen = new Fenetre("test",500,500,fenPrinc,true);
		JPanel testpan=new JPanel();
		testfen.setContentPane(testpan);
		testpane = new JTextPane();
		btValideTable=new JButton("Imprimer");
		btValideTable.addActionListener(this);
		
		testpan.add(testpane);
                testpan.add(btValideTable);
                
                JButton btn = new JButton("Annuler");
                ecouteur.setDispose(btn, testfen);
                testpan.add(btn);
                
                
		testpane.setPreferredSize(new Dimension(450,400));
		testpane.setEditable(false);
		String facttable="Id \t| Nom Produit \t| prix\t| quantite\t| total\n\n";
		nouv=new Facture(BDD.getCon());
		numfact=nouv.getlastfact()+1;
		for(int i=0;i<table.getRowCount();i++)
		{
			for(int j=1;j<table.getColumnCount();j++)
			{
				if(j<table.getColumnCount()-1)
				{
					facttable+=table.getValueAt(i, j).toString()+"\t| ";
				}else
					{
						facttable+=table.getValueAt(i, j).toString();
					}
				
			}facttable+="\n";
			
		}
		testpane.setText(
				"Numero de la facture : "+numfact+"\n\nIdentite\n" +
				"Nom : "+userSelect[2]+"\tPrenom : "+userSelect[3]+
				"\nmail : "+userSelect[1]+
				"\n\n\nAchat\n" +
				""+facttable+
				"\n\t\t Total hors T.V.A. : "+df.format(totalFacture)+" €"+
                                "\n\t\t T.V.A. (21%)      : "+df.format(totalFacture*0.21)+" €"+
                                "\n\t\t "+
                                "\n\t\t À payer           : "+df.format(totalFacture*1.21)+" €");
		testfen.setVisible(true);
	}
	/**
	 * Imprime la facture
	 */
	private void print() 
	{
            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
            Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            
            
            
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("DD_MM_yyy_HH-mm-ss");
	    String date = sdf.format(new Date());
            
	    Document document = new Document(PageSize.A4.rotate());
            
	    try 
	    {
             String nom_Fichier="Facture de "+userSelect[2]+" "+userSelect[3]+"du "+date;
                
	      PdfWriter writer = PdfWriter.getInstance(document,
	      new FileOutputStream(nom_Fichier+".pdf"));
              System.out.println(new File(".").getAbsolutePath());
              System.out.println(System.getProperty("user.dir"));
	      
              
              document.open();
              document.addTitle(nom_Fichier);
              document.addSubject("Facture en PDF via java et itext");
              document.addKeywords("TR3SYC, IRT, Java, PDF, iText");
              document.addAuthor(userSelect[2]+" "+userSelect[3]);
              document.addCreator(userSelect[2]+" "+userSelect[3]);
              
              Paragraph preface = new Paragraph();
              // We add one empty line
              addEmptyLine(preface, 1);
              // Lets write a big header
              Paragraph num_Facture = new Paragraph("Numéro de la facture : "+numfact, catFont);
              num_Facture.setAlignment(Paragraph.ALIGN_RIGHT);
              preface.add(num_Facture);
              
              Paragraph str_Date = new Paragraph("Date: "+ date, smallBold);
              str_Date.setAlignment(Paragraph.ALIGN_RIGHT);
              preface.add(str_Date);
                           
              addEmptyLine(preface, 1);
    
              document.add(preface);
              
              PdfPTable tableau = new PdfPTable(5);

    // t.setBorderColor(BaseColor.GRAY);
    // t.setPadding(4);
    // t.setSpacing(4);
    // t.setBorderWidth(1);

                PdfPCell c1 = new PdfPCell(new Phrase("Numéro d'article",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell(c1);

    c1 = new PdfPCell(new Phrase("Libelle",smallBold));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    tableau.addCell(c1);

    c1 = new PdfPCell(new Phrase("Quantité",smallBold));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    tableau.addCell(c1);
    tableau.setHeaderRows(1);

    c1 = new PdfPCell(new Phrase("Prix H.T.",smallBold));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    tableau.addCell(c1);
    tableau.setHeaderRows(1);
    
     c1 = new PdfPCell(new Phrase("Total en €",smallBold));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    tableau.addCell(c1);
    tableau.setHeaderRows(1);
    
    
    for(int i=0;i<table.getRowCount();i++)
		{
			for(int j=1;j<table.getColumnCount();j++)
			{
                            
                                tableau.addCell(table.getValueAt(i, j).toString());
			}
		}
  
    
    
    document.add(tableau);
    
    
    
    
    
              
            
              
	      /*PdfContentByte cb = writer.getDirectContent();
	      
	      cb.saveState();
	      Graphics2D g2 = cb.createGraphicsShapes(500, 500);

	      Shape oldClip = g2.getClip();
	      g2.clipRect(0, 0, 500, 500);
	      
	      document.add(new Paragraph("\n\nIdentite\n" +
					"Nom : "+userSelect[2]+"\tPrenom : "+userSelect[3]+
					"\nmail : "+userSelect[1]+""));
              
	      //testpane.print();
	      table.print(g2);
	      g2.setClip(oldClip);

	      g2.dispose();
	      cb.restoreState();
	      document.add(new Paragraph("Le montant total  est de "+total+" euro "));*/
		  document.newPage();
	      //////////////
	      
	    } catch (Exception e) {
	      System.err.println(e.getMessage());
	    }
	    document.close();
	  }
        
        
         private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
         
         public JTable getTableauPanier()
         {
             return table;
         }
}


class Espace extends JPanel
{

    public Espace() {
        this.setBackground(Color.lightGray);
    }

}
