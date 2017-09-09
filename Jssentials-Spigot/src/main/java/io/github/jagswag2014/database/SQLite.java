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

import io.github.jagswag2014.Jssentials;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLite extends Database {

    /**
     * Database connection
     */
    private Connection connection;

    /**
     * Default constructor
     *
     * @param plugin main plugin instance
     */
    public SQLite(Jssentials plugin) {
        super(plugin, Type.SQLITE);
        initialize();
    }

    /**
     * Return database connection
     *
     * @return connection
     */
    public Connection getConnection() {
        File dbFile = new File(plugin.getDataFolder(), "Jssentials.db");
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (connection != null || !connection.isClosed())
                return connection;

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Initialize SQLite database
     */
    private void initialize() {
        try {
            PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS PLAYER_TABLE " +
                    "(ID INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, " +
                    "UUID CHAR(36) UNIQUE NOT NULL, " +
                    "USERNAME VARCHAR(32) NOT NULL, " +
                    "HOMES TEXT NULL" +
                    "IGNORES TEXT NULL);");

            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
