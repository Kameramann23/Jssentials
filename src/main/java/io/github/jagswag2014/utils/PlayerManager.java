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

package io.github.jagswag2014.utils;

import org.bukkit.entity.Player;

public class PlayerManager {

    private int id;
    private Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }

    public PlayerManager(int id, Player player) {

    }
}
