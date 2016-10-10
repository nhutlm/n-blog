/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.dac;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author nhutlm
 */
public class DBHelper {

    public static ResultSet executeQuery(String sql, ArrayList arrs, Connection connection) throws Exception {
        ResultSet l_rs = null;
        PreparedStatement l_stmt = null;
        try {
            l_stmt = connection.prepareStatement(sql);

            setArguments(l_stmt, arrs);
            l_rs = l_stmt.executeQuery();
            return l_rs;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {

            try {
                connection.close();
            } catch (Exception e) {

            }
            l_stmt = null;
        }
    }

    public static int executeUpdate(String sql, ArrayList arrs, Connection connection) throws Exception {
        PreparedStatement l_stmt = null;
        try {
            l_stmt = connection.prepareStatement(sql);

            setArguments(l_stmt, arrs);
            return l_stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                l_stmt.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {

            }
            l_stmt = null;
        }
    }

    private static void setArguments(PreparedStatement p_stmt, ArrayList<?> p_args) throws Exception {
        Object l_obj;
        if (p_args == null) {
            return;
        }
        for (int l_i = 0; l_i < p_args.size(); l_i++) {
            l_obj = p_args.get(l_i);

            if (l_obj == null) {
                p_stmt.setNull(l_i + 1, Types.NULL);
                continue;
            }

            if ((l_obj instanceof Double)) {
                p_stmt.setDouble(l_i + 1, ((Double) l_obj));

            } else if ((l_obj instanceof Integer)) {
                p_stmt.setInt(l_i + 1, ((Integer) l_obj));

            } else if ((l_obj instanceof Long)) {
                p_stmt.setLong(l_i + 1, ((Long) l_obj));

            } else if ((l_obj instanceof BigDecimal)) {
                p_stmt.setBigDecimal(l_i + 1, (BigDecimal) l_obj);

            } else if ((l_obj instanceof String)) {
                p_stmt.setString(l_i + 1, (String) l_obj);

            } else if ((l_obj instanceof java.sql.Date)) {
                p_stmt.setDate(l_i + 1, (java.sql.Date) l_obj);

            } else if ((l_obj instanceof Timestamp)) {
                p_stmt.setTimestamp(l_i + 1, (Timestamp) l_obj);

            } else if ((l_obj instanceof java.util.Date)) {
                p_stmt.setDate(l_i + 1, new java.sql.Date(((java.util.Date) l_obj).getTime()));

            } else if ((l_obj instanceof byte[])) {

                if (((byte[]) l_obj).length <= 4000) {
                    p_stmt.setBytes(l_i + 1, (byte[]) l_obj);
                } else {
                    ByteArrayInputStream l_bip = null;
                    l_bip = new ByteArrayInputStream((byte[]) l_obj);
                    p_stmt.setBinaryStream(l_i + 1, l_bip, ((byte[]) l_obj).length);
                }

            } else {

                p_stmt.setNull(l_i + 1, Types.NULL);

            }
        }
    }

}
