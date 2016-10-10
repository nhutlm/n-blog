/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.model;

import java.util.List;
import nblog.dtos.PostDTO;

/**
 *
 * @author nhutlm
 */
public class PostModel extends BasePage{

    /**
     * @return the selectedPost
     */
    public int getSelectedPost() {
        return selectedPost;
    }

    /**
     * @param selectedPost the selectedPost to set
     */
    public void setSelectedPost(int selectedPost) {
        this.selectedPost = selectedPost;
    }
    public String searchKeyword;
    public List<PostDTO> postList;
    public int selectedPost;
    /**
     * @return the searchKeyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * @param searchKeyword the searchKeyword to set
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
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
}
