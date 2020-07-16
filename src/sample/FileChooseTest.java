package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/16 12:32
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


/**
 * @program: FXTest
 *
 * @description:文件夹/文件 单/多 选择
 *
 * @author: WuYe
 *
 * @create: 2020-02-16 12:32
 **/
public class FileChooseTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        VBox vBox = new VBox(10);

        TextField textField = new TextField();
        textField.setPrefWidth(800);
        textField.setPrefHeight(400);

        Button button1 = new Button("单选");
        Button button2 = new Button("多选");
        Button button3 = new Button("打开");
        Button button4 = new Button("保存");
        Button button5 = new Button("文件夹选择");
        button1.setOnAction(event -> {
            FileChooser fileChooser = FileChooseBuild();
            fileChooser.setTitle("单选文件");
            fileChooser.showOpenDialog(new Stage());
        });
        button2.setOnAction(event -> {
            FileChooser fileChooser = FileChooseBuild();
            fileChooser.setTitle("多选文件");
            fileChooser.showOpenMultipleDialog(new Stage());
        });
        button3.setOnAction(event -> {
            FileChooser fileChooser = FileChooseBuild();
            fileChooser.setTitle("打开文本");
            File file = fileChooser.showOpenDialog(new Stage());
                try {
                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader gbkReader = new InputStreamReader(fis, "GBK");
                    BufferedReader bufferedReader = new BufferedReader(gbkReader);
                    String s;
                    while ( (s = bufferedReader.readLine())!=null){
                       textField.appendText(s);
                    }
                } catch (FileNotFoundException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        button4.setOnAction(event -> {
            FileChooser fileChooser = FileChooseBuild();
        fileChooser.setTitle("保存文件");
        File file = fileChooser.showSaveDialog(new Stage());
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos,"GBK");
            osw.write(textField.getText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (osw!=null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
        TextField DirectoryName = new TextField();
        DirectoryName.setPromptText("选择文件夹路径");
        DirectoryName.setPrefWidth(800);
        button5.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择文件夹");
            directoryChooser.setInitialDirectory(new File("D:"+File.separator+"bigdata"));
            File file = directoryChooser.showDialog(new Stage());
            if(file!=null) {
                DirectoryName.setText(file.getAbsolutePath());
            }
            else {
                DirectoryName.setText("没有选择文件夹");
            }
        });

        vBox.getChildren().addAll(button1,button2,button3,button4,textField,DirectoryName,button5);


        anchorPane.getChildren().addAll(vBox);
        primaryStage.setScene(new Scene(anchorPane,1200,800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static FileChooser FileChooseBuild(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("图片类型","*.jpg","*.png"),
                new FileChooser.ExtensionFilter("文本类型","*.txt","*.dat"),
                new FileChooser.ExtensionFilter("所有类型","*.*")
        );
        fileChooser.setInitialDirectory(new File("D:"+File.separator+"bigdata"));
        return fileChooser;
    }
}
