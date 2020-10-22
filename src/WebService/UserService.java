/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.Affect;
import Entity.Meeting;
import Entity.Project;
import Entity.User;
import Utils.Utils;
import static Utils.Utils.BASE_URL;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class UserService {
    
    
    
    ArrayList<User> users;
ArrayList<Affect> affects;
    public static UserService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public User User = new User();

    private UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {

        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User parseUser(String jsonText) {
   
        User UserL = new User();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if (UserListJson.get("type").equals("Login succeed")) {
                
                 float id = Float.parseFloat(UserListJson.get("id").toString());
                UserL.setId((int) (id));
                UserL.setUserName(UserListJson.get("userName").toString());
                UserL.setLastname(UserListJson.get("lastname").toString());
                UserL.setUserMail(UserListJson.get("userMail").toString());
                UserL.setUserDayOfBirth(UserListJson.get("userDayOfBirth").toString());
                UserL.setUserCin(UserListJson.get("userCin").toString());
                UserL.setUserPhone(UserListJson.get("userPhone").toString());
                UserL.setUserPhoto(UserListJson.get("userPhoto").toString());
                UserL.setUserPassword(UserListJson.get("userPassword").toString());
                UserL.setRoles(UserListJson.get("roles").toString());
                
    

            } else {
                return null;
            }

        } catch (IOException ex) {
                ex.getMessage();
        }

        return UserL;
    }

    public User Login(String username,String password) {
        String url =BASE_URL +"loginMobile/"+username+"/"+password;
        System.out.println("urlll ="+BASE_URL);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                User = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }

     public ArrayList<User> getAllUsers() {
        String url = Utils.BASE_URL + "users";
                        System.out.println("urlll ="+BASE_URL);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parsingUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
     
      public ArrayList<User> parsingUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User UserL = new User();
               
                 float id = Float.parseFloat(obj.get("id").toString());
                UserL.setId((int) (id));
                UserL.setUserName(obj.get("username").toString());
                UserL.setLastname(obj.get("lastname").toString());
                UserL.setUserMail(obj.get("userMail").toString());
                UserL.setUserDayOfBirth(obj.get("user_day_birth").toString());
                UserL.setUserCin(obj.get("user_cin").toString());
                UserL.setUserPhone(obj.get("user_phone").toString());
                UserL.setUserPhoto(obj.get("user_photo").toString());
                
                UserL.setRoles(obj.get("roles").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(UserL);

            }

        } catch (IOException ex) {

        }
        /*
         A ce niveau on a pu récupérer une liste des tâches à partir
         de la base de données à travers un service web
        
         */
        return users;
    }
    
      
       public ArrayList<Affect> parsingMUs(String jsonText) {
        try {
            affects = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Affect UserL = new Affect();
               
                 float idu = Float.parseFloat(obj.get("user_id").toString());
                UserL.setIdu((int) (idu));
                 float idm = Float.parseFloat(obj.get("meeting_id").toString());
                UserL.setIdm((int) (idm));
               
                affects.add(UserL);

            }

        } catch (IOException ex) {

        }
        /*
         A ce niveau on a pu récupérer une liste des tâches à partir
         de la base de données à travers un service web
        
         */
        return affects;
    }
      
          public ArrayList<Affect> getAllAffects() {
        String url = Utils.BASE_URL + "member";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                affects = parsingMUs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return affects;
    }
}
