/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Lenovo
 */
public class Theme {
 int    id_theme;
 String nom_theme ;
 int   total_estimation_theme_jours;
 int    id_backlog;

    public Theme() {
    }

    public Theme(String nom_theme, int id_backlog) {
        this.nom_theme = nom_theme;
        this.id_backlog = id_backlog;
    }
    

    public Theme(int id_theme, String nom_theme, int total_estimation_theme_jours) {
        this.id_theme = id_theme;
        this.nom_theme = nom_theme;
        this.total_estimation_theme_jours = total_estimation_theme_jours;
    }
    

    public Theme(String nom_theme, int total_estimation_theme_jours, int id_backlog) {
        this.nom_theme = nom_theme;
        this.total_estimation_theme_jours = total_estimation_theme_jours;
        this.id_backlog = id_backlog;
    }

    public Theme(int id_theme, String nom_theme, int total_estimation_theme_jours, int id_backlog) {
        this.id_theme = id_theme;
        this.nom_theme = nom_theme;
        this.total_estimation_theme_jours = total_estimation_theme_jours;
        this.id_backlog = id_backlog;
    }

    public int getId_theme() {
        return id_theme;
    }

    public void setId_theme(int id_theme) {
        this.id_theme = id_theme;
    }

    public String getNom_theme() {
        return nom_theme;
    }

    public void setNom_theme(String nom_theme) {
        this.nom_theme = nom_theme;
    }

    public int getTotal_estimation_theme_jours() {
        return total_estimation_theme_jours;
    }

    public void setTotal_estimation_theme_jours(int total_estimation_theme_jours) {
        this.total_estimation_theme_jours = total_estimation_theme_jours;
    }

    public int getId_backlog() {
        return id_backlog;
    }

    public void setId_backlog(int id_backlog) {
        this.id_backlog = id_backlog;
    }

    @Override
    public String toString() {
        return "Theme{" + "id_theme=" + id_theme + ", nom_theme=" + nom_theme + ", total_estimation_theme_jours=" + total_estimation_theme_jours + ", id_backlog=" + id_backlog + '}';
    }
 
}
