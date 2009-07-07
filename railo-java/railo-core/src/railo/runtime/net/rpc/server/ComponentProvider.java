package railo.runtime.net.rpc.server;

import java.lang.reflect.Method;

import org.apache.axis.MessageContext;
import org.apache.axis.providers.java.RPCProvider;

import railo.runtime.Component;
import railo.runtime.PageContext;
import railo.runtime.exp.PageException;
import railo.runtime.net.rpc.AxisCaster;

public final class ComponentProvider extends RPCProvider {

	public static final String PAGE_CONTEXT = PageContext.class.getName();
	public static final String COMPONENT = Component.class.getName();
 
	
	/**
	 * @see org.apache.axis.providers.java.RPCProvider#invokeMethod(org.apache.axis.MessageContext, java.lang.reflect.Method, java.lang.Object, java.lang.Object[])
	 */
	protected Object invokeMethod(MessageContext mc, Method method, Object trg, Object[] args) throws Exception {
		PageContext pc=(PageContext) mc.getProperty(Constants.PAGE_CONTEXT);
		Component c= (Component) mc.getProperty(Constants.COMPONENT);
        
		return AxisCaster.toAxisType(c.call(pc,method.getName(),toRailoType(args)));
	}

	private Object[] toRailoType(Object[] args) throws PageException {
		Object[] trgs=new Object[args.length];
		for(int i=0;i<trgs.length;i++) {
			trgs[i]=AxisCaster.toRailoType(args[i]);
		}
		return trgs;
	}
	

}
