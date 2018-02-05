package physics_area;

public class Newton {

	public static String[] derivation;
	public static double final_ans;

	//Force

	public static String[] newton_force_formula_ma(double g_mass, double g_acceleration, String g_mass_unit, String g_acceleration_unit, double mass_multi, double acceleration_multi){
			final_ans = (g_mass * mass_multi) * (g_acceleration * acceleration_multi);
			derivation = new String[6];
			derivation[0] = "\tForce = Mass * Acceleration";
			derivation[1] = "\tForce = " + g_mass + " " + g_mass_unit + " * " + g_acceleration + " " + g_acceleration_unit;
			derivation[2] = "\tForce = " + (g_mass * mass_multi) + " kg * "+ (g_acceleration * acceleration_multi) + " mps2";
			derivation[3] = "\tForce = " + final_ans ;
			derivation[4] = "\n";
			derivation[5] = "Final Answer : " + final_ans + " Newton/s";
			
			return derivation;
	}

	public static String[] newton_mass_formula_fa(double g_force, double g_acceleration, String g_force_unit, String g_acceleration_unit, double force_multi, double acceleration_multi){
			final_ans = (g_force * force_multi) / (g_acceleration * acceleration_multi);
			derivation = new String[6];
			derivation[0] = "\tMass = Force / Acceleration";
			derivation[1] = "\tMass = " + g_force + " " + g_force_unit + " / " + g_acceleration + " " + g_acceleration_unit;
			derivation[2] = "\tMass = " + (g_force * force_multi) + " N / " + (g_acceleration * acceleration_multi) + " mps2";
			derivation[3] = "\tMass = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer : " + final_ans + " kilogram/s";

			return derivation;
	}

	public static String[] newton_acceleration_formula_fm(double g_force, double g_mass, String g_force_unit, String g_mass_unit, double force_multi, double mass_multi){
			final_ans = (g_force * force_multi) / (g_mass * mass_multi);
			derivation = new String[5];
			derivation[0] = "\tAcceleration = Force / Mass";
			derivation[1] = "\tAcceleration = " + g_force + " " + g_force_unit + " / " + g_mass + " " + g_mass_unit;
			derivation[2] = "\tAcceleration = " + (g_force * force_multi) + " N / " + (g_mass * mass_multi) + " kg";
			derivation[3] = "\tAcceleration = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer = " + final_ans + " meter/s per second\\u00B2";

			return derivation;
			
	}
}

