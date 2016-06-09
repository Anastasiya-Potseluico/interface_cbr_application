/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import jcolibri.cbrcore.Attribute;
import jcolibri.datatypes.Instance;

/**
 *
 * @author Анастасия
 */
public class InterfaceSolution implements jcolibri.cbrcore.CaseComponent {
    public Instance USER_RESULT;
    
    public Instance id;
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id", this.getClass());
    }
    public Instance getRESULT() {
        return USER_RESULT;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRESULT(Instance result) {
        USER_RESULT = result;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(Instance id) {
        this.id = id;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Instance getId() {
        return id;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String toString() {
        return "("+ id + ";"+ USER_RESULT + ")";
    }
}
