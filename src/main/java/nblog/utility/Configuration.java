/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.utility;

import nblog.dac.DBHandleImplement;

/**
 *
 * @author nhutlm
 */
public class Configuration {

    public void init() throws Exception {
        if (Helper.getDbHandler() == null) {
            String username = "y1_master";
            String password = "12345?Abc";
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://y1cms.crja74daggdu.us-west-2.rds.amazonaws.com:3306/blogdb?characterEncoding=UTF8";
            int maxconn = 5;
            Helper.setDbHandler(new DBHandleImplement(username, password, driver, url, maxconn));
        }

    }

    public void destroy() throws Exception {

    }
}
