package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
*
* @author Freddylie
*/


@Entity @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name = "remboursement")

public class Remboursement extends BaseModel{
	    @Column(name = "montant")
	    private double montant;
	    @Column(name = "date")
	    private Date dateRemboursement;
	    @Column(name = "id_pret")
	    private int pret;
	    
	    
		public Remboursement(double montant, Date dateRemboursement, int pret) {
			super();
			this.montant = montant;
			this.dateRemboursement = dateRemboursement;
			this.pret = pret;
		}

		
		public Remboursement() {
			super();
		}


		public double getMontant() {
			return montant;
		}


		public void setMontant(double montant) {
			this.montant = montant;
		}


		public Date getDateRemboursement() {
			return dateRemboursement;
		}


		public void setDateRemboursement(Date dateRemboursement) {
			this.dateRemboursement = dateRemboursement;
		}


		public int getPret() {
			return pret;
		}


		public void setPret(int pret) {
			this.pret = pret;
		}
	    
	    
		
}
