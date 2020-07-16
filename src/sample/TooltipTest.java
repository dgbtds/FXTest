package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/17 11:49
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @program: FXTest
 *
 * @description:提示框
 *
 * @author: WuYe
 *
 * @create: 2020-02-17 11:49
 **/
public class TooltipTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();

        Button click = new Button("click");
        click.setPrefWidth(100);
        click.setPrefHeight(50);

        Tooltip tooltip = new Tooltip();
        tooltip.setPrefWidth(200);
        tooltip.setPrefHeight(100);
        BackgroundFill backgroundFill = new BackgroundFill(Color.YELLOW, new CornerRadii(20), new Insets(10));
        VBox vBox = new VBox();
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(100);
        vBox.setBackground(new Background(backgroundFill));
        Label label = new Label("这是提示框，2020/2/17");
        label.setWrapText(true);
        label.setFont(Font.font(20));
        vBox.getChildren().addAll(label);


        tooltip.setGraphic(vBox);
        tooltip.setStyle("-fx-background-color:#ffffff00");
//        tooltip.setText("这是提示框，2020/2/17");
//        tooltip.setFont(Font.font(20));
//        tooltip.setWrapText(true);

        click.setTooltip(tooltip);
        ap.getChildren().addAll(click);

        AnchorPane.setTopAnchor(click,200.0);
        AnchorPane.setLeftAnchor(click,200.0);
        primaryStage.setScene(new Scene(ap,600,400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
