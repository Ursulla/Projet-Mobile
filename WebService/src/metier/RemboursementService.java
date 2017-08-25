package metier;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import model.Pret;
import model.RemboursementPlan;

/**
*
* @author Freddylie
*/

@Component
public class RemboursementService extends BaseService {

	
	
	
	public static RemboursementPlan[] calculRemboursement(Pret demande) throws Exception{
	    
		System.out.println("********************* CALCUL *************************");
                                   
        //Création tableu de remboursement avec taille = nombre de remboursement (supposons que celui ci se fait par mois)
        RemboursementPlan[] liste = new RemboursementPlan[demande.getDurree()]; //car durrée = nombre de mois de remboursement
        for(int e=0;e<liste.length;e++){
            liste[e]=new RemboursementPlan();
        }
        //Initialisation
        liste = initialisation(liste,demande);
    
        //Calcul
        for(int i=1;i<liste.length;i++){

            Calendar c = Calendar.getInstance();
            c.setTime(liste[i-1].getDate());
            c.add(Calendar.MONTH,1);
            liste[i].setIdPret(demande.getId());
            liste[i].setDate(c.getTime());
            liste[i].setResteCapital(Math.round((liste[i-1].getResteCapital()-(liste[i-1].getEcheance()-liste[i-1].getInteret()))*(1.0/0.01))/(1.0/0.01) );
            liste[i].setEcheance(Math.round(liste[i-1].getEcheance()*(1.0/0.01))/(1.0/0.01));
            liste[i].setInteret(Math.round((liste[i].getResteCapital()*(double)(((double)demande.getTauxInteret()/(double)100)/(double)12))*(1.0/0.01))/(1.0/0.01));
            liste[i].setCapital(Math.round((liste[i].getEcheance()-liste[i].getInteret())*(1.0/0.01))/(1.0/0.01));
            liste[i].setEtat(1);
            liste[i].setMontantPrincipal(0.00);
            liste[i].setPenalite(0.00);
            liste[i].setPayementPenalite(0.00);
            liste[i].setReste(Math.round(liste[i-1].getEcheance()*(1.0/0.01))/(1.0/0.01));
            
        }
        
        //Retourner tableau complet
        return liste;
    }
    
    public static RemboursementPlan[] initialisation(RemboursementPlan[] liste,Pret demande){
    	
        double tauxAnnuel = ((double)demande.getTauxInteret()/(double)100)/(double)12;
        Date datePret = demande.getDatePret();  
        double resteCapital = (double)demande.getMontant();
        double interet = resteCapital*tauxAnnuel;
        double echeance = ((double)demande.getMontant()*tauxAnnuel)/(1-Math.pow(1+tauxAnnuel,-(double)demande.getDurree()));
        double capital = echeance - interet;
        double penalite = 0.00;
        double payementPenalite = 0.00;
        double montantPrincipal = echeance;

        liste[0].setIdPret(demande.getId());
        liste[0].setDate(randomDate(datePret));
        liste[0].setResteCapital(Math.round(resteCapital*(1.0/0.01))/(1.0/0.01));
        liste[0].setInteret(Math.round(interet*(1.0/0.01))/(1.0/0.01));
        liste[0].setEcheance(Math.round(echeance*(1.0/0.01))/(1.0/0.01));
        liste[0].setCapital(Math.round(capital*(1.0/0.01))/(1.0/0.01));
        liste[0].setEtat(1);
        liste[0].setMontantPrincipal(0.00);
        liste[0].setPenalite(penalite);
        liste[0].setPayementPenalite(payementPenalite);
        liste[0].setReste(montantPrincipal);
        
        
        return liste;
    }

    @SuppressWarnings("deprecation")
	public static Date randomDate(Date date){
    	
    	//NB: DATE: MM/JJ/AAAA
        Date suivant = new Date();
        suivant = date;
        suivant.setMonth(date.getMonth()+1);
        
        return suivant;
    }
}
