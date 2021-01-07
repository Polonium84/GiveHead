package com.po.givehead;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.UUID;

public class GiveHead extends JavaPlugin{
    @Override
    public void onEnable(){
        getCommand("givehead").setExecutor(new GiveHeadExecutor());
        getLogger().info("定制头颅插件已启动");
        getLogger().info("作者：Polonium");
    }
}
class GiveHeadExecutor implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
        String value="eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y" +
                "5YmMwMzVjZGM4MGYxYWI1ZTExOThmMjlmM2FkM2ZkZDJiNDJkOWE2OWFlYjY0ZGU5OTA2ODE4MDBiOThkYyJ9fX0=";
        String count = "1";
        String player=sender.getName();
        if (args.length == 1) {
            value = args[0];
        } else if (args.length == 2) {
            value = args[0];
            count = args[1];
        } else
            return false;
        String command=String.format(
                "give %s minecraft:player_head{display:{Name:\"{\\\"text\\\":\\\"自定义头颅\\\"}\"}," +
                        "SkullOwner:{%s,Properties:{textures:[{Value:\"%s\"}]}}} %s",
                player,
                randomID(),
                value,
                count
        );
        Server server=sender.getServer();
        if(server.dispatchCommand(server.getConsoleSender(),command))
            return true;
        return false;
    }
    String randomID(){
        UUID uuid=UUID.randomUUID();
        String strU=uuid.toString().replace("-","");
        int[] I=new int[4];
        for(int i=0;i<4;i++){
            String s=strU.substring(8*i,8*i+8);
            I[i]=Integer.parseUnsignedInt(s,16);
        }
        String ID=String.format("Id:[I;%d,%d,%d,%d]",I[0],I[1],I[2],I[3]);
        return ID;
    }
}
