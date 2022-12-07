package Controller;


import Infomation.CacHo;
import Infomation.Info;
import Infomation.KhoanThu;
import com.example.nhankhaumanager.Connect;
import com.example.nhankhaumanager.DBUtil;
import com.example.nhankhaumanager.FadeAnimationMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML Button loginButton;
    @FXML SplitPane pnB1;
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML Label notify;
    public void LoginButton(ActionEvent e){

        String user = username.getText().replaceAll("\\s" , "");
        String pass = password.getText();

        if(user.equals("admin") && pass.equals("admin")){
            new FadeAnimationMode(pnB1);
            pnB1.toFront();
        }
        else{
            notify.setTextFill(Color.RED);
            notify.setText("Check your account again!");
        }
    }

    // **********************************----------------****************************//

                            //*****************************//


    //-------------------------*****************------------------------------------//



    @FXML private TableView<CacHo> tableCacHo;
    @FXML private TableColumn<Info , String> maHo,maHo_KhoanThu;
    @FXML private TableColumn<Info,String> tenChuHo, tenChuHo_KhoanThu;
    @FXML private TableColumn<CacHo,String> soNha;
    @FXML private TableColumn<CacHo,String> Duong;
    @FXML private TableColumn<CacHo,String> Phuong;
    @FXML private TableColumn<CacHo,String> Quan;
    @FXML private BorderPane pnCacHo;
    private ObservableList<CacHo> listCacHo;

    // Khoan Thu
    @FXML private BorderPane pnKhoanThu;

    @FXML private TableView<KhoanThu> tableKhoanThu;
    @FXML private TableColumn<KhoanThu,Double> SoTienDaThu;
    @FXML private TableColumn<KhoanThu,Double> ConPhaiDong;
    private ObservableList<KhoanThu> listKhoanThu;

    public void DanhSachCacHoButton(ActionEvent e){
        pnCacHo.toFront();
    }
    public void KhoanThuButton(ActionEvent e){
        pnKhoanThu.toFront();
    }
    public void addCacHo(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ThemHoScene.fxml"));
        Parent root = loader.load();
        ThemHoController them = loader.getController();
        them.setInit(listCacHo);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Thêm hộ khẩu");
        stage.show();
    }
    public void editCacHo(ActionEvent e) throws IOException {
        CacHo ho = tableCacHo.getSelectionModel().getSelectedItem();
        int i = tableCacHo.getSelectionModel().getSelectedIndex();

        if(ho != null){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCacHo.fxml"));
            Parent root = loader.load();
            EditCacHo_controller edit = loader.getController();
            edit.getTextField(ho,listCacHo,i);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit hộ khẩu");
            stage.show();
        }
    }
    public void XoaHo(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING!");
        alert.setHeaderText("Are you sure for removing?");
        alert.setContentText("You're about to remove!");
        if(alert.showAndWait().get() == ButtonType.OK){
            Info ho = tableCacHo.getSelectionModel().getSelectedItem();
            try {
                Connection c = DBUtil.getConnection();
                String sql = "DELETE FROM CACHO WHERE MAHO = ?;";
                PreparedStatement pst = c.prepareStatement(sql);
                pst.setString(1,ho.getMaHo());

                int ok = pst.executeUpdate();
                if(ok > 0){
                    System.out.println("delete" + ok + "rows successfully!");
                }
                else System.out.println("Failed");
                DBUtil.Close(c);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            listCacHo.remove(ho);
        }
    }
    public void addKhoanThu(ActionEvent e){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CacHo c = new CacHo("wae","asdadaw",12,"asdawd","bvb","ewrew");

        listCacHo = FXCollections.observableArrayList();

        maHo.setCellValueFactory(new PropertyValueFactory<Info,String>("maHo"));
        tenChuHo.setCellValueFactory(new PropertyValueFactory<Info,String>("ten"));
        soNha.setCellValueFactory(new PropertyValueFactory<CacHo,String>("soNha"));
        Duong.setCellValueFactory(new PropertyValueFactory<CacHo,String>("Duong"));
        Phuong.setCellValueFactory(new PropertyValueFactory<CacHo,String>("Phuong"));
        Quan.setCellValueFactory(new PropertyValueFactory<CacHo,String>("Quan"));

        listKhoanThu = FXCollections.observableArrayList();
        maHo_KhoanThu.setCellValueFactory(new PropertyValueFactory<Info,String>("MaHo"));
        tenChuHo_KhoanThu.setCellValueFactory(new PropertyValueFactory<Info,String>("ten"));
        SoTienDaThu.setCellValueFactory(new PropertyValueFactory<KhoanThu,Double>("SoTienDaThu"));
        ConPhaiDong.setCellValueFactory(new PropertyValueFactory<KhoanThu,Double>("ConPhaiDong"));

        tableCacHo.setItems(listCacHo);
        tableKhoanThu.setItems(listKhoanThu);

        loadData("CACHO");
    }

    public void loadData(String table){
        try {
            Connection conn = DBUtil.getConnection();

            String sql = "SELECT * FROM " + table;

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                if(table == "CACHO"){
                    listCacHo.add(new CacHo(rs.getString("MAHO"),
                            rs.getString("TENCHUHO"),
                            rs.getInt("SONHA"),
                            rs.getString("DUONG"),
                            rs.getString("PHUONG"),
                            rs.getString("QUAN")));
                }
            }

            DBUtil.Close(conn);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
