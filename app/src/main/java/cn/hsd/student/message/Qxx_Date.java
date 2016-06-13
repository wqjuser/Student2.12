package cn.hsd.student.message;

import java.util.Calendar;

public class Qxx_Date {

	public String getDate() {
		Calendar localCalendar = Calendar.getInstance();
		int i = localCalendar.get(Calendar.YEAR);
		int j = 1 + localCalendar.get(Calendar.MONTH);
		int k = localCalendar.get(Calendar.DAY_OF_MONTH);
		int l = localCalendar.get(Calendar.HOUR_OF_DAY);
		int i1 = localCalendar.get(Calendar.MINUTE);
		if(j<10){
			if(k<10){
				if(l<10){
					if(i1<10){
						return i + "-" + "0" +j + "-" + "0" +k + "  " + "0" + l
								+ ":" + "0" + i1;
					}
					return i + "-" + "0" +j + "-" + "0" +k + "  " + "0" + l
					+ ":" + i1;
				}
				return i + "-" + "0" +j + "-" + "0" +k + "  " + l
						+ ":" + i1;
			}
			return i + "-" + "0" +j + "-" + k + "  " + l
					+ ":" + i1;
		}
		return i + "-" + j + "-" + k + "  " + l 
				+ ":" + i1;
		
	}
}
