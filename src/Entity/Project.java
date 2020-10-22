/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Project {
    
     private int id;
    private String title;
    private String description;
    private String deadline;
    private String category;
    private String version;
    private String date_creation;
    private String time_creation;
   
    

    public Project() {
    }

    public Project(int id, String title, String description,  String deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
       // this.created = created;
        this.deadline = deadline;
    }

    public Project(int id, String title, String description, String deadline, String category, String version, String date_creation, String time_creation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.category = category;
        this.version = version;
        this.date_creation = date_creation;
        this.time_creation = time_creation;
    }
    public Project(String title, String description, String deadline, String category, String version, String date_creation) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.category = category;
        this.version = version;
        this.date_creation = date_creation;
        this.time_creation = time_creation;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getTime_creation() {
        return time_creation;
    }

    public void setTime_creation(String time_creation) {
        this.time_creation = time_creation;
    }


    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.deadline);
        hash = 83 * hash + Objects.hashCode(this.category);
        hash = 83 * hash + Objects.hashCode(this.version);
        hash = 83 * hash + Objects.hashCode(this.date_creation);
        hash = 83 * hash + Objects.hashCode(this.time_creation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.deadline, other.deadline)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.date_creation, other.date_creation)) {
            return false;
        }
        if (!Objects.equals(this.time_creation, other.time_creation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", title=" + title + ", description=" + description + ", deadline=" + deadline + ", category=" + category + ", version=" + version + ", date_creation=" + date_creation + ", time_creation=" + time_creation + '}';
    }

   
    
    
    
}
