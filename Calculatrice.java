import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
  
public class Calculatrice extends JFrame implements ActionListener  {
	
	private JPanel container;
	private JTextField tf ;
	private String[] btnLabelTab =  {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
	private JButton[] btnTab ;
	private String opd1="", opd2="", opr="";
	private JPanel btnContainer; 

	Calculatrice()
	{
		setDefaultCloseOperation(Calculatrice.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(300,210);
		setResizable(false);
		
		
		//Instanciation des conteneurs
		container  = new JPanel(new BorderLayout(0,10));
		container.setSize(280,280);
		
		btnContainer = new JPanel(new FlowLayout());
		btnContainer.setSize(240,180);
		
		// Instanciation des Bouttons 
		btnTab = new JButton[17];
		int i;
		
		for(i=0;i<17;i++)
		{
			btnTab[i]= new JButton(btnLabelTab[i]);
			btnTab[i].addActionListener(this);
			btnContainer.add(btnTab[i]);
		}
		container.add(btnContainer,BorderLayout.CENTER);
		//container.add(numberPan,BorderLayout.WEST);
		
		//Instanciation du champ d'affichage 
		tf = new JTextField();
		tf.setPreferredSize(new Dimension(260,60));
		tf.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		tf.setEditable(false);
			
		//Ajouter le textField au conteneur principal 
		container.add(tf,BorderLayout.NORTH);
		setContentPane(container);	
		
		//Display
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		// Recuperer la source de l'evenement 
		String s = e.getActionCommand();
		
		// si la valeur est un nombre 
		if((s.charAt(0)>='0'&& s.charAt(0)<='9')|| s.charAt(0)=='.')

        {  
			
			// Si on saisi sur le 2eme operande
			if(!(opr==""))
				opd2= opd2+s;
	
			else
				opd1= opd1+s;
				
			// Mettre a jour le text dans le champ d'affichage 
			tf.setText(opd1 + opr + opd2);
			
        }
		else if (s.charAt(0) =='C')
		{
			//Effacer le contenu des var ainsi que le text affiche'
			opd1=opd2=opr="";
			tf.setText("");
		}
		
		else if (s.charAt(0) == '=')
		{	
			Double rslt=(double) 0;
			
			// Selon l'operateur en entree', effectuer le traitement adequat.
			if(opr.charAt(0) == '+')
				rslt= Double.parseDouble(opd1) + Double.parseDouble(opd2);
			else if (opr.charAt(0) == '-')
				rslt= Double.parseDouble(opd1) - Double.parseDouble(opd2);
			else if (opr.charAt(0) == '*')
				rslt= Double.parseDouble(opd1) * Double.parseDouble(opd2);
			else 
				rslt= Double.parseDouble(opd1) / Double.parseDouble(opd2);
				
			// Afficher le resultat 
			tf.setText(opd1+opr+opd2+'=' +rslt);
			
			//Stocker le resultat dans le 1er operande apres conversion 
			opd1= Double.toString(rslt);
			
			// Remettre le reste a blanc 
			opd2=opr="";
		}
		
		else // Cas d'un operateurs
		{
			
			//Si y'a pas de 2 eme operande ou d'oprateur 
			if( opd2 == "" || opr == "")
				opr=s; 
			else
			{
				// Faire le calcule avant, puis sauvgarder l'operateur pour faire le prochain calcule 
				Double rslt;
				
				// Selon l'operateur en entree', effectuer le traitement adequat.
				if(opr.charAt(0) == '+')
					rslt= Double.parseDouble(opd1) + Double.parseDouble(opd2);
				else if (opr.charAt(0) == '-')
					rslt= Double.parseDouble(opd1) - Double.parseDouble(opd2);
				else if (opr.charAt(0) == '*')
					rslt= Double.parseDouble(opd1) * Double.parseDouble(opd2);
				else 
					rslt= Double.parseDouble(opd1) / Double.parseDouble(opd2);
				
				//Stocker le resultat dans le 1er operande apres conversion 
				opd1= Double.toString(rslt);
				
				//Mettre a Jour les tempons 
				opd2="";
				opr=s;
				
			}
			
			// Mettre a jour le text dans le champ d'affichage 
						tf.setText(opd1+ opr + opd2);
		}
		
		
	}
	
	

}