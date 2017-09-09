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
import io.github.jagswag2014.managers.PlayerManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL extends Database {

    /**
     * MySQL connection pool
     */
    private HikariDataSource dataSource;

    /**
     * Default constructor
     *
     * @param plugin main plugin instance
     */
    public MySQL(Jssentials plugin) {
        super(plugin, Type.MYSQL);
        dataSource = new HikariDataSource(new HikariConfig("database.properties"));
        initialize();
    }

    /**
     * Returns the database connection
     *
     * @return database connection
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Initialize MySQL database
     */
    private void initialize() {
        try {
            PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS player_table (" +
                    "UUID CHAR(36) NOT NULL UNIQUE, " +
                    "USERNAME VARCHAR(32) NOT NULL," +
                    "HOMES TEXT NULL," +
                    "IGNORES TEXT NULL);");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveEntry(PlayerManager playerManager) {
        String homes = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playerManager.getHomes().size(); i++) {
            playerManager.getHomes().get(i);
        }

        String ignores = null;
        StringBuilder sb1 = new StringBuilder();
        for(int i = 0; i < playerManager.getIgnores().size(); i++) {

        }

        try {
            PreparedStatement ps = getConnection().prepareStatement("ALTER TABLE player_table ADD UNIQUE (UUID);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
