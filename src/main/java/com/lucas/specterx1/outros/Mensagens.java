package com.lucas.specterx1.outros;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;



public class Mensagens {
	
	private static Data data = Data.getInstance();
	
	public static String formatMessage(String message) {
		return message
				.replace("&", "§")
				.replace("voce", "você")
				.replace("Voce", "Você")
				.replace("nao", "não")
				.replace("Nao", "Não")
				.replace("ninguem", "ninguém")
				.replace("Ninguem", "Ninguém")
				.replace("numero", "número")
				.replace("Numero", "Número")
				.replace("ha", "há")
				.replace("ja", "já")
				.replace("Ja", "Já")
				.replace("sera", "será")
				.replace("chao", "chão");
	}
	
	public static void semPermMensagem(Player p) {
		p.sendMessage("§cVocê não tem acesso a este comando.");
	}
	
	public static void mensagemDesafiado(Player p, Player p2) {
		for (String msg : data.getMensagens().getStringList("MensagemDesafiado")) {
			Bukkit.broadcastMessage(formatMessage(msg).replace("{player}", p.getName()).replace("{desafiado}", p2.getName()));
		}
	}
	
	public static void mensagemPreferiuIgnorar(Player p, Player p2) {
		for (String msg : data.getMensagens().getStringList("PreferiuIgnorar")) {
			Bukkit.broadcastMessage(formatMessage(msg).replace("{player}", p.getName()).replace("{desafiado}", p2.getName()));
		}
	}
		
	public static void mensagemAceitou(Player p) {
		for (String msg : data.getMensagens().getStringList("AceitouDesafio")) {
			Bukkit.broadcastMessage(formatMessage(msg).replace("{desafiado}", p.getName()));
		}
	}
	
	public static void mensagemVoceFoiDesafiado(Player p, Player p2) {
		for (String msg : data.getMensagens().getStringList("VoceFoiDesafiado")) {
			p2.sendMessage(formatMessage(msg).replace("{player}", p.getName()));
		}
	}
	
	public static void mensagemRecusouDesafio(Player p, Player p2) {
		for (String msg : data.getMensagens().getStringList("RecusouDesafio")) {
			Bukkit.broadcastMessage(formatMessage(msg).replace("{player}", p.getName()).replace("{desafiado}", p2.getName()));
		}
	}
	
	public static void mensagemMorreu(Player winner, Player loser) {
		for (String msg : data.getMensagens().getStringList("VenceuMorte")) {
			Bukkit.broadcastMessage(formatMessage(msg).replace("{vencedor}", winner.getName()).replace("{perdedor}", loser.getName()));
		}
	}
	
	public static void mensagemFugiu(Player winner, Player loser) {
		for (String msg : data.getMensagens().getStringList("VenceuDisconnect")) {
			Bukkit.broadcastMessage(formatMessage(msg).replace("{vencedor}", winner.getName()).replace("{perdedor}", loser.getName()));
		}
	}
}
