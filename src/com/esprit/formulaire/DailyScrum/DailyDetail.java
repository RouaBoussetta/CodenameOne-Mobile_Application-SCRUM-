/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.DailyScrum;

import Entity.Affect;
import Entity.DailyScrum;
import Entity.Meeting;
import Entity.User;
import WebService.DailyScrumService;
import WebService.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
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
 * @author Deku
 */
public class DailyDetail extends SideMenuBaseForm{
    
public DailyDetail(DailyScrum d,User u, Resources res) {
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
                                new Label(d.getTitle(), "WelcomeBlue")
//                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
     ImageViewer img = new ImageViewer(res.getImage("file.png").scaled(500, 500));
        Label title = new Label("Daily File name :");
        Label work = new Label("Yesterday work:");
        Label today = new Label("Today plan:");
        Label blocker = new Label("Blockers:");
        Label brunt = new Label("Brunt-hours:");
        Label completed = new Label("Completed-Hours:");
        Label ctime = new Label("Created Time :");
        Label cdate=new Label("Created Date :");
        Label mtime = new Label("Modification Time :");
        Label mdate = new Label("Modification Date :");
        Label createdby = new Label("Created By :");

      
        /****conversion*****/
        
        String hrsbrunt = String.valueOf(d.getHrsbrunt());
        String hrscomp = String.valueOf(d.getHrscompleted());
        String username = u.getUserName();
        
       Label l1=new Label(d.getTitle());
       Label l2=new Label(d.getYesterdaywork());
       Label l3=new Label(d.getTodayplan());
       Label l4=new Label(d.getBlockers());
       Label l5=new Label(hrsbrunt);
       Label l6=new Label(hrscomp);
       Label l7=new Label(d.getTime_creation());
       Label l8=new Label(d.getDate_creation());
       Label l9=new Label(d.getTime_modification());
       Label modifDate=new Label(d.getDate_modification());
       Label l=new Label(u.getUserName());


        Container Container = new Container(BoxLayout.y());
        Container.addAll(title, l1, work, l2, today, l3, blocker,l4, brunt, l5, completed, l6, ctime, l7,cdate,l8,mtime, l9, mdate, modifDate,createdby, l);
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
            String result = DailyScrumService.getInstance().DeleteDaily(d);
            if (!result.equals("Error")) {
                Dialog.show("Success", result, new Command("OK"));
                new Dailys(u,res).showBack();
                 Message msg = new Message(d.getTitle()+"  was deleted now ");

Display.getInstance().sendMessage(new String[] {u.getUserMail()}, "Canceled meeting ", msg);
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });
        Edit.addActionListener(ev -> {
            
            new EditDailyScrum(d, u, res).show();
          
        });
       
        }
        


    }

    @Override
    protected void showOtherForm(User u, Resources res) {
        
    }
    
    
    
}
