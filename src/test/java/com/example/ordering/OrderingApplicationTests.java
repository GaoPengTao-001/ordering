package com.example.ordering;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class OrderingApplicationTests {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderingApplicationTests.class);
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.218.223.138:3306/config_platform?useUnicode=true&characterEncoding=utf8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root@123";
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);

        }
    }

    @Test
    void contextLoads() {
        List<Map<String, String>> tableNames = getTableNames();
        for (Map<String, String> tableDate : tableNames) {
            String tableName = tableDate.get("name");
            System.out.print(tableName);
            System.out.print("\t" + tableDate.get("comment"));
            System.out.print("\t\t\t\t" + "V1.0");
            System.out.println();
            List<Map<String, String>> colData = getTableCol(tableName);
            for (Map<String, String> col : colData) {
                System.out.print("\t" + "\t" + col.get("name"));
                System.out.print("\t" + col.get("type"));
                System.out.print("\t" + (StringUtils.isEmpty(col.get("comment")) ? "" : col.get("comment").replaceAll("\n", "")));
                System.out.print("\t" + "V1.0");
                System.out.println();

            }
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);

        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<Map<String, String>> getTableNames() {
        List<Map<String, String>> tableData = new ArrayList<>();
        Connection conn = getConnection();
        String tableSql = "SELECT\n" +
                "\tTABLE_NAME,\n" +
                "\tTABLE_COMMENT\n" +
                "FROM\n" +
                "\tinformation_schema. TABLES\n" +
                "WHERE\n" +
                "\ttable_schema = 'config_platform';";
        PreparedStatement pStemt = null;
        ResultSet rsmd = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rsmd = pStemt.executeQuery();
            while (rsmd.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("name", rsmd.getString(1));
                map.put("comment", rsmd.getString(2));
                tableData.add(map);
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rsmd.close();
                closeConnection(conn);
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return tableData;
    }


    public static List<Map<String, String>> getTableCol(String tableName) {
        List<Map<String, String>> tableData = new ArrayList<>();
        Connection conn = getConnection();
        String tableSql = "SELECT\n" +
                "\tCOLUMN_NAME,\n" +
                "\tCOLUMN_TYPE,\n" +
                "\tCOLUMN_COMMENT\n" +
                "FROM\n" +
                "\tinformation_schema.`COLUMNS`\n" +
                "WHERE\n" +
                "\tTABLE_SCHEMA = 'config_platform'\n" +
                "AND TABLE_NAME = '" + tableName + "'";
        PreparedStatement pStemt = null;
        ResultSet rsmd = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rsmd = pStemt.executeQuery();
            while (rsmd.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("name", rsmd.getString(1));
                map.put("type", rsmd.getString(2));
                map.put("comment", rsmd.getString(3));
                tableData.add(map);
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rsmd.close();
                closeConnection(conn);
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
        return tableData;
    }

}
