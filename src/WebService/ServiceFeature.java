/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package WebService;


import Entity.Feature;
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
public class ServiceFeature {
    
     public ArrayList<Feature> features;
    
    public static ServiceFeature instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
      private ServiceFeature() {
         req = new ConnectionRequest();
    }

    public static ServiceFeature getInstance() {
        if (instance == null) {
            instance = new ServiceFeature();
        }
        return instance;
    }

    
    
    public ArrayList<Feature> parseFeature(String jsonText){
        try {
           // tasks=new ArrayList<>();
            features=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              //  Task t = new Task();
                 Feature f = new Feature();
                
                float id = Float.parseFloat(obj.get("id_feature").toString());
                f.setId_feature((int)id);
                //pb.(((int)Float.parseFloat(obj.get("status").toString())));
               // t.setNom_theme(jsonText); e(obj.get("nom_theme").toString());
                
                f.setNom_feature(obj.get("nom_feature").toString());
                
                 float iduser = Float.parseFloat(obj.get("id_user_respensability").toString());
                f.setId_user_respensability((int)iduser);
                
                 float totalEstim = Float.parseFloat(obj.get("total_estimation_feature_jours").toString());
                f.setTotal_estimation_feature_jours((int)totalEstim);
                
                 float statue = Float.parseFloat(obj.get("statue").toString());
                f.setStatue((int)statue);
                
               
                features.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return features;
    }
    
    
   public ArrayList<Feature> getAllFeature(int idt){
     //   String url = Statics.BASE_URL+"/tasks/";
        String url = Utils.BASE_URL +"afficheeFeature/" +idt ;
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                features = parseFeature(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return features;
    }
    
    
     
     public boolean addFeature(Feature f) {
        String url = Utils.BASE_URL +"addfeature/" + f.getNom_feature()+ "/" + f.getId_theme();
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
