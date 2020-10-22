/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Backlog;

import Entity.Theme;
import WebService.ServiceTheme;
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
public class afficheTheme extends Form {
    
   
     public afficheTheme (int idpb,Form previous) {
         setLayout(BoxLayout.y());
        setTitle("List tasks");
       // Container InfoContainer = new Container();
       Container c1 = new Container();
       Container c2 = new Container();
        for (Theme t  : ServiceTheme.getInstance().getAllTheme(idpb)) 
        {
            Container InfoContainer = new Container(BoxLayout.y());
            Container InfoContainerr = new Container(BoxLayout.x());
            Label nom = new Label(t.getNom_theme());
            Label description = new Label(String.valueOf(t.getTotal_estimation_theme_jours()));   
            
            
            Button btnReadFeature = new Button("Read  Feature");
            btnReadFeature.addActionListener(e-> new afficheFeature(t.getId_theme(),previous).show());
            
              Button btnAddFeature = new Button("Add  Feature");
            btnAddFeature.addActionListener(e-> new addFeauture(t.getId_theme(),previous).show());
            
             InfoContainer.add(nom);
            InfoContainer.add(description);
            InfoContainerr.add(btnReadFeature);
            InfoContainerr.add(btnAddFeature);
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
