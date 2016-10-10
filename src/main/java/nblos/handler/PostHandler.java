/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblos.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nblog.dac.DBHelper;
import nblog.dtos.PostDTO;
import nblog.dtos.ResultDTO;
import nblog.dtos.UserDTO;
import nblog.utility.Helper;

/**
 *
 * @author nhutlm
 */
public class PostHandler {

    public int getTotalPost() {
        return Helper.getDbHandler().getTotalPost();
    }

    public List<PostDTO> searchPosts(String keyword, UserDTO puser) {
        List<PostDTO> postList = new ArrayList();

        try {
            ResultSet rs;
            if (puser.userRole.equalsIgnoreCase("ADMIN")) {
                rs = Helper.getDbHandler().searchPosts(keyword);
            } else {
                rs = Helper.getDbHandler().searchPostsByUser(keyword, puser.userID);
            }

            while (rs.next()) {
                PostDTO post = new PostDTO();
                post.postId = rs.getInt("Id");
                post.postTitle = rs.getString("Title");
                post.postSubTitle = rs.getString("SubTitle");
                post.postBody = rs.getString("Body");
                post.dateCreated = rs.getTimestamp("DateCreated");
                post.dateModified = rs.getTimestamp("DateModified");
                UserDTO user = new UserDTO();
                user.userID = rs.getInt("userID");
                user.fullName = rs.getString("Fullname");
                user.userName = rs.getString("UserName");
                post.postAuthor = user;
                post.isPublished = rs.getString("IsPublished");
                postList.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return postList;
    }

    public PostDTO getPostDetail(int postId, boolean getAll, UserDTO puser) {
        PostDTO post = null;

        try {
            ResultSet rs;
            if (puser==null || puser.userRole.equalsIgnoreCase("ADMIN")) {
                rs = Helper.getDbHandler().getPostDetail(postId, getAll);
            } else {
                rs = Helper.getDbHandler().getPostDetailByUser(postId, getAll, puser.userID);
            }
            while (rs.next()) {
                post = new PostDTO();
                post.postId = rs.getInt("Id");
                post.postTitle = rs.getString("Title");
                post.postSubTitle = rs.getString("SubTitle");
                post.postBody = rs.getString("Body");
                post.dateCreated = rs.getTimestamp("DateCreated");
                post.dateModified = rs.getTimestamp("DateModified");
                UserDTO user = new UserDTO();
                user.userID = rs.getInt("userID");
                user.fullName = rs.getString("Fullname");
                user.userName = rs.getString("UserName");
                post.postAuthor = user;
                post.isPublished = rs.getString("IsPublished");
                break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PostHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

    public List<PostDTO> getPostsPagination(int pageNo) {
        List<PostDTO> postList = new ArrayList();
        try {
            ResultSet rs = Helper.getDbHandler().getPostsPagination(pageNo);
            while (rs.next()) {
                PostDTO post = new PostDTO();
                post.postId = rs.getInt("Id");
                post.postTitle = rs.getString("Title");
                post.postSubTitle = rs.getString("SubTitle");
                post.postBody = rs.getString("Body");
                post.dateCreated = rs.getTimestamp("DateCreated");
                post.dateModified = rs.getTimestamp("DateModified");
                UserDTO user = new UserDTO();
                user.userID = rs.getInt("userID");
                user.fullName = rs.getString("Fullname");
                user.userName = rs.getString("UserName");
                post.postAuthor = user;
                postList.add(post);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return postList;
    }

    public ResultDTO updatePost(PostDTO post) {
        ResultDTO result = new ResultDTO();
        if (Helper.getDbHandler().updatePost(post.postAuthor.userID, post.postTitle, post.postSubTitle, post.imgUrl, post.postBody, post.isPublished, post.postId) == 1) {
            result.setReturnCode(0);
            result.setMsg("successfully updated");
        } else {
            result.setErr("Something went wrong. Please try again");
        }
        return result;
    }

    public ResultDTO deletePost(int postID) {
        ResultDTO result = new ResultDTO();
        if (Helper.getDbHandler().deletePost(postID) == 1) {
            result.setReturnCode(0);
            result.setMsg("Deleted successfully");
        } else {
            result.setErr("Something went wrong. Please try again");
        }
        return result;
    }

    public ResultDTO insertPost(PostDTO post) {
        ResultDTO result = new ResultDTO();
        if (Helper.getDbHandler().insertPost(post.postAuthor.userID, post.postTitle, post.postSubTitle, post.imgUrl, post.postBody, post.isPublished) == 1) {
            result.setReturnCode(0);
            result.setMsg("uccessfully updated");
        } else {
            result.setErr("Something went wrong. Please try again");
        }
        return result;
    }
}
