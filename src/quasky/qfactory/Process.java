package quasky.qfactory;


public class Process{
	String ProcessName;
	String FuelMaterial;
	int FuelCount;
	String InputMaterial;
	int InputCount;
	String OutputMaterial;
	int OutputCount;
	SettingsManager settings = SettingsManager.getInstance();
	
	public Process(String fn, String pn) {
		ProcessName = pn;
		
		
		//Fuel 
		String temp = settings.getConfig().getConfigurationSection("Factorys."+fn+"."+ProcessName+".").getString("fuel");
		String delims = "[ ,]+";
		String[] tempstring = temp.split(delims);
		FuelMaterial = tempstring[0].toUpperCase();
		FuelCount = Integer.parseInt(tempstring[1]);
		
		
		//Input
		temp = settings.getConfig().getConfigurationSection("Factorys."+fn+"."+ProcessName+".").getString("input");
		tempstring = temp.split(delims);
		InputMaterial = tempstring[0].toUpperCase();
		InputCount = Integer.parseInt(tempstring[1]);
		
		//output
		temp = settings.getConfig().getConfigurationSection("Factorys."+fn+"."+ProcessName+".").getString("output");
		tempstring = temp.split(delims);
		OutputMaterial = tempstring[0].toUpperCase();
		OutputCount = Integer.parseInt(tempstring[1]);
		
		System.out.println("----"+FuelMaterial+", "+FuelCount);
		System.out.println("----"+InputMaterial+", "+InputCount);
		System.out.println("----"+OutputMaterial+", "+OutputCount);
		
    	//for(int i = 0; i<FactoryNames.length;i++){
    	//	FactoryList[i] = new Factory(FactoryNames[i]);
    	//	System.out.println(FactoryNames[i]);}
	}
	
	
}
