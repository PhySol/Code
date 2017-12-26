import java.util.*;

class ParameterHandling {

	/*
		Physics Classifications
			1 : voltage
			2 : resistance
			3 : current
			4 : velocity
			5 : acceleration
			6 : speed
	
	*/
	
	// public static String[] givenParameter = {"pan:110:volt", "pan:10:ohms"};
	// public static String requiredParameter = "pan:amperes"; 
	public static ArrayList<String> parametersNeeded = new ArrayList<String>();

	
	public static void mapGivenParameters(String[] givenParameters){
		for(int i = 0; i<=2; i++){
			for(int j = 0; j<Units.units[i].length; j++){
				System.out.print(Units.units[i][j] + " ");
			}
		}

		System.out.println(Units.units.length);
		int gPLength = givenParameters.length;
		int[] phy_cat = new int[gPLength];
		for(int i = 0; i<gPLength ; i++){
			String unit_spec = (givenParameters[i].split(":"))[2];
			System.out.println(unit_spec + ":");

			phy_cat[i] = checkUnitMap(unit_spec);
			if(phy_cat[i] == 1)
				System.out.println("	-VOLTAGE");
			else if(phy_cat[i] == 2)
				System.out.println("	-CURRENT");
			else if(phy_cat[i] == 3)
				System.out.println("	-RESISTANCE");
		}
		
	}
	
	// Check unit of extracted parameters from Unit Dictionary
	public static int checkUnitMap(String unit_spec){

		for(int i = 0; i<Units.units.length ; i++){ //per row
			for(int j = 0; j<(Units.units[i].length) ; j++){ // per column
				if(Units.units[i][j].equals(unit_spec)){
					return i + 1;
				}
			}
		}
		return -1;
	}
	
	

}