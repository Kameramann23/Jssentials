/*
 * This file is part of Jssentials-Spigot. Jssentials-Spigot is free software:
 * you can redistribute it or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version. Jssentials-Spigot is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along
 * with Jssentials-Spigot. If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.jagswag2014.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.jagswag2014.Jssentials;
import io.github.jagswag2014.configuration.SettingsManager;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private final SettingsManager settings = SettingsManager.getInstance();
    private Jssentials plugin;
    private DBType type;
    private HikariDataSource hikari;

    public Database(Jssentials plugin) {
        this.plugin = plugin;
        String type = determineType(settings.getConfig().getString("database"));

        Properties props = new Properties();
        if (getType().equals(DBType.MYSQL))
            props.setProperty("jdbcUrl", type + settings.getConfig().getString("server_properties.hostname")
                    + ":" + settings.getConfig().getString("port") + "/"
                    + settings.getConfig().getString("server_properties.database"));
        else
            props.setProperty("dataSourceClassName", type);

        props.setProperty("dataSource.user", settings.getConfig().getString("server_properties.username"));
        props.setProperty("dataSource.password", settings.getConfig().getString("server_properties.password"));
        props.setProperty("dataSource.databaseName", settings.getConfig().getString("server_properties.database"));
        props.put("dataSource.logWriter", new PrintWriter(System.out));
        hikari = new HikariDataSource(new HikariConfig(props));
    }

    private String determineType(String type) {
        String className;
        if (type.equalsIgnoreCase("mysql")) {
            className = "jdbc:mysql://";
            this.type = DBType.MYSQL;
        } else if (type.equalsIgnoreCase("mariadb")) {
            className = "org.mariadb.jdbc.MySQLDataSource";
            this.type = DBType.MARIADB;
        } else if (type.equalsIgnoreCase("postgresql")) {
            className = "org.postgresql.ds.PGSimpleDataSource";
            this.type = DBType.POSTGRESQL;
        } else if (type.equalsIgnoreCase("h2")) {
            className = "org.h2.jdbcx.JdbcDataSource";
            this.type = DBType.H2;
        } else {
            className = "org.sqlite.SQLiteDataSource";
            this.type = DBType.SQLITE;
        }
        return className;
    }

    private Connection getConnection() throws SQLException {
        return hikari.getConnection();
    }

    private DBType getType() {
        return type;
    }
}
