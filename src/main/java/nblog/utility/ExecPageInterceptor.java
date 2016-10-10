/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nblog.dtos.UserDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author nhutlm
 */
public class ExecPageInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletpath = request.getServletPath();
        if (request.getMethod().equalsIgnoreCase("GET")
                && servletpath.equals("/login.html")) {
            request.getSession(true).invalidate();
        }
        if (servletpath.contains("/admin/")) {
            UserDTO userSession = Helper.getUserSession(request);
            if (userSession == null) {
                response.sendRedirect(request.getContextPath() + "/login.html");
                return false;
            }
            //Check permission
            if (!userSession.userRole.equalsIgnoreCase("ADMIN") && (servletpath.contains("/admin/users.html")
                    || servletpath.contains("/admin/userdetail.html"))) {
                response.setStatus(403);
                //return false;
            }
        }
        return true;
    }
}
