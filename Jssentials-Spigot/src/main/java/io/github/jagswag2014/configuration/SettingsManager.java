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

package io.github.jagswag2014.configuration;

import io.github.jagswag2014.Jssentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;

public class SettingsManager {

    private static SettingsManager instance = new SettingsManager();
    private Jssentials plugin;
    private FileConfiguration config;
    private File cFile;
    private FileConfiguration locale;
    private File lFile;

    private SettingsManager() {
    }

    public static SettingsManager getInstance() {
        return instance;
    }

    public void setup(Jssentials plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        cFile = new File(plugin.getDataFolder(), "config.yml");
        saveConfig();

        locale = new YamlConfiguration();
        this.plugin.saveResource("locale_" + getConfig().getString("language") + ".yml", false);
        lFile = new File(plugin.getDataFolder(), "locale_" + getConfig().getString("language") + ".yml");
        saveLocale();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public FileConfiguration getLocale() {
        return locale;
    }

    public void saveConfig() {
        try {
            config.save(cFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLocale() {
        try {
            locale.save(lFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        YamlConfiguration.loadConfiguration(cFile);
    }

    public void reloadLocale() {
        YamlConfiguration.loadConfiguration(lFile);
    }

    public PluginDescriptionFile getDesc() {
        return plugin.getDescription();
    }
}
