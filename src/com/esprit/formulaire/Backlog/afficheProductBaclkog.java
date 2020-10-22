/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Backlog;

import Entity.product_backlog;
import WebService.productBacklogService;
import com.codename1.components.SpanLabel;
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
public class afficheProductBaclkog extends Form {
    
    public afficheProductBaclkog(Form previous) {
        int x=1;
        setTitle("List tasks");
       // Container InfoContainer = new Container();
        for (product_backlog pb  : productBacklogService.getInstance().getAllProductBacklog()) 
        {
            Container InfoContainer = new Container(BoxLayout.y());
            Container InfoContainerr = new Container(BoxLayout.x());
            Label nom = new Label(pb.getProductBacklog());
           // Label description = new Label(String.valueOf(pb.getIdBacklog())); 
        //  BorderLayout bor =new BorderLayout().
            Button btnAddTask = new Button("Add Theme");
             btnAddTask.addActionListener(e-> new addTheme(pb.getIdBacklog(),previous).show());
             Button btnReadTheme = new Button("Read Theme");
             btnReadTheme.addActionListener(e-> new afficheTheme(pb.getIdBacklog(),previous).show());
             
             btnReadTheme.setIcon(FontImage.createMaterial(FontImage.MATERIAL_OPEN_IN_NEW, btnReadTheme.getUnselectedStyle()));
             
             InfoContainer.add(nom);
           // InfoContainer.add(description);
           
            if (x==1){
                
            InfoContainerr.add(btnAddTask);}
            InfoContainerr.add(btnReadTheme);
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
