/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Backlog;

import Entity.Feature;
import WebService.ServiceFeature;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Lenovo
 */
public class afficheFeature extends Form {
    
    
    public afficheFeature (int idt,Form previous) {
        
        setTitle("List Feature");
        setLayout(BoxLayout.y());
       // Container InfoContainer = new Container();
       Container c1 = new Container();
       Container c2 = new Container();
        for (Feature f  : ServiceFeature.getInstance().getAllFeature(idt)) 
        {
            Container InfoContainer = new Container(BoxLayout.y());
            Container InfoContainerr = new Container(BoxLayout.x());
            //BoxLayout InfoContainerRight = new BoxLayout(BoxLayout.Y_AXIS);
            Label nomf = new Label(f.getNom_feature());
            Label description = new Label(String.valueOf(f.getId_user_respensability()));  
            
            
         /*    if  (f.getId_user_respensability()==0){
            Developpers dev=   ServiceDeveloppers.getInstance().finduser(f.getId_user_respensability());
            Label FirstNameDev = new Label(dev.getFirstname());
            Label LastNameDev = new Label(dev.getLastname());}
           
            */
            
          
           
            Label total = new Label(String.valueOf(f.getTotal_estimation_feature_jours()));  
            Label statue = new Label(String.valueOf(f.getStatue()));  
            
            Button btnReadUserStory = new Button("Read User Story");
            btnReadUserStory.addActionListener(e-> new afficheUserStory(f.getId_feature(),previous).show());
            
              Button btnAddUserStory = new Button("Add User Story" );
            btnAddUserStory.addActionListener(e-> new addUserStory(f.getId_feature(),previous).show());
            
             Button btnprogress = new Button("Progress" );
            btnprogress.addActionListener(e-> new progress(f.getId_feature(),previous).show());
            
                Button btnaffect = new Button("Affect developper" );
//            btnaffect.addActionListener(e-> new affectDevelopperToFeature(f.getId_feature(),previous).show());
            
            InfoContainer.add(nomf);
            InfoContainer.add(description);
//             if  (f.getId_user_respensability()!=0){
//            Developpers dev=   ServiceDeveloppers.getInstance().finduser(f.getId_user_respensability());
//            Label FirstNameDev = new Label(dev.getFirstname());
//            Label LastNameDev = new Label(dev.getLastname());
//                InfoContainer.add(FirstNameDev);
//            InfoContainer.add(LastNameDev);
//             }
           
          
            InfoContainer.add(total);
            InfoContainer.add(statue);
          //  if (role == maser)
            InfoContainerr.add(btnReadUserStory);
            InfoContainerr.add(btnAddUserStory);
             InfoContainerr.add(btnprogress);
              InfoContainerr.add(btnaffect);
           // c1.add(pb.getProductBacklog());
        InfoContainer.add(InfoContainerr);
        //    c2.add(pb.getIdBacklog());
        
        

        
       // SpanLabel sp = new SpanLabel();
        //sp.setText(productBacklogService.getInstance().getAllProductBacklog().toString());
     /*   
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button name = new Button("", "Label");
    name.addActionListener(e -> Log.p("You clicked: " ));
    // ...
    Container b = BorderLayout.center(cnt).add(BorderLayout.EAST);
    b.add(name);
    
    
        */
        
        
       add(InfoContainer);
        }
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
