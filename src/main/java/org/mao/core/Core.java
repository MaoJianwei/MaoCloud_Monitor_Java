package org.mao.core;

import io.netty.bootstrap.Bootstrap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mao.AppEntry;
import org.mao.base.NodeUdpInfo;
import org.mao.network.NetworkCore;
import org.mao.network.api.UdpListener;

/**
 * Created by mao on 2016/9/30.
 */
public class Core {

    private AppEntry appEntry;
    private NetworkCore networkCore;
    private UdpListener udpListener = new InternalUdpListener();

    private ObservableList<NodeUdpInfo> data = FXCollections.observableArrayList();

    public Core(AppEntry appEntry){
        this.appEntry = appEntry;
        networkCore = new NetworkCore();
    }

    public void start(){
        networkCore.startUdpRecv();
        networkCore.addListener(udpListener);
        appEntry.getMainPageGuiController().setTableViewDataBinding(data);
    }

    public void shutdown(){
        appEntry.getMainPageGuiController().cancalTableViewDataBinding();
        networkCore.delListener(udpListener);
        networkCore.stopUdpRecv();
    }



    private class InternalUdpListener implements UdpListener {
        @Override
        public void processMessage(String msg){
            NodeUdpInfo nodeUdpInfo = buildNodeUdpInfo(msg);

            int index = data.indexOf(nodeUdpInfo);
            if(index == -1){
                data.add(nodeUdpInfo);
            } else {
                data.set(index, nodeUdpInfo);
            }
        }

        private NodeUdpInfo buildNodeUdpInfo(String sourceMsg){
            NodeUdpInfo nodeUdpInfo = new NodeUdpInfo();

            String [] items = sourceMsg.split(";");

            for(String item : items){
                String [] kv = item.split("=");
                switch(kv[0]) {
                    case "IP":
                        nodeUdpInfo.setIp(kv[1]);
                        break;
                    case "CPU_Temp":
                        nodeUdpInfo.setCpuTemp(Double.parseDouble(kv[1]));
                        break;
                    case "GPU_Temp":
                        nodeUdpInfo.setGpuTemp(Double.parseDouble(kv[1]));
                        break;
                    case "Count":
                        nodeUdpInfo.setCount(Integer.parseInt(kv[1]));
                        break;
                    case "SysTime":
                        nodeUdpInfo.setSysTime(kv[1]);
                        break;
                    case "GPS":
                        String[] gpsInfo = kv[1].split(",");
                        if(!gpsInfo[0].equals("lost")){
                            nodeUdpInfo.setLatitude(Double.parseDouble(gpsInfo[0]));
                        }
                        if(!gpsInfo[1].equals("lost")) {
                            nodeUdpInfo.setLongitude(Double.parseDouble(gpsInfo[1]));
                        }
                        nodeUdpInfo.setSatellite(Integer.parseInt(gpsInfo[2]));
                        break;
                    case "GpsTime":
                        nodeUdpInfo.setGpsTime(kv[1]);
                        break;
                    case "Temperature":
                        nodeUdpInfo.setEnvTemp(Double.parseDouble(kv[1]));
                        break;
                    case "NodeName":
                        nodeUdpInfo.setName(kv[1].replace("Bigmao",""));
                        break;
                    default:
                }
            }

            return nodeUdpInfo;
        }
    }
}
