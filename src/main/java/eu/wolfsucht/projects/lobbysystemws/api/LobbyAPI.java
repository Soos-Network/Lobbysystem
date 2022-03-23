package eu.wolfsucht.projects.lobbysystemws.api;


import eu.wolfsucht.projects.lobbysystemws.inventorys.Creator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

public class LobbyAPI {

    public void getItems(Player player) {

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.getInventory().setItem(0, Creator.itemcreator(Material.COMPASS, "§8» ┃ §9Teleporter", 1, (short)0, null));
        player.getInventory().setItem(2, Creator.itemcreator(Material.GOLDEN_AXE, "§8» ┃ §6PvP", 1, (short)0, null));
        player.getInventory().setItem(4, Creator.itemcreator(Material.BARRIER, "§fNo Gadgets choosed", 1, (short)0, null));
        player.getInventory().setItem(6, Creator.itemcreator(Material.CAKE, "§8» ┃ §5Gadgets", 1, (short)0, null));
        player.getInventory().setItem(8, Creator.itemcreator(Material.POTION, "§8» ┃ §cHide Players", 1, (short)0, null));

    }

    public void getScoreboard(Player player) {

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard s = sm.getNewScoreboard();
        s.registerNewObjective("board", "dummy");
        Objective o = s.getObjective("board");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§7 §8- §c§lSOOS §8- §7");
        o.getScore("§9§8§9§7§e§7").setScore(18);
        o.getScore("§7 §8┃ §7Rank").setScore(17);
        if (player.hasPermission("Prefix.Player")) {
            o.getScore("§7  §8» §9Spieler").setScore(16);
        } else if (player.hasPermission("Prefix.VIP")) {
            o.getScore("§7  §8» §6§lVIP").setScore(16);
        } else if (player.hasPermission("Prefix.JrBuilder")) {
            o.getScore("§7  §8» §2JrBuilder").setScore(16);
        } else if (player.hasPermission("Prefix.JrSupporter")) {
            o.getScore("§7  §8» §9JrSupporter").setScore(16);
        } else if (player.hasPermission("Prefix.JrModerator")) {
            o.getScore("§7  §8» §cJrModerator").setScore(16);
        } else if (player.hasPermission("Prefix.JrDeveloper")) {
            o.getScore("§7  §8» §JrDeveloper").setScore(16);
        } else if (player.hasPermission("Prefix.Builder")) {
            o.getScore("§7  §8» §2Builder").setScore(16);
        } else if (player.hasPermission("Prefix.Supporter")) {
            o.getScore("§7  §8» §9Supporter").setScore(16);
        } else if (player.hasPermission("Prefix.Moderator")) {
            o.getScore("§7  §8» §cModerator").setScore(16);
        } else if (player.hasPermission("Prefix.Developer")) {
            o.getScore("§7  §8» §3Developer").setScore(16);
        } else if (player.hasPermission("Prefix.TeamAdmin")) {
            o.getScore("§7  §8» §4Lead").setScore(16);
        } else if (player.hasPermission("Prefix.Admin")) {
            o.getScore("§7  §8» §4Admin").setScore(16);
        }
        o.getScore("§7§8§9§a§e§f").setScore(15);
        o.getScore("§7 §8┃ §7Coins").setScore(11);
        //o.getScore("§7  §8» §6" + CoinsSystem.getApi().getCoins(player)).setScore(10);
        o.getScore("§7  §8» §6 Coming Soon");
        o.getScore("§8§9").setScore(9);
        o.getScore("§7 §8┃ §7Players").setScore(8);
        o.getScore("§7  §8» §c"  + Bukkit.getServer().getOnlinePlayers().size()).setScore(7);
        o.getScore("§8§9§f§m").setScore(6);
        o.getScore("§7 §8┃ §7OnlineTime").setScore(5);
        //o.getScore("§7  §8» §3" + OnlineTimeAPI.getZeit(player.getUniqueId().toString()) + "§3h").setScore(4);
        o.getScore("§7  §8» §6 Coming Soon");
        o.getScore("§8§9§f§k§d").setScore(3);
        o.getScore("§7 §8┃ §7 Discord").setScore(2);
        o.getScore("§7  §8» §f Link will added soon").setScore(1);

        player.setScoreboard(s);

    }

}
