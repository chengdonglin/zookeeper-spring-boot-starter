package com.ssn.zookeeper.core.sequence;

import com.ssn.zookeeper.core.AbstractOperator;
import com.ssn.zookeeper.core.Session;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author linchengdong
 * @Date 2024-05-28 19:59
 * @PackageName:com.ssn.zookeeper.core
 * @ClassName: SequenceOperator
 * @Description: 自增id实现类
 * @Version 1.0
 */
public class SequenceOperator extends AbstractOperator implements Sequence{

    RetryPolicy retryPolicy = new ExponentialBackoffRetry(500,3);
    private final static Map<String,DistributedAtomicLong> SEQUENCE_MAP = new ConcurrentHashMap<>(10);

    public SequenceOperator(CuratorFramework curatorFramework) {
        super(curatorFramework);
    }

    @Override
    public Long nextId(String path) throws Exception {
        DistributedAtomicLong distributedAtomicLong = SEQUENCE_MAP.get(path);
        if (distributedAtomicLong == null) {
            synchronized (this) {
                distributedAtomicLong = new DistributedAtomicLong(getCurator(),path,retryPolicy);
                SEQUENCE_MAP.put(path,distributedAtomicLong);
            }
        }
        AtomicValue<Long> value = distributedAtomicLong.increment();
        return value.postValue();
    }

}
