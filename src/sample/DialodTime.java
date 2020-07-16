package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/10 14:19
 */

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.URL;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: FXTest
 *
 * @description: 倒计时对话框进度条
 *
 * @author: WuYe
 *
 * @create: 2020-02-10 14:19
 **/
public class DialodTime extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();

        Button click = new Button("click");
        click.setPrefWidth(100);
        click.setPrefHeight(50);


        click.setOnAction(event -> {

            URL resource = getClass().getClassLoader().getResource("chuy.png");
            ImageView imageView = new ImageView(resource.toExternalForm());
            DialogPane dialogPane = new DialogPane();
            Stage stage = new Stage();
            AtomicInteger time = new AtomicInteger(10);
            ProgressBar bar = new ProgressBar();
            bar.setProgress(0);


            stage.setTitle("下载进度...");
            stage.initOwner(primaryStage);
            stage.initStyle(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(dialogPane, 500, 200));
            stage.setResizable(false);
            stage.show();

            dialogPane.getButtonTypes().add(ButtonType.OK);
            dialogPane.getButtonTypes().add(ButtonType.APPLY);
            dialogPane.setHeader(bar);

            Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
            Button alyButton = (Button) dialogPane.lookupButton(ButtonType.APPLY);

            imageView.setFitHeight(100);
            imageView.setFitWidth(200);
            dialogPane.setGraphic(imageView);
            dialogPane.setExpanded(false);
            dialogPane.setExpandableContent(new Text("kuaozhanneirong"));

            ScheduledService scheduledService = runDelayTask2(time, dialogPane, stage, bar);

            okButton.setOnAction(event1 ->{
                scheduledService.cancel();
                stage.close();
            });
            //呼吸效果
            alyButton.setOnAction(event1 ->{
                FadeTransition fadeTransition = new FadeTransition();
                fadeTransition.setDuration(Duration.millis(1000));
                fadeTransition.setFromValue(0);
                fadeTransition.setNode(dialogPane);
                fadeTransition.setToValue(1);
                fadeTransition.play();
            });

        });

        ap.getChildren().add(click);
        AnchorPane.setTopAnchor(click,200.0);
        AnchorPane.setLeftAnchor(click,200.0);
        primaryStage.setScene(new Scene(ap,600,400));
        primaryStage.show();
    }
    private void runDelayTask1(AtomicInteger time,DialogPane dialogPane,Stage stage){
        //            //延时任务方法1
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            // 给线程起个名字
            AtomicInteger atomicInteger = new AtomicInteger(0);
            thread.setName("MyThread--" + atomicInteger.getAndIncrement());
            return thread;
        };
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,threadFactory);
        executor.scheduleAtFixedRate(()->{
            int i = time.decrementAndGet();
            System.out.println(Thread.currentThread().getName()+"***time"+i);
            Platform.runLater(()->{
                if (i>=0) {
                    dialogPane.setContentText(i+"");
                }
                else {
                    executor.shutdown();
                    stage.close();
                }
            });

        },0,1, TimeUnit.SECONDS);
    }
    private ScheduledService runDelayTask2(AtomicInteger time,DialogPane dialogPane,Stage stage,ProgressBar bar){
        final int max = 10;
        //延时任务方法2.适用于频繁更新值
        class myScheduledService extends  ScheduledService<Integer>{
            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        int i = time.getAndDecrement();
                        //updateProgress(max-i, max);
                        return i;
                    }

                    @Override
                    protected void updateValue(Integer value) {
                        if (value < 0) {
                            myScheduledService.this.cancel();
                            stage.close();
                        } else {
                            ProgressBar header = (ProgressBar) dialogPane.getHeader();
                            double v = (double) (max - value) / (double) max;
                            header.setProgress(v);
                            dialogPane.setContentText(value + "");
                        }
                    }
                };
            }
        }
        ScheduledService<Integer> scheduledService = new myScheduledService();
        scheduledService.setDelay(Duration.seconds(0));
        scheduledService.setPeriod(Duration.seconds(1));
        scheduledService.start();
        return scheduledService;
    }

    public static void main(String[] args) {
//        System.out.println("main:"+Thread.currentThread().getName());
        launch(args);
    }

}
