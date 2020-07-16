package worker;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/3/3 18:45
 */

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: FXTest
 *
 * @description:
 *
 * @author: WuYe
 *
 * @create: 2020-03-03 18:45
 **/
public class ScheduleServiceTest extends Application {
    private ArrayList<Integer> arrayList=new ArrayList<>(2);
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane ap=new AnchorPane();

        Button click = new Button("click");
        click.setPrefWidth(100);
        click.setPrefHeight(50);

        ap.getChildren().addAll(click);

            MyScheduleService myScheduleService = new MyScheduleService();
            myScheduleService.setPeriod(Duration.seconds(1));
        click.setOnAction(event -> {
            System.out.println("click");
            myScheduleService.start();

        });
        AnchorPane.setTopAnchor(click,200.0);
        AnchorPane.setLeftAnchor(click,200.0);
        primaryStage.setScene(new Scene(ap,600,400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    class MyScheduleService extends ScheduledService<Integer> {

        @Override
        protected Task<Integer> createTask() {
            return new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    System.out.println("1111111");
                    return null;
                }
            };
        }
    }
}
