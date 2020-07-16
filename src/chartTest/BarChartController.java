package chartTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dgbtds
 */
public class BarChartController {
    private ArrayList<Integer> arrayList=new ArrayList<>(2);
    private myScheduleService scheduleService;
    private XYChart.Series<Number, Number> detectorA = new XYChart.Series<>();
    private XYChart.Series<Number, Number> detectorB = new XYChart.Series<>();
    @FXML
    private BarChart<String,Number> barChart;
    @FXML
    private CategoryAxis categoryAxis1;
    @FXML
    private NumberAxis numberAxis1;
    @FXML
    private LineChart<Number,Number> LineChart;
    @FXML
    private NumberAxis LineChartX;
    @FXML
    private NumberAxis LineChartY;
    @FXML
    private LineChart<Number,Number> dynamicLineChart;
    @FXML
    private NumberAxis dynamicLineChartX;
    @FXML
    private NumberAxis dynamicLineChartY;
    @FXML
    private void initialize(){
        setbarChart();
        setLineChart();
        setDynamicLineChart();
    }
    @FXML
    private void monitorAction(){
        if (!scheduleService.isRunning()) {
            scheduleService.start();
        }
    }
    @FXML
    private void pauseAction(){
        if (scheduleService.isRunning()) {
            scheduleService.cancel();
            scheduleService.reset();
        }
    }
    private  void  setDynamicLineChart(){
        arrayList.add(0);
        arrayList.add(0);
        dynamicLineChartX.setLabel("时间");
        dynamicLineChartY.setLabel("温度");
        detectorA.setName("探测器A");
        detectorB.setName("探测器B");
        dynamicLineChart.getData().addAll(detectorA,detectorB);

        scheduleService=new myScheduleService();
        scheduleService.setPeriod(Duration.seconds(1));
        scheduleService.valueProperty().addListener((observer,oldValue,newValue)->{
            Integer integerA = arrayList.get(0);
            Integer integerB = arrayList.get(1);
            System.out.println("change: A="+integerA+" , B="+integerB);
            int xIndex=detectorA.getData().size();
            if (xIndex>30){
                xIndex=0;
                detectorA.getData().clear();
                detectorB.getData().clear();
                dynamicLineChartX.setLowerBound(0);
                dynamicLineChartX.setUpperBound(20);
            }
            if (xIndex>dynamicLineChartX.getUpperBound()-1) {
                dynamicLineChartX.setLowerBound(dynamicLineChartX.getLowerBound()+1);
                dynamicLineChartX.setUpperBound(dynamicLineChartX.getUpperBound()+1);
            }
            detectorA.getData().add( new XYChart.Data<Number,Number>(xIndex,integerA));
            detectorB.getData().add( new XYChart.Data<Number,Number>(xIndex,integerB));
        });

    }
    private void setLineChart() {
        LineChartX.setLabel("年");
        LineChartY.setLabel("万亿");
        XYChart.Data<Number, Number> c1 = new XYChart.Data<Number, Number>(2000, 100);
        XYChart.Data<Number, Number> c2 = new XYChart.Data<Number, Number>(2005, 200);
        XYChart.Data<Number, Number> c3 = new XYChart.Data<Number, Number>(2010, 300);
        XYChart.Data<Number, Number> c4 = new XYChart.Data<Number, Number>(2015, 400);

        XYChart.Data<Number, Number> a1 = new XYChart.Data<>(2000, 250);
        XYChart.Data<Number, Number> a2 = new XYChart.Data<>(2005, 300);
        XYChart.Data<Number, Number> a3 = new XYChart.Data<>(2010, 400);
        XYChart.Data<Number, Number> a4 = new XYChart.Data<>(2015, 450);

        XYChart.Series<Number, Number> China = new XYChart.Series<Number, Number>();
        China.getData().setAll(c1, c2,c3,c4);
        China.setName("中国");

        XYChart.Series<Number, Number> Americal = new XYChart.Series<Number, Number>();
        Americal.getData().setAll(a1, a2,a3,a4);
        Americal.setName("美国");

        LineChart.getData().addAll(China,Americal);

    }
    private void setbarChart(){
        categoryAxis1.setLabel("国家");
        numberAxis1.setLabel("生产总值");
        XYChart.Data<String, Number> c1 = new XYChart.Data<>("GDP", 900);
        XYChart.Data<String, Number> a2 = new XYChart.Data<>("GDP", 1000);
        XYChart.Data<String, Number> j3 = new XYChart.Data<>("GDP", 300);

        XYChart.Data<String, Number> c4 = new XYChart.Data<>("GNP", 500);
        XYChart.Data<String, Number> a5 = new XYChart.Data<>("GNP", 700);
        XYChart.Data<String, Number> j6 = new XYChart.Data<>("GNP", 600);


        XYChart.Series<String, Number> China = new XYChart.Series<String, Number>();
        China.setName("中国");
        China.getData().setAll(c1,c4);
        XYChart.Series<String, Number> Americal = new XYChart.Series<String, Number>();
        Americal.setName("美国");
        Americal.getData().setAll(a2,a5);
        XYChart.Series<String, Number> Japanese = new XYChart.Series<String, Number>();
        Japanese.setName("日本");
        Japanese.getData().setAll(j3,j6);

        barChart.getData().addAll(China,Americal,Japanese);
    }
    class myScheduleService extends ScheduledService<Integer>{
        @Override
        protected Task createTask() {

            return new Task() {
                @Override
                protected Integer call() throws Exception {
                    Random random = new Random();
                    int r1 = random.nextInt(100);
                    int r2 = random.nextInt(100);
                    arrayList.set(0,r1);
                    arrayList.set(1,r2);
                    System.out.println("random number : A="+r1+" , B="+r2);
                    return new Integer(1);
                }
            };
        }
    }
}
