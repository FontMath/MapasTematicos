/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

/**
 *
 * @author 0160670
 */
public class Integrante {
    String ID;
    String Instancia;

    public String getID() {
        return ID;
    }

    public String getInstancia() {
        return Instancia;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setInstancia(String Instancia) {
        this.Instancia = Instancia;
    }

    public Integrante(String ID, String Instancia) {
        this.ID = ID;
        this.Instancia = Instancia;
    }
    
}
