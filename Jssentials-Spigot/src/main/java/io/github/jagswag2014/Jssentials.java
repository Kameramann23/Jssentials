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

import io.github.jagswag2014.commands.*;
import io.github.jagswag2014.configuration.SettingsManager;
import io.github.jagswag2014.database.Database;
import io.github.jagswag2014.database.MySQL;
import io.github.jagswag2014.database.SQLite;
import io.github.jagswag2014.database.Type;
import io.github.jagswag2014.events.JOINEvent;
import io.github.jagswag2014.managers.PlayerManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
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
        if (getDb().getType().equals(Type.MYSQL)) {
            MySQL mysql = (MySQL) db;
            try {
                mysql.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Register all commands
     */
    private void setupCommands() {
        getCommand("afk").setExecutor(new AFKCommand(this));
        getCommand("gamemode").setExecutor(new GAMEMODECommand(this));
        getCommand("home").setExecutor(new HOMECommand(this));
        getCommand("homes").setExecutor(new HOMESCommand(this));
        getCommand("ignore").setExecutor(new IGNORECommand(this));
        getCommand("ignores").setExecutor(new IGNORESCommand(this));
        getCommand("motd").setExecutor(new MOTDCommand(this));
        getCommand("msg").setExecutor(new MSGCommand(this));
        getCommand("reply").setExecutor(new REPLYCommand(this));
        getCommand("rules").setExecutor(new RULESCommand(this));
        getCommand("socialspy").setExecutor(new SOCIALSPYCommand(this));
        getCommand("sudo").setExecutor(new SUDOCommand(this));
        getCommand("time").setExecutor(new TIMECommand(this));
        getCommand("tpaccept").setExecutor(new TPACCEPTCommand(this));
        getCommand("tpa").setExecutor(new TPACommand(this));
        getCommand("tpall").setExecutor(new TPALLCommand(this));
        getCommand("tp").setExecutor(new TPCommand(this));
        getCommand("tpdeny").setExecutor(new TPDENYCommand(this));
        getCommand("tphere").setExecutor(new TPHERECommand(this));
        getCommand("tptoggle").setExecutor(new TPTOGGLECommand(this));
        getCommand("warp").setExecutor(new WARPCommand(this));
        getCommand("warps").setExecutor(new WARPSCommand(this));
        getCommand("weather").setExecutor(new WEATHERCommand(this));
    }

    /**
     * Register all events
     */
    private void setupEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JOINEvent(this), this);
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

    /**
     * Returns the database
     *
     * @return database
     */
    public Database getDb() {
        return db;
    }
}
