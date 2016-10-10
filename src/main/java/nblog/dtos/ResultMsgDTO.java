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
public class ResultMsgDTO implements Serializable{

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the msgCode
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * @param msgCode the msgCode to set
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
    public String msg;
    public String msgCode;
}
