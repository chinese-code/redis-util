package com.open.source.redis.util;

import com.alibaba.fastjson.JSON;
import com.open.source.redis.config.Config;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tumingjian
 *         该类是JedisCommandsProxy的一个静态化类.可以在里面添加新的方法,包含了所有的基础命令.
 */
public class RedisUtil {
    /**
     * 强制简单的key有最大存活时间:1个月.
     *
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        String result = RedisCommandsProxy.commands.set(key, value);
        return result;
    }

    public static Set<String> keys(String pattern) {
        return RedisCommandsProxy.getCommands(MultiKeyCommands.class).keys(pattern);
    }

    public static String setNoExpire(String key, String value) {
        String result = RedisCommandsProxy.commands.set(key, value);
        return result;
    }

    public static String type(String key) {
        return RedisCommandsProxy.commands.type(key);
    }

    public static Long sadd(String key, String... member) {
        return RedisCommandsProxy.commands.sadd(key, member);
    }

    public static Long zlexcount(String key, String min, String max) {
        return RedisCommandsProxy.commands.zlexcount(key, min, max);
    }

    public static List<GeoCoordinate> geopos(String key, String... members) {
        return RedisCommandsProxy.commands.geopos(key, members);
    }

    public static Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return RedisCommandsProxy.commands.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public static Long pfadd(String key, String... elements) {
        return RedisCommandsProxy.commands.pfadd(key, elements);
    }

    public static Long lpushx(String key, String... string) {
        return RedisCommandsProxy.commands.lpushx(key, string);
    }

    public static ScanResult<String> sscan(String key, String cursor) {
        return RedisCommandsProxy.commands.sscan(key, cursor);
    }

    public static Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return RedisCommandsProxy.commands.zrevrangeByScore(key, max, min, offset, count);
    }

    public static Double incrByFloat(String key, double value) {
        return RedisCommandsProxy.commands.incrByFloat(key, value);
    }

    public static String get(String key) {
        return RedisCommandsProxy.commands.get(key);
    }

    public static Boolean sismember(String key, String member) {
        return RedisCommandsProxy.commands.sismember(key, member);
    }

    public static Long move(String key, int dbIndex) {
        return RedisCommandsProxy.commands.move(key, dbIndex);
    }

    public static String getrange(String key, long startOffset, long endOffset) {
        return RedisCommandsProxy.commands.getrange(key, startOffset, endOffset);
    }

    public static Long append(String key, String value) {
        return RedisCommandsProxy.commands.append(key, value);
    }

    public static long pfcount(String key) {
        return RedisCommandsProxy.commands.pfcount(key);
    }

    public static Long bitcount(String key, long start, long end) {
        return RedisCommandsProxy.commands.bitcount(key, start, end);
    }

    @Deprecated
    public static List<String> brpop(String arg) {
        return RedisCommandsProxy.commands.brpop(arg);
    }

    public static Set<String> zrevrangeByScore(String key, double max, double min) {
        return RedisCommandsProxy.commands.zrevrangeByScore(key, max, min);
    }

    public static Boolean getbit(String key, long offset) {
        return RedisCommandsProxy.commands.getbit(key, offset);
    }

    public static Long geoadd(String key, double longitude, double latitude, String member) {
        return RedisCommandsProxy.commands.geoadd(key, longitude, latitude, member);
    }

    public static Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return RedisCommandsProxy.commands.geoadd(key, memberCoordinateMap);
    }

    public static ScanResult<Tuple> zscan(String key, String cursor) {
        return RedisCommandsProxy.commands.zscan(key, cursor);
    }

    public static Set<String> smembers(String key) {
        return RedisCommandsProxy.commands.smembers(key);
    }

    public static Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return RedisCommandsProxy.commands.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    public static ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return RedisCommandsProxy.commands.sscan(key, cursor, params);
    }

    public static List<String> lrange(String key, long start, long end) {
        return RedisCommandsProxy.commands.lrange(key, start, end);
    }

    public static List<String> sort(String key, SortingParams sortingParameters) {
        return RedisCommandsProxy.commands.sort(key, sortingParameters);
    }

    public static Long llen(String key) {
        return RedisCommandsProxy.commands.llen(key);
    }

    public static Long zremrangeByScore(String key, double start, double end) {
        return RedisCommandsProxy.commands.zremrangeByScore(key, start, end);
    }

    public static Set<String> zrange(String key, long start, long end) {
        return RedisCommandsProxy.commands.zrange(key, start, end);
    }

    public static Long zremrangeByLex(String key, String min, String max) {
        return RedisCommandsProxy.commands.zremrangeByLex(key, min, max);
    }

    public static List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return RedisCommandsProxy.commands.georadius(key, longitude, latitude, radius, unit, param);
    }

    private static String set(String key, String value, String nxxx, String expx, long time) {
        return RedisCommandsProxy.commands.set(key, value, nxxx, expx, time);
    }

    /**
     * @param key   key
     * @param value value
     * @param time  超时时间,单位(秒)
     * @return
     */
    public static String set(String key, String value, int time) {
        String result = RedisCommandsProxy.commands.set(key, value);
        if (time > 0) {
            expire(key, time);
        } else {
            throw new RuntimeException("expire time must be greater than zero");
        }
        return result;
    }

    /**
     * @param key   key
     * @param value value
     * @param time  超时时间,单位(毫秒)
     * @return
     */
    public static String pset(String key, String value, long time) {
        String result = RedisCommandsProxy.commands.set(key, value);
        if (time > 0) {
            pexpire(key, time);
        } else {
            throw new RuntimeException("expire time must be greater than zero");
        }
        return result;
    }

    public static Long zremrangeByRank(String key, long start, long end) {
        return RedisCommandsProxy.commands.zremrangeByRank(key, start, end);
    }

    public static Long lpush(String key, String... string) {
        return RedisCommandsProxy.commands.lpush(key, string);
    }

    public static String lset(String key, long index, String value) {
        return RedisCommandsProxy.commands.lset(key, index, value);
    }

    public static Long persist(String key) {
        return RedisCommandsProxy.commands.persist(key);
    }

    public static String hmset(String key, Map<String, String> hash) {
        return RedisCommandsProxy.commands.hmset(key, hash);
    }

    public static Long scard(String key) {
        return RedisCommandsProxy.commands.scard(key);
    }

    public static Boolean setbit(String key, long offset, String value) {
        return RedisCommandsProxy.commands.setbit(key, offset, value);
    }

    public static Long decr(String key) {
        return RedisCommandsProxy.commands.decr(key);
    }

    public static Long hdel(String key, String... field) {
        return RedisCommandsProxy.commands.hdel(key, field);
    }

    public static Long zrevrank(String key, String member) {
        return RedisCommandsProxy.commands.zrevrank(key, member);
    }

    public static Map<String, String> hgetAll(String key) {
        return RedisCommandsProxy.commands.hgetAll(key);
    }

    public static Boolean hexists(String key, String field) {
        return RedisCommandsProxy.commands.hexists(key, field);
    }

    public static Long lrem(String key, long count, String value) {
        return RedisCommandsProxy.commands.lrem(key, count, value);
    }

    public static String psetex(String key, long milliseconds, String value) {
        return RedisCommandsProxy.commands.psetex(key, milliseconds, value);
    }

    public static Double zscore(String key, String member) {
        return RedisCommandsProxy.commands.zscore(key, member);
    }

    public static Long rpush(String key, String... string) {
        return RedisCommandsProxy.commands.rpush(key, string);
    }

    public static Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return RedisCommandsProxy.commands.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Deprecated
    public static List<String> blpop(String arg) {
        return RedisCommandsProxy.commands.blpop(arg);
    }

    public static Long hsetnx(String key, String field, String value) {
        return RedisCommandsProxy.commands.hsetnx(key, field, value);
    }

    public static String getSet(String key, String value) {
        return RedisCommandsProxy.commands.getSet(key, value);
    }

    public static String substr(String key, int start, int end) {
        return RedisCommandsProxy.commands.substr(key, start, end);
    }

    public static Long hincrBy(String key, String field, long value) {
        return RedisCommandsProxy.commands.hincrBy(key, field, value);
    }

    public static Long zadd(String key, Map<String, Double> scoreMembers) {
        return RedisCommandsProxy.commands.zadd(key, scoreMembers);
    }

    public static ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return RedisCommandsProxy.commands.zscan(key, cursor, params);
    }

    public static Long pttl(String key) {
        return RedisCommandsProxy.commands.pttl(key);
    }

    public static Long rpushx(String key, String... string) {
        return RedisCommandsProxy.commands.rpushx(key, string);
    }

    public static Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return RedisCommandsProxy.commands.zrevrangeByScoreWithScores(key, max, min);
    }

    public static Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return RedisCommandsProxy.commands.zrangeByScore(key, min, max, offset, count);
    }

    public static Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return RedisCommandsProxy.commands.zrangeWithScores(key, start, end);
    }

    public static Long incrBy(String key, long integer) {
        return RedisCommandsProxy.commands.incrBy(key, integer);
    }

    public static String hget(String key, String field) {
        return RedisCommandsProxy.commands.hget(key, field);
    }

    public static Long decrBy(String key, long integer) {
        return RedisCommandsProxy.commands.decrBy(key, integer);
    }

    public static Set<String> zrangeByLex(String key, String min, String max) {
        return RedisCommandsProxy.commands.zrangeByLex(key, min, max);
    }

    public static String lpop(String key) {
        return RedisCommandsProxy.commands.lpop(key);
    }

    public static Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return RedisCommandsProxy.commands.zrangeByScoreWithScores(key, min, max);
    }

    public static List<String> srandmember(String key, int count) {
        return RedisCommandsProxy.commands.srandmember(key, count);
    }

    public static ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return RedisCommandsProxy.commands.hscan(key, cursor, params);
    }

    public static Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return RedisCommandsProxy.commands.zrevrangeByScoreWithScores(key, max, min);
    }

    public static Long bitpos(String key, boolean value) {
        return RedisCommandsProxy.commands.bitpos(key, value);
    }

    public static String setex(String key, int seconds, String value) {
        return RedisCommandsProxy.commands.setex(key, seconds, value);
    }

    public static Long setnx(String key, String value) {
        return RedisCommandsProxy.commands.setnx(key, value);
    }

    public static List<String> geohash(String key, String... members) {
        return RedisCommandsProxy.commands.geohash(key, members);
    }

    public static Long setrange(String key, long offset, String value) {
        return RedisCommandsProxy.commands.setrange(key, offset, value);
    }

    public static Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return RedisCommandsProxy.commands.zrevrangeByScore(key, max, min, offset, count);
    }

    public static List<String> hmget(String key, String... fields) {
        return RedisCommandsProxy.commands.hmget(key, fields);
    }

    public static Boolean setbit(String key, long offset, boolean value) {
        return RedisCommandsProxy.commands.setbit(key, offset, value);
    }

    public static Long del(String key) {
        return RedisCommandsProxy.commands.del(key);
    }

    public static Set<String> spop(String key, long count) {
        return RedisCommandsProxy.commands.spop(key, count);
    }

    public static String srandmember(String key) {
        return RedisCommandsProxy.commands.srandmember(key);
    }

    @Deprecated
    public static ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {
        return RedisCommandsProxy.commands.hscan(key, cursor);
    }

    public static Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return RedisCommandsProxy.commands.zrevrangeByLex(key, max, min, offset, count);
    }

    public static Double zincrby(String key, double score, String member) {
        return RedisCommandsProxy.commands.zincrby(key, score, member);
    }

    public static Long expire(String key, int seconds) {
        return RedisCommandsProxy.commands.expire(key, seconds);
    }

    public static Long zremrangeByScore(String key, String start, String end) {
        return RedisCommandsProxy.commands.zremrangeByScore(key, start, end);
    }

    public static String ltrim(String key, long start, long end) {
        return RedisCommandsProxy.commands.ltrim(key, start, end);
    }

    public static Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return RedisCommandsProxy.commands.zrangeByLex(key, min, max, offset, count);
    }

    public static ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return RedisCommandsProxy.commands.hscan(key, cursor);
    }

    public static Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return RedisCommandsProxy.commands.zrangeByScore(key, min, max, offset, count);
    }

    public static Boolean exists(String key) {
        return RedisCommandsProxy.commands.exists(key);
    }

    public static Long expireAt(String key, long unixTime) {
        return RedisCommandsProxy.commands.expireAt(key, unixTime);
    }

    public static Long hset(String key, String field, String value) {
        return RedisCommandsProxy.commands.hset(key, field, value);
    }

    public static Long zadd(String key, double score, String member, ZAddParams params) {
        return RedisCommandsProxy.commands.zadd(key, score, member, params);
    }

    public static Long zcount(String key, String min, String max) {
        return RedisCommandsProxy.commands.zcount(key, min, max);
    }

    public static Long ttl(String key) {
        return RedisCommandsProxy.commands.ttl(key);
    }

    public static Long pexpireAt(String key, long millisecondsTimestamp) {
        return RedisCommandsProxy.commands.pexpireAt(key, millisecondsTimestamp);
    }

    public static Long zcard(String key) {
        return RedisCommandsProxy.commands.zcard(key);
    }

    public static Long zrem(String key, String... member) {
        return RedisCommandsProxy.commands.zrem(key, member);
    }

    public static Long srem(String key, String... member) {
        return RedisCommandsProxy.commands.srem(key, member);
    }

    public static Long incr(String key) {
        return RedisCommandsProxy.commands.incr(key);
    }

    public static Double geodist(String key, String member1, String member2) {
        return RedisCommandsProxy.commands.geodist(key, member1, member2);
    }

    @Deprecated
    public static ScanResult<Tuple> zscan(String key, int cursor) {
        return RedisCommandsProxy.commands.zscan(key, cursor);
    }

    public static List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return RedisCommandsProxy.commands.georadius(key, longitude, latitude, radius, unit);
    }

    public static Set<String> zrangeByScore(String key, String min, String max) {
        return RedisCommandsProxy.commands.zrangeByScore(key, min, max);
    }

    public static Long zrank(String key, String member) {
        return RedisCommandsProxy.commands.zrank(key, member);
    }

    public static Set<String> zrevrangeByScore(String key, String max, String min) {
        return RedisCommandsProxy.commands.zrevrangeByScore(key, max, min);
    }

    public static Set<String> hkeys(String key) {
        return RedisCommandsProxy.commands.hkeys(key);
    }

    public static Long pexpire(String key, long milliseconds) {
        return RedisCommandsProxy.commands.pexpire(key, milliseconds);
    }

    public static List<String> brpop(int timeout, String key) {
        return RedisCommandsProxy.commands.brpop(timeout, key);
    }

    public static Double hincrByFloat(String key, String field, double value) {
        return RedisCommandsProxy.commands.hincrByFloat(key, field, value);
    }

    public static String echo(String string) {
        return RedisCommandsProxy.commands.echo(string);
    }

    public static Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return RedisCommandsProxy.commands.geodist(key, member1, member2, unit);
    }

    public static List<String> hvals(String key) {
        return RedisCommandsProxy.commands.hvals(key);
    }

    // public static String set(String key, String value, String nxxx) {
    //    return RedisCommandsProxy.commands.set(key, value, nxxx);
    // }

    public static Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return RedisCommandsProxy.commands.zrangeByScoreWithScores(key, min, max);
    }

    public static Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return RedisCommandsProxy.commands.linsert(key, where, pivot, value);
    }

    public static Long zadd(String key, double score, String member) {
        return RedisCommandsProxy.commands.zadd(key, score, member);
    }

    public static Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return RedisCommandsProxy.commands.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    public static Long bitpos(String key, boolean value, BitPosParams params) {
        return RedisCommandsProxy.commands.bitpos(key, value, params);
    }

    public static Long strlen(String key) {
        return RedisCommandsProxy.commands.strlen(key);
    }

    public static Long zcount(String key, double min, double max) {
        return RedisCommandsProxy.commands.zcount(key, min, max);
    }

    public static String spop(String key) {
        return RedisCommandsProxy.commands.spop(key);
    }

    public static List<String> sort(String key) {
        return RedisCommandsProxy.commands.sort(key);
    }

    public static String lindex(String key, long index) {
        return RedisCommandsProxy.commands.lindex(key, index);
    }

    public static Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return RedisCommandsProxy.commands.zrevrangeWithScores(key, start, end);
    }

    public static Long hlen(String key) {
        return RedisCommandsProxy.commands.hlen(key);
    }

    public static Double zincrby(String key, double score, String member, ZIncrByParams params) {
        return RedisCommandsProxy.commands.zincrby(key, score, member, params);
    }

    public static String rpop(String key) {
        return RedisCommandsProxy.commands.rpop(key);
    }

    public static List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return RedisCommandsProxy.commands.georadiusByMember(key, member, radius, unit, param);
    }

    public static Set<String> zrevrange(String key, long start, long end) {
        return RedisCommandsProxy.commands.zrevrange(key, start, end);
    }

    public static Set<String> zrangeByScore(String key, double min, double max) {
        return RedisCommandsProxy.commands.zrangeByScore(key, min, max);
    }

    public static Set<String> zrevrangeByLex(String key, String max, String min) {
        return RedisCommandsProxy.commands.zrevrangeByLex(key, max, min);
    }

    @Deprecated
    public static ScanResult<String> sscan(String key, int cursor) {
        return RedisCommandsProxy.commands.sscan(key, cursor);
    }

    public static Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return RedisCommandsProxy.commands.zadd(key, scoreMembers, params);
    }

    public static List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return RedisCommandsProxy.commands.georadiusByMember(key, member, radius, unit);
    }

    public static List<String> blpop(int timeout, String key) {
        return RedisCommandsProxy.commands.blpop(timeout, key);
    }

    public static Long bitcount(String key) {
        return RedisCommandsProxy.commands.bitcount(key);
    }

    /**
     * redis代理类
     */
    public static class RedisCommandsProxy {
        public static final Logger logger = LoggerFactory.getLogger(RedisCommandsProxy.class);
        private static volatile JedisPool jedisPool;
        private static ReentrantLock locker = new ReentrantLock();
        private static AtomicInteger errorCount = new AtomicInteger(0);
        private static JedisCommands commands;

        static {
            createPool();
            getJedisCommands();
        }

        public RedisCommandsProxy() {

        }

        private static JedisCommands getJedisCommands() {
            if (commands == null) {
                synchronized (RedisCommandsProxy.class) {
                    if (commands == null) {
                        Class<?>[] interfaces = Jedis.class.getInterfaces();
                        RedisCommandsProxy.commands = (JedisCommands) Proxy.newProxyInstance(RedisCommandsProxy.class.getClassLoader(), interfaces, new CommandProxyHandler());
                    }
                }
            }
            return commands;
        }

        public static <T> T getCommands(Class<T> classes) {
            Class<?>[] interfaces = Jedis.class.getInterfaces();
            T command = (T) Proxy.newProxyInstance(classes.getClassLoader(), interfaces, new CommandProxyHandler());
            return command;
        }

        private static void printCurrentPoolInfo() {
            int numWaiters = jedisPool.getNumWaiters();
            logger.info("当前等待线程数量:" + numWaiters);
            int numActive = jedisPool.getNumActive();
            logger.info("当前激活线程数量:" + numActive);
            int numIdle = jedisPool.getNumIdle();
            logger.info("当前空闲线程数量:" + numIdle);
        }

        private static class CommandProxyHandler implements InvocationHandler {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Exception exception = null;
                for (int i = 0; i < 5; i++) {
                    Jedis resource = null;
                    try {
                        resource = jedisPool.getResource();
                        Object invokeResult = method.invoke(resource, args);
                        return invokeResult;
                    } catch (Exception e) {
                        exception = e;
                        int value = errorCount.addAndGet(1);
                        logger.info("redis请求错误次数:" + value);
                    } finally {
                        if (resource != null) {
                            resource.close();
                        }
                    }
                }
                String errorMessage = MessageFormat.format("redis重试了5次后仍然失败了,command:{0},params:{1}", method.getName(), JSON.toJSON(args));
                logger.error(errorMessage);
                throw new RuntimeException(errorMessage, exception);
            }
        }


        /**
         * 创建连接池
         */
        public static void createPool() {
            if (null == jedisPool) {
                try {
                    locker.lock();
                    if (null == jedisPool) {
                        jedisPool = initPool();
                    }
                } catch (Exception e) {
                    logger.error("error:redis 线程池启动出错,需要立即处理.", e);
                    throw new RuntimeException(e);
                } finally {
                    locker.unlock();
                }
            }
        }

        private static JedisPool initPool() {
            Config config = new Config();
            logger.info(MessageFormat.format("redis client  config info:hostname:{0},port:{1},timeout:{2},maxtotal:{3},maxIdle:{4},minIdle:{5}",
                    config.getHostname(), config.getPort(), config.getTimeout(), config.getMaxTotal(), config.getMaxIdle(), config.getMinIdle()));
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxTotal(config.getMaxTotal());
            poolConfig.setMaxIdle(config.getMaxIdle());
            poolConfig.setMinIdle(config.getMinIdle());
            poolConfig.setMaxWaitMillis(config.getTimeout());
            logger.info("正在创建redis连接池..............");
            JedisPool jedisPool;
            if (config.getPassword() != null) {
                jedisPool = new JedisPool(poolConfig, config.getHostname(), config.getPort(), config.getTimeout(), config.getPassword());
            } else {
                jedisPool = new JedisPool(poolConfig, config.getHostname(), config.getPort(), config.getTimeout());
            }
            logger.info("redis连接池创建完成.");
            return jedisPool;
        }

        /**
         * 关闭destoryPool
         */
        public static void destoryPool() {
            if (!jedisPool.isClosed()) {
                logger.info("正在消毁redis线程池..............");
                jedisPool.close();
                logger.info("正在消毁redis线程池完成");
            }
        }
    }
}
