package Controller;

import Infomation.CacHo;
import com.example.nhankhaumanager.DBUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThemHoController {
    ObservableList<CacHo> list;
    @FXML private TextField maho;
    @FXML private TextField tenChuHo;
    @FXML private TextField soNha;
    @FXML private TextField Duong;
    @FXML private TextField Phuong;
    @FXML private TextField Quan;
    @FXML private Label notify;

    public void setInit(ObservableList<CacHo> list){
        this.list = list;
    }

    private boolean Error(){
        return maho.getText().isEmpty()
                || tenChuHo.getText().isEmpty()
                || soNha.getText().isEmpty()
                || Duong.getText().isEmpty()
                || Phuong.getText().isEmpty()
                || Quan.getText().isEmpty();
    }

    public void edit(ActionEvent e){

        if(Error()){
            notify.setText("You must fill all the blank!!");
        }
        else{
            list.add(new CacHo(maho.getText(),tenChuHo.getText(),Integer.parseInt(soNha.getText()),Duong.getText(),Phuong.getText(),Quan.getText()));
            try {
                Connection conn = DBUtil.getConnection();

                String sql = "INSERT INTO CACHO " +
                        "VALUES(?,?,?,?,?,?);";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,maho.getText());
                pst.setString(2,tenChuHo.getText());
                pst.setString(3,soNha.getText());
                pst.setString(4,Duong.getText());
                pst.setString(5,Phuong.getText());
                pst.setString(6,Quan.getText());

                int ss1 = pst.executeUpdate();
                if(ss1 > 0){
                    System.out.println("Insert successfully!" + ss1 + "rows");
                }
                else System.out.println("failed");

                DBUtil.Close(conn);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
