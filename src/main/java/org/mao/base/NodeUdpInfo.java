package org.mao.base;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;

/**
 * Created by mao on 2016/10/1.
 */
public class NodeUdpInfo {

    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty ip = new SimpleStringProperty("");
    private final SimpleStringProperty sysTime = new SimpleStringProperty("");
    private final SimpleIntegerProperty count = new SimpleIntegerProperty(0);
    private final SimpleDoubleProperty cpuTemp = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty gpuTemp = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty envTemp = new SimpleDoubleProperty(0);
    private final SimpleStringProperty gpsTime = new SimpleStringProperty("");
    private final SimpleDoubleProperty latitude = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty longitude = new SimpleDoubleProperty(0);
    private final SimpleIntegerProperty satellite = new SimpleIntegerProperty(0);

    @Override
    public boolean equals(Object nodeUdpInfo){
        if (!(nodeUdpInfo instanceof NodeUdpInfo)) {
            return false;
        }
        return ((NodeUdpInfo) nodeUdpInfo).getName().equals(this.getName());
    }

    public NodeUdpInfo setName(String name){
        this.name.set(name);
        return this;
    }
    public String getName(){
        return name.get();
    }

    public NodeUdpInfo setIp(String ip){
        this.ip.set(ip);
        return this;
    }
    public String getIp(){
        return ip.get();
    }

    public NodeUdpInfo setSysTime(String sysTime){
        this.sysTime.set(sysTime);
        return this;
    }
    public String getSysTime(){
        return sysTime.get();
    }

    public NodeUdpInfo setCount(int count){
        this.count.set(count);
        return this;
    }
    public int getCount(){
        return count.get();
    }

    public NodeUdpInfo setCpuTemp(double cpuTemp){
        this.cpuTemp.set(cpuTemp);
        return this;
    }
    public double getCpuTemp(){
        return cpuTemp.get();
    }

    public NodeUdpInfo setGpuTemp(double gpuTemp){
        this.gpuTemp.set(gpuTemp);
        return this;
    }
    public double getGpuTemp(){
        return gpuTemp.get();
    }

    public NodeUdpInfo setEnvTemp(double envTemp){
        this.envTemp.set(envTemp);
        return this;
    }
    public double getEnvTemp(){
        return envTemp.get();
    }

    public NodeUdpInfo setGpsTime(String gpsTime){
        this.gpsTime.set(gpsTime);
        return this;
    }
    public String getGpsTime(){
        return gpsTime.get();
    }

    public NodeUdpInfo setLatitude(double latitude){
        this.latitude.set(latitude);
        return this;
    }
    public double getLatitude(){
        return latitude.get();
    }

    public NodeUdpInfo setLongitude(double longitude){
        this.longitude.set(longitude);
        return this;
    }
    public double getLongitude(){
        return longitude.get();
    }

    public NodeUdpInfo setSatellite(int satellite){
        this.satellite.set(satellite);
        return this;
    }
    public int getSatellite(){
        return satellite.get();
    }
}
