package com.ssn.zookeeper.core.exception;

import com.ssn.zookeeper.core.constants.ZookeeperErrorCode;

/**
 * @Author linchengdong
 * @Date 2024-05-29 11:20
 * @PackageName:com.ssn.zookeeper.core.exception
 * @ClassName: ZookeeperException
 * @Description: k8s异常类
 * @Version 1.0
 */
public class ZookeeperException extends RuntimeException{

    private Integer code;


    public ZookeeperException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public ZookeeperException(String message, Throwable cause) {
        super(message, cause);
        this.code = ZookeeperErrorCode.DEFAULT_ERROR;
    }

    public ZookeeperException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code =code;
    }


    public ZookeeperException(String message) {
        super(message);
        this.code = ZookeeperErrorCode.DEFAULT_ERROR;
    }
}
