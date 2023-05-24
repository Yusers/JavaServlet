/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import basicobject.Car;
import basicobject.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author overw
 */
public class UserDao {

    // target: doc bang tblUser de lay tat ca cac dong
    // tra ve ArrayList<User>
    public static ArrayList<User> getAllUsers() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        //b1: tao cau noi
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //b2: viet sql va exec
            String sql = "select [userID], [fullName], [roleID], [password]\n"
                    + "from dbo.tblUsers";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String role = rs.getString("roleID");
                    String password = rs.getString("password");
                    list.add(new User(userID, fullName, role, password));
                }
            }
            cn.close();
        }

        return list;
    }

    public static int removeUser(String userid) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "delete from tblUsers where userID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    public static int resetPassword(String userid) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update tblUsers set password=? where userID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            int newPassword = (int) (Math.random() * 1000000) % 1000 + 10000;
            pst.setString(1, "" + newPassword);
            pst.setString(2, userid);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    // ham nay de lay user trong tblUsers dua vao userid
    // tra ve Users
    public static User getUser(String userid) throws Exception {
        User us = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //b2: viet sql va exec
            String sql = "select [userID], [fullName], [roleID], [password]\n"
                    + "from dbo.tblUsers where userid=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String role = rs.getString("roleID");
                    String password = rs.getString("password");
                    us = new User(userID, fullName, role, password);
                }
            }
            cn.close();
        }
        return us;
    }

    // ham nay de insert 1 user moi vao bang tblUsers
    // tra ve 1/0
    public static int insertUser(String userid, String fullname) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert dbo.tblUsers([userID],[fullName],[roleID],[password])\n"
                    + "values(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.setString(2, fullname);
            pst.setString(3, "US");
            int newPassword = (int) (Math.random() * 1000000) % 1000 + 10000;
            pst.setString(4, "" + newPassword);
            rs = pst.executeUpdate();
            cn.close();
        }

        return rs;
    }

    // ham nay de lay user trong tblUsers dua vao userid
    // tra ve Users
    public static Car getCar(String carid) throws Exception {
        Car car = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //b2: viet sql va exec
            String sql = "Select [id], [name], [description], [price], [speed], [status]\n"
                    + "from dbo.tblCars where id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, carid);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String carID = rs.getString("id");
                    String carName = rs.getString("name");
                    String desscription = rs.getString("description");
                    double price = rs.getDouble("price");
                    int speed = rs.getInt("speed");
                    boolean status = rs.getBoolean("status");
                    car = new Car(carID, carName, desscription, price, speed, status);
                }
            }
            cn.close();
        }
        return car;
    }
}
