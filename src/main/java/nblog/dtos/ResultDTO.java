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
public class ResultDTO implements Serializable {

    /**
     * @return the returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the errors
     */
    public ErrorDTO[] getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(ErrorDTO[] errors) {
        this.errors = errors;
    }

    /**
     * @return the warnings
     */
    public WarningDTO[] getWarnings() {
        return warnings;
    }

    /**
     * @param warnings the warnings to set
     */
    public void setWarnings(WarningDTO[] warnings) {
        this.warnings = warnings;
    }

    /**
     * @return the resultMsgs
     */
    public ResultMsgDTO[] getResultMsgs() {
        return resultMsgs;
    }

    /**
     * @param resultMsgs the resultMsgs to set
     */
    public void setResultMsgs(ResultMsgDTO[] resultMsgs) {
        this.resultMsgs = resultMsgs;
    }

    public int returnCode = -1;
    public ErrorDTO[] errors;
    public WarningDTO[] warnings;
    public ResultMsgDTO[] resultMsgs;

    public void setMsg(String msg) {
        ResultMsgDTO rmsg = new ResultMsgDTO();
        rmsg.msg = msg;
        setResultMsgs(new ResultMsgDTO[]{rmsg});
    }

    public void setErr(String msg) {
        ErrorDTO errDTO = new ErrorDTO();
        errDTO.errorMsg = msg;
        errors = new ErrorDTO[]{errDTO};
    }

}
