package calculatrice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Fencal extends JFrame {	
	//Calcul
	private static int nbreOperateurInterMax = 10;
	private int nbreOperateurInter = 0;
	private String calcul; //Formule affichée en haut
	private String[] operateurs = new String[nbreOperateurInterMax]; //Contient tous les opérateurs tapés
	private String[] calculinter = new String[7];
	private StringBuilder calcT = new StringBuilder();
	//résultat
	private static int nbreNombreInterMax = 11;
	private int nbreNombreInter = 0, nbrChiffreNombre = 0;
	private double resultatC = 0; //Résultat finale
	private double[] resultaInter = new double[nbreNombreInterMax];
	private String resultatT= new String("0");
	private StringBuilder nombre = new StringBuilder("");
	
	//Boutons
	private GridLayout GD = new GridLayout(4,3); //Grille des boutons de gauche
	private String[] boutons= {"0","1","2","3","4","5","6","7","8","9",".","="}, boutonsE= {"+","-","x","/"};
	private JButton bA = new JButton("A"); //Bouton annuler
	private JButton[] jbouti = new JButton[boutons.length]; //Boutons de gauche
	private JButton[] yemen = new JButton[boutonsE.length]; //Boutons de droite
	private Boolean activationChiffres = true, activationOpérateurs = true;
	
	//Panneau principal
	private JPanel container = new JPanel();
	//Panneaux partie input
	private JPanel pzt = new JPanel(); //Panneau de toutes les touches
	private JPanel pA = new JPanel(), Est = new JPanel();
	private JPanel[] panyen = new JPanel[boutonsE.length];
	private JPanel Ouest = new JPanel();
	//Panneaux partie affichage :
	private JPanel pg = new JPanel(), pd = new JPanel();
	private JPanel p2 = new JPanel(), p3 = new JPanel();
	private JPanel ptra = new JPanel();
	
	private Color rouge = Color.RED, noir = Color.BLACK, vert = Color.GREEN, blanc = Color.WHITE;
	private Font ta = new Font("Tahoma", Font.BOLD, 18); //Police du bouton annuler
	private Font flr = new Font("Courrier", Font.BOLD, 30); //Police du résultat
	private Font flr2 = new Font("Courrier", Font.ITALIC, 18); //Police du calcul
	private Border labborr = BorderFactory.createLineBorder(rouge);
	private JLabel lr = new JLabel("0"); //Label du résultat
	private JLabel lr2 = new JLabel("Entrer le calcul "); //Label du calcul
	
	//***************************************
	//*				CONSTRUCTEUR			*
	//***************************************
	public Fencal() {
		//Propriétés de la fenêtre
		this.setTitle("Calculatrice");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//-------------------------------------------------------------------
		//						Zone des chiffres
		//-------------------------------------------------------------------
		Dimension dim = new Dimension(this.getWidth()/7,this.getHeight()/10);
		Ouest.setBackground(noir);
		GD.setHgap(10); GD.setVgap(10);
		Ouest.setLayout(GD);
		for(int i=0;i<boutons.length;i++) {
			jbouti[i] = new JButton(boutons[i]);
			jbouti[i].setPreferredSize(dim);
			jbouti[i].setBackground(rouge);
			switch (i) {
			case 10: //Touche décimale
				jbouti[i].setEnabled(activationChiffres);
				jbouti[i].addActionListener(new BLV());
				break;
			case 11: //Touche égale
				jbouti[i].addActionListener(new BLG());
				jbouti[i].setEnabled(true);
				break;
			default: //Touche 0 à 9
				jbouti[i].setEnabled(activationChiffres);
				jbouti[i].addActionListener(new BLC());
				break;
				}
			Ouest.add(jbouti[i]);
			}
		//------------------------------------------------------------------
		//						Boutons de droites
		//------------------------------------------------------------------
		dim = new Dimension(this.getWidth()/7,this.getHeight()/14);
		Est.setBackground(noir);
		Est.setLayout(new BoxLayout(Est, BoxLayout.Y_AXIS));
		//Bouton annulation
		bA.setEnabled(true);
		bA.setBackground(Color.YELLOW);
		bA.setForeground(rouge);
		bA.setFont(ta);
		bA.setPreferredSize(new Dimension(jbouti[1].getPreferredSize()));
		bA.setEnabled(true);
		bA.addActionListener(new BLA());
		pA.setBackground(noir);
		pA.add(bA);
		Est.add(pA);
		//Zone des opérateurs
		for(int k=0;k<boutonsE.length;k++) {
			yemen[k]=new JButton(boutonsE[k]);
			yemen[k].setPreferredSize(dim);
			yemen[k].setBackground(vert);
			switch (k) {
			case 0: //Touche +
				yemen[k].setEnabled(activationOpérateurs);
				yemen[k].addActionListener(new BLplus());
				panyen[k]=new JPanel();
				panyen[k].add(yemen[k]);
				panyen[k].setBackground(noir);
				Est.add(panyen[k]);
				break;
			case 1://Touche -
				yemen[k].setEnabled(activationOpérateurs);
				yemen[k].addActionListener(new BLmoins());
				panyen[k]=new JPanel();
				panyen[k].add(yemen[k]);
				panyen[k].setBackground(noir);
				Est.add(panyen[k]);
				break;
			case 2://Touche x
				yemen[k].setEnabled(activationOpérateurs);
				yemen[k].addActionListener(new BLfois());
				panyen[k]=new JPanel();
				panyen[k].add(yemen[k]);
				panyen[k].setBackground(noir);
				Est.add(panyen[k]);
				break;				
			case 3://Touche -
				yemen[k].setEnabled(activationOpérateurs);
				yemen[k].addActionListener(new BLdiviser());
				panyen[k]=new JPanel();
				panyen[k].add(yemen[k]);
				panyen[k].setBackground(noir);
				Est.add(panyen[k]);
				break;
			default:
				System.out.println("Impossible d'aavoir cette touche à droite de l'écran");
				break;
				}
			}
		
		//Zone des touches
		pzt.setBackground(noir);
		pzt.add(Ouest, BorderLayout.WEST);
		pzt.add(Est,BorderLayout.EAST);
		
		//----------------------------------------------------------------------
		//								Labels
		//----------------------------------------------------------------------
		dim = new Dimension(this.getWidth()/2,this.getHeight()/14);
		//Opération
		lr2.setPreferredSize(dim);
		lr2.setHorizontalAlignment(JLabel.LEFT);
		lr2.setFont(flr2);
		lr2.setForeground(blanc);
		lr2.setBorder(labborr);
		p2.setPreferredSize(dim);
		p2.add(lr2);
		p2.setBackground(noir);
		
		//Résultat affiché
		lr.setHorizontalAlignment(JLabel.RIGHT);
		lr.setFont(flr);
		lr.setForeground(vert);
		lr.setPreferredSize(dim);
		p3.setPreferredSize(dim);
		p3.add(lr);
		
		p3.setBackground(blanc); ptra.setBackground(noir);
		pg.setBackground(noir); pd.setBackground(noir);
		
		dim = new Dimension(this.getWidth()/18,this.getHeight()/18);
		pg.setPreferredSize(dim);
		pd.setPreferredSize(dim);
		ptra.setLayout(new BoxLayout(ptra, BoxLayout.LINE_AXIS));
		ptra.add(pg); ptra.add(p3); ptra.add(pd);
		
		//--------------------------------------------------------------------------
		//Propriétés du panneau principal
		//--------------------------------------------------------------------------
		container.setBackground(noir);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(p2); container.add(ptra); container.add(pzt);
		
		this.setContentPane(container);
		this.setVisible(true);
	}

	private void go() {
		// TODO Auto-generated method stub
		resultatC=0;
		for(int j=0;j<nbreOperateurInter;j++) {
			if (j==0)
				resultatC=resultaInter[j];
			//Opération + --------------------------------------------
			if (operateurs[j].equals("+"))
				resultatC+=resultaInter[j+1];
			//Opération - --------------------------------------------
			else if (operateurs[j].equals("-"))
				resultatC=resultatC-resultaInter[j+1];
			//Opération x --------------------------------------------
			else if (operateurs[j].equals("x"))
				resultatC=resultatC*resultaInter[j+1];
			//Opération / --------------------------------------------
			else if (operateurs[j].equals("/")) {
				if (!(resultaInter[j+1]==0))
					resultatC=	resultatC/resultaInter[j+1];
				else {
					Annulation();
					lr2.setText("division par 0 !!!!!!");
				}
			}
			else
				System.out.println("Votre opérateur n'existe pas sur cette calculatrice !");
		}
		//Affichage du résultat
		lr.setText(String.valueOf(resultatC));	
	}
	
	private void finCreationNombre() {
		nbrChiffreNombre= 0;
		calculinter = new String[7];
		nbreOperateurInter++; nbreNombreInter++;
	}
	
	private void creationNombre() {
		// TODO Auto-generated method stub
		calcT.append(calculinter[nbrChiffreNombre]); // Renseignement texte calcul		
		nombre=new StringBuilder("");
		resultaInter[nbreNombreInter]=0;
		for(int n=1;n<=nbrChiffreNombre;n++) {
			if(calculinter[n]==boutons[11] && n==nbrChiffreNombre) { //Gestion décimale
				calculinter[n+1]="0";
				nbrChiffreNombre++;
			}
			nombre.append(calculinter[n]); //Création du nombre
		}
		resultaInter[nbreNombreInter]=Double.parseDouble(nombre.toString()); //Renseignement du nombre
		lr2.setText(calcT.toString());
	}
	
	private void Annulation() {
		resultatC = 0; nbreNombreInter = 0;
		nbreOperateurInter=0; calcul="Entrer le calcul ";
		activationChiffres=true; activationOpérateurs = true;
		lr.setText(resultatT);
		resultatT= new String("0");
		resultaInter = new double[7];
		calcT = new StringBuilder();
	}


	//***************************************
	//		Gestionn d'entrée des chiffres
	//--------------------------------------
	class BLC implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Vérification du nombre maximum de nombre
			if (nbreNombreInter > nbreNombreInterMax) {
				lr2.setText("Pas plus de "+nbreNombreInterMax+" chiffres");
				activationChiffres=false;
			}
			else {
				nbrChiffreNombre++; //Nombre de chiffres par nombre
				calculinter[nbrChiffreNombre]=((JButton)e.getSource()).getText();
				creationNombre();
				}
			}
		}
	
	//--------------------------------------
	//		Gestion d'entrée de la virgule
	//--------------------------------------
	class BLV implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nbrChiffreNombre++; //Nombre de chiffres par nombre
			calculinter[nbrChiffreNombre]=((JButton)e.getSource()).getText();
			creationNombre();
			}
		}
	
	//************************************
	//		Gestion d'entrée du signe +
	//----------------------------------
	class BLplus implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Vérification du nombre maximum d'opérateur
			if (nbreOperateurInter > nbreOperateurInterMax) {
				lr2.setText("Pas plus de "+nbreNombreInterMax+" opérateurs");
				activationOpérateurs=false;
			} else {
				calcT.append("+");
				operateurs[nbreOperateurInter]="+";
				finCreationNombre();
				}
			}
		}
	
	//----------------------------------
	//		Gestion d'entrée du signe -
	//----------------------------------
	class BLmoins implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Vérification du nombre maximum d'opérateur
			if (nbreOperateurInter > nbreOperateurInterMax) {
				lr2.setText("Pas plus de "+nbreNombreInterMax+" opérateurs");
				activationOpérateurs=false;
			} else {
				calcT.append("-");
				operateurs[nbreOperateurInter]="-";
				finCreationNombre();
				}
			}
		}
	
	//----------------------------------
	//		Gestion d'entrée du signe x
	//----------------------------------
	class BLfois implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Vérification du nombre maximum d'opérateur
			if (nbreOperateurInter > nbreOperateurInterMax) {
				lr2.setText("Pas plus de "+nbreNombreInterMax+" opérateurs");
				activationOpérateurs=false;
			} else {
				calcT.append("*");
				operateurs[nbreOperateurInter]="x";
				finCreationNombre();
				}
			}
		}
	
	//----------------------------------
	//		Gestion d'entrée du signe /
	//----------------------------------
	class BLdiviser implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Vérification du nombre maximum d'opérateur
			// TODO Auto-generated method stub
			if (nbreOperateurInter > nbreOperateurInterMax) {
				lr2.setText("Pas plus de "+nbreNombreInterMax+" opérateurs");
				activationOpérateurs=false;
			} else {
				calcT.append("/");
				operateurs[nbreOperateurInter]="/";
				finCreationNombre();
				}
			}
		}
	
	
	//************************************
	//				Annulation
	//************************************
	class BLA implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("BLA"); //TODO
			Annulation();
			lr2.setText(calcul);
			}
		}

	//----------------------------------
	//		Gestion d'entrée du signe =
	//----------------------------------
	class BLG implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("BLG"); //TODO
			nbrChiffreNombre= 0;
			calculinter = new String[7];
			go();
			}
		}
}