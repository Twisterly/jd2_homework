package masha.cha.util;

import masha.cha.MysqlJdbcDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TriggerManager {

    Connection conn = new MysqlJdbcDataSource().getConnection();

    public TriggerManager() {
    }

    public void createSaveTrigger(){
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "create trigger " +
                            "tr_save_person\n" +
                            "before insert on person\n" +
                            "for each row\n" +
                            "set NEW.name = UPPER(NEW.name);");
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createUpdateTrigger() {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "create trigger " +
                            "tr_update_person\n" +
                            "before update on person\n" +
                            "for each row\n" +
                            "set NEW.name = LOWER(NEW.name);");
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void deleteSaveTrigger() {
        try {
            conn.createStatement().executeUpdate("drop trigger if exists " +
                    "tr_save_person;");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void deleteUpdateTrigger() {
        try {
            conn.createStatement().executeUpdate("drop trigger if exists " +
                    "tr_update_person;");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
