package railo.runtime.type.dt;

import railo.runtime.PageContext;
import railo.runtime.dump.DumpData;
import railo.runtime.dump.DumpProperties;
import railo.runtime.dump.DumpTable;
import railo.runtime.dump.SimpleDumpData;
import railo.runtime.exp.ExpressionException;
import railo.runtime.exp.PageException;
import railo.runtime.functions.dateTime.DateUtil;
import railo.runtime.op.Caster;
import railo.runtime.op.Operator;

/**
 * TimeSpan Object, represent a timespan
 */
public final class TimeSpanImpl implements TimeSpan {

	private double value;
	private long valueMillis;
	
	private int day;
	private int hour;
	private int minute;
	private int second;
	private int milli;
	


	/**
     * constructor of the timespan class
	 * @param value
     */
    public TimeSpanImpl(double value) {
    	valueMillis=(long)(value*86400000D);
    	
		
		
		day=(int)value;
		double diff=value-day;
		diff*=24;
		hour=(int)diff;
		diff=diff-hour;
		diff*=60;
		minute=(int)diff;
		diff=diff-minute;
		diff*=60;
		second=(int)diff;
		this.value=value;
		milli=(int)(valueMillis-((second+(minute*60L)+(hour*3600L)+(day*3600L*24L))*1000));
		
		//total=(second+(minute*60L)+(hour*3600L)+(day*3600L*24L))*1000;
		//total=(second+(minute*60L)+(hour*3600L)+(day*3600L*24L))*1000;
		
    }
		
	/**
	 * constructor of the timespan class
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public TimeSpanImpl(int day, int hour, int minute, int second) {
		this.day=day;
		this.hour=hour;
		this.minute=minute;
		this.second=second;
		value=day+(((double)hour)/24)+(((double)minute)/24/60)+(((double)second)/24/60/60);
		valueMillis=(second+(minute*60L)+(hour*3600L)+(day*3600L*24L))*1000;
	}
	
	/**
	 * constructor of the timespan class
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public TimeSpanImpl(int day, int hour, int minute, int second, int millisecond) {
		this.day=day;
		this.hour=hour;
		this.minute=minute;
		this.second=second;
		this.milli=millisecond;
		value=day+(((double)hour)/24)+(((double)minute)/24/60)+(((double)second)/24/60/60)+(((double)millisecond)/24/60/60/1000);
		valueMillis=((second+(minute*60L)+(hour*3600L)+(day*3600L*24L))*1000)+millisecond;
	}
	
    /**
	 * @see railo.runtime.op.Castable#castToString()
	 */
	public String castToString() {
		return Caster.toString(value);
	}

	/**
	 * @see railo.runtime.op.Castable#castToString(java.lang.String)
	 */
	public String castToString(String defaultValue) {
		return Caster.toString(value);
	}

	/**
	 * @see railo.runtime.op.Castable#castToBooleanValue()
	 */
	public boolean castToBooleanValue() throws ExpressionException {
		throw new ExpressionException("can't cast Timespan to boolean");
	}
    
    /**
     * @see railo.runtime.op.Castable#castToBoolean(java.lang.Boolean)
     */
    public Boolean castToBoolean(Boolean defaultValue) {
        return defaultValue;
    }

	/**
	 * @see railo.runtime.op.Castable#castToDoubleValue()
	 */
	public double castToDoubleValue() {
		return value;
	}
    
    /**
     * @see railo.runtime.op.Castable#castToDoubleValue(double)
     */
    public double castToDoubleValue(double defaultValue) {
        return value;
    }

	/**
	 * @see railo.runtime.op.Castable#castToDateTime()
	 */
	public DateTime castToDateTime() throws ExpressionException {
		return DateUtil.getDateTimeInstance(value);
	    //throw new ExpressionException("can't cast Timespan to Date Object");
	}
    
    /**
     * @see railo.runtime.op.Castable#castToDateTime(railo.runtime.type.dt.DateTime)
     */
    public DateTime castToDateTime(DateTime defaultValue) {
        return DateUtil.getDateTimeInstance(value);
    }


	/**
	 * @see railo.runtime.op.Castable#compare(boolean)
	 */
	public int compareTo(boolean b) {
		return Operator.compare(value, b?1D:0D);
	}

	/**
	 * @see railo.runtime.op.Castable#compareTo(railo.runtime.type.dt.DateTime)
	 */
	public int compareTo(DateTime dt) throws PageException {
		return Operator.compare(value, dt.castToDoubleValue());
	}

	/**
	 * @see railo.runtime.op.Castable#compareTo(double)
	 */
	public int compareTo(double d) throws PageException {
		return Operator.compare(value, d);
	}

	/**
	 * @see railo.runtime.op.Castable#compareTo(java.lang.String)
	 */
	public int compareTo(String str) throws PageException {
		return Operator.compare(value, str);
	}

	/**
	 * @see railo.runtime.dump.Dumpable#toDumpData(railo.runtime.PageContext, int)
	 */
	public DumpData toDumpData(PageContext pageContext, int maxlevel, DumpProperties dp) {
		DumpTable table=new DumpTable("#ffb200","#ffcc00","#263300");
		/*
		table.setTitle("Timespan");
		table.appendRow(1, new SimpleDumpData("day"), new SimpleDumpData(day));
		table.appendRow(1, new SimpleDumpData("hour"), new SimpleDumpData(hour));
		table.appendRow(1, new SimpleDumpData("minute"), new SimpleDumpData(minute));
		table.appendRow(1, new SimpleDumpData("second"), new SimpleDumpData(second));
		if(milli>0)table.appendRow(1, new SimpleDumpData("millisecond"), new SimpleDumpData(milli));
		*/
		if(milli>0)table.appendRow(1, new SimpleDumpData("Timespan"), new SimpleDumpData("createTimeSpan("+day+","+hour+","+minute+","+second+","+milli+")"));
		else table.appendRow(1, new SimpleDumpData("Timespan"), new SimpleDumpData("createTimeSpan("+day+","+hour+","+minute+","+second+")"));
		
		
		
		return table;
	}
	/**
     * @see railo.runtime.type.dt.TimeSpan#getMillis()
     */
	public long getMillis() {
		return valueMillis;
	}
	public long getMilli() {
		return milli;
	}
	
	/**
     * @see railo.runtime.type.dt.TimeSpan#getSeconds()
     */
	public long getSeconds() {
		return valueMillis/1000;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if(milli>0)
			return "createTimeSpan("+day+","+hour+","+minute+","+second+","+milli+")";
		return "createTimeSpan("+day+","+hour+","+minute+","+second+")";
	}

    /**
     * @see railo.runtime.type.dt.TimeSpan#getDay()
     */
    public int getDay() {
        return day;
    }
    /**
     * @see railo.runtime.type.dt.TimeSpan#getHour()
     */
    public int getHour() {
        return hour;
    }
    /**
     * @see railo.runtime.type.dt.TimeSpan#getMinute()
     */
    public int getMinute() {
        return minute;
    }
    /**
     * @see railo.runtime.type.dt.TimeSpan#getSecond()
     */
    public int getSecond() {
        return second;
    }
}