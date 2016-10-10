/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import nblog.dac.DBHandleInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nblog.dtos.UserDTO;

/**
 *
 * @author nhutlm
 */
public class Helper {

    private static DBHandleInterface dbHandler;
    public static final int MAX_POST_PER_PAGE = 5;

    /**
     * @param aDbHandler the dbHandler to set
     */
    public static void setDbHandler(DBHandleInterface aDbHandler) {
        dbHandler = aDbHandler;
    }

    /**
     * @return the dbHandler
     */
    public static DBHandleInterface getDbHandler() {
        return dbHandler;
    }

    private static Object getObjectSession(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(true);
        return session.getAttribute(name);
    }

    public static UserDTO getUserSession(HttpServletRequest request) {
        return (UserDTO) Helper.getObjectSession(request, UserDTO.class.getName());
    }

    public static UserDTO setUserSession(HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(UserDTO.class.getName());
        session.setAttribute(UserDTO.class.getName(), user);
        session.setMaxInactiveInterval((int) user.getTimeout());
        return user;
    }

    public static String encryptPassword(String plainText) throws Exception {
        String md5 = null;

        if (null == plainText) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(plainText.getBytes(), 0, plainText.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }   
}
