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
import javax.servlet.http.HttpServletRequest;
import nblog.dtos.ResultDTO;
import nblog.dtos.UserDTO;
import nblog.utility.Helper;

/**
 *
 * @author nhutlm
 */
public class UserHandler {

    public UserDTO getUser(int idUser) {
        try {
            ResultSet rs = Helper.getDbHandler().getUserById(idUser);
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.userID = rs.getInt("Id");
                user.userName = rs.getString("UserName");
                user.fullName = rs.getString("FullName");
                user.userRole = rs.getString("Role");
                user.active = rs.getString("IsActive").equalsIgnoreCase("Y");
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<UserDTO> getUserByName(String keyword) {
        List<UserDTO> userList = new ArrayList();

        try {
            ResultSet rs = Helper.getDbHandler().getUserByName(keyword);
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.userID = rs.getInt("Id");
                user.userName = rs.getString("UserName");
                user.fullName = rs.getString("FullName");
                user.userRole = rs.getString("Role");
                user.active = rs.getString("IsActive").equalsIgnoreCase("Y");
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public ResultDTO deleteUser(int userID) {
        ResultDTO result = new ResultDTO();
        if (Helper.getDbHandler().deleteUser(userID) == 1) {
            result.setReturnCode(0);
            result.setMsg("Deleted successfully");
        } else {
            result.setErr("Something went wrong. Please try again");
        }
        return result;
    }

    public ResultDTO doLogin(HttpServletRequest request, String userName, String password) {

        ResultDTO result = new ResultDTO();
        try {
            ResultSet rs = Helper.getDbHandler().getUser(userName, "Y");
            while (rs.next()) {
                String hashPass = rs.getString("Password");
                try {
                    if (!hashPass.isEmpty() && hashPass.equals(Helper.encryptPassword(rs.getInt("Id") + password))) {
                        UserDTO user = new UserDTO();
                        user.userID = rs.getInt("Id");
                        user.userName = rs.getString("UserName");
                        user.fullName = rs.getString("FullName");
                        user.userRole = rs.getString("Role");
                        user.active = rs.getString("IsActive").equalsIgnoreCase("Y");
                        Helper.setUserSession(request, user);
                        result.returnCode = 0;
                        return result;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.setErr("Wrong username or password!");
        return result;
    }

    public ResultDTO updateUser(int userId, String userName, String fullName, boolean isActive, String password, String oldPass, boolean checkPass, String role) {
        ResultDTO result = new ResultDTO();
        try {
            if (checkPass) {
                if (!checkPassword(userName, oldPass)) {
                    result.setErr("Wrong password");
                    return result;
                }
            }
            if (Helper.getDbHandler().updateUser(userId, fullName, isActive, password.isEmpty() ? password : Helper.encryptPassword(userId + password)) == 1) {
                if (updateRole(userId, role).returnCode == 0) {
                    result.returnCode = 0;
                    result.setMsg("Your profile has been updated");
                    return result;
                }
            }
        } catch (Exception ex) {

        }
        result.setErr("Something went wrong. Please try again");
        return result;
    }

    public ResultDTO insertUser(String userName, String fullName, String password, boolean isActive, String role) {
        ResultDTO result = new ResultDTO();

        try {
            ResultSet rs = Helper.getDbHandler().getUser(userName, "");
            if (rs.next()) {
                result.setErr("That username is taken. Try another.");
                return result;
            }
            if (Helper.getDbHandler().insertUser(userName, fullName, "temp", isActive) == 1) {
                rs = Helper.getDbHandler().getUser(userName, "");
                while (rs.next()) {
                    UserDTO user = new UserDTO();
                    user.userID = rs.getInt("Id");
                    user.userName = rs.getString("UserName");
                    user.fullName = rs.getString("FullName");
                    user.userRole = rs.getString("Role");
                    user.active = rs.getString("IsActive").equalsIgnoreCase("Y");
                    if (insertRole(user.userID, role).returnCode == 0) {
                        if (updateUser(user.userID, user.userName, user.fullName, user.active, password, "", false, role).returnCode == 0) {
                            result.returnCode = 0;
                            result.setMsg("");
                            return result;
                        }
                    }

                }

            }
        } catch (Exception ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        result.setErr("Something went wrong. Please try again");
        return result;
    }

    public ResultDTO insertRole(int userID, String role) {
        ResultDTO result = new ResultDTO();
        try {
            if (Helper.getDbHandler().insertRole(userID, role) == 1) {
                result.returnCode = 0;
//                result.setMsg("Thêm mới thành công");
            }
        } catch (Exception ex) {

        }
        return result;
    }

    public ResultDTO updateRole(int userID, String role) {
        ResultDTO result = new ResultDTO();
        try {
            if (Helper.getDbHandler().updateRole(userID, role) == 1) {
                result.returnCode = 0;
            } else {
                if (Helper.getDbHandler().insertRole(userID, role) == 1) {
                    result.returnCode = 0;

                }
            }
        } catch (Exception ex) {

        }
        return result;
    }

    public boolean checkPassword(String userName, String password) {
        try {
            ResultSet rs = Helper.getDbHandler().getUser(userName, "Y");
            while (rs.next()) {
                String hashPass = rs.getString("Password");
                if (!hashPass.isEmpty() && hashPass.equals(Helper.encryptPassword(rs.getInt("Id") + password))) {
                    return true;
                }
            }
        } catch (Exception ex) {

        }
        return false;
    }

}
