package com.maojianwei.maocloud.monitor.raspberry.network;

import com.maojianwei.maocloud.monitor.raspberry.network.api.UdpListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mao on 2016/9/30.
 */
public class NetworkCore {

    private static final int REPORT_PORT = 7181;

    private Bootstrap bootstrap;
    private List<UdpListener> listeners = new ArrayList<>();

    public void addListener(UdpListener udpListener){listeners.add(udpListener);}
    public void delListener(UdpListener udpListener){listeners.remove(udpListener);}

    /**
     * Start Udp Receiver.
     */
    public void startUdpRecv() {
        bootstrap = new Bootstrap();
        try{

            bootstrap.group(new NioEventLoopGroup())
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpReportRecvHandler(listeners));

            Channel ch = bootstrap.bind(REPORT_PORT).sync().channel();

        } catch(Exception e) {
            e.printStackTrace();
            bootstrap.config().group().shutdownGracefully();
        }
    }

    public void stopUdpRecv(){
        if(bootstrap != null) {
            bootstrap.config().group().shutdownGracefully();
            bootstrap = null;
        }
    }
}
