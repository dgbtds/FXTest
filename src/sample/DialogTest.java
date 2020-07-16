package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/23 10:47
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @program: FXTest
 *
 * @description:
 *
 * @author: WuYe
 *
 * @create: 2020-02-23 10:47
 **/
public class DialogTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();


        Button click = new Button("click");
        click.setPrefWidth(100);
        click.setPrefHeight(50);

        Dialog<ButtonType> dialog = new Dialog<>();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().add(ButtonType.OK);
        dialogPane.getButtonTypes().add(ButtonType.NEXT);
        dialog.setHeaderText("标题");
        dialog.setGraphic(new Label("node ADD"));
        dialog.setContentText("content");

        click.setOnMouseClicked(event ->{
            Optional<ButtonType> buttonType = dialog.showAndWait();
            buttonType.ifPresent(new Consumer<ButtonType>() {
                @Override
                public void accept(ButtonType buttonType) {
                    if (buttonType.equals(ButtonType.OK)){
                        dialog.setContentText("111111111");
                    }
                    else {
                        dialog.setContentText("2222222222");
                    }
                }
            });
        });


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

