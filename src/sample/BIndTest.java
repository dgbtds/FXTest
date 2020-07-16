package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/16 11:19
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * @program: FXTest
 *
 * @description: 单向双向绑定控件
 *
 * @author: WuYe
 *
 * @create: 2020-02-16 11:19
 **/
public class BIndTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        Button button1 = new Button("单向绑定父组件");
        button1.prefWidthProperty().bind(anchorPane.widthProperty().divide(2));

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        textField1.textProperty().bindBidirectional(textField2.textProperty(), new StringConverter<String>() {
            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                if(string.contains("5")){
                    String s = string.replace('5', '吴');
                    return s;
                }
                return string;
            }
        });

        Button bing_slider = new Button("bing slider");
        Slider slider = new Slider(0,500,0);
        slider.setPrefWidth(500);
        bing_slider.translateXProperty().bind(slider.valueProperty());

        AnchorPane.setTopAnchor(textField1,  100.0);
        AnchorPane.setTopAnchor(bing_slider,  200.0);
        AnchorPane.setTopAnchor(slider,  250.0);
        AnchorPane.setTopAnchor(textField2,100.0);
        AnchorPane.setLeftAnchor(textField2,200.0);
        anchorPane.getChildren().addAll(button1,textField1,textField2,bing_slider,slider);
        primaryStage.setScene(new Scene(anchorPane,600,400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
