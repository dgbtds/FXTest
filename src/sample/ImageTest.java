package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;


public class ImageTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();
//        //圆角矩形
//        Image image = new Image("chuy.png",350,
//                306,true,false);
//
//        ImageView iv = new ImageView(image);
//        AnchorPane.setTopAnchor(iv,100.0);
//        AnchorPane.setLeftAnchor(iv,100.0);
//        Rectangle rec = new Rectangle(350, iv.prefHeight(-1));
//        rec.setArcHeight(50);
//        rec.setArcWidth(50);
//        iv.setClip(rec);

//        //画线条
//        int w=100;
//        int h=100;
//        WritableImage writableImage = new WritableImage(w, h);
//        PixelWriter pw = writableImage.getPixelWriter();
//        for (int i=0;i<w;i++){
//            for (int j=0;j<w;j++){
//                pw.setColor(i,j, Color.YELLOW);
//            }
//        }
//        for (int j=0;j<w;j++){
//            pw.setColor(j,j, Color.RED);
//        }
//
//        ImageView iv = new ImageView(writableImage);


        //截图保存
        VBox vBox = new VBox();
        Button button = new Button("截图");
        ImageView iv = new ImageView("chuy.png");
        button.setOnAction(event -> {

            WritableImage wi = iv.snapshot(null, null);
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(wi, null);
            try {
                FileChooser fileChooser = FileChooseTest.FileChooseBuild();
                fileChooser.setTitle("保存文件");
                File file = fileChooser.showSaveDialog(new Stage());
                ImageIO.write(bufferedImage,"png",file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        vBox.getChildren().addAll(iv,button);

        ap.getChildren().addAll(vBox);
        primaryStage.setScene(new Scene(ap,800,600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
