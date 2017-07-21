package com.maojianwei.maocloud.monitor.raspberry.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mao on 2016/9/28.
 */
public class MainPageGuiController {

    private Map<String, Stage> stageMap = new HashMap<>();
    private boolean isFullScreen = true;

    public void addStage(String title, Stage stage){
        stageMap.put(title, stage);
    }
    public void delStage(String title){
        stageMap.remove(title);
    }
    public void delStage(Stage stage){
        stageMap.remove(stage.getTitle());
    }

    @FXML
    private TableView nodeTable;

    @FXML
    public void changeMainPageFullScreen(){
        if(isFullScreen){
            isFullScreen = false;
            stageMap.get("main").setFullScreen(false);
        }else{
            isFullScreen = true;
            stageMap.get("main").setFullScreen(true);
        }
    }

    public void setTableViewDataBinding(ObservableList data){

        List<TableColumn> columns = nodeTable.getColumns();
        for(TableColumn c : columns){
            switch(c.getText()){
                case "Name":
                    c.setCellValueFactory(new PropertyValueFactory<>("name"));
                    break;
                case "IP":
                    c.setCellValueFactory(new PropertyValueFactory<>("ip"));
                    break;
                case "SysTime":
                    c.setCellValueFactory(new PropertyValueFactory<>("sysTime"));
                    break;
                case "CpuT":
                    c.setCellValueFactory(new PropertyValueFactory<>("cpuTemp"));
                    break;
                case "GpuT":
                    c.setCellValueFactory(new PropertyValueFactory<>("gpuTemp"));
                    break;
            }
        }

        nodeTable.setItems(data);
    }
    public void cancalTableViewDataBinding(){
        nodeTable.setItems(null);
    }
}
