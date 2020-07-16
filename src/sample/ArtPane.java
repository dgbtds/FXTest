package sample;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ArtPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();


        //画板区域
        int w= 600;
        int h= 400;
        WritableImage writableImage = new WritableImage(w, h);
        PixelWriter pw = writableImage.getPixelWriter();
        for (int i=0;i<w;i++){
            for (int j=0;j<h;j++){
                pw.setColor(i,j, Color.YELLOW);
            }
        }
        ImageView iv = new ImageView(writableImage);
        iv.setFocusTraversable(true);

        //逻辑
        iv.setOnMouseEntered(event -> {
            iv.setCursor(new ImageCursor(new Image("pen.png")));
            System.out.println("enter");
                }
        );
        int Wight=3;
        iv.setOnMouseDragged(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x>=0&&x<=w-Wight&&y>=0&&y<=h-Wight) {
                for(int i=0;i<Wight;i++){
                    for(int j=0;j<Wight;j++){
                        pw.setColor(x+i,y+j,Color.BLACK);
                    }
                }
            }
        });
        //iv.setOnDragDetected(event -> iv.startFullDrag() );

        ap.getChildren().addAll(iv);
        AnchorPane.setTopAnchor(iv,200.0);
        AnchorPane.setLeftAnchor(iv,200.0);


        primaryStage.setScene(new Scene(ap,1200,800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
