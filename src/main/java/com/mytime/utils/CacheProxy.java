package com.mytime.utils;


import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class CacheProxy {

    /**
     * cahce实例
     */
    private static CacheProxy instance = new CacheProxy();

    private Ehcache cache;

    private CacheProxy(){
        CacheManager cacheManager = CacheManager.create("config/ehcache.xml");
        cache = cacheManager.getCache("ehCache");
    }

    /**
     * 添加缓存key/value
     * @param key
     * @param value
     * @return
     */
    public static boolean put(Object key, Object value) {
        boolean result=true;
        try{
            Element element = new Element(key, value);
            CacheProxy.instance.cache.put(element);
        } catch (Exception e){
            result=false;
            Logger.error(CacheProxy.class, String.format("ehCache put failed, key=%s",key),e);
        }
        return result;
    }

    /**
     * 获取缓存key对应值
     * @param key
     * @return
     */
    public static Object get(Object key) {
        try{
            return CacheProxy.instance.cache.get(key);
        } catch (Exception e){
            Logger.error(CacheProxy.class, String.format("ehCache get failed, key=%s",key),e);
        }
        return null;
    }

    /**
     * 清除缓存key对应值
     * @param key
     * @return
     */
    public static boolean remove(Object key) {
        try{
            return CacheProxy.instance.cache.remove(key);
        } catch (Exception e){
            Logger.error(CacheProxy.class, String.format("ehCache remove failed, key=%s",key),e);
        }
        return false;
    }
}
