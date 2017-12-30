package physics_area;

import java.util.ArrayList;

public class Electricity {
	
	public static String[] derivation;
	
//Voltage

	/**
	 * Formula for Voltage using Current and Resistance
	 * @param g_current - Current Variable
	 * @param g_resistance - Resistance Variable
	 * @return double
	 */
	public static String[] elec_voltage_formula_cr(double g_current, double g_resistance, String g_current_unit, String g_resistance_unit){
			double final_ans =  g_current * g_resistance;
			derivation = new String[5];
			derivation[0] = "\tVoltage = Current * Resistance";
			derivation[1] = "\tVoltage = " + g_current + " " + g_current_unit + " * "  + g_resistance + " " + g_resistance_unit;
			derivation[2] = "\tVoltage = " + Double.toString(final_ans);
			derivation[3] = "\n";
			derivation[4] = "Final Answer: " + Double.toString(final_ans) + " volts";
			return derivation;
	}
	 
	/**
	 * Formula for Voltage using Power and Current
	 * @param g_power
	 * @param g_current
	 * @return
	 */
	public static double elec_voltage_formula_pc(double g_power, double g_current){

			return (g_power / g_current) + (g_power % g_current);
	}
	
	/**
	 * Formula for Voltage using Power and Resistance
	 * @param g_power
	 * @param g_resistance
	 * @return+
	 */
	public static double elec_voltage_formula_pr(double g_power, double g_resistance){

			return Math.sqrt(g_power * g_resistance);
	}


//Resistance	
	/**
	 * Formula for Resistance using Voltage and Current
	 * @param g_voltage - Voltage Variable
	 * @param g_current - Current Variable
	 * @return double 
	 */
	public static double elec_resistance_formula_vc(double g_voltage, double g_current){

			return (g_voltage / g_current) + (g_voltage % g_current);
	}
	
	/**
	 * Formula for Resistance using Power and Current
	 * @param g_power - Power Variable
	 * @param g_current - Current Variable
	 * @return double
	 */
	public static double elec_resistance_formula_pc(double g_power, double g_current){
		
			return (g_power / (Math. pow(g_current, 2))) + (g_power % (Math.pow(g_current, 2)));
	}
	
	/**
	 * Formula for Resistance using Voltage and Power
	 * @param g_voltage - Voltage Variable
	 * @param g_power - Power Variable
	 * @return double
	 */
	public static double elec_resistance_formula_vp(double g_voltage, double g_power){
			
			return (Math.pow(g_voltage, 2)/ g_power) + (Math.pow(g_voltage,2) % g_power);
			
	}

	
//Current
	/**
	 * Formula for Current using Resistance and Voltage
	 * @param g_resistance - Resistance Variable
	 * @param g_voltage - Voltage Variable
	 * @return double
	 */
	public static double elec_current_formula_rv(double g_resistance, double g_voltage){

			return (g_voltage / g_resistance) + (g_voltage % g_resistance);
	}

	/**
	 * Formula for Current using Power and Resistance
	 * @param g_power - Power Variable
	 * @param g_resistance - Resistance Variable
	 * @return double
	 */
	public static double elec_current_formula_pr(double g_power, double g_resistance){

			return Math.sqrt((g_power/g_resistance) + (g_power%g_resistance));
	}
	
	/**
	 * Formula for Current using Power and Voltage
	 * @param g_power
	 * @param g_voltage
	 * @return double
	 */
	public static double elec_current_formula_pv(double g_power, double g_voltage){

			return (g_power / g_voltage) + (g_power % g_voltage);
	}
	
// Power
	
	/**
	 * Formula for Power using Voltage and Current
	 * @param g_voltage - Voltage Variable
	 * @param g_current - Current Variable
	 * @return double
	 */
	public static double elec_power_formula_vc(double g_voltage, double g_current){

			return g_current * g_voltage;
	}

	/**
	 * Formula for Power using Voltage and Resistance
	 * @param g_voltage - Voltage Variable
	 * @param g_resistance - Resistance Variable
	 * @return double
	 */
	public static double elec_power_formula_vr(double g_voltage, double g_resistance){
			double ans_whole = (Math.pow(g_voltage, 2))/ g_resistance;
			double ans_rem = (Math.pow(g_voltage, 2)) % g_resistance;
			return ans_whole + ans_rem;
	}
	/**
	 * Formula for Power using Current and Resistance
	 * @param g_current
	 * @param g_resistance
	 * @return double
	 */
	public static double elec_power_formula_cr(double g_current, double g_resistance){
		
			return (Math.pow(g_current, 2)) * g_resistance;
	}
	
}
