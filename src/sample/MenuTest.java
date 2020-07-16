package sample;
/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/12 11:00
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @program: FXTest
 *
 * @description:菜单
 *
 * @author: WuYe
 *
 * @create: 2020-02-12 11:00
 **/
public class MenuTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();


        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu mutiple = new Menu("多选");

        ImageView imageView = new ImageView("chuy.png");
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        MenuItem item1 = new MenuItem("item1",imageView);
        MenuItem item2 = new MenuItem("item2");
        MenuItem item3 = new MenuItem("item3");
        MenuItem item4 = new MenuItem("item4");

        Menu effect = new Menu("effect");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem radioMenuItem1 = new RadioMenuItem("effect 1");
        RadioMenuItem radioMenuItem2 = new RadioMenuItem("effect 2");
        RadioMenuItem radioMenuItem3 = new RadioMenuItem("effect 3");
        radioMenuItem1.setToggleGroup(toggleGroup);
        radioMenuItem2.setToggleGroup(toggleGroup);
        radioMenuItem3.setToggleGroup(toggleGroup);

        menuBar.getMenus().addAll(file,edit,mutiple);
        file.getItems().addAll(item1,item2,item3,item4);
        item1.setAccelerator(KeyCombination.valueOf("ctrl+alt+a"));
        item1.setOnAction(event -> {primaryStage.close();});

        radioMenuItem2.setSelected(true);
        effect.getItems().addAll(radioMenuItem1,radioMenuItem2,radioMenuItem3);
        edit.getItems().addAll(effect);

        CheckMenuItem checkMenuItem1 = new CheckMenuItem("checkMenuItem1");
        CheckMenuItem checkMenuItem2 = new CheckMenuItem("checkMenuItem2");
        CheckMenuItem checkMenuItem3 = new CheckMenuItem("checkMenuItem3");

        mutiple.getItems().addAll(checkMenuItem1,checkMenuItem2,checkMenuItem3);

        menuBar.setPrefWidth(ap.getPrefWidth());
        ap.getChildren().add(menuBar);
        ap.widthProperty().addListener((observable,oldValue,newValue) -> {
            menuBar.setPrefWidth(newValue.doubleValue());
        });

        primaryStage.setTitle("菜单测试 ");
        primaryStage.setScene(new Scene(ap,600,400));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        System.out.println("main:"+Thread.currentThread().getName());
        launch(args);
    }

}
