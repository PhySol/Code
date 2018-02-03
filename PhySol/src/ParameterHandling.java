import java.util.*;
import physics_area.Electricity;
import physics_area.Kinematics;
import physics_area.Newton;


class ParameterHandling {

	/*
		Physics Classifications
			1 : voltage
			2 : current
			3 : resistance
			4 : power
			5 : speed
			6 : time
			7 : distance
			8 : force
			9 : mass
		   10 : acceleration

	
	*/
	
	// public static String[] givenParameter = {"pan:110:volt", "pan:10:ohms"};
	// public static String requiredParameter = "pan:amperes"; 
	public static ArrayList<String> parametersNeeded = new ArrayList<String>();
	public static String[] givenParameter;
	public static int[] given_phy_area;
	public static double[] multiplier;

	
	public static int[] mapGivenParameter(String[] givenParameters, int opt){
//		for(int i = 0; i<=Units.units.length; i++){
//			for(int j = 0; j<Units.units[i].length; j++){
//				System.out.print(Units.units[i][j] + " ");
//			}
//		}

		int gPLength = givenParameters.length;
		int[] phy_area = new int[gPLength];
		int tmp_j;
		double[] tmp_multiplier = new double[gPLength];
		for(int i = 0; i<gPLength ; i++){
			String unit_spec = (givenParameters[i].split(":"))[2];
			phy_area[i] = checkUnitMap(unit_spec, 0);
			tmp_j = checkUnitMap(unit_spec, 1);
			tmp_multiplier[i] = Double.parseDouble(Units.units[phy_area[i]-1][tmp_j].split(":")[1]);
		}
		multiplier = tmp_multiplier;
		
		return phy_area;
	}
	
	// Check unit of extracted parameters from Unit Dictionary
	public static int checkUnitMap(String unit_spec, int opt){
		
		for(int i = 0; i<Units.units.length ; i++){ //per row
			for(int j = 0; j<(Units.units[i].length) ; j++){ // per column
				if(Units.units[i][j].split(":")[0].equalsIgnoreCase(unit_spec)){
					if(opt == 0)
						return i + 1;
					else
						return j;
				}
			}
		}
		return -1;
	}

	public static int mapRequiredParameter(String required){
		
		 String keyword = required.split(":")[1].toLowerCase();
		 int requiredCode = 0;
		 if(keyword.equalsIgnoreCase("voltage"))
			requiredCode = 1;
		 
		 else if(keyword.equalsIgnoreCase("current"))
		 	requiredCode = 2;

		 else if(keyword.equalsIgnoreCase("resistance"))
		 	requiredCode = 3;

		 else if(keyword.equalsIgnoreCase("power"))
		 	requiredCode = 4;
		 
		 else if(keyword.equalsIgnoreCase("speed"))
			 requiredCode = 5;
		 
		 else if(keyword.equalsIgnoreCase("time"))
			 requiredCode = 6;
		 
		 else if(keyword.equalsIgnoreCase("distance"))
			 requiredCode = 7;
		 
		 else if(keyword.equalsIgnoreCase("force"))
			 requiredCode = 8;
		 
		 else if(keyword.equalsIgnoreCase("mass"))
			 requiredCode = 9;
		 
		 else if(keyword.equalsIgnoreCase("acceleration"))
			 requiredCode = 10;
		 return requiredCode;
			
	}
	
	public static double extractNumeric(int value){
		
		String gParam = givenParameter[findIndex(given_phy_area, value)];
		
		return Double.parseDouble(gParam.split(":")[1]);
	}
	
	public static String extractUnit(int value){
		
		String gParam = givenParameter[findIndex(given_phy_area, value)];
		
		return gParam.split(":")[2];
	}
	
	public static double extractMultiplier(int value){
		
		return multiplier[findIndex(given_phy_area, value)];
	}
	
	
	
	public static int findIndex(int[] array, int value) {
	    for(int i=0; i<array.length; i++) 
	         if(array[i] == value)
	             return i;
	    
	    return -1;
	}
	
	public static boolean exists(int value){
		
		for(int element : given_phy_area){
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

	public static String[] ArrayListConversion(ArrayList<String> aList){
		
		String[] temp = new String[aList.size()];
		//System.out.println(aList.size() + " " + temp.length);
		for(int i = 0; i<=temp.length - 1; i++){
			temp[i] = aList.get(i);
		}
		
		return temp;
	}
	
	
	
	public static void formula_engine(String[] givenParameter2, String requiredParameter){
		
		givenParameter = givenParameter2;
	
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
		
		given_phy_area = mapGivenParameter(givenParameter, 0);
		
		int requiredCode = mapRequiredParameter(requiredParameter);
		
		

		if(requiredCode == 1){			// Voltage
				// Current and Resistance
				if(exists(2) && exists(3)){
						derivation = Electricity.elec_voltage_formula_cr(extractNumeric(2), extractNumeric(3), extractUnit(2), extractUnit(3), extractMultiplier(2), extractMultiplier(3));
						display(derivation);
				}
				// Power and Current
				else if(exists(4) && exists(2)){
						derivation = Electricity.elec_voltage_formula_pc(extractNumeric(4), extractNumeric(2), extractUnit(4), extractUnit(2), extractMultiplier(4), extractMultiplier(2));
						display(derivation);
				}
				// Power and Resistance
				else if(exists(4) && exists(3)){
						derivation = Electricity.elec_voltage_formula_pr(extractNumeric(4), extractNumeric(3), extractUnit(4), extractUnit(3), extractMultiplier(4), extractMultiplier(3));
						display(derivation);
				}	
		}

		else if(requiredCode == 2){		// Current
				// Voltage and Resistance
				if(exists(1) && exists(3)){
						derivation = Electricity.elec_current_formula_vr(extractNumeric(1), extractNumeric(3), extractUnit(1), extractUnit(3), extractMultiplier(1), extractMultiplier(3));
						display(derivation);
				}
				// Power and Resistance
				else if(exists(4) && exists(3)){
						derivation = Electricity.elec_current_formula_pr(extractNumeric(4), extractNumeric(3), extractUnit(4), extractUnit(3), extractMultiplier(4), extractMultiplier(3));
						display(derivation);
				}
				// Power and Voltage
				else if(exists(4) && exists(1)){
						derivation = Electricity.elec_current_formula_pv(extractNumeric(4), extractNumeric(1), extractUnit(4), extractUnit(1), extractMultiplier(4), extractMultiplier(1));
						display(derivation);
				}
		}

		else if(requiredCode == 3){		//Resistance
				// Voltage and Current
				if(exists(1) && exists(2)){
						derivation = Electricity.elec_resistance_formula_vc(extractNumeric(1), extractNumeric(2), extractUnit(1), extractUnit(2), extractMultiplier(1), extractMultiplier(2));
						display(derivation);
				}
				// Power and Current
				else if(exists(4) && exists(2)){
						derivation = Electricity.elec_resistance_formula_pc(extractNumeric(4), extractNumeric(2), extractUnit(4), extractUnit(2), extractMultiplier(4), extractMultiplier(2));
						display(derivation);
				}
				// Voltage and Power
				else if(exists(1) && exists(4)){
						derivation = Electricity.elec_resistance_formula_vp(extractNumeric(1), extractNumeric(4), extractUnit(1), extractUnit(4), extractMultiplier(1), extractMultiplier(4));
						display(derivation);
				}
		}

		else if(requiredCode == 4){		//Power
				// Current and Voltage
				if(exists(2) && exists(1)){
						derivation = Electricity.elec_power_formula_cv(extractNumeric(2), extractNumeric(1), extractUnit(2), extractUnit(1), extractMultiplier(2), extractMultiplier(1));
						display(derivation);
				}
				// Voltage and Resistance
				else if(exists(1) && exists(3)){
						derivation = Electricity.elec_power_formula_vr(extractNumeric(1), extractNumeric(3), extractUnit(1), extractUnit(3), extractMultiplier(1), extractMultiplier(3));
						display(derivation);
				}
				// Current and Resistance
				else if(exists(2) && exists(3)){
						derivation = Electricity.elec_power_formula_cr(extractNumeric(2), extractNumeric(3), extractUnit(2), extractUnit(3), extractMultiplier(2), extractMultiplier(3));
						display(derivation);
				}
		}
		
		else if(requiredCode == 5){ //Speed
				derivation = Kinematics.kinematics_speed_formula_dt(extractNumeric(7), extractNumeric(6), extractUnit(7), extractUnit(6), extractMultiplier(7), extractMultiplier(6));
				display(derivation);
		}
				
		else if(requiredCode == 6){ //Time
				derivation = Kinematics.kinematics_time_formula_ds(extractNumeric(7), extractNumeric(5), extractUnit(7), extractUnit(5), extractMultiplier(7), extractMultiplier(7));
				display(derivation);
		}
				
		else if(requiredCode == 7){ //Distance
				derivation = Kinematics.kinematics_distance_formula_st(extractNumeric(5), extractNumeric(6), extractUnit(5), extractUnit(6), extractMultiplier(5), extractMultiplier(6));
				display(derivation);
		}
		
		else if(requiredCode == 8){ //Force
				derivation = Newton.newton_force_formula_ma(extractNumeric(9), extractNumeric(10), extractUnit(9), extractUnit(10), extractMultiplier(9), extractMultiplier(10));
				display(derivation);
		}
				
		else if(requiredCode == 9){ //Mass
				derivation = Newton.newton_mass_formula_fa(extractNumeric(8), extractNumeric(10), extractUnit(8), extractUnit(10), extractMultiplier(8), extractMultiplier(10));
				display(derivation);
		}
				
		else if(requiredCode == 10){ //Acceleration
				derivation = Newton.newton_acceleration_formula_fm(extractNumeric(8), extractNumeric(9), extractUnit(8), extractUnit(9), extractMultiplier(8), extractMultiplier(9));
				display(derivation);
		}
	}
}
