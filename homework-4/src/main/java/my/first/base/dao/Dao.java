package my.first.base.dao;

import my.first.base.entity.BaseEntity;

import java.io.Serializable;

public interface Dao<K extends Serializable, E extends BaseEntity<K>> {

    void delete(E entity);

    void saveOrUpdate(E entity);

    E findById(K id);

}
