package com.lucas.specterx1.comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.lucas.specterx1.Main;
import com.lucas.specterx1.metodos.Metodos;
import com.lucas.specterx1.outros.Data;
import com.lucas.specterx1.outros.Mensagens;

public class Comandos implements CommandExecutor {
	
	public static ArrayList<Player> emx1 = new ArrayList<>();
	public static ArrayList<Player> convite = new ArrayList<>();

	Data data = Data.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String LsCommand, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cEste comando não pode ser executado pelo console.");
			return true;
		}
		
		final Player p = (Player)sender;
		
		if (LsCommand.equalsIgnoreCase("x1")) {
			
			if (args.length == 0) {
				for (String s : data.getMensagens().getStringList("Usage")) {
					if (!p.hasPermission("specterx1.admin") && s.contains("set")) {
						return true;
					}
					p.sendMessage("");
					p.sendMessage("§f§lREDE §b§lSPECTER §8§l- §eSistema de X1");
					p.sendMessage("§6/x1 desafiar <Jogador> §c- §eDesafia um jogador para x1.");
					p.sendMessage("§6/x1 aceitar §c- §eAceita o desafio de x1.");
					p.sendMessage("§6/x1 recusar §c- §eRecusa o desafio de x1.");
					p.sendMessage("§6/x1 camarote §c- §eTeleporta para o camarote.");
					p.sendMessage("§6/x1 setpos1 §c- §eSeta a posicao 1 do x1.");
					p.sendMessage("§6/x1 setpos2 §c- §eSeta a posicao 2 do x1.");
					p.sendMessage("§6/x1 setsaida §c- §eSeta a saida do x1");
					p.sendMessage("§6/x1 setcamarote §c- §eSeta o camarote do x1.");
					p.sendMessage("");
				}
				return true;
			}
				
			if (args[0].equalsIgnoreCase("desafiar")) {
				
				if (args.length == 1) {
					p.sendMessage(" §cUse /x1 desafiar (Jogador)");
					return true;
				}
				
				if (convite.size() > 0 || emx1.size() > 0) {
					p.sendMessage("§cJá existe um convite ou x1 em andamento, aguarde.");
					return true;
				}
				
				final Player p2 = Bukkit.getPlayer(args[1]);
				
				if (p2 == null) {
					p.sendMessage("§cJogador não encontrado.");
					return true;
				}
					
				if (p2.getName() == p.getName()) {
					p.sendMessage("§cVocê não pode desafiar a si mesmo.");
					return true;
				}
				
				convite.add(p);
				convite.add(p2);
				Mensagens.mensagemDesafiado(p, p2);
				p.sendMessage("§fDesafio enviado para §c" + p2.getName() + "§f.");
				Mensagens.mensagemVoceFoiDesafiado(p, p2);
					
				new BukkitRunnable() {
					
					@Override
					public void run() {
						
						convite.clear();
						Mensagens.mensagemPreferiuIgnorar(p, p2);
						
					}
				}.runTaskLater(Main.getInstance(), Main.getInstance().getConfig().getInt("ConviteTempo") * 20);
					
			}
				
			if (args[0].equalsIgnoreCase("setpos1")) {
				if (p.hasPermission("specterx1.setpos1")) {
					
					data.getData().set("Pos1.world", p.getLocation().getWorld().getName());
					data.getData().set("Pos1.x", p.getLocation().getX());
					data.getData().set("Pos1.y", p.getLocation().getY());
					data.getData().set("Pos1.z", p.getLocation().getZ());
					data.getData().set("Pos1.yaw", p.getLocation().getYaw());
					data.getData().set("Pos1.pitch", p.getLocation().getPitch());
					data.saveData();
			           
					p.sendMessage(" §ePosição 1 setada com sucesso.");
				} else {
					Mensagens.semPermMensagem(p);
				}
			}
				
			if (args[0].equalsIgnoreCase("setpos2")) {
				if (p.hasPermission("specterx1.setpos2")) {
					
					data.getData().set("Pos2.world", p.getLocation().getWorld().getName());
					data.getData().set("Pos2.x", p.getLocation().getX());
					data.getData().set("Pos2.y", p.getLocation().getY());
					data.getData().set("Pos2.z", p.getLocation().getZ());
					data.getData().set("Pos2.yaw", p.getLocation().getYaw());
					data.getData().set("Pos2.pitch", p.getLocation().getPitch());
					data.saveData();
			           
					p.sendMessage(" §ePosição 2 setada com sucesso.");
				} else {
					Mensagens.semPermMensagem(p);
				}
			}
			
			if (args[0].equalsIgnoreCase("setsaida")) {
				if (p.hasPermission("specterx1.setsaida")) {
					
					data.getData().set("Saida.world", p.getLocation().getWorld().getName());
					data.getData().set("Saida.x", p.getLocation().getX());
					data.getData().set("Saida.y", p.getLocation().getY());
					data.getData().set("Saida.z", p.getLocation().getZ());
					data.getData().set("Saida.yaw", p.getLocation().getYaw());
					data.getData().set("Saida.pitch", p.getLocation().getPitch());
					data.saveData();
			           
					p.sendMessage(" §eSaida setada com sucesso.");
				} else {
					Mensagens.semPermMensagem(p);
				}
			}
			
			if (args[0].equalsIgnoreCase("cancelar")) {
				if (p.hasPermission("specterx1.cancelar")) {
					if (!emx1.isEmpty()) {
						Bukkit.broadcastMessage("§ex1 finalizado por um administrador.");
						for(Player pp : emx1) {
							Metodos.teleportSaida(pp);
						}
						Metodos.finalizarX1();
					} else {
						p.sendMessage("§cNão há nenhum x1 acontecendo no momento.");
					}
				} else {
					Mensagens.semPermMensagem(p);
				}
			}
			
			if (args[0].equalsIgnoreCase("setcamarote")) {
				if (p.hasPermission("specterx1.setcamarote")) {
					
					data.getData().set("Camarote.world", p.getLocation().getWorld().getName());
					data.getData().set("Camarote.x", p.getLocation().getX());
					data.getData().set("Camarote.y", p.getLocation().getY());
					data.getData().set("Camarote.z", p.getLocation().getZ());
					data.getData().set("Camarote.yaw", p.getLocation().getYaw());
					data.getData().set("Camarote.pitch", p.getLocation().getPitch());
					data.saveData();
			           
					p.sendMessage("§eCamarote setado com sucesso.");
				} else {
					Mensagens.semPermMensagem(p);
				}
			}
			
			if (args[0].equalsIgnoreCase("aceitar")) {
				if (!convite.isEmpty()) {
					if (convite.get(1).equals(p)) {
						emx1.add(p);
						emx1.add(convite.get(0));
						Metodos.teleportX1(p, convite.get(0));
						convite.clear();
						Bukkit.getScheduler().cancelTasks(Main.getInstance());
						
						Mensagens.mensagemAceitou(p);
						
						new BukkitRunnable() {
							
							@Override
							public void run() {
								Bukkit.broadcastMessage("§eTivemos empate, x1 finalizado.");
								for(Player pp : emx1) {
									Metodos.teleportSaida(pp);
								}
								Metodos.finalizarX1();
							}
						}.runTaskLater(Main.getInstance(), Main.getInstance().getConfig().getInt("TempoMaximo") * 60 * 20);
						
					} else {
						p.sendMessage("§cVocê não tem um desafio pendente.");
					}
				} else {
					p.sendMessage("§cVocê não tem um desafio pendente.");
				}
			}
			
			if (args[0].equalsIgnoreCase("recusar")) {
				if (!convite.isEmpty()) {
					if (convite.get(1).equals(p)) {
						
						Mensagens.mensagemRecusouDesafio(convite.get(0), p);
						convite.clear();
						Bukkit.getScheduler().cancelTasks(Main.getInstance());
						
					} else {
						p.sendMessage("§cVocê não tem um desafio pendente.");
					}
				} else {
					p.sendMessage("§cVocê não tem um desafio pendente.");
				}
			}
			
			if (args[0].equalsIgnoreCase("camarote")) {
				if (Metodos.getCamaroteLocation() == null) {
					p.sendMessage("§cO camarote não foi definido.");
					return true;
				}
				if (emx1.size() <= 0) {
					p.sendMessage("§cNão há nenhum x1 acontecendo no momento.");
					return true;
				} else {
					p.teleport(Metodos.getCamaroteLocation());
					p.sendMessage("§eVocê foi teleportado para o camarote.");
				}
					
			}
				
		}
		return false;
	}
}
