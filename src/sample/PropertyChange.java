package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/12 20:35
 */

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @program: FXTest
 *
 * @description:属性监听器
 *
 * @author: WuYe
 *
 * @create: 2020-02-12 20:35
 **/

public class PropertyChange extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();

        TextField textField = new TextField();
        textField.setLayoutX(300);
        textField.setLayoutY(100);
        textField.setPrefHeight(50);
        textField.setPrefWidth(200);

        Button click = new Button("click");
        click.setPrefWidth(100);
        click.setPrefHeight(50);

        SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(5);

        click.setOnAction(event -> {
            CharSequence characters = textField.getCharacters();
            String s = characters.toString();
            integerProperty.set(Integer.valueOf(s));
        });
         System.out.println("old value=="+integerProperty.get());

         integerProperty.addListener((observer,oldValue,newValue)->{
             System.out.println("new value= "+newValue);
         });

        ap.getChildren().addAll(click,textField);
        AnchorPane.setTopAnchor(click,200.0);
        AnchorPane.setLeftAnchor(click,200.0);
        primaryStage.setTitle("属性检测");
        primaryStage.setScene(new Scene(ap,600,400));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        System.out.println("main:"+Thread.currentThread().getName());
        launch(args);
    }

}
