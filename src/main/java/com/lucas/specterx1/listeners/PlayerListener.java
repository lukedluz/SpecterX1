package com.lucas.specterx1.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lucas.specterx1.Main;
import com.lucas.specterx1.comandos.Comandos;
import com.lucas.specterx1.metodos.Metodos;
import com.lucas.specterx1.outros.Data;
import com.lucas.specterx1.outros.Mensagens;

public class PlayerListener implements Listener {
	
	@EventHandler
	void onDeath(PlayerDeathEvent e) {
		if (Comandos.emx1.contains(e.getEntity().getPlayer())) {
			Comandos.emx1.remove(e.getEntity().getPlayer());
			if (Comandos.emx1.isEmpty()) {
				Metodos.teleportSaida(e.getEntity());
				return;
			}
			Mensagens.mensagemMorreu(Comandos.emx1.get(0), e.getEntity().getPlayer());
			Metodos.setVencedor(Comandos.emx1.get(0));
			Metodos.teleportSaida(e.getEntity().getPlayer());
		}
	}
	
	@EventHandler
	void onQuit(PlayerQuitEvent e) {
		if (Comandos.convite.contains(e.getPlayer())) {
			Bukkit.broadcastMessage("§c" + e.getPlayer().getName() + " §edesconectou, x1 cancelado.");
			Metodos.finalizarX1();
		}
		
		if (Comandos.emx1.contains(e.getPlayer())) {
			Comandos.emx1.remove(e.getPlayer());
			if (Comandos.emx1.isEmpty()) {
				Metodos.teleportSaida(e.getPlayer());
				return;
			}
			Mensagens.mensagemFugiu(Comandos.emx1.get(0), e.getPlayer());
			Metodos.setVencedor(Comandos.emx1.get(0));
			Metodos.teleportSaida(e.getPlayer());
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	void blockComands(PlayerCommandPreprocessEvent e) {
		if (Main.getInstance().getConfig().getBoolean("BloquearComandos") && Comandos.emx1.contains(e.getPlayer())) {
			for (String comando : Main.getInstance().getConfig().getStringList("ComandosLiberados")) {
				if (e.getMessage().startsWith(comando))return;
			}
			e.getPlayer().sendMessage("§cVocê não pode usar comandos durante o x1.");
			e.setCancelled(true);
		}
	}
}
