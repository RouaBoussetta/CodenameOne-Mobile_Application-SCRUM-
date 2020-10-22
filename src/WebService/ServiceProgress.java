/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.progressData;
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
public class ServiceProgress {
    
    
     public ArrayList<progressData> pro;
     
     public progressData pd;
    
     public static ServiceProgress instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
     private ServiceProgress() {
         req = new ConnectionRequest();
    }

    public static ServiceProgress getInstance() {
        if (instance == null) {
            instance = new ServiceProgress();
        }
        return instance;
    }
    
    
     public ArrayList<progressData> parseProgress(String jsonText){
        try {
            //tasks=new ArrayList<>();
            pro=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("totaldone: "+tasksListJson.get("totaldone")); 
            System.out.println("lust: "+tasksListJson.get("todo"));
            
           
          //  String x=  tasksListJson.get("todo").toString();
            progressData pb = new progressData();
           
            
            
             float id = Float.parseFloat(tasksListJson.get("totaldone").toString());
            pb.setTotaldone((int)id);
            
             float idd = Float.parseFloat(tasksListJson.get("todo").toString());
            pb.setTodo((int)idd); 
            
         
            
            
         //   Map<String,Object> list = ( Map<String,Object>)tasksListJson.get("root");
           // for(Map<String,Object> obj : list){
              //  Task t = new Task();
            //  System.out.println("lissst:"+list );
         
                     
               
                pro.add(pb);
          //  }
            
            
        } catch (IOException ex) {
            
        }
        return pro;
    }
    
     
     
     
     
          public progressData getAllprogress(int idf){
             
       String url = Utils.BASE_URL+"processFeature/" +idf ;;
      //  String url = Statics.BASE_URL;
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pro = parseProgress(new String(req.getResponseData()));
                  pd = pro.get(0);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return pd;
    }

     
     
     
     
    
    
}
