package com.lucas.specterx1.outros;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.Bukkit;

public class Hooks {

	public static SimpleClans sc;
	
	public static void hookSimpleClans(){
	    if(Bukkit.getServer().getPluginManager().getPlugin("SimpleClans") != null){
		       sc = ((SimpleClans)Bukkit.getServer().getPluginManager().getPlugin("SimpleClans"));
		    }
	}
}
