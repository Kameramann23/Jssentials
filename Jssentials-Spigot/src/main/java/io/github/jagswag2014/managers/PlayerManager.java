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

package io.github.jagswag2014.managers;

import io.github.jagswag2014.Jssentials;
import io.github.jagswag2014.configuration.SettingsManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private Jssentials plugin;
    private Player player;
    private boolean afk;
    private Player messaging;
    private List<String> homes;
    private List<UUID> ignores;
    private boolean flying;

    public PlayerManager(Jssentials plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        messaging = null;
        afk = false;
        homes = new ArrayList<>();
        ignores = new ArrayList<>();
        flying = false;

        this.plugin.playerManagers.put(this.player, this);
    }

    public PlayerManager(Jssentials plugin, Player player, List<String> homes, List<UUID> ignores, boolean flying) {
        this.plugin = plugin;
        this.player = player;
        this.homes = homes;
        this.ignores = ignores;
        this.flying = flying;
        this.afk = false;
        this.messaging = null;

        this.plugin.playerManagers.put(this.player, this);
    }

    public boolean isAfk() {
        return afk;
    }

    public List<String> getHomes() {
        return homes;
    }

    public boolean isFlying() {
        return flying;
    }

    public List<UUID> getIgnores() {
        return ignores;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getMessaging() {
        return messaging;
    }

    public void setAfk(boolean afk) {
        this.afk = afk;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public void setHomes(List<String> homes) {
        this.homes = homes;
    }

    public void setIgnores(List<UUID> ignores) {
        this.ignores = ignores;
    }

    public void setMessaging(Player messaging) {
        this.messaging = messaging;
    }
}
