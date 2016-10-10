/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.model;

import java.util.List;
import nblog.dtos.UserDTO;

/**
 *
 * @author nhutlm
 */
public class UserModel extends BasePage {

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the userList
     */
    public List<UserDTO> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    /**
     * @return the confirmpassword
     */
    public String getConfirmpassword() {
        return confirmpassword;
    }

    /**
     * @param confirmpassword the confirmpassword to set
     */
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * @return the newpassword
     */
    public String getNewpassword() {
        return newpassword;
    }

    /**
     * @param newpassword the newpassword to set
     */
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    /**
     * @return the newUser
     */
    public boolean isNewUser() {
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
    public UserDTO user;
    public String password;
    public String newpassword;
    public String confirmpassword;
    public boolean newUser = true;
    public List<UserDTO> userList;
    public String keyword;
    public int selectedUserId;

    /**
     * @return the selectedUserId
     */
    public int getSelectedUserId() {
        return selectedUserId;
    }

    /**
     * @param selectedUserId the selectedUserId to set
     */
    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }
}
