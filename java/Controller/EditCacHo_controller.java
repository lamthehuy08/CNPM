package Controller;

import Infomation.CacHo;
import com.example.nhankhaumanager.DBUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditCacHo_controller {
    private CacHo cacHo;
    private ObservableList<CacHo> cur;
    private int index;
    @FXML private TextField maho;
    @FXML private TextField tenChuHo;
    @FXML private TextField soNha;
    @FXML private TextField Duong;
    @FXML private TextField Phuong;
    @FXML private TextField Quan;


    public void getTextField(CacHo cacHo,ObservableList<CacHo> cur,int index){
        this.index = index;
        this.cur = cur;
        this.cacHo = cacHo;
        maho.setText( this.cacHo.getMaHo());
        tenChuHo.setText(this.cacHo.getTen());
        soNha.setText(String.valueOf(cacHo.getSoNha()));
        Duong.setText(this.cacHo.getDuong());
        Phuong.setText(this.cacHo.getPhuong());
        Quan.setText(this.cacHo.getQuan());
    }
    
    public void edit(ActionEvent e){
        CacHo c = new CacHo(maho.getText(),tenChuHo.getText(),Integer.parseInt(soNha.getText()),Duong.getText(),Phuong.getText(),Quan.getText());
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "UPDATE CACHO " +
                    "SET MAHO = ?," +
                    "TENCHUHO = ?," +
                    "SONHA = ?," +
                    "DUONG = ?," +
                    "PHUONG = ?," +
                    "QUAN = ? " +
                    "WHERE MAHO = ?;";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,maho.getText());
            pst.setString(2,tenChuHo.getText());
            pst.setString(3,soNha.getText());
            pst.setString(4,Duong.getText());
            pst.setString(5,Phuong.getText());
            pst.setString(6,Quan.getText());
            pst.setString(7,cacHo.getMaHo());

            int ss =  pst.executeUpdate();
            System.out.println(ss);

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        this.cur.set(index,c);
    }
}
