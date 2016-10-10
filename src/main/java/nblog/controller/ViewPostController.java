/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nblog.dtos.PostDTO;
import nblog.dtos.UserDTO;
import nblog.model.Page;
import nblog.model.ViewPostModel;
import nblog.utility.Configuration;
import nblog.utility.Helper;
import nblos.handler.PostHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author nhutlm
 */
@SessionAttributes("data")
public class ViewPostController {

    @RequestMapping(value = "/viewpost.html", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam(value = "id", required = false) String p_pid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (p_pid == null || p_pid.isEmpty()) {
            return Page.REDIRECT_HOME.value();
        }
        try {
            ViewPostModel postModel = new ViewPostModel();
            PostHandler postHandler = new PostHandler();
            postModel.post = postHandler.getPostDetail(Integer.parseInt(p_pid), false,null);
            model.addAttribute("data", postModel);
        } catch (Exception ex) {
            return Page.REDIRECT_HOME.value();
        }

        return Page.VIEW_POST.value();
    }
}
