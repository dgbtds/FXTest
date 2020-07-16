package worker;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/26 17:36
 */

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @program: FXTest
 *
 * @description:
 *
 * @author: WuYe
 *
 * @create: 2020-02-26 17:36
 **/
public class ServiceTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();

        HBox hBox = new HBox();

        Button start = new Button("start");
        Button cancel = new Button("cancel");
        Button restart = new Button("restart");
        Button reset = new Button("reset");
        start.setPrefWidth(100);
        start.setPrefHeight(25);
        cancel.setPrefWidth(100);
        cancel.setPrefHeight(25);
        restart.setPrefWidth(100);
        restart.setPrefHeight(25);
        reset.setPrefWidth(100);
        reset.setPrefHeight(25);

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        TextField state_field = new TextField("state");
        state_field.setEditable(false);
        state_field.setPrefWidth(100);

        TextField title_field = new TextField("title");
        title_field.setEditable(false);
        title_field.setPrefWidth(100);

        TextField massage_field = new TextField("massage");
        massage_field.setEditable(false);
        massage_field.setPrefWidth(100);

        TextField taskName_field = new TextField("taskName");
        taskName_field.setEditable(false);
        taskName_field.setPrefWidth(100);


        hBox.getChildren().addAll(start,cancel,restart,reset,progressBar,state_field,title_field,massage_field,taskName_field);
        hBox.setSpacing(20.0);
        ap.getChildren().addAll(hBox);
        AnchorPane.setTopAnchor(hBox,200.0);
        AnchorPane.setLeftAnchor(hBox,10.0);

            Service<Number> ns = new MyService();
        start.setOnAction(event -> {
            start.setDisable(true);
            taskName_field.setText("service test");
            ns.start();
        });

        ns.progressProperty().addListener((observer,oldValue,newValue)->{
            progressBar.setProgress(newValue.doubleValue());
        });
        ns.messageProperty().addListener((observer,oldValue,newValue)->{
            massage_field.setText(newValue);
        });
        ns.stateProperty().addListener((observer,oldValue,newValue)->{
            state_field.setText(newValue.toString());
        });

        reset.setOnAction(event -> {
            ns.reset();
            start.setDisable(false);
            progressBar.setProgress(0);
        });
        restart.setOnAction(event -> {
            ns.restart();
        });
        cancel.setOnAction(event -> {
            ns.cancel();
        });


        primaryStage.setScene(new Scene(ap,1200,800));
        primaryStage.show();
    }
    class MyService extends Service<Number>{

        @Override
        protected Task<Number> createTask() {
            return new MyTask();
        }
    }
    class MyTask extends Task<Number> {

        @Override
        protected Number call() throws Exception {
            double max=100;
            double progess=0;
            for(int i=0;i<=max;i++){
                progess=(double) i/max;
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
                Thread.sleep(50);
            }

            return progess;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
