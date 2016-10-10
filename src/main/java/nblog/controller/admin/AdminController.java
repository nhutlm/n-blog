/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nblog.dtos.PostDTO;
import nblog.model.UserModel;
import nblog.model.Page;
import nblog.model.PostModel;
import nblog.model.ViewPostModel;

import nblog.utility.Helper;
import nblos.handler.PostHandler;
import nblos.handler.UserHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author nhutlm
 */
@RequestMapping("/admin/*")
@SessionAttributes("data")
public class AdminController {

    @RequestMapping(value = "/setting.html", method = RequestMethod.GET)
    public String doGetSetting(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return Page.SETTING.value();
    }

    @RequestMapping(value = "/posts.html", method = RequestMethod.GET)
    public String doGetAllPost(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PostModel postModel = new PostModel();
        PostHandler postHander = new PostHandler();
        postModel.postList = postHander.searchPosts("",Helper.getUserSession(request));
        model.addAttribute("data", postModel);
        return Page.POSTS.value();
    }

    @RequestMapping(value = "/posts.html", method = RequestMethod.POST)
    public String doPostSearchPost(ModelMap model, @ModelAttribute("data") PostModel postModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        postModel.result = null;
        PostHandler postHander = new PostHandler();
        if (postModel.action.equalsIgnoreCase("delete")) {
            postModel.result = postHander.deletePost(postModel.selectedPost);
        }

        postModel.postList = postHander.searchPosts(postModel.searchKeyword,Helper.getUserSession(request));
        postModel.action = "";
        model.addAttribute("data", postModel);
        return Page.POSTS.value();
    }

    @RequestMapping(value = "/postdetail.html", method = RequestMethod.GET)
    public String doGetPostDetail(ModelMap model, @RequestParam(value = "id", required = false) String Id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViewPostModel viewPostModel = new ViewPostModel();
        try {
            int postId = Integer.parseInt(Id);
            PostHandler postHander = new PostHandler();
            viewPostModel.post = postHander.getPostDetail(postId, true,Helper.getUserSession(request));
            if (viewPostModel.post != null) {
                viewPostModel.publish = viewPostModel.post.isPublished.equalsIgnoreCase("Y");
                viewPostModel.isNewPost = false;
            } else {
                viewPostModel.post = new PostDTO();
            }
        } catch (Exception ex) {
        }

        model.addAttribute("data", viewPostModel);
        return Page.POST_DETAIL.value();
    }

    @RequestMapping(value = "/postdetail.html", method = RequestMethod.POST)
    public String doPostPostDetail(ModelMap model, @ModelAttribute("data") ViewPostModel viewPostModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        viewPostModel.post.isPublished = viewPostModel.publish ? "Y" : "N";
        PostHandler postHander = new PostHandler();
        if (!viewPostModel.isNewPost) {
            viewPostModel.result = postHander.updatePost(viewPostModel.post);
        } else {
            viewPostModel.post.postAuthor = Helper.getUserSession(request);
            viewPostModel.result = postHander.insertPost(viewPostModel.post);
            if (viewPostModel.result.returnCode == 0) {
                return Page.REDIRECT_POST.value();
            }
        }
        model.addAttribute("data", viewPostModel);
        return Page.POST_DETAIL.value();
    }

    @RequestMapping(value = "/userdetail.html", method = RequestMethod.GET)
    public String doGetUserDetail(ModelMap model, @RequestParam(value = "id", required = false) String Id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel userModel = new UserModel();
        if (Id != null && !Id.isEmpty()) {
            try {
                int userId = Integer.parseInt(Id);
                UserHandler userHandler = new UserHandler();
                userModel.user = userHandler.getUser(userId);
                userModel.newUser = userModel.user == null;
            } catch (Exception ex) {
            }
        }
        model.addAttribute("data", userModel);
        return Page.USER_DETAIL.value();
    }

    @RequestMapping(value = "/userdetail.html", method = RequestMethod.POST)
    public String doPostUserDetail(ModelMap model, @ModelAttribute("data") UserModel userModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserHandler userHandler = new UserHandler();
        if (!userModel.newUser) {
            userModel.result = userHandler.updateUser(userModel.user.userID, userModel.user.userName, userModel.user.fullName, userModel.user.active, userModel.password, userModel.password, false, userModel.user.userRole);
        } else {
            userModel.result = userHandler.insertUser(userModel.user.userName, userModel.user.fullName, userModel.password, userModel.user.active, userModel.user.userRole);
            if (userModel.result.returnCode == 0) {
                return Page.REDIRECT_USERS.value();
            }
        }
        model.addAttribute("data", userModel);
        return Page.USER_DETAIL.value();
    }

    @RequestMapping(value = "/profile.html", method = RequestMethod.GET)
    public String doGetProfile(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel loginModel = new UserModel();
        loginModel.user = Helper.getUserSession(request);
        model.addAttribute("data", loginModel);
        return Page.PROFILE.value();
    }

    @RequestMapping(value = "/profile.html", method = RequestMethod.POST)
    public String doGetProfile(ModelMap model, @ModelAttribute("data") UserModel loginModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserHandler userHandler = new UserHandler();
        if (!loginModel.password.isEmpty()) {
            if (!loginModel.confirmpassword.equals(loginModel.newpassword)) {
                loginModel.result.setErr("Password does not match the confirm password");
            } else {
                loginModel.result = userHandler.updateUser(loginModel.user.userID, loginModel.user.userName, loginModel.user.fullName, loginModel.user.active, loginModel.newpassword, loginModel.password, true, loginModel.user.userRole);
            }
        } else {
            loginModel.result = userHandler.updateUser(loginModel.user.userID, loginModel.user.userName, loginModel.user.fullName, loginModel.user.active, loginModel.newpassword, loginModel.password, false, loginModel.user.userRole);
        }

        model.addAttribute("data", loginModel);
        return Page.PROFILE.value();
    }

    @RequestMapping(value = "/users.html", method = RequestMethod.GET)
    public String doGetAllUser(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel userModel = new UserModel();
        UserHandler userHandler = new UserHandler();
        userModel.userList = userHandler.getUserByName("");
        model.addAttribute("data", userModel);
        return Page.USERS.value();
    }

    @RequestMapping(value = "/users.html", method = RequestMethod.POST)
    public String doPostSearchUsers(ModelMap model, @ModelAttribute("data") UserModel userModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userModel.result = null;
        UserHandler userHandler = new UserHandler();
        if (userModel.action.equalsIgnoreCase("delete")) {
            userModel.result = userHandler.deleteUser(userModel.selectedUserId);
        }

        userModel.userList = userHandler.getUserByName(userModel.keyword);
        userModel.action = "";
        model.addAttribute("data", userModel);
        return Page.USERS.value();
    }
}
