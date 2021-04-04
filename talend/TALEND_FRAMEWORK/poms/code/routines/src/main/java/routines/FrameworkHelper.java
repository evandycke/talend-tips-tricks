/*
 Copyright 2015, Yellow Pelican Ltd. www.YellowPelican.co.uk

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package routines;

import java.util.Date;

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
public class FrameworkHelper {

	/**
	 * This method is required to support Jobs created with Talend Framework version 1.6 and earlier.
	 * 
	 * @param runBetweenStart
	 * @param runBetweenEnd
	 * @return
	 */
	public static Boolean inRunWindow(String runBetweenStart, String runBetweenEnd) {
		return inRunWindow(runBetweenStart, runBetweenEnd, 0, false);
	}

	/**
	 * Return true, if the current time is between the two supplied times.
	 * 
	 * If both parameters are empty, true will be returned.
	 * 
	 * @param runBetweenStart
	 * @param runBetweenEnd
	 * @param sleepTimeAllowance
	 * @param debug
	 * @return
	 */
		public static Boolean inRunWindow(String runBetweenStart, String runBetweenEnd, int sleepTimeAllowance, boolean debug) {
			if(debug) System.err.println(String.format("FrameworkHelper.isRunWindow: runBetweenStart: %s, runBetweenEnd: %s", runBetweenStart, runBetweenEnd));

			if(FieldHelper.isEmpty(runBetweenStart)) {
				if(FieldHelper.isEmpty(runBetweenEnd)) return true;
				else return false;
			}
			else if(FieldHelper.isEmpty(runBetweenEnd)) return false;
			else {
				try {
					Integer start = new Integer(runBetweenStart.replaceAll(":", ""));
					Integer end = new Integer(runBetweenEnd.replaceAll(":", ""));
					Integer now = new Integer(TalendDate.formatDate("HH:mm", TalendDate.getCurrentDate()).replaceAll(":", ""));
	
					if(debug) System.err.println(String.format("FrameworkHelper.isRunWindow: start: %d, end: %d, now: %d", start, end, now));
					
					if(sleepTimeAllowance != 0) {
						java.util.Date endTime = TalendDate.addDate(TalendDate.parseDate("HH:mm", runBetweenEnd), (sleepTimeAllowance * -1), "ss");
						end = new Integer(TalendDate.formatDate("HH:mm", endTime).replaceAll(":", ""));
						if(debug) System.err.println(String.format("FrameworkHelper.isRunWindow: start: %d, end: *%d*, now: %d, sleepTimeAllowance: %d", start, end, now, sleepTimeAllowance));
					}
			
					/*
					 * E.g. 00:00 00:00 true  (always run)
					 *      03:00 03:00 false (invalid)
					 *      00:00 03:00 run between midnight and 3am
					 *      01:00 03:00 run between 1am and 3 am
					 *      03:00 00:00 run between 3am and midnight
					 *      23:00 03:00 run between 11pm and 3am (do not run between 3am and 11pm) 
					 *      03:00 02:00 run between 3am and 2am (do not run between 2am and 3am)
					 */
					//System.err.println(String.format("now=%s, start=%s, end=%s", now, start, end));
					if(start == 0 && end == 0) {
						if(debug) System.err.println("FrameworkHelper.isRunWindow: start == 0, end == 0: true");
						return true;
					}
					else if(start == end) {
						if(debug) System.err.println("FrameworkHelper.isRunWindow: start == end: false");
						return false;
					}
					else if(start < (end == 0 ? 2400 : end)) {
						if(now >= start && now < (end == 0 ? 2400 : end)) {
							if(debug) System.err.println("FrameworkHelper.isRunWindow: start < end && now >= start && now < end: true");
							return true;
						}
						else {
							if(debug) System.err.println("FrameworkHelper.isRunWindow: start < end && NOT now >= start && now < end: false");
							return false;
						}
					}
					else if(start > (end == 0 ? 2400 : end)) {
						if(now >= start || now <= (end == 0 ? 2400 : end)) {
							if(debug) System.err.println("FrameworkHelper.isRunWindow: start > end && (now >= start || now <= end): true");
							return true;
						}
						else {
							if(debug) System.err.println("FrameworkHelper.isRunWindow: start > end && NOT (now >= start || now <= end): false");
							return false;
						}
					}
					else {
						if(debug) System.err.println("FrameworkHelper.isRunWindow: unexpected state: false");
						return false;
					}
				}
				catch (Exception e) {
					return false;
				}
			}
		}
}