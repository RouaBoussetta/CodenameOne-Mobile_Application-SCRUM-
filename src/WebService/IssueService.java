/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.Issue;
import Entity.Meeting;
import Entity.Project;
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
public class IssueService {
    
    public ArrayList<Issue> issues;
    public String result = "";

    public static IssueService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private IssueService() {
        req = new ConnectionRequest();
    }

    public static IssueService getInstance() {
        if (instance == null) {
            instance = new IssueService();
        }
        return instance;
    }

    public ArrayList<Issue> parseIssues(String jsonText) {
        try {
            issues = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> issuesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) issuesListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Issue t = new Issue();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setType(obj.get("type").toString());
                t.setDescription(obj.get("description").toString());
                t.setSummary(obj.get("summary").toString());
                t.setPriority(obj.get("priority").toString());
                t.setStatus(obj.get("status").toString());
  

               

                //Ajouter la tâche extraite de la réponse Json à la liste
                issues.add(t);

            }

        } catch (IOException ex) {

        }
        /*
         A ce niveau on a pu récupérer une liste des tâches à partir
         de la base de données à travers un service web
        
         */
        return issues;
    }

    public ArrayList<Issue> getAllIssues() {
        String url = Utils.BASE_URL + "issues";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                issues = parseIssues(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return issues;
    }

    public boolean AddIssues(Issue p) {
        String url = Utils.BASE_URL + "AddIssueMobile/?type="+p.getType()+"&description="+p.getDescription()+"&summary="+p.getSummary()+"&priority="+p.getPriority()+"&status="+p.getStatus()+"&project_id="+p.getProject();

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
    
     public String DeleteIssue(Issue p) {
        String url = Utils.BASE_URL + "DeleteIssueMobile/?id=" + p.getId();
                        System.out.println("urlll ="+BASE_URL);

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> issueListJson;
                    issueListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) issueListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
     
      public boolean EditIssue(Issue p) {
        String url = Utils.BASE_URL + "EditIssues/?id="+p.getId()+"&type="+p.getType()+"&description="+p.getDescription()+"&summary="+p.getSummary()+"&priority="+p.getPriority()+"&status="+p.getStatus();
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
