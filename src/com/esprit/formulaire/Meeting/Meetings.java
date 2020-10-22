/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Meeting;

import Entity.Meeting;
import Entity.User;
import WebService.MeetingClaimService;
import WebService.MeetingService;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;

import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.SideMenuBaseForm;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Meetings extends SideMenuBaseForm{
 
       Form current;
    public Meetings(User u,Resources res) {
        

          super( BoxLayout.y());
        Toolbar tb = getToolbar();
for (Meeting m:MeetingService.getInstance().getAllMeetings()){
                MultiButton mb = new MultiButton();
            mb.setTextLine1(m.getTitle());
            mb.setTextLine2(m.getType());
            mb.setIcon(res.getImage("calendarr.png").scaled(150, 150));
         mb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                                        new MeetingDetail(m,u, res).show();

                    
                }
            });
            add(mb);
            
}
revalidate();
        tb.addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    }else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
   
            
        }
        getContentPane().animateLayout(150);
    }
        }, 4);
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("bleu.png");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Meeting  ", "WelcomeBlue"),
                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
        
        
         if(u.getRoles().equals("[ROLE_MASTER, ROLE_USER]")|| u.getRoles().equals("[ROLE_PRODUCT_OWNER, ROLE_USER]")){
        Container B=new Container();
        
  Button add=new Button("Create New Meeting");
          add.setUIID("LoginButton");
add.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent evt) {
new AddMeeting(u,res).show();
                  System.out.println("hellooo!");
              }
          });
  B.add(add);
  add(B);
         }

        
        setupSideMenu(u,res);
        
     
    }


    
    @Override
    protected void showOtherForm(User u,Resources res) {

    }

   


    
    
}
