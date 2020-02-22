package cc.sdspar.spar.command;

import cc.sdspar.spar.network.api.Updater;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandUpdateClient extends CommandUpdate {

	@Override
	public String getName() {
		return "spup";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (server == null) {
			sender.sendMessage(new TextComponentString((char) 167 + "e").appendSibling(new TextComponentTranslation("command.update.server_warning")));
		}
		Thread installer = new Thread(new Updater.GrabUpdate(sender));
		installer.run();
		
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

}
