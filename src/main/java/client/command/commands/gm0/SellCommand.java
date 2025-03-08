package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;
import client.inventory.Inventory;
import client.inventory.InventoryType;
import client.inventory.Item;
import server.ItemInformationProvider;
import server.Shop;
import server.ShopFactory;

import java.util.Arrays;
import java.util.Set;

public class SellCommand extends Command {
    {
        setDescription("Sells all items in an inventory tab or a specific item by name.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();

        if (params.length < 2) {
            player.yellowMessage("Syntax: @sell <all, equip, use, etc, setup, cash, item> [item_name or sell slot amount]");
            return;
        }

        String type = params[0].toLowerCase();
        Shop shop = ShopFactory.getInstance().getShop(1337); // GM Shop
        int sellSlotAmount = 101;

        if (type.equals("item")) {
            if (params.length < 3) {
                player.yellowMessage("Syntax: @sell item <item_name>");
                return;
            }
            String itemName = String.join(" ", Arrays.copyOfRange(params, 2, params.length)).toLowerCase();
            sellItemByName(c, shop, player, itemName);
            return;
        }

        if (params.length >= 2) {
            try {
                sellSlotAmount = Integer.parseInt(params[1]);
            } catch (NumberFormatException e) {
                player.yellowMessage("Invalid slot amount. Using default value: 101.");
            }
        }

        boolean isAll = type.equals("all");

        if (!allTypesAsString.contains(type)) {
            player.yellowMessage("Error: The specified inventory type '" + type + "' does not exist.");
            return;
        }

        for (InventoryType inventoryType : allTypes) {
            if (isAll || inventoryType.name().toLowerCase().equals(type)) {
                if (isAll && inventoryType == InventoryType.CASH) {
                    continue;
                }
                for (short i = 0; i <= sellSlotAmount; i++) {
                    Item tempItem = player.getInventory(inventoryType).getItem((byte) i);
                    if (tempItem != null) {
                        shop.sell(c, inventoryType, i, tempItem.getQuantity());
                    }
                }
                if (!isAll) {
                    player.yellowMessage("Sold all items in " + type + " inventory!");
                    return;
                }
            }
        }
        player.yellowMessage("All applicable inventory items have been sold!");
    }

    private void sellItemByName(Client c, Shop shop, Character player, String itemName) {
        ItemInformationProvider itemInfoProvider = ItemInformationProvider.getInstance();
        boolean itemFound = false;

        for (InventoryType inventoryType : allTypes) {
            Inventory inventory = player.getInventory(inventoryType);
            for (byte i = 0; i < inventory.getSlotLimit(); i++) {
                Item tempItem = inventory.getItem(i);
                if (tempItem != null) {
                    String tempItemName = itemInfoProvider.getName(tempItem.getItemId());
                    if (tempItemName != null && tempItemName.equalsIgnoreCase(itemName)) {
                        shop.sell(c, inventoryType, i, tempItem.getQuantity());
                        itemFound = true;
                    }
                }
            }
        }

        if (itemFound) {
            player.yellowMessage("Sold all items named '" + itemName + "'!");
        } else {
            player.yellowMessage("No items found with the name '" + itemName + "'.");
        }
    }

    private final InventoryType[] allTypes = {InventoryType.EQUIP, InventoryType.USE, InventoryType.ETC, InventoryType.SETUP, InventoryType.CASH};
    private final Set<String> allTypesAsString = Set.of("equip", "use", "setup", "etc", "cash", "all", "item");
}
