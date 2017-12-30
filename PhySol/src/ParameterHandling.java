import java.util.*;
import java.util.stream.IntStream;

import physics_area.Electricity;


class ParameterHandling {

	/*
		Physics Classifications
			1 : voltage
			2 : resistance
			3 : current
			4 : speed
			5 : acceleration
			6 : velocity
	
	*/
	
	// public static String[] givenParameter = {"pan:110:volt", "pan:10:ohms"};
	// public static String requiredParameter = "pan:amperes"; 
	public static ArrayList<String> parametersNeeded = new ArrayList<String>();

	
	public static int[] mapGivenParameter(String[] givenParameters){
//		for(int i = 0; i<=Units.units.length; i++){
//			for(int j = 0; j<Units.units[i].length; j++){
//				System.out.print(Units.units[i][j] + " ");
//			}
//		}

		int gPLength = givenParameters.length;
		int[] phy_area = new int[gPLength];
		for(int i = 0; i<gPLength ; i++){
			String unit_spec = (givenParameters[i].split(":"))[2];
			

			phy_area[i] = checkUnitMap(unit_spec);
			// if(phy_cat[i] == 1)
			// 	System.out.println("	-VOLTAGE");
			// else if(phy_cat[i] == 2)
			// 	System.out.println("	-CURRENT");
			// else if(phy_cat[i] == 3)
			// 	System.out.println("	-RESISTANCE");
			// else if(phy_cat[i] == 4)
			// 	System.out.println("	-SPEED");
			// else if(phy_cat[i] == 5)
			// 	System.out.println("	-ACCELRATION");
		}
		
		return phy_area;
		
	}
	
	// Check unit of extracted parameters from Unit Dictionary
	public static int checkUnitMap(String unit_spec){

		for(int i = 0; i<Units.units.length ; i++){ //per row
			for(int j = 0; j<(Units.units[i].length) ; j++){ // per column
				if(Units.units[i][j].equalsIgnoreCase(unit_spec)){
					return i + 1;
				}
			}
		}
		return -1;
	}

	public static int mapRequiredParameter(String required){
		
		 String keyword = required.split(":")[1].toLowerCase();
		 int requiredCode = 0;
		 if(keyword.equalsIgnoreCase("voltage")){
			 requiredCode = 1;
		 }
		 
		 return requiredCode;
			
	}
	
	public static double extractNumeric(int value, String[] givenParameter, int[] phy_area){
		
		String gParam = givenParameter[findIndex(phy_area, value)];
		
		return Double.parseDouble(gParam.split(":")[1]);
	}
	
	public static String extractUnit(int value, String[] givenParameter, int[] phy_area){
		
		String gParam = givenParameter[findIndex(phy_area, value)];
		
		return gParam.split(":")[2];
	}
	
	
	
	public static int findIndex(int[] array, int value) {
	    for(int i=0; i<array.length; i++) 
	         if(array[i] == value)
	             return i;
	    
	    return -1;
	}
	
	public static boolean exists(int[] array, int value){
		
		for(int element : array){
			if(element == value){
				return true;
			}
		}
		
		return false;
	}
	
	public static void display(String[] derivation){
		
		for(String der : derivation){
			System.out.println(der);
		}
	}
	
	
	
	public static void formula_engine(String[] givenParameter, String requiredParameter){
		
		System.out.println("Given : ");
		System.out.println();
		for(String param : givenParameter){
			System.out.println("\t- " + param.split(":")[1] + " " + param.split(":")[2]);
			System.out.println();
		}
		
		System.out.println("Required : " + requiredParameter.split(":")[0] + "'s " + requiredParameter.split(":")[1]);
		System.out.println();
		
		System.out.println("Solution : ");
		System.out.println();
		
		
		String[] derivation;
		
		int[] given_phy_area = mapGivenParameter(givenParameter);
		
		int requiredCode = mapRequiredParameter(requiredParameter);
		
		if(requiredCode == 1){			//Voltage
				if(exists(given_phy_area, 2) && exists(given_phy_area, 3)){
						derivation = Electricity.elec_voltage_formula_cr(extractNumeric(2, givenParameter, given_phy_area), extractNumeric(3, givenParameter, given_phy_area), extractUnit(2, givenParameter, given_phy_area), extractUnit(3, givenParameter, given_phy_area));
						display(derivation);
				}
		}
		
		
	}
	
	

}