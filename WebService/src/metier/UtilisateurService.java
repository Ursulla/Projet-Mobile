/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;

import org.springframework.stereotype.Component;

import model.BaseModel;
import model.Utilisateur;

/**
 *
 * @author Freddylie
 */
@Component
public class UtilisateurService extends BaseService {
    
    public static Utilisateur connexion(List<BaseModel> liste, String login, String password) throws Exception
    {
        for(int i=0;i<liste.size();i++)
        {
            Utilisateur user = (Utilisateur) liste.get(i);
            if(user.getLogin().equals(login) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }
    

}
