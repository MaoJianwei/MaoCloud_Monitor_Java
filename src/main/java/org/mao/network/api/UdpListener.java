package org.mao.network.api;

/**
 * Created by mao on 2016/9/30.
 */
public interface UdpListener {
    void processMessage(String msg);
}
