/*
    This file is part of the HeavenMS MapleStory Server, commands OdinMS-based
    Copyleft (L) 2016 - 2019 RonanLana

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
   @Author: Roey Zeldin - command to sell all slots
*/
package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;
import client.inventory.InventoryType;
import client.inventory.Item;
import constants.id.ItemId;
import server.Shop;
import server.ShopFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SellCommand extends Command {
    {
        setDescription("Sells all items in an inventory tab, by item name, or all items of a specified name");
    }
    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: @sell <all, equip, use, setup, etc or cash> <sell slot amount> or @sell <Item name> or @sell item <item name>");
            return;
        }
        String type = params[0];
        Shop shop = ShopFactory.getInstance().getShop(1337); // this is the GM shop

        if (type.equalsIgnoreCase("item") && params.length >= 2) {
            String itemName = String.join(" ", params[1].split("_"));
            sellAllItemsByName(player, shop, itemName);
            return;
        }

        int sellSlotAmount = 101;
        if (params.length >= 2) {
            if (params.length >= 2) {
                try {
                    sellSlotAmount = Integer.parseInt(params[1]);
                } catch (NumberFormatException e) {
                    sellItemsByName(player, shop, type);
                    return;
                }
            }
        }

        boolean isAll = type.equalsIgnoreCase("all");
        if (!allTypesAsString.contains(type.toLowerCase()) && !isAll) {
            player.yellowMessage("Error: The specified slot type '" + type + "' does not exist.");
            return;
        }
        for (InventoryType inventoryType : allTypes) {
            if (isAll || inventoryType.name().toLowerCase().equals(type)) {
                if (isAll && inventoryType == InventoryType.CASH) {
                    continue;
                }
                for (short i = 0; i <= sellSlotAmount; i++) {
                    Item tempItem = c.getPlayer().getInventory(inventoryType).getItem((byte) i);
                    if (tempItem != null) {
                        shop.sell(c, inventoryType, i, tempItem.getQuantity());
                    }
                }
                if (!isAll) { // quick break
                    player.yellowMessage("Slot" + type + " sold!");
                    return;  // Early return after clearing the specific type
                }
            }
        }
        player.yellowMessage("All slots sold!");
    }
    private void sellItemsByName(Character player, Shop shop, String itemName) {
        for (InventoryType inventoryType : allTypes) {
            for (short i = 0; i < player.getInventory(inventoryType).getSlotLimit(); i++) {
                Item tempItem = player.getInventory(inventoryType).getItem((byte) i);
                if (tempItem != null && String.valueOf(tempItem.getItemId()).equalsIgnoreCase(itemName)) {
                    shop.sell(player.getClient(), inventoryType, i, tempItem.getQuantity());
                }
            }
        }
        player.yellowMessage("Items with name '" + itemName + "' sold!");
    }

    private void sellAllItemsByName(Character player, Shop shop, String itemName) {
        for (InventoryType inventoryType : allTypes) {
            for (short i = 0; i < player.getInventory(inventoryType).getSlotLimit(); i++) {
                Item tempItem = player.getInventory(inventoryType).getItem((byte) i);
                if (tempItem != null && String.valueOf(tempItem.getItemId()).toLowerCase().contains(itemName.toLowerCase())) {
                    shop.sell(player.getClient(), inventoryType, i, tempItem.getQuantity());
                }
            }
        }
        player.yellowMessage("All items containing '" + itemName + "' in their name sold!");
    }
    private final InventoryType[] allTypes = {InventoryType.EQUIP, InventoryType.USE, InventoryType.ETC, InventoryType.SETUP, InventoryType.CASH};
    private final Set<String> allTypesAsString = Set.of("equip", "use", "setup", "etc", "cash", "all", "item");
}