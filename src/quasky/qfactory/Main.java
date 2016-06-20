package quasky.qfactory;

import java.util.Arrays;
//import java.util.Map.Entry;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
//import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.World;

public class Main extends JavaPlugin implements Listener {
	Plugin plug = this;
	String[] FactoryNames;
	Factory[] FactoryList;
	String[] bacon = new String[100];
	
	SettingsManager settings = SettingsManager.getInstance();
	
    @Override
    public void onEnable() {
    	settings.setup(this);
    	getServer().getPluginManager().registerEvents(new MasterListener(),this);
    	RecipeCreator();
    	BuildFactorys();
    }
    
    @Override
    public void onDisable() {}
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	if(cmd.getName().equalsIgnoreCase("qf") || cmd.getName().equalsIgnoreCase("qfactory")){
    		if(args.length == 0)
    		sender.sendMessage("[QFactory] For help, run /qf help");
    		else
	    		if(args[0].equalsIgnoreCase("worktool"))
	    			CMDworktool(sender);
	    		else if(args[0].equalsIgnoreCase("factorys") || args[0].equalsIgnoreCase("f")){
	    			if(args.length == 1)
	    				CMDlistfactorys(sender);
    				if(args.length == 2)
    					CMDfactoryinfo(sender, args);}
	    		else if(args[0].equalsIgnoreCase("process")  || args[0].equalsIgnoreCase("p"))
	    			CMDlistprocess(sender, args);
	    		else if(args[0].equalsIgnoreCase("reload"))
	    			CMDreloadqfactory(sender);
	    		else 
	        		sender.sendMessage("[QFactory] For help, run /qf help");}
    	return true;
    }
    
    
	private void BuildFactorys(){
    	FactoryNames = getConfig().getConfigurationSection("Factorys").getKeys(false).toArray(new String[0]);
    	FactoryList = new Factory[FactoryNames.length];
    	for(int i = 0; i<FactoryNames.length;i++){
    		System.out.println(FactoryNames[i]);
    		FactoryList[i] = new Factory(FactoryNames[i]);
    	} 	
    }
    
    private void RecipeCreator(){
    	//Creates the item
    	ItemStack Worktool = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta im = Worktool.getItemMeta();
        im.setDisplayName("Trusty worktool");
        im.setLore(Arrays.asList("Strange markings are", "etched into the", "handle..."));
        im.spigot().setUnbreakable(true);
        Worktool.setItemMeta(im);
	        //Creates the Recipe
	        ShapedRecipe WorktoolRecipe = new ShapedRecipe(Worktool);
	        WorktoolRecipe.shape(
	                        " gb",
	                        " dg",
	                        "b  ");
	        WorktoolRecipe.setIngredient('g', Material.GOLD_INGOT);
	        WorktoolRecipe.setIngredient('d', Material.DIAMOND);
	        WorktoolRecipe.setIngredient('b', Material.BLAZE_ROD);
	        Bukkit.getServer().addRecipe(WorktoolRecipe);
    
        //BrickTool
        ItemStack BrickTool = new ItemStack(Material.CLAY_BRICK, 1);
        ItemMeta BrkIM = BrickTool.getItemMeta();
        BrkIM.setDisplayName("Process Switchblade");
        BrkIM.setLore(Arrays.asList("Strange markings are", "etched into the", "handle..."));
        BrkIM.spigot().setUnbreakable(true);
        BrickTool.setItemMeta(BrkIM);
	        //Creates the Recipe
	        ShapedRecipe BrickToolRecipe = new ShapedRecipe(BrickTool);
	        BrickToolRecipe.shape(
	                        " g ",
	                        "gbg",
	                        " g ");
	        BrickToolRecipe.setIngredient('g', Material.GOLD_INGOT);
	        BrickToolRecipe.setIngredient('b', Material.CLAY_BRICK);
	        Bukkit.getServer().addRecipe(BrickToolRecipe);}
    
    private void CMDworktool(CommandSender sender){
    	//Gives the player the worktool, used to interface with the workbench.
    	if (!(sender instanceof Player))
    	    sender.sendMessage("Console can not run the woorktool command... :/ Sorry...");
    	else{
    		//Gets the players inventory
    	    PlayerInventory pi = ((HumanEntity) sender).getInventory();
    	    
    	    //Creates our BlazeTool item with name, lore, then the tags.
    	    ItemStack Worktool = new ItemStack(Material.BLAZE_ROD, 1);
    	    ItemMeta im = Worktool.getItemMeta();
            im.setDisplayName("Trusty worktool");
  	        im.setLore(Arrays.asList("Strange matkings are", "etched into the", "handle..."));
   	        im.spigot().setUnbreakable(true);
   	        Worktool.setItemMeta(im);
   	        pi.addItem(Worktool);

    	    //Creates our BrickTool item with name, lore, then the tags.
    	    Worktool = new ItemStack(Material.CLAY_BRICK, 1);
    	    im = Worktool.getItemMeta();
    	    im.setDisplayName("Process Switchblade");
    	    im.setLore(Arrays.asList("Strange matkings are", "etched into the", "handle..."));
    	    im.spigot().setUnbreakable(true);
    	    Worktool.setItemMeta(im);
    	    pi.addItem(Worktool);
    	    sender.sendMessage("[QFactory] As if by magic the worktools appear out of thin air");
    	}
    }
    
    private void CMDlistfactorys(CommandSender sender){
    	String flist = "";
		char delim;
		for(int i = 0; i<FactoryList.length;i++){
			if((i==FactoryList.length-1)) delim = '.'; else delim = ',';
			flist = flist +' '+ FactoryList[i].FactoryName + delim;}
		sender.sendMessage("[QFactory] Current list of factorys: " + flist);
	}
    
    private void CMDlistprocess(CommandSender sender, String[] args){
    	boolean gotem = false;
    	if(args.length == 2){
	    	for(int i = 0; i<FactoryList.length;i++){
	    		if(args[1].equalsIgnoreCase(FactoryList[i].FactoryName) ){
	    			FactoryList[i].ListProcesses(sender);
	    			gotem = true;}}}
    	if(gotem==false) sender.sendMessage("[QFactory] You did not supply a correct factory type");
    }
    
    private void CMDreloadqfactory(CommandSender sender){
    	sender.sendMessage(ChatColor.AQUA + "[QFactory] Recreating factory tables. This may take a bit.");
    	//da fuk
    	sender.sendMessage(ChatColor.GREEN + "[QFactory] Tables have been recreated.");
    }
    
    private void CMDfactoryinfo(CommandSender sender, String[] args){
    	boolean gotem = false;
    	for(int i = 0; i<FactoryList.length;i++){
			if(args[1].equalsIgnoreCase(FactoryList[i].FactoryName)){
				sender.sendMessage("[QFactory] Factory: " + FactoryList[i].FactoryName);
				sender.sendMessage("[QFactory] Build Cost: "+FactoryList[i].CreationCost+" "+FactoryList[i].CreationMaterial+"(s)");
				gotem = true;
				break;
			}
		}
    	if(gotem==false) sender.sendMessage("[QFactory] You did not supply a correct factory type");
    }
    
}




