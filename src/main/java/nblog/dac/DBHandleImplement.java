/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.dac;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nblog.dtos.PostDTO;
import nblog.dtos.ResultDTO;
import nblog.dtos.UserDTO;
import nblog.utility.Helper;

/**
 *
 * @author nhutlm
 */
public class DBHandleImplement implements DBHandleInterface {

    private static BoneCP CONN_POOL;
    public Connection connection = null;

    private static final String INSERT_POST = "INSERT INTO posts(AuthorID, Title, SubTitle, Image, Body, DateCreated,  IsPublished) VALUES(?,?,?,?,?,SYSDATE(),?)";
    private static final String UPDATE_POST = "update posts set AuthorID=?, Title=?, SubTitle=?, Image=?, Body=?,  DateModified=SYSDATE(), IsPublished=? where Id =?";
    private static final String COUNT_TOTAL_POST = "select count(*) as total from posts where IsPublished='Y' and ShowOnHome='Y'";
    private static final String SELECT_USER = "select u.*,r.Role FROM users u left join userroles r on u.Id = r.UserId where u.UserName = ? and u.IsActive LIKE ?";
    private static final String SELECT_USER_BY_ID = "select u.*,r.Role FROM users u left join userroles r on u.Id = r.UserId where  u.Id = ?";
    private static final String SELECT_USER_BY_NAME = "select u.*,r.Role FROM users u left join userroles r on u.Id = r.UserId where u.UserName like ? or u.FullName like ?";
    private static final String DELET_POST = "delete from posts where Id=?";
    private static final String DELET_USER = "delete from users where Id=?";
    private static final String UPDATE_USER = "update users set FullName=?,IsActive=? where Id=?";
    private static final String UPDATE_USER_WITH_PASS = "update users set FullName=?,IsActive=?,Password=? where Id=?";
    private static final String INSERT_USER = "INSERT INTO users(UserName,FullName,Password,IsActive) values (?,?,?,?)";
    private static final String INSERT_ROLE = "insert into userroles (UserId,Role) Values (?,?)";
    private static final String UPDATE_ROLE = "update userroles set Role = ? where UserId=?";

    public DBHandleImplement(String username, String password, String driver, String url, int maxconn) throws Exception {
        Class.forName(driver);
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(maxconn);
        config.setPartitionCount(2);
        config.setConnectionTimeoutInMs(10000);
        config.setIdleConnectionTestPeriodInMinutes(3);
        config.setIdleMaxAgeInSeconds(30);
        config.setLogStatementsEnabled(false);
        CONN_POOL = new BoneCP(config);
    }

    public Connection getConnection() throws Exception {
        return (java.sql.Connection) CONN_POOL.getConnection();
    }

    @Override
    public int updatePost(int userID, String postTitle, String postSubTitle, String imgUrl, String postBody, String isPublished, int postId) {
        try {
            ArrayList arr = new ArrayList();
            arr.add(userID);
            arr.add(postTitle);
            arr.add(postSubTitle);
            arr.add(imgUrl);
            arr.add(postBody);
            arr.add(isPublished);
            arr.add(postId);
            return DBHelper.executeUpdate(UPDATE_POST, arr, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -99;
    }

    @Override
    public int insertPost(int userID, String postTitle, String postSubTitle, String imgUrl, String postBody, String isPublished) {
        try {
            ArrayList arr = new ArrayList();
            arr.add(userID);
            arr.add(postTitle);
            arr.add(postSubTitle);
            arr.add(imgUrl);
            arr.add(postBody);

            arr.add(isPublished);
            return DBHelper.executeUpdate(INSERT_POST, arr, getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -99;
    }

    @Override
    public int getTotalPost() {
        try {
            ResultSet rs = DBHelper.executeQuery(COUNT_TOTAL_POST, null, getConnection());
            while (rs.next()) {
//                return (int) Math.ceil((double) rs.getInt("total") / MAX_POST_PER_PAGE);
                return rs.getInt("total") / Helper.MAX_POST_PER_PAGE;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public ResultSet searchPosts(String keyword) {
        ArrayList arrs = new ArrayList();
        String query = "select p.*,u.UserName,u.FullName,u.Id as userID FROM posts p inner join users u on p.AuthorID=u.Id";
        if (keyword != null && !keyword.isEmpty()) {
            query += " where p.Title like ?";
            arrs.add("%" + keyword + "%");
        }
        query += " order by p.DateCreated desc";
        try {
            return DBHelper.executeQuery(query, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ResultSet searchPostsByUser(String keyword, int userID) {
        ArrayList arrs = new ArrayList();
        String query = "select p.*,u.UserName,u.FullName,u.Id as userID FROM posts p inner join users u on p.AuthorID=u.Id where p.AuthorID=?";
        arrs.add(userID);
        if (keyword != null && !keyword.isEmpty()) {
            query += " and p.Title like ?";
            arrs.add("%" + keyword + "%");
        }
        query += " order by p.DateCreated desc";
        try {
            return DBHelper.executeQuery(query, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int deletePost(int postID) {
        try {
            ArrayList arr = new ArrayList();
            arr.add(postID);
            return DBHelper.executeUpdate(DELET_POST, arr, getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -99;

    }

    @Override
    public ResultSet getPostsPagination(int pageNo) {
        ArrayList arrs = new ArrayList();
        String query = "select p.*,u.UserName,u.FullName,u.Id as userID FROM posts p inner join users u on p.AuthorID=u.Id where  p.IsPublished='Y' and p.ShowOnHome='Y' order by p.DateCreated desc";
        if (pageNo >= 0) {
            query = query + " limit ?, ?";
            arrs.add(pageNo * Helper.MAX_POST_PER_PAGE);
            arrs.add(Helper.MAX_POST_PER_PAGE);
        }
        try {
            return DBHelper.executeQuery(query, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ResultSet getPostDetail(int postId, boolean getAll) {

        String query = "select p.*,u.UserName,u.FullName,u.Id as userID FROM posts p inner join users u on p.AuthorID=u.Id where p.Id=?";
        if (!getAll) {
            query += " and p.IsPublished='Y'";
        }
        ArrayList arrs = new ArrayList();
        arrs.add(postId);
        try {
            return DBHelper.executeQuery(query, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ResultSet getPostDetailByUser(int postId, boolean getAll, int userID) {

        String query = "select p.*,u.UserName,u.FullName,u.Id as userID FROM posts p inner join users u on p.AuthorID=u.Id where p.Id=? and p.AuthorID=?";
        if (!getAll) {
            query += " and p.IsPublished='Y'";
        }
        ArrayList arrs = new ArrayList();
        arrs.add(postId);
        arrs.add(userID);
        try {
            return DBHelper.executeQuery(query, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ResultSet getUser(String userName, String activeStatus) {
        ArrayList arrs = new ArrayList();
        arrs.add(userName);
        if (activeStatus != null && !activeStatus.isEmpty()) {
            arrs.add(activeStatus);
        } else {
            arrs.add("%");
        }
        try {
            return DBHelper.executeQuery(SELECT_USER, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public ResultSet getUserById(int userID) {
        ArrayList arrs = new ArrayList();
        arrs.add(userID);
        try {
            return DBHelper.executeQuery(SELECT_USER_BY_ID, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ResultSet getUserByName(String keyword) {
        ArrayList arrs = new ArrayList();
        if (keyword != null && !keyword.isEmpty()) {
            arrs.add("%" + keyword + "%");
            arrs.add("%" + keyword + "%");
        } else {
            arrs.add("%");
            arrs.add("%");
        }
        try {
            return DBHelper.executeQuery(SELECT_USER_BY_NAME, arrs, getConnection());

        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int deleteUser(int userID) {
        try {
            ArrayList arr = new ArrayList();
            arr.add(userID);
            return DBHelper.executeUpdate(DELET_USER, arr, getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -99;
    }

    @Override
    public int updateUser(int userId, String fullName, boolean isActive, String password) {
        try {
            String sql = UPDATE_USER;
            ArrayList arrs = new ArrayList();
            arrs.add(fullName);
            arrs.add(isActive ? "Y" : "N");
            if (password != null && !password.isEmpty()) {
                arrs.add(password);
                sql = UPDATE_USER_WITH_PASS;
            }
            arrs.add(userId);
            return DBHelper.executeUpdate(sql, arrs, getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -99;

    }

    @Override
    public int insertUser(String userName, String fullName, String password, boolean isActive) {
        try {
            ArrayList arr = new ArrayList();
            arr.add(userName);
            arr.add(fullName);
            arr.add(password);
            arr.add(isActive ? "Y" : "N");
            return DBHelper.executeUpdate(INSERT_USER, arr, getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -99;
    }

    @Override
    public int insertRole(int userID, String role) {
        try {
            ArrayList arr = new ArrayList();
            arr.add(userID);
            arr.add(role);
            return DBHelper.executeUpdate(INSERT_ROLE, arr, getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -99;
    }

    @Override
    public int updateRole(int userID, String role) {
        try {
            ArrayList arr = new ArrayList();
            arr.add(role);
            arr.add(userID);
            return DBHelper.executeUpdate(UPDATE_ROLE, arr, getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -99;

    }
}
