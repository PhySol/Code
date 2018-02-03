package physics_area;

public class Kinematics {

	public static String[] derivation;
	public static double final_ans;

	//Distance
	public static String[] kinematics_distance_formula_st(double g_speed, double g_time, String g_speed_unit, String g_time_unit, double speed_multi, double time_multi){

			final_ans = (g_speed * speed_multi) * (g_time * time_multi);

			derivation = new String[6];
			derivation[0] = "\tDistance = Speed * Time";
			derivation[1] = "\tDistance = " + g_speed  + " " + g_speed_unit + " * " + g_time + " " + g_time_unit;
			derivation[2] = "\tDistance = " + (g_speed * speed_multi) + " mps * " + (g_time * time_multi) + " seconds";
			derivation[3] = "\tDistance = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer = " + final_ans + " meters";

			return derivation;
	}

	public static String[] kinematics_speed_formula_dt(double g_distance, double g_time, String g_distance_unit, String g_time_unit, double distance_multi, double time_multi){

			final_ans = (g_distance * distance_multi) / (g_time * time_multi);
		
			derivation = new String[6];
			derivation[0] = "\tSpeed = Distance / Time";
			derivation[1] = "\tSpeed = " + g_distance + " " + g_distance_unit + " / " + g_time + " " + g_time_unit;
			derivation[2] = "\tSpeed = " + (g_distance * distance_multi) + " meters / " + (g_time * time_multi) + " seconds";
			derivation[3] = "\tSpeed = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer = " + final_ans + " mps";

			return derivation;
	}

	public static String[] kinematics_time_formula_ds(double g_distance, double g_speed, String g_distance_unit, String g_speed_unit, double distance_multi, double speed_multi){

			final_ans = (g_distance * distance_multi) / (g_speed * speed_multi);

			derivation = new String[6];
			derivation[0] = "\tTime = Distance / Speed";
			derivation[1] = "\tTime = " + g_distance + " " + g_distance_unit + " / " + g_speed + " " + g_speed_unit;
			derivation[2] = "\tTime = " + (g_distance * distance_multi) + " meters / " + (g_speed * speed_multi) + " kmph";
			derivation[3] = "\tTime = " + final_ans;
			derivation[4] = "\n";
			derivation[5] = "Final Answer = " + final_ans + " seconds";

			return derivation;
	}
}
