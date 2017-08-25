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
@Table(name = "remboursement_plan")

public class RemboursementPlan extends BaseModel{
		@Column(name = "id_pret")
	    private int idPret;
	 	@Column(name = "date")
	    private Date date;
	    @Column(name = "reste_capital")
	    private double resteCapital;
	    @Column(name = "echeance")
	    private double echeance;
	    @Column(name = "interet")
	    private double interet;
	    @Column(name = "capital")
	    private double capital;
	    @Column(name = "penalite")
	    private double penalite;
	    @Column(name = "payement_penalite")
	    private double payementPenalite;
	    @Column(name = "montant_principal")
	    private double montantPrincipal;
	    @Column(name = "reste")
	    private double reste;
	    @Column(name = "etat")
	    private int etat;
	    
	    
		


		public RemboursementPlan(int idPret, Date date, double resteCapital, double echeance, double interet,
				double capital, double penalite, double payementPenalite, double montantPrincipal, double reste,
				int etat) {
			super();
			this.idPret = idPret;
			this.date = date;
			this.resteCapital = resteCapital;
			this.echeance = echeance;
			this.interet = interet;
			this.capital = capital;
			this.penalite = penalite;
			this.payementPenalite = payementPenalite;
			this.montantPrincipal = montantPrincipal;
			this.reste = reste;
			this.etat = etat;
		}


		public RemboursementPlan() {
			super();
		}


		public int getIdPret() {
			return idPret;
		}


		public void setIdPret(int idPret) {
			this.idPret = idPret;
		}


		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}


		public double getResteCapital() {
			return resteCapital;
		}


		public void setResteCapital(double resteCapital) {
			this.resteCapital = resteCapital;
		}


		public double getEcheance() {
			return echeance;
		}


		public void setEcheance(double echeance) {
			this.echeance = echeance;
		}


		public double getInteret() {
			return interet;
		}


		public void setInteret(double interet) {
			this.interet = interet;
		}


		public double getCapital() {
			return capital;
		}


		public void setCapital(double capital) {
			this.capital = capital;
		}


		public double getPenalite() {
			return penalite;
		}


		public void setPenalite(double penalite) {
			this.penalite = penalite;
		}


		public double getPayementPenalite() {
			return payementPenalite;
		}


		public void setPayementPenalite(double payementPenalite) {
			this.payementPenalite = payementPenalite;
		}


		public double getMontantPrincipal() {
			return montantPrincipal;
		}


		public void setMontantPrincipal(double montantPrincipal) {
			this.montantPrincipal = montantPrincipal;
		}


		public double getReste() {
			return reste;
		}


		public void setReste(double reste) {
			this.reste = reste;
		}


		public int getEtat() {
			return etat;
		}


		public void setEtat(int etat) {
			this.etat = etat;
		}
	    
	    
	    
	    
}
