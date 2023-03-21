package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Util.Judge;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public abstract class BaseDAO<T> {

    private MongoTemplate mongoTemplate;
    private Class<T> Clazz = (java.lang.Class)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public BaseDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public T findOneByKey (String Key, String Value, String CollectionName) {
        Query query = new Query(Criteria.where(Key).is(Value));
        return mongoTemplate.findOne(query, Clazz, CollectionName);
    }

    public void Save (T t, String CollectionName) {
        mongoTemplate.insert(t, CollectionName);
    }

    public void RemoveByKey (String Key, String Value, String CollectionName) {
        Query query = new Query(Criteria.where(Key).is(Value));
        mongoTemplate.remove(query, Clazz, CollectionName);
    }

    public void UpdateByKey (String Key, String Value, String CollectionName, HashMap<String, Object> data) {
        Update update = new Update();
        for (Map.Entry<String, Object> m : data.entrySet()) {
            if (m.getValue() instanceof String) {
                update.set(m.getKey(), Judge.Numbers.isNumeric(m.getValue().toString()) ? Integer.valueOf(m.getValue().toString()) : m.getValue());
                continue;
            }
            if (m.getValue() instanceof List<?>) {
                update.set(m.getKey(), m.getValue());
                continue;
            }
            System.out.println(m.getValue().getClass());
            break;
        }
        Query query = new Query(Criteria.where(Key).is(Value));
        mongoTemplate.upsert(query, update, Clazz, CollectionName);
    }
}
