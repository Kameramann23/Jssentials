/*
 * This file is part of Jssentials. Jssentials is free software:
 * you can redistribute it or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version. Jssentials is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along
 * with Jssentials. If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.jagswag2014;

import io.github.jagswag2014.commands.CommandChangeTime;
import io.github.jagswag2014.configuration.SettingsManager;
import io.github.jagswag2014.database.Database;
import io.github.jagswag2014.events.PlayerPreLogin;
import io.github.jagswag2014.events.PlayerQuit;
import io.github.jagswag2014.utils.PlayerManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Jssentials extends JavaPlugin {

    /**
     * Settings manager instance
     */
    private final SettingsManager settings = SettingsManager.getInstance();

    public HashMap<Player, PlayerManager> playerManagerHashMap;

    private Database db;

    @Override
    public void onEnable() {
        playerManagerHashMap = new HashMap<>();

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
        getCommand("time").setExecutor(new CommandChangeTime());
    }

    /**
     * Register all events
     */
    private void setupEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerPreLogin(this), this);
        pm.registerEvents(new PlayerQuit(this), this);
    }

    /**
     * Register database
     */
    private void setupDatabase() {
        db = new Database(this);
    }

    /**
     * Register metrics
     */
    private void setupMetrics() {
        /*Metrics metrics = */
        new Metrics(this);
    }

    /**
     * Returns active database
     *
     * @return database
     */
    public Database getDb() {
        return db;
    }

    /**
     * Returns the PlayerManager of the specified player
     *
     * @param player to retrieve PlayerManager for
     * @return player's PlayerManager
     */
    public PlayerManager getPlayerManager(Player player) {
        return playerManagerHashMap.getOrDefault(player, null);
    }
}
