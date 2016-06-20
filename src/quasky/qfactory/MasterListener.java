package quasky.qfactory;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MasterListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void useWorktoolOnFactory(PlayerInteractEvent event){
    	//Checks if this is a block interaction event, If not, We skip everything.
    	Action a = event.getAction();
    	if (a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_BLOCK){
    	Block b = event.getClickedBlock();
	    	if ((event.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD || event.getPlayer().getItemInHand().getType() == Material.CLAY_BRICK) && event.getPlayer().getItemInHand().getItemMeta().hasLore() && b.getType() == Material.WALL_SIGN){	
	    	Player p = event.getPlayer();
	    	Location l = b.getLocation();
	    	//ItemStack is = event.getItem();
	    	
	    		//World W = b.getWorld();
	    		
	    		//Sign is East. Furnace is -1x , Chest is +1x, Workbench is +1z
				if(b.getData() == 0x2){
	    			Location WBL = new Location(b.getWorld(),l.getX(),l.getY(),l.getZ()+1);
	    			Block WB = WBL.getBlock();
	        		p.sendMessage(WB.toString());
	        		p.sendMessage("East");}
	    		
	    		//Sign is West. Furnace i0s +1x , Chest is -1x, Workbench is -1z
				else if(b.getData() == 0x3){
	    			Location WBL = new Location(b.getWorld(),l.getX(),l.getY(),l.getZ()-1);
	    			Block WB = WBL.getBlock();
	        		p.sendMessage(WB.toString());
	        		p.sendMessage("West");}
	    		
				//Sign is North. Furnace is -1z , Chest is +1z, Workbench is +1x
				else if(b.getData() == 0x4){
	    			Location WBL = new Location(b.getWorld(),l.getX()+1,l.getY(),l.getZ());
	    			Block WB = WBL.getBlock();
	        		p.sendMessage(WB.toString());
	        		p.sendMessage("North");}
	    		
	    		//Sign is South. Furnace is +1z , Chest is -1z, Workbench is -1x
				else if(b.getData() == 0x5){
	    			Location WBL = new Location(b.getWorld(),l.getX()-1,l.getY(),l.getZ());
	    			Block WB = WBL.getBlock();
	        		p.sendMessage(WB.toString());
	        		p.sendMessage("South");}
				
				else 
	        		p.sendMessage("This message is imposible to get... :o ");
				
	    	}
	    }
    }
	
	@EventHandler
    public void PreventBreakingWithTools(BlockBreakEvent event){
		ItemStack is = event.getPlayer().getItemInHand();
		if ((is.getType() == Material.BLAZE_ROD || is.getType() == Material.CLAY_BRICK) && is.getItemMeta().hasLore()){
			event.setCancelled(true);}}
	
	
}
