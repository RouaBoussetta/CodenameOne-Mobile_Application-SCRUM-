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
public class product_backlog {
    
      public int idBacklog;
    public String ProductBacklog;

    public product_backlog() {
    }

    public product_backlog(String ProductBacklog) {
        this.ProductBacklog = ProductBacklog;
    }

    public product_backlog(int idBacklog, String ProductBacklog) {
        this.idBacklog = idBacklog;
        this.ProductBacklog = ProductBacklog;
    }

    public int getIdBacklog() {
        return idBacklog;
    }

    public void setIdBacklog(int idBacklog) {
        this.idBacklog = idBacklog;
    }

    public String getProductBacklog() {
        return ProductBacklog;
    }

    public void setProductBacklog(String ProductBacklog) {
        this.ProductBacklog = ProductBacklog;
    }

    @Override
    public String toString() {
        return "product_backlog{" + "idBacklog=" + idBacklog + ", ProductBacklog=" + ProductBacklog + '}';
    }
    
    
}
