package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import error.DuplicateEmailException;
import model.UserInfo;




public class AccessDAO {

    private static DataSource ds = null;

    private static final String JNDI_NAME = "java:comp/env/jdbc/projdb";

    private static DataSource getDataSource() throws NamingException {
        if (ds == null) {
            //
            InitialContext ic = new InitialContext();
            //
            ds = (DataSource) ic.lookup(JNDI_NAME);
        }
        return ds;
    }


    public Collection<UserInfo> findAllUserInfo() throws SQLException {
        // 
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //
            String sql = "SELECT name, yomi, zip, address,"
                    + "tel, email FROM addressbook";
            //
            Collection<UserInfo> userList = new ArrayList<UserInfo>();

            // Connection
            conn = getDataSource().getConnection();
            // PreparedStatement
            ps = conn.prepareStatement(sql);
            //
            rs = ps.executeQuery();
            //
            while (rs.next()) {
                //
                UserInfo userInfo = new UserInfo();
                //
                userInfo.setName(rs.getString("name"));
                userInfo.setYomi(rs.getString("yomi"));
                userInfo.setZip(rs.getString("zip"));
                userInfo.setAddress(rs.getString("address"));
                userInfo.setTel(rs.getString("tel"));
                userInfo.setEmail(rs.getString("email"));
                //
                //
                userList.add(userInfo);
            }
            //
            return userList;
        } catch (NamingException e) {
            e.printStackTrace();
            throw new SQLException(e);
        } finally {
            //
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    public void registUserInfo(UserInfo userInfo) throws SQLException,
            DuplicateEmailException {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            //
            String sql = "INSERT INTO addressbook(name, yomi, zip, address, tel, email) VALUES (?,?,?,?,?,?)";
            //
            String sqlForCheck = "SELECT email FROM addressbook WHERE email=?";

            //
            conn = getDataSource().getConnection();
            //
            ps1 = conn.prepareStatement(sqlForCheck);
            //
            ps1.setString(1, userInfo.getEmail());
            //
            rs = ps1.executeQuery();
            if (rs.next()) {
                throw new DuplicateEmailException();
            }

            //
            ps2 = conn.prepareStatement(sql);
            //
            ps2.setString(1, userInfo.getName());
            ps2.setString(2, userInfo.getYomi());
            ps2.setString(3, userInfo.getZip());
            ps2.setString(4, userInfo.getAddress());
            ps2.setString(5, userInfo.getTel());
            ps2.setString(6, userInfo.getEmail());
            //
            ps2.executeUpdate();
        } catch (NamingException e) {
            e.printStackTrace();
            throw new SQLException(e);
        } finally {
            //
            if (rs != null) {
                rs.close();
            }
            if (ps1 != null) {
                ps1.close();
            }
            if (ps2 != null) {
                ps2.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteUserInfo(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // SQL
            String sql = "DELETE FROM addressbook WHERE email=?";

            // Connection
            conn = getDataSource().getConnection();
            //
            ps = conn.prepareStatement(sql);
            //
            ps.setString(1, email);
            // SQL
            ps.executeUpdate();
        } catch (NamingException e) {
            e.printStackTrace();
            throw new SQLException(e);
        } finally {
            //クローズ処理
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}

