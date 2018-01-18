package physics_area;

public class Newton {

	public static String[] derivation;
	public static double final_ans;

	//Force

	public static String[] newton_force_formula_ma(double g_mass, double g_acceleration, String g_mass_unit, String g_acceleration_unit){
			final_ans = g_mass * g_acceleration;
			derivation = new String[5];
			derivation[0] = "\tForce = Mass * Acceleration";
			derivation[1] = "\tForce = " + g_mass + " " + g_mass_unit + " * " + g_acceleration + " " + g_acceleration_unit;
			derivation[2] = "\tForce = " + final_ans ;
			derivation[3] = "\n";
			derivation[4] = "Final Answer : " + final_ans + " N";
	
			return derivation;
	}

	public static String[] newton_mass_formula_fa(double g_force, double g_acceleration, String g_force_unit, String g_acceleration_unit){
			final_ans = g_force / g_acceleration;
			derivation = new String[5];
			derivation[0] = "\tMass = Force / Acceleration";
			derivation[1] = "\tMass = " + g_force + " " + g_force_unit + " / " + g_acceleration + " " + g_acceleration_unit;
			derivation[2] = "\tMass = " + final_ans;
			derivation[3] = "\n";
			derivation[4] = "Final Answer : " + final_ans + " kg";

			return derivation;
	}

	public static String[] newton_acceleration_formula_fm(double g_force, double g_mass, String g_force_unit, String g_mass_unit){
			final_ans = g_force / g_mass;
			derivation = new String[5];
			derivation[0] = "\tAcceleration = Force / Mass";
			derivation[1] = "\tAcceleration = " + g_force + " " + g_force_unit + " / " + g_mass + " " + g_mass_unit;
			derivation[2] = "\tAcceleration = " + final_ans;
			derivation[3] = "\n";
			derivation[4] = "Final Answer = " + final_ans + " ms2";

			return derivation;
			
	}
}

