package my.first.exercise6.dao;

import my.first.base.entity.BaseEntity;

import java.io.Serializable;

public interface IPrint<K extends Serializable, E extends BaseEntity<K>> {
    void saveAndPrintIdentity(E entity);
}
