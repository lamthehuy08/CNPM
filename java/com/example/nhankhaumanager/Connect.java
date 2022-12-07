package com.example.nhankhaumanager;

import Infomation.CacHo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConnection();

            Statement st = conn.createStatement();

            String sql = "INSERT INTO CACHO " +
                    "VALUES('3','Huy',"+20+", 'thôn 2','Quảng Hòa','Quảng Xương')";

            /*
            String sql2 = "SELECT * FROM CACHO";
            ArrayList<CacHo> c = new ArrayList<>();

            ResultSet rs = st.executeQuery(sql2);

            while(rs.next()){
                String ma = rs.getString("MAHO");
                String ten  = rs.getString("TENCHUHO");
                int sonha = rs.getInt("SONHA");
                String duong = rs.getString("DUONG");
                String phuong  = rs.getString("PHUONG");
                String quan = rs.getString("QUAN");
                CacHo ccac = new CacHo(ma,ten,sonha,duong,phuong,quan);
                c.add(ccac);
            }

            for(CacHo cc : c){
                System.out.println(cc.toString());
            }
            */


            int check = st.executeUpdate(sql);

            DBUtil.Close(conn);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
