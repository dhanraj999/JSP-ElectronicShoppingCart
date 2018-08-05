package com.electronic.dao;

import com.electronic.beans.User;
import java.sql.*;
import java.util.ArrayList;
import com.electronic.util.SHA;

/**
 *
 * @author Dhannu
 */
public class UserDbModel {

    User bean = new User();
    Connection con;
    DbConnection db = new DbConnection();
    boolean b = false;

    private boolean search(String usrName) {

        try {

            con = db.openConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * from users where (username=?)");
            pst.setString(1, usrName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                b = true;
            } else {
                b = false;
            }
            db.closeConnection();
        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return b;
    }

    public boolean signUp(User bean) {

        boolean b = false;

        if (!search(bean.getUserName())) {
            try {
                con = db.openConnection();

                PreparedStatement pst1 = con.prepareStatement("INSERT into users (username,email,address, password,job,cash,role)"
                        + "values (?,?,?,?,?,?,?)");

                pst1.setString(1, bean.getUserName());
                pst1.setString(2, bean.getEmail());
                pst1.setString(3, bean.getAddress());
                pst1.setString(4, SHA.encrypt(bean.getPassword()));
                pst1.setString(5, bean.getJob());
                pst1.setDouble(6, bean.getCash());
                pst1.setString(7, bean.getRole());

                pst1.executeUpdate();
                pst1.close();
                b = true;
                ////////////////////////////////////////

                //con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return b;
            }
        }

        return b;
    }

    public User signIn(String username, String password) {

        User userSinIn = null;
        try {
            if (search(username)) {
                con = db.openConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * from users where (username=?) and (PASSWORD=?)");
                pst.setString(1, username);
                pst.setString(2, SHA.encrypt(password));
                ResultSet rs = pst.executeQuery();
                System.out.println("osaamaa       " + rs);
                if (rs.next()) {
                    userSinIn = new User();
                    userSinIn.setUserId(rs.getInt("id"));
                    userSinIn.setUserName(rs.getString("username"));
                    userSinIn.setEmail(rs.getString("email"));
                    userSinIn.setAddress(rs.getString("address"));
                    userSinIn.setPassword("");
                    userSinIn.setJob(rs.getString("job"));
                    
                    userSinIn.setCash(rs.getInt("cash"));
                    userSinIn.setRole(rs.getString("role"));
                    userSinIn.setPhoto(rs.getString("photo"));
                    System.out.println(userSinIn);
                }
                db.closeConnection();
                System.out.println(userSinIn);
                return userSinIn;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            db.closeConnection();
        }

        return null;
    }

    public boolean updateUser(User updateUser, String path) {

        try {
            System.out.println("Start");
            if (search(updateUser.getUserName())) {
                System.out.println("########## in method");
                User temp = getUser(updateUser.getUserId());
                if (!temp.getPhoto().equalsIgnoreCase(updateUser.getPhoto())) {
                    boolean deleteFile = com.electronic.util.FileUpload.deleteFile(temp.getPhoto(), path);
                    System.out.println("--=-=-=-=-=-=-=-=-=-==-0" + deleteFile);
                }
                System.out.println("-=-=-=-=-= 0 -=-=-=-=-= ");
                con = db.openConnection();
                PreparedStatement pst = con.prepareStatement("update users "
                        + "set username=?,password=?,email=?,job=?,address=?,creaditCard=?,cash=?,role=? ,photo=? "
                        + "where id=? ");
                pst.setString(1, updateUser.getUserName());
                pst.setString(2, SHA.encrypt(updateUser.getPassword()));
                pst.setString(3, updateUser.getEmail());
                pst.setString(4, updateUser.getJob());
                pst.setString(5, updateUser.getAddress());
                
                pst.setDouble(7, updateUser.getCash());
                pst.setString(8, updateUser.getRole());
                pst.setString(9, updateUser.getPhoto());
                pst.setInt(10, updateUser.getUserId());
                System.out.println("--=-=-=-=-=-=-=-=-=-==-1");
                pst.executeUpdate();
                System.out.println("--=-=-=-=-=-=-=-=-=-==-2");
                db.closeConnection();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            db.closeConnection();
        }
        return false;
    }

    public ArrayList<User> getAllUsers() {
        con = db.openConnection();
        ArrayList<User> allUser = new ArrayList();
        try {

            PreparedStatement pst = con.prepareStatement("SELECT * from users ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setPassword("");
                user.setJob(rs.getString("job"));
                user.setCash(rs.getInt("cash"));
                user.setRole(rs.getString("role"));
                user.setPhoto(rs.getString("photo"));
                allUser.add(user);
            }
            db.closeConnection();
            return allUser;
        } catch (SQLException ex) {
            ex.printStackTrace();
            db.closeConnection();
        }
        return null;
    }

    public User getUser(int id) {
        User user = null;
        try {

            con = db.openConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * from users where (id=?)");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setPassword("");
                user.setJob(rs.getString("job"));
                user.setCash(rs.getInt("cash"));
                user.setRole(rs.getString("role"));
                user.setPhoto(rs.getString("photo"));
            }
            db.closeConnection();
        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();

        }
        return user;

    }

    public boolean updateUserBalance(User updateUser) throws SQLException {

        if (search(updateUser.getUserName())) {
            con = db.openConnection();
            PreparedStatement pst = con.prepareStatement("update users set cash=? where id=?");
            pst.setDouble(1, updateUser.getCash());
            pst.setInt(2, updateUser.getUserId());
            pst.executeUpdate();
            db.closeConnection();
            return true;
        }

        return false;
    }
}
