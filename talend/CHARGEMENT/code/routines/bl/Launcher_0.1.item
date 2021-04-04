// template routine Java
package routines;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class Launcher {
	
	public static final int STATUS_OK = 0;
	public static final int ERREUR_TECHNIQUE = -99;
	public static final int ERREUR_CONFIGURATION = -98;

	private static List<String> jobList;
	private static String executionPath;
	private static String projectName;

	/**
	 * Returns the current execution path.
	 * 
	 * {talendTypes} String
	 * 
	 * {Category} Launcher
	 */
	public static String getExecutionPath() {
		if (executionPath == null) {
			Class<?> jLauncherClass;
			try {
				jLauncherClass = Class.forName(projectName.toLowerCase()
						+ ".jlauncher_0_1.jLauncher");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			executionPath = jLauncherClass.getResource("..").getPath()
					.replaceAll("/", File.pathSeparator).replaceAll("[\\\\]",
							File.pathSeparator);
		}
		return executionPath;
	}

	/**
	 * Returns the project name.
	 * 
	 * {talendTypes} String
	 * 
	 * {Category} Infos
	 */
	public static void setProjectName(String projectNamez) {
		projectName = projectNamez.toLowerCase();
	}

	/**
	 * Returns the jobs list.
	 * 
	 * {talendTypes} void
	 * 
	 * {Category} Infos
	 */
	public static List<String> getJobList() {
		if (jobList == null) {
			File executionDirectory = new File(getExecutionPath());
			jobList = Arrays.asList(executionDirectory.list());
		}
		return jobList;
	}

	/**
	 * Asserts a job exists.
	 * 
	 * {talendTypes} boolean
	 * 
	 * {Category} Infos
	 */
	public static boolean isJobInList(String job) {
		return jobList.contains(job);
	}

	/**
	 * Execution d'un Job metier
	 * @param job
	 * @param version
	 * @param params
	 * @return Le code retour du Job metier ou un code d'erreur technique
	 */
	public static int runJob(String job, String version, List<String> params) {
		Class<?> jobClass = null;
		try {
			jobClass = Class.forName(projectName + "." + job.toLowerCase()
					+ "_" + version.replaceAll("[.]", "_") + "." + job);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		}
		
		Object jobObject = null;
		try {
			jobObject = jobClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		}
		
		Class<?> paramClasses[] = new Class[1];
		paramClasses[0] = String[].class;
		Method runMethod = null;
		try {
			runMethod = jobClass.getMethod("runJob", paramClasses);
		} catch (SecurityException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		}
		
		String[] paramValues = (String[]) params.toArray(new String[params
				.size()]);
		try {
			String[][] bufferValue = (String[][]) runMethod.invoke(jobObject, new Object[] { paramValues });
			return Integer.parseInt(bufferValue[0][0]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERREUR_TECHNIQUE;
		}
		
	}

}