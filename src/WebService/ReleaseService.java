/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.Issue;
import Entity.Meeting;
import Entity.Project;
import Entity.Release;
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
public class ReleaseService {

    public ArrayList<Release> release;
    public String result = "";

    public static ReleaseService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ReleaseService() {
        req = new ConnectionRequest();
    }

    public static ReleaseService getInstance() {
        if (instance == null) {
            instance = new ReleaseService();
        }
        return instance;
    }

    public ArrayList<Release> parseReleases(String jsonText) {
        try {
            release = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> releaseListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) releaseListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Release t = new Release();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setName(obj.get("name").toString());
                t.setDescription(obj.get("description").toString());
                t.setStartDate(obj.get("startDate").toString());
                t.setReleaseDate(obj.get("releaseDate").toString());
//                float project = Float.parseFloat(obj.get("project_id").toString());
//                t.setProject((int) project);


                //Ajouter la tâche extraite de la réponse Json à la liste
                release.add(t);

            }

        } catch (IOException ex) {

        }
        /*
         A ce niveau on a pu récupérer une liste des tâches à partir
         de la base de données à travers un service web
        
         */
        return release;
    }

    public ArrayList<Release> getAllReleases() {
        String url = Utils.BASE_URL + "Releases";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                release = parseReleases(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return release;
    }

    public boolean AddRelease(Release r) {
        String url = Utils.BASE_URL + "AddReleasesMobile/?type=" + r.getName() + "&description=" + r.getDescription() + "&startDate=" + r.getStartDate()+ "&releaseDate=" + r.getReleaseDate() + "&project_id=" + r.getProject();

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

    public String DeleteRelease(Release r) {
        String url = Utils.BASE_URL + "DeleteReleases/?id=" + r.getId();
        System.out.println("urlll =" + BASE_URL);

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> releaseListJson;
                    releaseListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) releaseListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public boolean EditIssue(Release r) {
        String url = Utils.BASE_URL + "EditReleases/?id=" + r.getId() + "&type=" + r.getName() + "&description=" + r.getDescription() + "&startDate=" + r.getStartDate()+ "&releaseDate=" + r.getReleaseDate() ;
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
