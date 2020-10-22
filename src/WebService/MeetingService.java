/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.Meeting;
import Entity.Project;
import Utils.Utils;
import static Utils.Utils.BASE_URL;
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

/**
 *
 * @author Lenovo
 */
public class MeetingService {

    public ArrayList<Meeting> meetings;
    public String result = "";

    public static MeetingService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public MeetingService() {
        req = new ConnectionRequest();
    }

    public static MeetingService getInstance() {
        if (instance == null) {
            instance = new MeetingService();
        }
        return instance;
    }

    public ArrayList<Meeting> parseMeetings(String jsonText) {
        try {
            meetings = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> meetingsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) meetingsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Meeting t = new Meeting();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                t.setTitle(obj.get("title").toString());

                t.setGoal(obj.get("goal").toString());
                t.setIssues(obj.get("issues").toString());

                t.setType(obj.get("type").toString());
                t.setDate(obj.get("date").toString());
                t.setTime(obj.get("time").toString());
                t.setDuration(obj.get("duration").toString());
                t.setLocation(obj.get("location").toString());
                t.setOrganizedBy(obj.get("organizedBy").toString());

//              float project = Float.parseFloat(obj.get("project_id").toString());
//                t.setProject((int) project);

                //Ajouter la tâche extraite de la réponse Json à la liste
                meetings.add(t);

            }

        } catch (IOException ex) {

        }

        return meetings;
    }

    public ArrayList<Meeting> getAllMeetings() {
        String url = Utils.BASE_URL + "meetings";
        System.out.println("urlll =" + BASE_URL);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                meetings = parseMeetings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return meetings;
    }

    public boolean AddMeeting(Meeting m) {
        String url = Utils.BASE_URL + "AddMeetingMobile/?title=" + m.getTitle() + "&goal=" + m.getGoal() + "&issues=" + m.getIssues() + "&project_id=" + m.getProject() + "&type=" + m.getType() + "&date=" + m.getDate() + "&time=" + m.getTime() + "&duration=" + m.getDuration() + "&location=" + m.getLocation() + "&organizedBy=" + m.getOrganizedBy(); //création de l'URL
        System.out.println("urlll =" + BASE_URL);

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion

        System.out.println("" + req);
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

    public String DeleteMeeting(Meeting m) {
        String url = Utils.BASE_URL + "deleteMeetingMobile/?id=" + m.getId();
        System.out.println("urlll =" + BASE_URL);

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

    public boolean EditEvenement(Meeting m) {
        // String url = Utils.BASE_URL + "EditEventMobile/?id="+e.getId()+"&nom=" + e.getNom() + "&etat=" + e.getEtat() + "&nombreplaces=" + e.getNombreplaces(); //création de l'URL
        String url = Utils.BASE_URL + "EditMeetingMobile/?id=" + m.getId() + "&title=" + m.getTitle() + "&goal=" + m.getGoal() + "&issues=" + m.getIssues() + "&type=" + m.getType() + "&duration=" + m.getDuration() + "&location=" + m.getLocation(); //création de l'URL
        System.out.println("urlll =" + BASE_URL);
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

    public ArrayList<Meeting> countMeetings() {
        String url = Utils.BASE_URL + "totalmeeting";
        System.out.println("urlll =" + BASE_URL);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                meetings = parseMeetings(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return meetings;
    }

}
