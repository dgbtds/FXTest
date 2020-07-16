package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/22 10:27
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * @program: FXTest
 *
 * @description: 拖拽，粘贴事件
 *
 * @author: WuYe
 *
 * @create: 2020-02-22 10:27
 **/
public class DragClipTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Border border = new Border(
                new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(4.0)));
        AnchorPane ap=new AnchorPane();
        ap.setBackground(new Background(
                new BackgroundFill(Color.BLUE,new CornerRadii(0),new Insets(0))));

        Label label = new Label("hello world");
        label.setBackground(new Background(
                new BackgroundFill(Color.YELLOW,new CornerRadii(0),new Insets(0))));
        label.setBorder(border);

        HBox hBox = new HBox();

        TextField textField = new TextField();
        textField.setPromptText("拖拽文本到此处");
        textField.setFocusTraversable(false);
        textField.setPrefWidth(150);
        textField.setPrefHeight(200);

        ImageView iv = new ImageView();
        iv.setFitHeight(200);
        iv.setFitWidth(300);
        //iv.setImage(new Image("chuy.png"));
        hBox.getChildren().add(iv);

        label.setOnDragDetected(event -> {
            Dragboard dragboard = label.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            WritableImage writableImage = new WritableImage((int) label.getWidth(),(int) label.getHeight());
            label.snapshot(new SnapshotParameters(),writableImage);
            dragboard.setDragView(writableImage);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(label.getText());
            dragboard.setContent(clipboardContent);

        });
        hBox.setOnDragEntered(event -> hBox.setBorder(border));
        hBox.setOnDragOver(event ->  event.acceptTransferModes(event.getTransferMode()) );
        hBox.setOnDragExited(event -> hBox.setBorder(null));

        hBox.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasString()){
                textField.setText(dragboard.getString());
            }
            else if(dragboard.hasImage()){
                Image image = dragboard.getImage();
                iv.setImage(image);
            }
            else if (dragboard.hasUrl()){
                iv.setImage(new Image(dragboard.getUrl()));
            }
            else {

            }
            event.setDropCompleted(true);
        });
        textField.setOnDragDone(event -> {
            label.setText("222222222222");
        });


        hBox.getChildren().add(textField);
        AnchorPane.setLeftAnchor(hBox,100.0);
        AnchorPane.setTopAnchor(hBox,100.0);
        AnchorPane.setLeftAnchor(label,200.0);
        AnchorPane.setTopAnchor(label,10.0);
        ap.getChildren().addAll(label,hBox);
        primaryStage.setScene(new Scene(ap,1200,800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
