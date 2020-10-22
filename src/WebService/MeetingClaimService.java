/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Entity.Claim;
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
public class MeetingClaimService {
    
    
      public ArrayList<Claim> claims;
    public String result = "";

    public static MeetingClaimService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public MeetingClaimService() {
        
                req = new ConnectionRequest();

    }
    
      public static MeetingClaimService getInstance() {
        if (instance == null) {
            instance = new MeetingClaimService();
        }
        return instance;
    }
      
          public ArrayList<Claim> parseClaims(String jsonText) {
        try {
            claims = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String, Object> claimsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
Meeting m=new Meeting();

            List<Map<String, Object>> list = (List<Map<String, Object>>) claimsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Claim t = new Claim();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                float user = Float.parseFloat(obj.get("user").toString());
                t.setUser((int) user);
                float meeting = Float.parseFloat(obj.get("meeting").toString());
                t.setMeeting((int) meeting);
                
                t.setName(obj.get("name").toString());
                t.setLastname(obj.get("lastname").toString());
                t.setMail(obj.get("mail").toString());
                t.setTel(obj.get("tel").toString());
                t.setAvailable(obj.get("available").toString());
                t.setOther(obj.get("other").toString());
                t.setReason(obj.get("reason").toString());
                t.setDate(obj.get("date").toString());
             

                claims.add(t);

            }

        } catch (IOException ex) {

        }
       
        return claims;
    }

    
    
 public ArrayList<Claim> getAllClaims() {
        String url = Utils.BASE_URL + "Claims";
                        System.out.println("urlll ="+BASE_URL);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                claims = parseClaims(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return claims;
    }
 
     public boolean AddClaim(Claim c) {
        String url = Utils.BASE_URL + "AddClaimMobile/?name="+c.getName()+"&lastname="+c.getLastname()+"&mail="+c.getMail()+"&tel="+c.getTel()+"&available="+c.getAvailable()+"&other="+c.getOther()+"&reason="+c.getReason()+"&date="+c.getDate()+"&meeting="+c.getMeeting()+"&user="+c.getUser(); //création de l'URL
                        System.out.println("urlll ="+url);

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
     
      public String DeleteClaim(Claim c) {
        String url = Utils.BASE_URL + "DeleteClaimMobile/?id=" + c.getId();
                        System.out.println("urlll ="+BASE_URL);

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> claimsListJson;
                    claimsListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) claimsListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

      
       public boolean EditClaim(Claim c) {
        String url = Utils.BASE_URL + "EditClaimMobile/?id="+c.getId()+"&name="+c.getName()+"&lastname="+c.getLastname()+"&mail="+c.getMail()+"&tel="+c.getTel()+"&availability="+c.getAvailable()+"&reason="+c.getReason()+"&other="+c.getOther()+"&date="+c.getDate(); //création de l'URL
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
