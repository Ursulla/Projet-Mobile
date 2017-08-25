/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import metier.BaseService;
import metier.UtilisateurService;
import model.BaseModel;
import model.Pret;
import model.Remboursement;
import model.RemboursementPlan;
//import model.Article;
import model.Utilisateur;
/**
 *
 * @author Freddylie
 */

@Controller
@RequestMapping(produces = "text/json")
public class IndexController {
    @Autowired
    private BaseService service;
    
    public BaseService getService() {
		return service;
	}


	public void setService(BaseService service) {
		this.service = service;
	}

    
    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public ResponseEntity<String> connexion(@RequestParam("login") String login, @RequestParam("password") String password)throws Exception{

    	HashMap<String,Object> map = new HashMap<String,Object>();
    	String json = "";
        try {
        	
            Utilisateur utilisateur;
            utilisateur = UtilisateurService.connexion(service.getAll(new Utilisateur()), login, password);
            
            if(utilisateur==null){                   
            	map.put("message", "error");
            	map.put("code", "200");
            	map.put("value","Erreur de la connexion");
            }
            else{
                System.out.println("Nom utilisateur: "+utilisateur.getNom());
                map.put("message", "success");
                map.put("value", utilisateur);           
            }
        }
        catch (Exception ex) {
        	map.put("message", "error");
        	map.put("value",ex.getMessage());
        }
        Gson gson = new Gson();
        json = gson.toJson(map);
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public ResponseEntity<String> ajoutRemboursementPlan(@RequestParam("nom") String nom,@RequestParam("prenom") String prenom,@RequestParam("adresse") String adresse,@RequestParam("telephone") String telephone,@RequestParam("login") String login,@RequestParam("password") String password,@RequestParam("type") String type, @RequestParam("profession") String profession)throws Exception{

    	HashMap<String,Object> map = new HashMap<String,Object>();
    	String json = "";
        try {

        	Utilisateur user = new Utilisateur( nom, prenom, adresse, telephone, login, password, type, profession);
            service.insert(user);
            
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
    
    
    @RequestMapping(value="/utilisateurs",method = RequestMethod.GET)
    public ResponseEntity<String> getUtilisateurs() {
        HashMap<String,Object> map = new HashMap<String,Object>();
        String json = "";
        try{
        	
            List<BaseModel> all_user = service.getAll(new Utilisateur());
            map.put("message", "success");
            map.put("code", "200");
            map.put("value", all_user);
            Gson gson = new Gson();
            json = gson.toJson(map);
        }
        catch(Exception ex){
            
        }
        return new ResponseEntity<String>(json, HttpStatus.OK);
    }
    
   

    
    
}
