/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.UserStory;
import Utils.Utils;
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
public class ServiceUserStory {
    
     public ArrayList<UserStory> userStories;
    
    public static ServiceUserStory instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
      private ServiceUserStory() {
         req = new ConnectionRequest();
    }

    public static ServiceUserStory getInstance() {
        if (instance == null) {
            instance = new ServiceUserStory();
        }
        return instance;
    }
    
    
    
      public ArrayList<UserStory> parseUserStory(String jsonText){
        try {
           // tasks=new ArrayList<>();
            userStories=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              //  Task t = new Task();
                 UserStory us = new UserStory();
                
                float id = Float.parseFloat(obj.get("id_user_story").toString());
                us.setId_user_story((int)id);
                //pb.(((int)Float.parseFloat(obj.get("status").toString())));
               // t.setNom_theme(jsonText); e(obj.get("nom_theme").toString());
                
                us.setUser_story_description(obj.get("user_story_description").toString());
                
                 float doing = Float.parseFloat(obj.get("doing").toString());
                us.setDoing((int)doing);
                
                 float totalEstim = Float.parseFloat(obj.get("total_estimation_userstory_jours").toString());
                us.setTotal_estimation_userstory_jours((int)totalEstim);
                
                 float priory = Float.parseFloat(obj.get("priority").toString());
                us.setPriority((int)priory);
                
            
                
               
                userStories.add(us);
            }
            
            
        } catch (IOException ex) {
            
        }
        return userStories;
    }
    
    
      
       public ArrayList<UserStory> getAllUserStory(int idf){
     //   String url = Statics.BASE_URL+"/tasks/";
        String url = Utils.BASE_URL +"afficheeUser/" +idf ;
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                userStories = parseUserStory(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return userStories;
    }
    
    
     
      public boolean addUserStory(UserStory us) {
        String url = Utils.BASE_URL +"addUserStory/" + us.getUser_story_description()+ "/" + us.getTotal_estimation_userstory_jours()+ "/" + us.getPriority()+ "/" + us.getId_feature();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
      
      
      
      
      
      
      
      
       public boolean UpdateProgressUserStory(int idus , int newValue ) {
        String url = Utils.BASE_URL +"upUserStory/" + idus+ "/" +  newValue;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
      
      
      
      
      
      
}
