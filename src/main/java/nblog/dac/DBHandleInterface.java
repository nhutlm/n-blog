/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.dac;

import java.sql.ResultSet;

import nblog.dtos.PostDTO;

/**
 *
 * @author nhutlm
 */
public interface DBHandleInterface {

    public int updatePost(int userID, String postTitle, String postSubTitle, String imgUrl, String postBody, String isPublished, int postId);

    public int insertPost(int userID, String postTitle, String postSubTitle, String imgUrl, String postBody, String isPublished);

    public int getTotalPost();

    public int deletePost(int postID);

    public ResultSet searchPosts(String keyword);

    public ResultSet searchPostsByUser(String keyword, int userID);

    public ResultSet getPostsPagination(int pageNo);

    public ResultSet getPostDetail(int postId, boolean getAll);

    public ResultSet getPostDetailByUser(int postId, boolean getAll, int userID);

    public ResultSet getUser(String userName, String activeStatus);

    public ResultSet getUserById(int userID);

    public ResultSet getUserByName(String keyword);

    public int updateUser(int userId, String fullName, boolean isActive, String password);

    public int deleteUser(int userID);

    public int insertUser(String userName, String fullname, String password, boolean isActive);

    public int insertRole(int userID, String role);

    public int updateRole(int userID, String role);
}
