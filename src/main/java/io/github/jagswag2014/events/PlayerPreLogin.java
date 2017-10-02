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

package io.github.jagswag2014.events;

import io.github.jagswag2014.Jssentials;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerPreLogin extends ZEventParent {

    public PlayerPreLogin(Jssentials plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {

    }
}
