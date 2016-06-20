package quasky.qfactory;

import org.bukkit.command.CommandSender;


public class Factory{
	String FactoryName;
	String CreationMaterial;
	int CreationCost;
	Process[] ProcessList;
	SettingsManager settings = SettingsManager.getInstance();
	
	
	public Factory(String n){
		FactoryName = n;
		String temp = settings.getConfig().getConfigurationSection("Factorys."+FactoryName+".").getString("CreationCost");
		String delims = "[ ,]+";
		String[] CreationString = temp.split(delims);
		CreationMaterial = CreationString[0].toUpperCase();
		CreationCost = Integer.parseInt(CreationString[1]);
		System.out.println("---Cost to build: "+CreationMaterial+", "+CreationCost);
		
		String[] ProcessNames = settings.getConfig().getConfigurationSection("Factorys."+FactoryName).getKeys(false).toArray(new String[0]);
		ProcessList = new Process[ProcessNames.length];
    	for(int i = 1; i<ProcessNames.length;i++){
    		System.out.println("--"+ProcessNames[i]);
    		ProcessList[i-1] = new Process(FactoryName,ProcessNames[i]);
    	}
	}
	
	
	public void ListProcesses(CommandSender p){
		p.sendMessage("[QFactory] " + FactoryName + " has " + (ProcessList.length-1) + " process(es):");
		String prolist = " ";
		char delim;
		for(int i = 0; i<ProcessList.length-1;i++){
			if((i==ProcessList.length-1)) delim = '.'; else delim = ',';
			prolist = prolist +' '+ ProcessList[i].ProcessName + delim;}
		p.sendMessage("[QFactory] " + prolist);
	}
}
