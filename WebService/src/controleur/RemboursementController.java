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
import model.Remboursement;

/**
*
* @author Freddylie
*/

@Controller
@RequestMapping(produces = "text/json")
public class RemboursementController {
	
	@Autowired
    private BaseService service;
    
    public BaseService getService() {
		return service;
	}


	public void setService(BaseService service) {
		this.service = service;
	}
	
	 @RequestMapping(value="/remboursements",method = RequestMethod.GET)
	    public ResponseEntity<String> getRemboursements() {
	        HashMap<String,Object> map = new HashMap<String,Object>();
	        String json = "";
	        try{
	        	
	            List<BaseModel> all_remboursement = service.getAll(new Remboursement());
	            map.put("message", "success");
	            map.put("code", "200");
	            map.put("value", all_remboursement);
	            Gson gson = new Gson();
	            json = gson.toJson(map);
	        }
	        catch(Exception ex){
	            
	        }
	        return new ResponseEntity<String>(json, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/remboursement/ajout", method = RequestMethod.POST)
	    public ResponseEntity<String> ajoutRemboursement(@RequestParam("montant") double montant, @RequestParam("date") Date dateRemb,@RequestParam("id_pret") int idPret)throws Exception{

	    	HashMap<String,Object> map = new HashMap<String,Object>();
	    	String json = "";
	        try {
	        	
	            Remboursement remb = new Remboursement(montant,dateRemb,idPret);
	            service.insert(remb);
	            
	            map.put("message", "success");
	            map.put("code", "200");
	            map.put("value", remb);           
	            
	        }
	        catch (Exception ex) {
	        	map.put("message", "error");
	        	map.put("value",ex.getMessage());
	        }
	        Gson gson = new Gson();
	        json = gson.toJson(map);
	        return new ResponseEntity<String>(json, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/remboursement/annuler", method = RequestMethod.POST)
	    public ResponseEntity<String> annulerRemboursement(@RequestParam("id") int idRemb)throws Exception{

	    	HashMap<String,Object> map = new HashMap<String,Object>();
	    	String json = "";
	        try {
	            
	            service.delete(new Remboursement(), idRemb);
	            
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
