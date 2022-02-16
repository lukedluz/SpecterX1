package com.lucas.specterx1;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucas.specterx1.comandos.Comandos;
import com.lucas.specterx1.listeners.PlayerListener;
import com.lucas.specterx1.outros.Data;
import com.lucas.specterx1.outros.Hooks;

public class Main extends JavaPlugin{
	
	private static Main instance;
	Data data = Data.getInstance();
	
	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("§7| §bSpecterX1              §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §bVersão 1.0             §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §fStatus: §aLigado         §7|");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("");
		Hooks.hookSimpleClans();
		Main.instance = this;
		data.setup(this);
		saveDefaultConfig();
		
		getCommand("x1").setExecutor(new Comandos());
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}

	public static Main getInstance(){
		return Main.instance;
	}
}
