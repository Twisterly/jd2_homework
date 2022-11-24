package my.first.exercise8_3.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_3.model.Equipment;
import org.hibernate.SessionFactory;

public class EquipmentDao extends DaoBase<Integer, Equipment> {

    public EquipmentDao(SessionFactory sessionFactory) {
        super(sessionFactory, Equipment.class);
    }

    public EquipmentDao() {
        super(MysqlSessionFactory.getInstance(), Equipment.class);
    }
}
