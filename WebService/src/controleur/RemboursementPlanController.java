package controleur;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import metier.BaseService;
import metier.RemboursementService;
import model.BaseModel;
import model.Pret;
import model.Remboursement;
import model.RemboursementPlan;

/**
*
* @author Freddylie
*/

@Controller
@RequestMapping(produces = "text/json")
public class RemboursementPlanController {
	
	@Autowired
    private BaseService service;
    
    public BaseService getService() {
		return service;
	}


	public void setService(BaseService service) {
		this.service = service;
	}
	
   
    
    @RequestMapping(value="/remboursement_plans",method = RequestMethod.GET)
    public ResponseEntity<String> getRemboursementPlans() {
        HashMap<String,Object> map = new HashMap<String,Object>();
        String json = "";
        try{
        	
            List<BaseModel> all_remboursement_plan  = service.getAll(new RemboursementPlan());
            map.put("message", "success");
            map.put("code", "200");
            map.put("value", all_remboursement_plan);
            Gson gson = new Gson();
            json = gson.toJson(map);
        }
        catch(Exception ex){
            
        }
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }
    

    
    @RequestMapping(value = "/remboursement_plan/ajout", method = RequestMethod.POST)
    public ResponseEntity<String> ajoutRemboursementPlan(@RequestParam("id_pret") int idPret,@RequestParam("date") Date date, @RequestParam("reste_capital") double resteCapital,@RequestParam("echeance") double echeance, @RequestParam("interet") double interet,@RequestParam("capital") double capital,@RequestParam("penalite") double penalite,@RequestParam("reste") double reste, @RequestParam("etat") int etat)throws Exception{

    	HashMap<String,Object> map = new HashMap<String,Object>();
    	String json = "";
        try {
        	
        	/* Etat
        	 etat = 1 (Non payé)
        	 etat = 5 (payé)
        	 etat = 9 (Avancé)
        	 etat = 13 (Pénalisé)
        	  
        	 */
            RemboursementPlan remb = new RemboursementPlan(idPret,date,resteCapital,echeance,interet,capital,penalite,0,0,echeance,1);
            service.insert(remb);
            
            map.put("message", "success");
            map.put("code", "200");
            
        }
        catch (Exception ex) {
        	map.put("message", "error");
        	map.put("value",ex.getMessage());
        }
        Gson gson = new Gson();
        json = gson.toJson(map);
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/remboursement_plan/calcul", method = RequestMethod.POST)
    public ResponseEntity<String> calculRemboursementPlan(@RequestParam("id") int idPret,@RequestParam("montant") double montant, @RequestParam("taux_interet") int taux,@RequestParam("durree") int durree,@RequestParam("date_pret") Date datePret)throws Exception{

    	HashMap<String,Object> map = new HashMap<String,Object>();
    	String json = "";
        try {
        	Pret pret = new Pret(idPret,montant,taux,durree,datePret);
        	RemboursementPlan[] liste;
        	liste = RemboursementService.calculRemboursement(pret);
        	
        	System.out.println("Taille: "+liste.length);
        	
        	map.put("value", liste); 
            map.put("message", "success");
            map.put("code", "200");
            
        }
        catch (Exception ex) {
        	map.put("message", "error");
        	map.put("value",ex.getMessage());
        }
        Gson gson = new Gson();
        json = gson.toJson(map);
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }

}
