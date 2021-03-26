package experiment2.wangwei.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import util.Resource;

import java.net.URL;

/**
 * @author yohoyes
 * @date 2021/3/26 13:20
 */
public class TestController {

    @FXML
    private WebView web;

    public void initialize() {
        URL resource = getClass().getResource("../view/index.html");
        System.out.println(resource.getPath());
        WebEngine engine = web.getEngine();
        engine.load(resource.toString());
    }

}
