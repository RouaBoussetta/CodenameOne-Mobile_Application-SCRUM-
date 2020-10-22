/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Backlog;

import Entity.UserStory;
import WebService.ServiceUserStory;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Lenovo
 */
public class afficheUserStory extends Form {
    
    public afficheUserStory (int idf,Form previous) {
        
        setTitle("List User Story");
        setLayout(BoxLayout.y());
       // Container InfoContainer = new Container();
      // Container c1 = new Container();
       //Container c2 = new Container();
        for (UserStory us  : ServiceUserStory.getInstance().getAllUserStory(idf)) 
        {
            Container InfoContainer = new Container(BoxLayout.y());
            Container InfoContainerr = new Container(BoxLayout.x());
            Label iddus = new Label(String.valueOf(us.getId_user_story()));  
            Label nom = new Label(us.getUser_story_description());
            Label description = new Label(String.valueOf(us.getTotal_estimation_userstory_jours()));  
            Label total = new Label(String.valueOf(us.getPriority()));  
            Label statue = new Label(String.valueOf(us.getDoing()));  
            
            Button btnUpdateUserStory = new Button("Update Progress User Story");
            
            
            btnUpdateUserStory.addActionListener(e-> new UpdateProgress(us.getId_user_story(),previous).show());
            
            InfoContainer.add(iddus);
            InfoContainer.add(nom);
            InfoContainer.add(description);
            InfoContainer.add(total);
            InfoContainer.add(statue);
            InfoContainerr.add(btnUpdateUserStory);
             InfoContainer.add(InfoContainerr);

           // c1.add(pb.getProductBacklog());
        
        //    c2.add(pb.getIdBacklog());
        
        

        
       // SpanLabel sp = new SpanLabel();
        //sp.setText(productBacklogService.getInstance().getAllProductBacklog().toString());
        
        
       add(InfoContainer);
        }
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    
}
