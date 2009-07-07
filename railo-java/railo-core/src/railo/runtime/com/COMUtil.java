package railo.runtime.com;

import railo.runtime.exp.ExpressionException;
import railo.runtime.op.Caster;
import railo.runtime.op.Constants;
import railo.runtime.type.ArrayImpl;
import railo.runtime.type.dt.DateTimeImpl;

import com.jacob.com.Variant;

/**
 * 
 */
public final class COMUtil {
	

    /**
     * translate a Variant Object to Object, when it is a Dispatch translate it to COMWrapper
     * @param parent 
     * @param variant
     * @param key
     * @return Object from Variant
     */
    public static Object toObject(COMObject parent, Variant variant, String key, Object defaultValue) {
        try {
        	return toObject(parent, variant,key);
        }
        catch(ExpressionException ee) {
            return defaultValue;
        }
    }
    
    /**
     * translate a Variant Object to Object, when it is a Dispatch translate it to COMWrapper
     * @param parent
     * @param variant
     * @param key
     * @return Object from Variant
     * @throws ExpressionException
     */
    public static Object toObject(COMObject parent,Variant variant, String key) throws ExpressionException {
        short type=variant.getvt();
        //print.ln(key+" -> variant.getvt("+toStringType(type)+")");
		
		/*
		TODO impl this
		Variant.VariantByref;
		Variant.VariantError;
		Variant.VariantTypeMask;
		*/
		
		
		if(type==Variant.VariantEmpty) return null;
		else if(type==Variant.VariantNull) return null;
		else if(type==Variant.VariantShort) return new Short(variant.toShort());
		else if(type==Variant.VariantInt) return Constants.Integer(variant.toInt());
		else if(type==Variant.VariantFloat) return new Float(variant.toFloat());
		else if(type==Variant.VariantDouble) return new Double(variant.toDouble());
		else if(type==Variant.VariantCurrency) return new Long(variant.toCurrency());
		else if(type==Variant.VariantObject) return variant.toEnumVariant();
		else if(type==Variant.VariantDate) return new DateTimeImpl((long)variant.toDate(),true);
		else if(type==Variant.VariantString) return variant.getString();
		else if(type==Variant.VariantBoolean) return variant.toBoolean()?Boolean.TRUE:Boolean.FALSE;
		else if(type==Variant.VariantByte) return new Byte(variant.toByte());
		else if(type==Variant.VariantVariant) {
		    throw new ExpressionException("type variant is not supported");
		    //return toObject(variant.getV.get());
		}
		else if(type==Variant.VariantArray) {
			Variant[] varr = variant.getVariantArrayRef();
			Object[] oarr = new Object[varr.length];
			for(int i=0;i<varr.length;i++) {
				oarr[i]=toObject(parent,varr[i],Caster.toString(i));
			}
			return new ArrayImpl(oarr);
		}
		else if(type==Variant.VariantDispatch) {
		    
		    return new COMObject(variant,variant.toDispatch(),parent.getName()+"."+key);
		}
		// TODO  ?? else if(type==Variant.VariantError) return variant.toError();

		throw new ExpressionException("COM Type ["+toStringType(type)+"] not supported");
    }
    

	/**
	 * translate a short Variant Type Defintion to a String (string,empty,null,short ...)
	 * @param type
	 * @return String Variant Type
	 */
	public static String toStringType(short type) {
		if(type==Variant.VariantEmpty) return "empty";
		else if(type==Variant.VariantNull) return "null";
		else if(type==Variant.VariantShort) return "Short";
		else if(type==Variant.VariantInt) return "Integer";
		else if(type==Variant.VariantFloat) return "Float";
		else if(type==Variant.VariantDouble) return "Double";
		else if(type==Variant.VariantCurrency) return "Currency";
		else if(type==Variant.VariantDate) return "Date";
		else if(type==Variant.VariantString) return "String";
		else if(type==Variant.VariantBoolean) return "Boolean";
		else if(type==Variant.VariantByte) return "Byte";
		else if(type==Variant.VariantArray) return "Array";
		else if(type==Variant.VariantDispatch) return "Dispatch";
		else if(type==Variant.VariantByref) return "Byref";
		else if(type==Variant.VariantCurrency) return "Currency";
		else if(type==Variant.VariantError) return "Error";
		else if(type==Variant.VariantInt) return "int";
		else if(type==Variant.VariantObject) return "Object";
		else if(type==Variant.VariantTypeMask) return "TypeMask";
		else if(type==Variant.VariantVariant) return "Variant";
		else return "unknow";
	}

}