package com.ssn.zookeeper.core.response;

import org.apache.zookeeper.data.Stat;
/**
 * @Author linchengdong
 * @Date 2024-05-29 12:29
 * @PackageName:com.ssn.zookeeper.core.response
 * @ClassName: StatePayload
 * @Description: TODO
 * @Version 1.0
 */
public class StatePayload{

    private String data;

    private Stat stat;

    public StatePayload() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
