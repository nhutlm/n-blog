/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.dtos;

import java.io.Serializable;

/**
 *
 * @author nhutlm
 */
public class WarningDTO implements Serializable{

    /**
     * @return the warningCode
     */
    public String getWarningCode() {
        return warningCode;
    }

    /**
     * @param warningCode the warningCode to set
     */
    public void setWarningCode(String warningCode) {
        this.warningCode = warningCode;
    }

    /**
     * @return the warningMsg
     */
    public String getWarningMsg() {
        return warningMsg;
    }

    /**
     * @param warningMsg the warningMsg to set
     */
    public void setWarningMsg(String warningMsg) {
        this.warningMsg = warningMsg;
    }

    public String warningCode;
    public String warningMsg;
}
