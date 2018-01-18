package physics_area;

public class Kinematics {

	public static String[] derivation;
	public static double final_ans;

	//Distance
	public static String[] kinematics_distance_formula_st(double g_speed, double g_time, String g_speed_unit, String g_time_unit){

			final_ans = g_speed * g_time;

			derivation = new String[5];
			derivation[0] = "\tDistance = Speed * Time";
			derivation[1] = "\tDistance = " + g_speed  + " " + g_speed_unit + " * " + g_time + " " + g_time_unit;
			derivation[2] = "\tDistance = " + final_ans;
			derivation[3] = "\n";
			derivation[4] = "Final Answer = " + final_ans + " meters";

			return derivation;
	}

	public static String[] kinematics_speed_formula_dt(double g_distance, double g_time, String g_distance_unit, String g_time_unit){

			final_ans = g_distance / g_time;

			derivation = new String[5];
			derivation[0] = "\tSpeed = Distance / Time";
			derivation[1] = "\tSpeed = " + g_distance + " " + g_distance_unit + " / " + g_time + " " + g_time_unit;
			derivation[2] = "\tSpeed = " + final_ans;
			derivation[3] = "\n";
			derivation[4] = "Final Answer = " + final_ans + " kmph";

			return derivation;
	}

	public static String[] kinematics_time_formula_ds(double g_distance, double g_speed, String g_distance_unit, String g_speed_unit){

			final_ans = g_distance / g_speed;

			derivation = new String[5];
			derivation[0] = "\tTime = Distance / Speed";
			derivation[1] = "\tTime = " + g_distance + " " + g_distance_unit + " / " + g_speed + " " + g_speed_unit;
			derivation[2] = "\tTime = " + final_ans;
			derivation[3] = "\n";
			derivation[4] = "Final Answer = " + final_ans + " seconds";

			return derivation;
	}
}
