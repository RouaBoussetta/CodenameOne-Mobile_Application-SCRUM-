/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Meeting;

import com.esprit.formulaire.Meeting.Meetings;
import Entity.Affect;
import Entity.Meeting;
import Entity.Session;
import Entity.User;
import WebService.MeetingService;
import WebService.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class MeetingDetail extends SideMenuBaseForm{

     
    public MeetingDetail(Meeting m,User u, Resources res) {
      super( BoxLayout.y());
        Toolbar tb = getToolbar();
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
                                new Label(m.getTitle(), "WelcomeBlue")
//                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
     ImageViewer img = new ImageViewer(res.getImage("calendarr.png").scaled(500, 500));
        Label title = new Label("Meeting title :");
        Label goal = new Label("Goal:");
        Label issues = new Label("Issues:");
        Label type = new Label("Type:");
        Label project = new Label("Project:");
        Label date = new Label("Date:");
        Label time = new Label("Time :");
        Label Member=new Label("Members :");
        Label duration = new Label("Duration :");
        Label location = new Label("Location :");

      
       Label l1=new Label(m.getTitle());
       Label l2=new Label(m.getGoal());
       Label l3=new Label(m.getIssues());
       Label l4=new Label(m.getType());
       Label l6=new Label(m.getDate());
       Label l7=new Label(m.getTime());
       Label l8=new Label(m.getDuration());
       Label l9=new Label(m.getLocation());
              Label l0=new Label();

        for (Affect a : UserService.getInstance().getAllAffects()) {
            
            l0.setText(String.valueOf(a.getIdu()));
        }
        

    
     
        Container Container = new Container(BoxLayout.y());
        Container.addAll(title, l1, goal, l2, issues, l3, type,l4,  date, l6, time, l7,Member,l0, duration, l8, location, l9);
        add(img);
        add(Container);
        setupSideMenu(u,res);

        
        if(u.getRoles().equals("[ROLE_MASTER, ROLE_USER]")|| u.getRoles().equals("[ROLE_PRODUCT_OWNER, ROLE_USER]")){
            
                Container ButtonsContainer = new Container(new FlowLayout());

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        add(ButtonsContainer);
        revalidate();
        Delete.addActionListener(ev -> {
            String result = MeetingService.getInstance().DeleteMeeting(m);
            if (!result.equals("Error")) {
                Dialog.show("Success", result, new Command("OK"));
                new Meetings(u,res).showBack();
                 Message msg = new Message(m.getTitle()+"  was deleted now ");

Display.getInstance().sendMessage(new String[] {u.getUserMail()}, "Canceled meeting ", msg);
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });
        Edit.addActionListener(ev -> {
            
            new EditMeeting(m, u, res).show();
          
        });
       
        }
        


    }

    @Override
    protected void showOtherForm(User u, Resources res) {
        
    }
    
    
    
}
