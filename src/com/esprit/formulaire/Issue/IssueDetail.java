/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Issue;

import Entity.Issue;
import Entity.Project;
import Entity.User;
import WebService.IssueService;
import WebService.ProjectService;
import com.codename1.components.ImageViewer;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.Project.EditProject;
import com.esprit.formulaire.Project.Projects;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class IssueDetail extends SideMenuBaseForm {

    public IssueDetail(Issue i, User u, Resources res) {

        super(BoxLayout.y());
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
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH,
                        FlowLayout.encloseIn(
                                new Label("Issue "+i.getId(), "WelcomeBlue")
                        //                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);

        ImageViewer img = new ImageViewer(res.getImage("issue.png").scaled(500, 500));
        Label type = new Label("Type :");
        Label description = new Label("Description");
        Label summary = new Label("Summary :");
        Label priority = new Label("Priority :");
        Label status = new Label("Status :");
        Label project = new Label("project :");

        Label typee = new Label(i.getType());
        Label descriptionn = new Label(i.getDescription());
        Label summaryy = new Label(i.getSummary());
        Label priorityy = new Label(i.getPriority());
        Label statuss = new Label(i.getStatus());
        Label projectt = new Label(String.valueOf(i.getProject()));
        


        Container Container = new Container(BoxLayout.y());
        Container.addAll(type, typee, description, descriptionn, summary, summaryy, priority, priorityy, status, statuss, project, projectt);
        add(img);
        add(Container);

        Container ButtonsContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        add(ButtonsContainer);
        revalidate();
        Delete.addActionListener(ev -> {
            String result = IssueService.getInstance().DeleteIssue(i);
            if (!result.equals("Error")) {
                Dialog.show("Success", result, new Command("OK"));
                new Issues(u, res).showBack();

            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });
        Edit.addActionListener(ev -> {

            new EditIssue(i, u, res).show();

        });

    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }

}
