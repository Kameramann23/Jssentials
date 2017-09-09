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

package io.github.jagswag2014;

import io.github.jagswag2014.configuration.SettingsManager;
import io.github.jagswag2014.database.Database;
import io.github.jagswag2014.database.MySQL;
import io.github.jagswag2014.database.SQLite;
import io.github.jagswag2014.managers.PlayerManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Jssentials extends JavaPlugin {

    /**
     * Settings manager instance
     */
    private final SettingsManager settings = SettingsManager.getInstance();

    public HashMap<Player, PlayerManager> playerManagers;

    /**
     * Database object
     */
    private Database db;

    @Override
    public void onEnable() {
        playerManagers = new HashMap<>();

        getLogger().info("Registering commands...");
        setupCommands();

        getLogger().info("Registering events...");
        setupEvents();

        getLogger().info("Registering database...");
        setupDatabase();

        getLogger().info("Registering metrics...");
        setupMetrics();
    }

    @Override
    public void onDisable() {

    }

    /**
     * Register all commands
     */
    private void setupCommands() {

    }

    /**
     * Register all events
     */
    private void setupEvents() {

    }

    /**
     * Register database
     */
    private void setupDatabase() {
        String dbType = settings.getConfig().getString("database");
        if (dbType.equalsIgnoreCase("mysql")) {
            db = new MySQL(this);
        } else {
            db = new SQLite(this);
        }
    }

    /**
     * Register metrics
     */
    private void setupMetrics() {
        /*Metrics metrics = */
        new Metrics(this);
    }
}
