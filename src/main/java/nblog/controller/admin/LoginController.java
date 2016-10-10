/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nblog.model.UserModel;
import nblog.model.Page;
import nblos.handler.UserHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author nhutlm
 */
@SessionAttributes("data")
public class LoginController {

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String doGetLogin(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel lgnModel = new UserModel();
        model.addAttribute("data", lgnModel);
        return Page.LOGIN.value();
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String doPostLogin(ModelMap model, @ModelAttribute("data") UserModel lgnModel, HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserHandler userHandler = new UserHandler();
        lgnModel.result = userHandler.doLogin(request, lgnModel.user.userName, lgnModel.password);
        if (lgnModel.result.returnCode == 0) {
            return Page.REDIRECT_POST.value();
        }

        model.addAttribute("data", lgnModel);
        return Page.LOGIN.value();
    }

}
