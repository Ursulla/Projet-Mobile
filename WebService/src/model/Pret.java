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
@Table(name = "pret")

public class Pret extends BaseModel{
	    @Column(name = "montant")
	    private double montant;
	    @Column(name = "taux_interet")
	    private int tauxInteret;
	    @Column(name = "durree")
	    private int durree;
	    @Column(name = "date_pret")
	    private Date datePret;
	    @Column(name = "id_utilisateur")
	    private int user;
	    @Column(name = "validation")
	    private int validation;
	    @Column(name = "date_deblocage")
	    private Date dateDeblocage;
	    
	    
		public Pret(double montant, int tauxInteret, int durree, Date datePret, int user, int validation,
				Date dateDeblocage) {
			super();
			this.montant = montant;
			this.tauxInteret = tauxInteret;
			this.durree = durree;
			this.datePret = datePret;
			this.user = user;
			this.validation = validation;
			this.dateDeblocage = dateDeblocage;
		}



		public Pret(int id,double montant, int tauxInteret, int durree, Date datePret) {
			this.setId(id);
			this.montant = montant;
			this.tauxInteret = tauxInteret;
			this.durree = durree;
			this.datePret = datePret;
		}



		public Pret() {
			super();
		}



		public double getMontant() {
			return montant;
		}


		public void setMontant(double montant) {
			this.montant = montant;
		}


		public int getTauxInteret() {
			return tauxInteret;
		}


		public void setTauxInteret(int tauxInteret) {
			this.tauxInteret = tauxInteret;
		}


		public int getDurree() {
			return durree;
		}


		public void setDurree(int durree) {
			this.durree = durree;
		}


		public Date getDatePret() {
			return datePret;
		}


		public void setDatePret(Date datePret) {
			this.datePret = datePret;
		}


		public int getUser() {
			return user;
		}


		public void setUser(int user) {
			this.user = user;
		}


		public int getValidation() {
			return validation;
		}


		public void setValidation(int validation) {
			this.validation = validation;
		}


		public Date getDateDeblocage() {
			return dateDeblocage;
		}


		public void setDateDeblocage(Date dateDeblocage) {
			this.dateDeblocage = dateDeblocage;
		}
	    
	    
		


}
