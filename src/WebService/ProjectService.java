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
public class ProjectService {
    
    public ArrayList<Project> projects;
    public String result = "";

    public static ProjectService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ProjectService() {
        req = new ConnectionRequest();
    }

    public static ProjectService getInstance() {
        if (instance == null) {
            instance = new ProjectService();
        }
        return instance;
    }

    public ArrayList<Project> parseProjects(String jsonText) {
        try {
            projects = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> projectsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) projectsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Project t = new Project();
                float id = Float.parseFloat(obj.get("idProject").toString());
                t.setId((int) id);
                t.setTitle(obj.get("projectTitle").toString());
                t.setDescription(obj.get("description").toString());
                t.setDate_creation(obj.get("date_creation").toString());
                t.setTime_creation(obj.get("time_creation").toString());
                t.setDeadline(obj.get("deadLine").toString());
                t.setCategory(obj.get("category").toString());
                t.setVersion(obj.get("version").toString());

               

                //Ajouter la tâche extraite de la réponse Json à la liste
                projects.add(t);

            }

        } catch (IOException ex) {

        }
        /*
         A ce niveau on a pu récupérer une liste des tâches à partir
         de la base de données à travers un service web
        
         */
        return projects;
    }

    public ArrayList<Project> getAllProjects() {
        String url = Utils.BASE_URL + "projects";
                               System.out.println("urlll ="+BASE_URL);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                projects = parseProjects(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return projects;
    }

    public boolean AddProject(Project p) {
        String url = Utils.BASE_URL + "AddProjectMobile/?projectTitle="+p.getTitle()+"&description="+p.getDescription()+"&deadline="+p.getDeadline()+"&category="+p.getCategory()+"&version="+p.getVersion()+"&date_creation="+p.getDate_creation()+"&time_creation="+p.getTime_creation();

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
    
     public String DeleteProject(Project p) {
        String url = Utils.BASE_URL + "DeleteProjectMobile/?idProject=" + p.getId();
                        System.out.println("urlll ="+BASE_URL);

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> projectListJson;
                    projectListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) projectListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
     
      public boolean EditProject(Project p) {
        String url = Utils.BASE_URL + "EditProjectMobile/?idProject="+p.getId()+"&projectTitle="+p.getTitle()+"&description="+p.getDescription()+"&deadline="+p.getDeadline()+"&category="+p.getCategory()+"&version="+p.getVersion();
                System.out.println("urlll ="+BASE_URL);
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
