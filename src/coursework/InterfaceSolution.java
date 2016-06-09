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
    public Instance RESULT;

    public Instance[] USER_INPUT_DATA_COMPONENT;
    public Instance[] USER_OUTPUT_DATA_COMPONENT;
    
    public Instance id;
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id", this.getClass());
    }
    public Instance getRESULT() {
        return RESULT;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRESULT(Instance result) {
        RESULT = result;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(Instance id) {
        this.id = id;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Instance getId() {
        return id;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String toString() {
        return "("+ id + ";"+ RESULT + ")";
    }
    
    public Instance[] getUSER_INPUT_DATA_COMPONENT() {
        return USER_INPUT_DATA_COMPONENT;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_INPUT_DATA_COMPONENT(Instance[] inputData) {
        USER_INPUT_DATA_COMPONENT = inputData;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Instance[] getUSER_OUTPUT_DATA_COMPONENT() {
        return USER_OUTPUT_DATA_COMPONENT;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_OUTPUT_DATA_COMPONENT(Instance[] outputData) {
        USER_OUTPUT_DATA_COMPONENT = outputData;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
