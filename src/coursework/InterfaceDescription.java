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
    public Instance USER_VIDEO_CAMERA;
    public Instance USER_AUDIO_COMPONENT;
    public Instance USER_MICROPHONE;
    public Instance USER_SPECIAL_DEVICE;
    public Instance USER_MAIN_DEVICE;
    public Instance USER_DISEASE_RANGE;
    public Instance USER_DISEASE;
    public Instance USER_AGE;
    public Instance CASEID;
    
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("CASEID", this.getClass());
    }
    public Instance getCASEID() {
        return CASEID;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Instance getUSER_VIDEO_CAMERA() {
        return USER_VIDEO_CAMERA;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getUSER_AUDIO_COMPONENT() {
        return USER_AUDIO_COMPONENT;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Instance getUSER_MICROPHONE() {
        return USER_MICROPHONE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getUSER_SPECIAL_DEVICE() {
        return USER_SPECIAL_DEVICE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getUSER_MAIN_DEVICE() {
        return USER_MAIN_DEVICE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Instance getUSER_DISEASE_RANGE() {
        return USER_DISEASE_RANGE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getUSER_DISEASE() {
        return USER_DISEASE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Instance getUSER_AGE() {
        return USER_AGE;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_VIDEO_CAMERA(Instance videoCamera) {
        USER_VIDEO_CAMERA = videoCamera;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_AUDIO_COMPONENT(Instance audioComponent) {
        USER_AUDIO_COMPONENT = audioComponent;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_MICROPHONE(Instance microphone) {
        USER_MICROPHONE = microphone;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_SPECIAL_DEVICE(Instance specialDevice) {
        USER_SPECIAL_DEVICE = specialDevice;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_MAIN_DEVICE(Instance mainDevice) {
        USER_MAIN_DEVICE = mainDevice;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setUSER_DISEASE_RANGE(Instance diseaseRange) {
        USER_DISEASE_RANGE = diseaseRange;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_DISEASE(Instance disease) {
        USER_DISEASE = disease;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUSER_AGE(Instance age) {
        USER_AGE = age;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCASEID(Instance caseId) {
        CASEID = caseId;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String toString() {
        return "("+ CASEID + ";"+ USER_AGE + ";"+ USER_AUDIO_COMPONENT 
                + ";"+ USER_DISEASE + ";"+ USER_DISEASE_RANGE + ";"+ USER_MAIN_DEVICE 
                + ";"+ USER_MICROPHONE + ";"+ USER_SPECIAL_DEVICE + ";"+ USER_VIDEO_CAMERA + ")";
    }
    
}
