/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.model;

/**
 *
 * @author nhutlm
 */
public enum Page {
    HOME_PAGE("home"),
    REDIRECT_HOME("redirect:/home.html"),
    VIEW_POST("viewpost"),
    SETTING("setting"),
    POSTS("posts"),
    POST_DETAIL("postdetail"),
    REDIRECT_POST("redirect:/admin/posts.html"),
    PROFILE("profile"),
    
    USER_DETAIL("userdetail"),
    USERS("users"),
    REDIRECT_USERS("redirect:/admin/users.html"),
    
    LOGIN("login");
//    REDIRECT_ADMIN("redirect:/admin/setting.html"),;
    private String pageValue;

    Page(String value) {
        pageValue = value;
    }
     public String value() {
        return pageValue;
    }
}
