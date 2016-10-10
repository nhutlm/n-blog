/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.model;

import nblog.dtos.PostDTO;

/**
 *
 * @author nhutlm
 */
public class ViewPostModel extends BasePage{

    /**
     * @return the textBody
     */
    public String getTextBody() {
        return textBody;
    }

    /**
     * @param textBody the textBody to set
     */
    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    /**
     * @return the publish
     */
    public boolean isPublish() {
        return publish;
    }

    /**
     * @param publish the publish to set
     */
    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    /**
     * @return the isNewPost
     */
    public boolean isIsNewPost() {
        return isNewPost;
    }

    /**
     * @param isNewPost the isNewPost to set
     */
    public void setIsNewPost(boolean isNewPost) {
        this.isNewPost = isNewPost;
    }

    /**
     * @return the post
     */
    public PostDTO getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(PostDTO post) {
        this.post = post;
    }
    public PostDTO post;
    public boolean isNewPost = true;
    public boolean publish;
    public String textBody;
}
