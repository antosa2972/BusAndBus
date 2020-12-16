package TimeWork;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class TimeWork {
    public static String[] getDates(){
        GregorianCalendar gregorianCalendar= new GregorianCalendar();
        String[] curDate = new String[10];
        int toAdd=1;
        for(int i=0;i<10;i++) {
            gregorianCalendar.add(Calendar.DATE,toAdd);
            curDate[i]=" ";
            curDate[i]+=gregorianCalendar.get(Calendar.DATE)+".";
            curDate[i]+=gregorianCalendar.get(Calendar.MONTH)+1;
            curDate[i]+="."+gregorianCalendar.get(Calendar.YEAR);
        }
        return curDate;
    }
    public static String getCurTime(){
        return new Date().toString();
    }
}
