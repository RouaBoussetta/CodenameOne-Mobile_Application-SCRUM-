/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.Feature;
import Entity.Theme;
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
public class ServiceTheme {
    
    
    
    
   // public ArrayList<Task> tasks;
    //public ArrayList<product_backlog> pbs;
    public ArrayList<Theme> themes;
    
    public static ServiceTheme instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTheme() {
         req = new ConnectionRequest();
    }

    public static ServiceTheme getInstance() {
        if (instance == null) {
            instance = new ServiceTheme();
        }
        return instance;
    }

    public boolean addTheme(Theme t) {
        String url = Utils.BASE_URL +"addtheme/" + t.getNom_theme()+ "/" + t.getId_backlog();
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

    public ArrayList<Theme> parseThemes(String jsonText){
        try {
           // tasks=new ArrayList<>();
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
    
    public ArrayList<Theme> getAllTheme(int idpb){
     //   String url = Statics.BASE_URL+"/tasks/";
        String url = Utils.BASE_URL +"afficheeTheme/" +idpb ;
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                themes = parseThemes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return themes;
    }
    
}
