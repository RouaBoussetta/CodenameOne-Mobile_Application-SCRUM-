/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

/**
 *
 * @author Deku
 */
import Entity.DailyScrum;
import Entity.User;
import Utils.Utils;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import java.util.List;
import java.util.Map;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class DailyScrumService {
    
    public ArrayList<DailyScrum> dailyscrum;
    public String result = "";

    public static DailyScrumService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public DailyScrumService () {
        req = new ConnectionRequest();
    }

    public static DailyScrumService getInstance() {
        if (instance == null) {
            instance = new DailyScrumService ();
        }
        return instance;
    }
    
    
    public ArrayList<DailyScrum> parseDailys(String jsonText) {
        try {
            dailyscrum = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> dailysListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) dailysListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                DailyScrum d = new DailyScrum();
                float id = Float.parseFloat(obj.get("id_daily").toString());
                d.setId_daily((int) id);
             
        
                d.setTitle(obj.get("title").toString());

                d.setYesterdaywork(obj.get("yesterdaywork").toString());
                d.setTodayplan(obj.get("todayplan").toString());
           
                d.setBlockers(obj.get("blockers").toString());
                
                float brunt = Float.parseFloat(obj.get("hrsbrunt").toString());
                d.setHrsbrunt((int) brunt);
                
                float completed = Float.parseFloat(obj.get("hrscompleted").toString());
                d.setHrscompleted((int) completed);
                
                
                d.setTime_creation(obj.get("time_creation").toString());
                d.setDate_creation(obj.get("date_creation").toString());
                d.setTime_modification(obj.get("time_modification").toString());
                d.setDate_modification(obj.get("date_modification").toString());
                
                float type = Float.parseFloat(obj.get("id_type").toString());
                d.setId_type((int) type);
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                dailyscrum.add(d);

            }

        } catch (IOException ex) {

        }
  
        return dailyscrum;
    }
    
    public ArrayList<DailyScrum> getDailyScrums() {
        String url = Utils.BASE_URL + "dailyscrums";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                dailyscrum = parseDailys(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return dailyscrum;
    }
    
        public boolean AddDaily(DailyScrum d) {
        String url = Utils.BASE_URL + "AddDailyMobile/?title="+d.getTitle()+"&yesterdaywork="+d.getYesterdaywork()+"&todayplan="+d.getTodayplan()+"&blockers="+d.getBlockers()+"&hrsbrunt="+d.getHrsbrunt()+"&hrscompleted="+d.getHrscompleted()+"&date_creation="+d.getDate_creation()+"&time_creation="+d.getTime_creation()+"&date_modification="+d.getDate_modification()+"&time_modification="+d.getTime_modification(); //création de l'URL
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        
        System.out.println(""+req);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                 La ConnectionRequest req est unique pour tous les appels de 
                 n'importe quelle méthode du Service task, donc si on ne supprime
                 pas l'ActionListener il sera enregistré et donc éxécuté même si 
                 la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        
        public String DeleteDaily(DailyScrum d) {
        String url = Utils.BASE_URL + "deleteDailyMob/?idDaily=" + d.getId_daily();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
            
        public boolean EditDaily(DailyScrum d) {
        String url = Utils.BASE_URL + "EditDailyMobile/?idDaily="+d.getId_daily()+"&title="+d.getTitle()+"&yesterdaywork="+d.getYesterdaywork()+"&todayplan="+d.getTodayplan()+"&blockers="+d.getBlockers()+"&hrsbrunt="+d.getHrsbrunt()+"&hrscompleted="+d.getHrscompleted()+d.getTime_creation()+"&date_modification="+d.getDate_modification()+"&time_modification="+d.getTime_modification(); //création de l'URL
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        
        
    
}
