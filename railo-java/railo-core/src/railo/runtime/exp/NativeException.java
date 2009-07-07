package railo.runtime.exp;

import railo.runtime.Info;
import railo.runtime.PageContext;
import railo.runtime.dump.DumpData;
import railo.runtime.dump.DumpProperties;
import railo.runtime.dump.DumpTable;
import railo.runtime.reflection.Reflector;


/**
 * Box a Native Exception, Native = !PageException
 */
public final class NativeException extends PageExceptionImpl {

	private Throwable t;

    /**
	 * Standart constructor for native Exception class
	 * @param t Throwable
	 */
	public NativeException(Throwable t) {
        super(t,t.getClass().getName());
        this.t=t;
	}

	/**
	 * @see railo.runtime.dump.Dumpable#toDumpData(railo.runtime.PageContext, int)
	 */
	public DumpData toDumpData(PageContext pageContext, int maxlevel, DumpProperties dp) {
	    DumpData data = super.toDumpData(pageContext, maxlevel,dp);
	    if(data instanceof DumpTable)
        ((DumpTable)data).setTitle("Railo ["+Info.getVersionAsString()+"] - Error ("+t.getClass().getName()+")");
        
        return data;
    }

    /**
     * @see railo.runtime.exp.IPageException#typeEqual(java.lang.String)
     */
    public boolean typeEqual(String type) {
    	if(super.typeEqual(type))return true;
        return Reflector.isInstaneOfIgnoreCase(t.getClass(),type);
    }

	/**
	 * @see railo.runtime.exp.PageExceptionImpl#setAddional(java.lang.String, java.lang.Object)
	 */
	public void setAddional(String key, Object value) {
		super.setAddional(key, value);
	}
}