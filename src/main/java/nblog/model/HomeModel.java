/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import nblog.dtos.PostDTO;

/**
 *
 * @author nhutlm
 */
public class HomeModel extends BasePage {

    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the postList
     */
    public List<PostDTO> getPostList() {
        return postList;
    }

    /**
     * @param postList the postList to set
     */
    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }

    public List<PostDTO> postList;
    public int currentPage;
    public int totalPage;

    public HomeModel() {
        currentPage = 0;
    }
}
