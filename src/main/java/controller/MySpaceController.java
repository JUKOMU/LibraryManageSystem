package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.alibaba.druid.util.StringUtils;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Result;

import javax.security.auth.login.LoginException;

public class MySpaceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private TextField pwd;

    @FXML
    private Button login_btn;

    @FXML
    private Pane login_pane;

    @FXML
    private Label tip_register1;

    @FXML
    private Label tip_register2;

    @FXML
    private Label errorinfo;

    @FXML
    void login(ActionEvent event) throws Exception {
        String name = this.name.getText().trim();
        String pwd = this.pwd.getText().trim();

        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            //给出提示
            errorinfo.setText("用户名或密码不能为空!");
            errorinfo.setStyle("-fx-text-fill: red");
            //设置可见
            errorinfo.setVisible(true);
            return;
        }

        UserService userService = new UserServiceImpl();

        User user = new User();
        user.setUsername(name);
        user.setPassword(pwd);

        try {
            Result result = userService.login(user);
            //判断结果对象中的状态码
            if(result.getCode()==200) {
                //正确
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mySpace.fxml"));
                Node node = loader.load();

                MainController mainController = (MainController) ControllerManager.controllers.get("mainController");
                HBox mainHbox = mainController.getMain_hbox();
                mainHbox.getChildren().clear();
                mainHbox.getChildren().addAll(node);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            errorinfo.setLayoutX(errorinfo.getLayoutX() + 40);
            errorinfo.setText("登陆失败!");
            errorinfo.setStyle("-fx-text-fill: red");
            //设置可见
            errorinfo.setVisible(true);
            return;
        }
    }

    @FXML
    void register(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert pwd != null : "fx:id=\"pwd\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert login_btn != null : "fx:id=\"login_btn\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert login_pane != null : "fx:id=\"login_pane\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert tip_register1 != null : "fx:id=\"tip_register1\" was not injected: check your FXML file 'mySpace.fxml'.";
        assert tip_register2 != null : "fx:id=\"tip_register2\" was not injected: check your FXML file 'mySpace.fxml'.";

        ControllerManager.controllers.put("mySpaceController",this);
    }
}
