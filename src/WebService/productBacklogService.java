/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.product_backlog;
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
public class productBacklogService {
    
     public ArrayList<product_backlog> pbs;
    
     public static productBacklogService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
     private productBacklogService() {
         req = new ConnectionRequest();
    }

    public static productBacklogService getInstance() {
        if (instance == null) {
            instance = new productBacklogService();
        }
        return instance;
    }
    
    
    
    
    
    
    
    
    
   /*   public ArrayList<Theme> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            themes=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              //  Task t = new Task();
                 Theme t = new Theme();
                
                float id = Float.parseFloat(obj.get("id_theme").toString());
                t.setId_theme((int)id);
                //pb.(((int)Float.parseFloat(obj.get("status").toString())));
               // t.setNom_theme(jsonText); e(obj.get("nom_theme").toString());
                
                t.setNom_theme(obj.get("nom_theme").toString());
                
                 float totalEstim = Float.parseFloat(obj.get("total_estimation_theme_jours").toString());
                t.setTotal_estimation_theme_jours((int)totalEstim);
                
               
                themes.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return themes;
    }
    
    
    
    */
    
    
    
    
    
    
    
    
    public ArrayList<product_backlog> parseProductBacklog(String jsonText){
       
        
        
        try {
            //tasks=new ArrayList<>();
            pbs=new ArrayList<>();
         
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          //  Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              //  Task t = new Task();
                 product_backlog pb = new product_backlog();
                
                float id = Float.parseFloat(obj.get("idBacklog").toString());
                pb.setIdBacklog((int)id);
                //pb.(((int)Float.parseFloat(obj.get("status").toString())));
               // t.setNom_theme(jsonText); e(obj.get("nom_theme").toString());
                
                pb.setProductBacklog(obj.get("ProductBacklog").toString());
                
                 
                
               
                pbs.add(pb);
            }
            
            
        } catch (IOException ex) {
            
        }
        return pbs;
    }
    
    
     public ArrayList<product_backlog> getAllProductBacklog(){
     //   String url = Statics.BASE_URL+"/tasks/";
        String url = Utils.BASE_URL+"affichee";
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pbs = parseProductBacklog(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return pbs;
    }
    
    
    
    
    
}
