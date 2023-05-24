/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import basicobject.Car;
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
public class CarDao {

    // target: doc bang tblUser de lay tat ca cac dong
    // tra ve ArrayList<User>
    public static ArrayList<Car> getAllUsers() throws Exception {
        ArrayList<Car> list = new ArrayList<>();
        //b1: tao cau noi
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //b2: viet sql va exec
            String sql = "Select [id], [name], [description], [price], [speed], [status]\n"
                    + "from dbo.tblCars";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String carID = rs.getString("id");
                    String carName = rs.getString("name");
                    String desscription = rs.getString("description");
                    double price = rs.getDouble("price");
                    int speed = rs.getInt("speed");
                    boolean status = rs.getBoolean("status");
                    list.add(new Car(carID, carName, desscription, price, speed, status));
                }
            }
            cn.close();
        }

        return list;
    }

    public static int updateStatus(String userid, boolean status) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update [tblCars] set [status]=? where [id]=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setBoolean(1, !status);
            pst.setString(2, userid);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    // get CarID
    public static Car getCar(String carID) throws Exception {
        Car car = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "Select [id],[name], [description], [price], [speed], [status]\n"
                    + "from tblCars where [id]=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, carID);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String descr = rs.getString("description");
                    double price = rs.getDouble("price");
                    int speed = rs.getInt("speed");
                    boolean status = rs.getBoolean("status");
                    car = new Car(id, name, descr, price, speed, status);
                }
            }
        }

        return car;
    }

    // ham nay de insert 1 user moi vao bang tblUsers
    // tra ve 1/0
    public static int insertCar(String userid, String fullname, String descr, double price, int speed, boolean status) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert dbo.tblCars([id], [name], [description], [price], [speed], [status])\n"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.setString(2, fullname);
            pst.setString(3, descr);
            pst.setDouble(4, price);
            pst.setInt(5, speed);
            pst.setBoolean(6, status);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

}
