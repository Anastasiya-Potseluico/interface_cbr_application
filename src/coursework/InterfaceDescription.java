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
public class InterfaceDescription implements jcolibri.cbrcore.CaseComponent {
    public Instance CARCASSESTYPE;
    public Instance MARK;
    public Instance TRACK;
    public Instance WEATHER;
    public Instance TIRESTYPE;
    public Instance CASEID;
    
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("CASEID", this.getClass());
    }
    public Instance getCASEID() {
        return CASEID;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Instance getCARCASSESTYPE() {
        return CARCASSESTYPE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getTIRESTYPE() {
        return TIRESTYPE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Instance getMARK() {
        return MARK;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getTRACK() {
        return TRACK;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getWEATHER() {
        return WEATHER;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMARK(Instance mark) {
        MARK = mark;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setWEATHER(Instance weather) {
        WEATHER = weather;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTIRESTYPE(Instance tirestype) {
        TIRESTYPE = tirestype;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCARCASSESTYPE(Instance carcasessestype) {
        CARCASSESTYPE = carcasessestype;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTRACK(Instance track) {
        TRACK = track;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCASEID(Instance caseId) {
        CASEID = caseId;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String toString() {
        return "("+ CASEID + ";"+ TRACK + ";"+ MARK + ";"+ TIRESTYPE + ";"+ CARCASSESTYPE + ";"+ WEATHER + ")";
    }
    
}
