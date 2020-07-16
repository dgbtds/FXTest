package chartTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;

/**
 * @author dgbtds
 */
public class PieChartController {
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String,Number> barChart1;
    @FXML
    private BarChart<String,Number> barChart2;
    @FXML
    private BarChart<String,Number> barChart3;
    @FXML
    private CategoryAxis categoryAxis1;
    @FXML
    private CategoryAxis categoryAxis2;
    @FXML
    private CategoryAxis categoryAxis3;
    @FXML
    private NumberAxis numberAxis1;
    @FXML
    private NumberAxis numberAxis2;
    @FXML
    private NumberAxis numberAxis3;
    @FXML
    private Button change;
    @FXML
    private Button barChartButton;
    @FXML
    private void initialize(){
       pieChartInitialize();

    }
    @FXML
    private void changeClicked(){
        PieChart.Data data = pieChart.getData().get(0);
        data.setPieValue(data.getPieValue()*1.1);
    }

    private void pieChartInitialize(){
        PieChart.Data d1=new PieChart.Data("data1",40);
        PieChart.Data d2=new PieChart.Data("data2",10);
        PieChart.Data d3=new PieChart.Data("data3",10);
        PieChart.Data d4=new PieChart.Data("data4",10);
        PieChart.Data d5=new PieChart.Data("data5",20);
        ObservableList< PieChart.Data> dataList = FXCollections.observableArrayList();
        dataList.addAll(d1,d2,d3,d4,d5);
        pieChart.setData(dataList);
        pieChart.setStartAngle(90);
        pieChart.setLabelsVisible(true);
        pieChart.setLegendSide(Side.TOP);
        pieChart.getData().forEach(data -> {
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip(data.getName() + ":" + data.getPieValue());
            tooltip.setFont(new Font(20));
            Tooltip.install(node,tooltip);
            data.pieValueProperty().addListener((observer,oldValue,newValue)->{
                String s = String.format("%s : %.3f", data.getName(), newValue.doubleValue());
                tooltip.setText(s);
            });
        });
    }

    @FXML
    private void barChartButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
