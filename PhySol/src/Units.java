class Units{



/**Electricity**/
	public static String[] voltage = {"volts:1", "volt:1", "v:1", "microvolt:0.000001", "microvolts:0.000001", "milivolts:0.001", "milivolts:0.001", "kilovolt:1000", "kilovolts:1000", "megavolt:1000000", "megavolts:1000000"};
	public static String[] current = {"amperes:1","amps:1", "A:1","microampere:0.000001", "microamperes:0.000001", "microamps:0.000001", "milliampere:0.001", "milliamperes:0.001", "milliamps:0.001", "kiloampere:1000", "kiloamperes:1000", "kiloamps:1000", "megaampere:1000000", "megaamperes:1000000", "megaamps:1000000"};
	public static String[] resistance = {"ohms:1", "ohm:1", " Ω:1", "milliohm:0.001", "milliohms:0.001", "kiloohm:1000", "kiloohms:1000", "megaohm:1000000", "megaohms:1000000"};
	public static String[] power = {"watt:1", "watts:1", "nanowatt:0.000000001", "nanowatts:0.000000001", "microwatt:0.000001", "microwatts:0.000001", "milliwatt:0.001", "milliwatts:0.001", "kilowatt:1000", "kilowatts:1000", "megawatt:1000000", "megawatts:1000000", "gigawatt:1000000000", "gigawatts:1000000000"};

/**Kinematics**/
	public static String[] speed = {"mps:1", "mips:0.44704", "kmph:0.277778", "ftps:0.3048"};
	public static String[] time = {"second:1", "seconds:1","s:1", "minute:60", "minutes:60", "min:60", "hour:3600", "hours:3600", "hr:3600", "microsecond:0.000001", "microseconds:0.000001", "millisecond:0.001", "milliseconds:0.001", "ms:0.001"};
	public static String[] distance = {"meter:1", "meters:1", "m:1", "centimeter:0.01", "centimeters:0.01", "cm:0.01", "kilometer:1000", "kilometers:1000", "km:1000", "millimeter:0.001", "millimeters:0.001", "mm:0.001", "foot:0.3048", "feet:0.3048", "yard:0.9144", "yards:0.9144", "miles:1609.34", "mile:1609.34", "inch:0.0254", "inch:0.0254"};
/**Forces**/
	public static String[] force = {"newton:1", "newtons:1", "N:1",  "dyne:0.00001", "dynes:0.00001", "centinewton:0.001", "centinewtons:0.001", "decinewton:0.0001", "decinewtons:0.0001", "dekanewton:0.000001", "dekanewtons:0.000001", "micronewton:10", "micronewtons:10", "millinewton:0.01", "millinewtons:0.01", "mN:0.01", "nanonewton:10000", "nanonewtons:10000"};
	public static String[] mass = { "kilogram:0.001", "kilograms:0.001", "kg:0.001", "miligram:0.000001", "milligrams:0.000001", "mg:0.000001", "gram:0.001", "grams:0.001", "g:0.001",  "hectogram:0.1", "hectograms:0.1", "hg:0.1", "pound:0.453592", "pounds:0.453592", "lb:0.453592", "lbs:0.453592"};
	public static String[] acceleration = {"mps2:1", "cmps2:100", "ftps2:3.280839895", "kmph2:12960", "mips2:0.00062137119224", "inps2:39.37007874"};


	public static String[][] units = {voltage, current, resistance, power, speed, time, distance, force, mass, acceleration};

	
} 	