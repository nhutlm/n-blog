/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nblog.model.HomeModel;
import nblog.model.Page;
import nblog.utility.Helper;
import nblos.handler.PostHandler;
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
@SessionAttributes("data")
public class HomeController {

    @RequestMapping(value = "/home.html", method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam(value = "page", required = false) String page, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HomeModel homeData = new HomeModel();
        if (page == null || page.isEmpty()) {
            homeData.currentPage = 0;
        } else {
            homeData.currentPage = Integer.valueOf(page);
        }
        return getHomeData(model, homeData);
    }

    @RequestMapping(value = "/home.html", method = RequestMethod.POST)
    public String doPost(ModelMap model, @ModelAttribute("data") HomeModel homeData, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (homeData.action.equalsIgnoreCase("next")) {
            homeData.currentPage++;
        } else if (homeData.action.equalsIgnoreCase("prev")) {
            homeData.currentPage--;
        }
        return getHomeData(model, homeData);
    }

    private String getHomeData(ModelMap model, HomeModel homeData) {
        PostHandler postHandler= new PostHandler();
        homeData.totalPage = postHandler.getTotalPost();
        homeData.postList = postHandler.getPostsPagination(homeData.currentPage);
        model.addAttribute("data", homeData);
        return Page.HOME_PAGE.value();
    }
}
