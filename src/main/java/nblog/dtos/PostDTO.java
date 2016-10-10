/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author nhutlm
 */
public class PostDTO implements Serializable{

    /**
     * @return the isPublished
     */
    public String getIsPublished() {
        return isPublished;
    }

    /**
     * @param isPublished the isPublished to set
     */
    public void setIsPublished(String isPublished) {
        this.isPublished = isPublished;
    }

    /**
     * @return the postSubTitle
     */
    public String getPostSubTitle() {
        return postSubTitle;
    }

    /**
     * @param postSubTitle the postSubTitle to set
     */
    public void setPostSubTitle(String postSubTitle) {
        this.postSubTitle = postSubTitle;
    }

    /**
     * @return the postId
     */
    public int getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * @return the postAuthor
     */
    public UserDTO getPostAuthor() {
        return postAuthor;
    }

    /**
     * @param postAuthor the postAuthor to set
     */
    public void setPostAuthor(UserDTO postAuthor) {
        this.postAuthor = postAuthor;
    }

    /**
     * @return the postTitile
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * @param postTitile the postTitile to set
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    /**
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return the postBody
     */
    public String getPostBody() {
        return postBody;
    }

    /**
     * @param postBody the postBody to set
     */
    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the dateModified
     */
    public Date getDateModified() {
        return dateModified;
    }

    /**
     * @param dateModified the dateModified to set
     */
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public int postId;
    public UserDTO postAuthor;
    public String postTitle;
    public String postSubTitle;
    public String imgUrl;
    public String postBody;
    public Date dateCreated;
    public Date dateModified;
    public String isPublished;
    
}
