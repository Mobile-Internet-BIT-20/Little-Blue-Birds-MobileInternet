package com.LLLT.MobileInternet.Dao;

import com.LLLT.MobileInternet.Util.Judgment.Number;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@Repository
public abstract class BaseDao<T> {

    private MongoTemplate mongoTemplate;
    private Class<T> Clazz = (java.lang.Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public BaseDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public T findOneByKey(String Key, String Value, String collectionName) {
        Query query = new Query(Criteria.where(Key).is(Value));
        return mongoTemplate.findOne(query, Clazz, collectionName);
    }
    public void Save(T t, String collectionName) {
        mongoTemplate.insert(t, collectionName);
    }
    public void RemoveByKey(String Key, String Value, String collectionName) {
        Query query = new Query(Criteria.where(Key).is(Value));
        mongoTemplate.remove(query, Clazz, collectionName);
    }
    public void UpdateByKey(String Key, String Value, String collectionName, HashMap<String, String> data) {
        Update update = new Update();
        for (Map.Entry<String, String> m : data.entrySet()) {
            update.set(m.getKey(), Number.isNumeric(m.getValue()) ? Integer.valueOf(m.getValue()) : m.getValue());
        }
        Query query = new Query(Criteria.where(Key).is(Value));
        mongoTemplate.upsert(query, update, Clazz, collectionName);
    }
}
