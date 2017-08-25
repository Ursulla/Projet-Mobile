package controleur;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import metier.BaseService;
import model.BaseModel;
import model.Pret;

/**
*
* @author Freddylie
*/

@Controller
@RequestMapping(produces = "text/json")
public class PretController {
	
	@Autowired
    private BaseService service;
    
    public BaseService getService() {
		return service;
	}


	public void setService(BaseService service) {
		this.service = service;
	}

	@RequestMapping(value="/prets",method = RequestMethod.GET)
    public ResponseEntity<String> getPrets() {
        HashMap<String,Object> map = new HashMap<String,Object>();
        String json = "";
        try{
        	
            List<BaseModel> all_pret = service.getAll(new Pret());
            map.put("message", "success");
            map.put("code", "200");
            map.put("value", all_pret);
            Gson gson = new Gson();
            json = gson.toJson(map);
        }
        catch(Exception ex){
            
        }
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }
	
	  @RequestMapping(value = "/pret/ajout", method = RequestMethod.POST)
	    public ResponseEntity<String> ajoutPret(@RequestParam("montant") double montant, @RequestParam("taux_interet") int taux,@RequestParam("duree") int durree,@RequestParam("date_pret") Date datePret,@RequestParam("id_utilisateur") int user,@RequestParam("validation") int validation)throws Exception{

	    	HashMap<String,Object> map = new HashMap<String,Object>();
	    	String json = "";
	        try {
	        	
	            Pret pret = new Pret(montant,taux,durree,datePret,user,validation,null);
	            service.insert(pret);
	            
	            map.put("message", "success");
	            map.put("code", "200");
	            map.put("value", pret);           
	            
	        }
	        catch (Exception ex) {
	        	map.put("message", "error");
	        	map.put("value",ex.getMessage());
	        }
	        Gson gson = new Gson();
	        json = gson.toJson(map);
	        return new ResponseEntity<String>(json, HttpStatus.OK);
	    }
	  
	  @RequestMapping(value = "/pret/validation", method = RequestMethod.POST)
	    public ResponseEntity<String> validationPret(@RequestParam("id") int id, @RequestParam("date_deblocage") Date deblocage)throws Exception{

	    	HashMap<String,Object> map = new HashMap<String,Object>();
	    	String json = "";
	        try {
	        	Pret pret;
	             pret = (Pret) service.getById(new Pret(),id);
	             pret.setValidation(5);
	             pret.setDateDeblocage(deblocage);
	             service.update(pret);
	            
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
