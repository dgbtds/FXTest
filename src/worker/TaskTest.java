package worker;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/26 11:13
 */

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.annotation.ElementType;

/**
 * @program: FXTest
 *
 * @description:
 *
 * @author: WuYe
 *
 * @create: 2020-02-26 11:13
 **/
public class TaskTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();

        HBox hBox = new HBox();

        Button start = new Button("start");
        Button cancel = new Button("cancel");
        start.setPrefWidth(100);
        start.setPrefHeight(25);
        cancel.setPrefWidth(100);
        cancel.setPrefHeight(25);

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        Label state = new Label("state");
        Label title = new Label("title");
        Label massage = new Label("massage");
        Label taskName = new Label("taskName");


        hBox.getChildren().addAll(start,cancel,progressBar,state,title,massage,taskName);
        hBox.setSpacing(20.0);
        ap.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox,200.0);
        AnchorPane.setLeftAnchor(hBox,50.0);

        start.setOnAction(event -> {
            myTask myTask = new myTask();
            new Thread(myTask).start();
            myTask.progressProperty().addListener((observer,oldValue,newValue)->{
                progressBar.setProgress(newValue.doubleValue());
            });
            myTask.messageProperty().addListener((observer,oldValue,newValue)->{
             massage.setText(newValue);
            });
            myTask.stateProperty().addListener((observer,oldValue,newValue)->{
                System.out.println(newValue.toString());
             state.setText(newValue.toString());
            });
        });



        primaryStage.setScene(new Scene(ap,1200,800));
        primaryStage.show();
    }
    class myTask extends Task{

        @Override
        protected Object call() throws Exception {
            int max=100;
            int sum=1;
            int progess=0;
            for(int i=0;i<max;i++){
                sum+=i;
                progess=sum/max;
                updateProgress(i,max);
                if (progess<0.5){
                    updateMessage("请耐心等待");
                }
                else if (progess<0.8){
                    updateMessage("八成");

                }
                else if(progess<1){
                    updateMessage("即将完成");

                }
                else if (progess>=1){
                    updateMessage("完成");
                }
                System.out.println("1111111111");
                Thread.sleep(100);
            }

            return progess;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
