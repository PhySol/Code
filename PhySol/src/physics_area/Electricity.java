package physics_area;


public class Electricity {
	
	public static String[] derivation;
	public static double final_ans;
	
//Voltage

	/**
	 * Formula for Voltage using Current and Resistance
	 * @param g_current - Current Variable
	 * @param g_resistance - Resistance Variable
	 * @return double
	 */
	public static String[] elec_voltage_formula_cr(double g_current, double g_resistance, String g_current_unit, String g_resistance_unit, double current_multi, double resistance_multi){
			final_ans =  (g_current * current_multi) * (g_resistance * resistance_multi);
			derivation = new String[6];
			derivation[0] = "\tVoltage = Current * Resistance";
			derivation[1] = "\tVoltage = " + g_current + " " + g_current_unit + " * "  + g_resistance + " " + g_resistance_unit;
			derivation[2] = "\tVoltage = " + (g_current * current_multi) + " volts * " + (g_resistance * resistance_multi) + " ohms";
			derivation[3] = "\tVoltage = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer: " + final_ans + " volts";
			return derivation;
	}
	 
	/**
	 * Formula for Voltage using Power and Current
	 * @param g_power
	 * @param g_current
	 * @return
	 */
	public static String[] elec_voltage_formula_pc(double g_power, double g_current, String g_power_unit, String g_current_unit, double power_multi, double current_multi){
		
			final_ans = (g_power * power_multi) / (g_current * current_multi);
			
			derivation = new String[5];
			derivation[0] = "\tVoltage = Power / Current";
			derivation[1] = "\tVoltage = " + g_power + " " + g_power_unit + " / " + g_current + " " + g_current_unit;
			derivation[2] = "\tVoltage = " + (g_power * power_multi) + " watts / " + (g_current * current_multi) + " amperes";
			derivation[3] = "\tVoltage = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer: " + final_ans + " volts";
			return derivation;
	}
	
	/**
	 * Formula for Voltage using Power and Resistance
	 * @param g_power
	 * @param g_resistance
	 * @return+
	 */
	public static String[] elec_voltage_formula_pr(double g_power, double g_resistance, String g_power_unit, String g_resistance_unit, double power_multi, double resistance_multi){

			final_ans =  Math.sqrt((g_power * power_multi) * (g_resistance* resistance_multi));
			
			derivation = new String[7];
			derivation[0] = "\tVoltage = \u221A(Power * Resistance)";
			derivation[1] = "\tVoltage = \u221A(" + g_power + " " + g_power_unit + " * " + g_resistance + " " + g_resistance_unit + ")";
			derivation[2] = "\tVoltage = \u221A(" + (g_power * power_multi) + " watts * " + (g_resistance * resistance_multi) + " ohms)";
			derivation[3] = "\tVoltage = \u221A(" + ((g_power * power_multi) * (g_resistance * resistance_multi)) + ")";
			derivation[4] = "\tVoltage = " + final_ans;
			derivation[5] = "\n";
			derivation[6] = "Final Answer: " + final_ans + " volts";
			return derivation;
	}


//Resistance	
	/**
	 * Formula for Resistance using Voltage and Current
	 * @param g_voltage - Voltage Variable
	 * @param g_current - Current Variable
	 * @return double 
	 */
	public static String[] elec_resistance_formula_vc(double g_voltage, double g_current, String g_voltage_unit, String g_current_unit, double voltage_multi, double current_multi){

			final_ans =  ((g_voltage * voltage_multi) / (g_current * current_multi));
			
			derivation = new String[6];
			derivation[0] = "\tResistance = Voltage / Current";
			derivation[1] = "\tResistance = " + g_voltage + " " + g_voltage_unit + " / " + g_current + " " + g_current_unit;
			derivation[2] = "\tResistance = " + (g_voltage * voltage_multi) + " volts / " + (g_current * current_multi) + " amperes";
			derivation[3] = "\tResistance = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer: " + final_ans + " ohms";
			return derivation;
	}
	
	/**
	 * Formula for Resistance using Power and Current
	 * @param g_power - Power Variable
	 * @param g_current - Current Variable
	 * @return double
	 */
	public static String[] elec_resistance_formula_pc(double g_power, double g_current, String g_power_unit, String g_current_unit, double power_multi, double current_multi){
			
			final_ans =  ((g_power * power_multi) / (Math. pow((g_current * current_multi), 2)));
	
			derivation = new String[7];
			derivation[0] = "\tResistance = Power / Current\u00B2";
			derivation[1] = "\tResistance = " + g_power +  " " +  g_power_unit  + " / (" + g_current + " " + g_current_unit + ")\u00B2";
			derivation[2] = "\tResistance = " + (g_power * power_multi) + " watts / (" + (g_current * current_multi) + "amperes)\u00B2";
			derivation[3] = "\tResistance = " + (g_power * power_multi) + " watts / " + Math.pow((g_current * current_multi), 2) + " amperes";
			derivation[4] = "\tResistance = " + final_ans;
			derivation[5] = "\n";
			derivation[6] = "Final Answer: " + final_ans + " ohms";
			return derivation;
	}
	
	/**
	 * Formula for Resistance using Voltage and Power
	 * @param g_voltage - Voltage Variable
	 * @param g_power - Power Variable
	 * @return double
	 */
	public static String[] elec_resistance_formula_vp(double g_voltage, double g_power, String g_voltage_unit, String g_power_unit, double voltage_multi, double power_multi){
			
			final_ans =  (Math.pow((g_voltage * voltage_multi), 2)/ (g_power * power_multi));
			
			derivation  = new String[7];
			derivation[0] = "\tResistance = Voltage\u00B2 / Power";
			derivation[1] = "\tResistance = (" + g_voltage + " " + g_voltage_unit + ")\u00B2" + " / " + g_power + " " + g_power_unit;
			derivation[2] = "\tResistance = (" + (g_voltage * voltage_multi) + ")\u00B2 volts / " + (g_power * power_multi) + " watts";
			derivation[3] = "\tResistance = " +  Math.pow((g_voltage * voltage_multi), 2) + "volts / " + (g_power * power_multi) + " watts";
			derivation[4] = "\tResistance = " + final_ans;
			derivation[5] = "\n";
			derivation[6] = "Final Answer: " + final_ans + " ohms";
			return derivation;
	}

	
//Current
	/**
	 * Formula for Current using Resistance and Voltage
	 * @param g_resistance - Resistance Variable
	 * @param g_voltage - Voltage Variable
	 * @return double
	 */
	public static String[] elec_current_formula_vr(double g_voltage, double g_resistance, String g_voltage_unit, String g_resistance_unit, double voltage_multi, double resistance_multi){

			final_ans = ((g_voltage* voltage_multi) / (g_resistance * resistance_multi));

			derivation = new String[6];
			derivation[0] = "\tCurrent = Voltage / Resistance";
			derivation[1] = "\tCurrent = " + g_voltage + " " + g_voltage_unit + " / " + g_resistance + " " + g_resistance_unit;
			derivation[2] = "\tCurrent = " + (g_voltage * voltage_multi) + " volts / " + (g_resistance * resistance_multi)+ " ohms";
			derivation[3] = "\tCurrent = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer: " + final_ans + " amperes";
			return derivation;
	}

	/**
	 * Formula for Current using Power and Resistance
	 * @param g_power - Power Variable
	 * @param g_resistance - Resistance Variable
	 * @return double
	 */
	public static String[] elec_current_formula_pr(double g_power, double g_resistance, String g_power_unit, String g_resistance_unit, double power_multi, double resistance_multi){

			final_ans =  Math.sqrt(((g_power * power_multi)/(g_resistance * resistance_multi)));

			derivation = new String[7];
			derivation[0] = "\tCurrent = \u221A(Power / Resistance)";
			derivation[1] = "\tCurrent = \u221A(" + g_power + " " + g_power_unit + " / " + g_resistance + " " + g_resistance_unit;
			derivation[2] = "\tCurrent = \u221A(" + (g_power * power_multi) + " watts / " + (g_resistance * resistance_multi) + " ohms";
			derivation[3] = "\tCurrent = \u221A (" + ((g_power * power_multi)/(g_resistance * resistance_multi)) + ")";
			derivation[4] = "\tCurrent = " + final_ans;
			derivation[5] = "\n";
			derivation[6] = "Final Answer: " + final_ans + " amperes";
			return derivation;
	}
	
	/**
	 * Formula for Current using Power and Voltage
	 * @param g_power
	 * @param g_voltage
	 * @return double
	 */
	public static String[] elec_current_formula_pv(double g_power, double g_voltage, String g_power_unit, String g_voltage_unit, double power_multi, double voltage_multi){

			final_ans =  ((g_power * power_multi) / (g_voltage * voltage_multi));

			derivation = new String[6];
			derivation[0] = "\tCurrent = Power / Voltage";
			derivation[1] = "\tCurrent = " + g_power + " " + g_power_unit + " / " + g_voltage + " " + g_voltage_unit;
			derivation[2] = "\tCurrent = " + (g_power * power_multi) + " watts / " + (g_voltage * voltage_multi) + " volts";
			derivation[3] = "\tCurrent = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer: " + final_ans + " amperes";
			return derivation;
	}
	
// Power
	
	/**
	 * Formula for Power using Voltage and Current
	 * @param g_voltage - Voltage Variable
	 * @param g_current - Current Variable
	 * @return double
	 */
	public static String[] elec_power_formula_cv(double g_current, double g_voltage, String g_current_unit, String g_voltage_unit, double current_multi, double voltage_multi){

			final_ans =  (g_current * current_multi) * (g_voltage * voltage_multi);

			derivation = new String[6];
			derivation[0] = "\tPower = Current * Voltage";
			derivation[1] = "\tPower = " + g_current + " " + g_current_unit + " * " + g_voltage + " " + g_voltage_unit;
			derivation[2] = "\tPower = " + (g_current * current_multi) + " amperes * " + (g_voltage * voltage_multi) + " volts";
			derivation[3] = "\tPower = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer: " + final_ans + " watts";
			return derivation;
	}

	/**
	 * Formula for Power using Voltage and Resistance
	 * @param g_voltage - Voltage Variable
	 * @param g_resistance - Resistance Variable
	 * @return double
	 */
	public static String[] elec_power_formula_vr(double g_voltage, double g_resistance, String g_voltage_unit, String g_resistance_unit, double voltage_multi, double resistance_multi){
			
			final_ans = (Math.pow((g_voltage * voltage_multi), 2))/ (g_resistance * resistance_multi);
			
			derivation = new String[7];
			derivation[0] = "\tPower = Voltage\u00B2 / Resistance";
			derivation[1] = "\tPower = (" + g_voltage + " " + g_voltage_unit + ")\u00B2 / " + g_resistance + " " + g_resistance_unit;
			derivation[2] = "\tPower = (" + (g_voltage * voltage_multi) + ")\u00B2 volts / " + (g_resistance * resistance_multi) + " ohms";
			derivation[3] = "\tPower = " + Math.pow((g_voltage * voltage_multi), 2) + " volts / " + (g_resistance * resistance_multi) + " ohms";
			derivation[4] = "\tPower = " + final_ans;
			derivation[5] = "\n";
			derivation[6] = "Final Answer: " + final_ans + " watts";
			return derivation;
	}
	/**
	 * Formula for Power using Current and Resistance
	 * @param g_current
	 * @param g_resistance
	 * @return double
	 */
	public static String[] elec_power_formula_cr(double g_current, double g_resistance, String g_current_unit, String g_resistance_unit, double current_multi, double resistance_multi){
		
			final_ans =  (Math.pow((g_current * current_multi), 2)) * (g_resistance * resistance_multi);

			derivation = new String[7];
			derivation[0] = "\tPower = Current\u00B2 * Resistance";
			derivation[1] = "\tPower = (" + g_current + " " + g_current_unit + ")\u00B2 * " + g_resistance + " " + g_resistance_unit; 
			derivation[2] = "\tPower = (" + (g_current * current_multi) + ")\u00B2 amperes * " + (g_resistance * resistance_multi) + " ohms";
			derivation[3] = "\tPower = " + Math.pow((g_current * current_multi), 2) + " amperes * " +(g_resistance * resistance_multi) + " ohms";
			derivation[4] = "\tPower = " + final_ans;
			derivation[5] = "\n";
			derivation[6] = "Final Answer: " + final_ans + " watts";
			return derivation;
	}
	
}
