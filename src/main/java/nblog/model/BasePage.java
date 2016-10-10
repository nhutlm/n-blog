/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.model;

import java.io.Serializable;
import nblog.dtos.ResultDTO;

/**
 *
 * @author nhutlm
 */
public class BasePage implements Serializable{

    /**
     * @return the pageImgURL
     */
    public String getPageImgURL() {
        return pageImgURL;
    }

    /**
     * @param pageImgURL the pageImgURL to set
     */
    public void setPageImgURL(String pageImgURL) {
        this.pageImgURL = pageImgURL;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
    public String pageImgURL;
    public String action;
    public ResultDTO result;

    /**
     * @return the result
     */
    public ResultDTO getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(ResultDTO result) {
        this.result = result;
    }
}
