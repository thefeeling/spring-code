package org.springframework.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.app.boardApp.domain.model.Board;

/**
 * : Message 처리 클래스
 * Map 기반으로 사용
 * @author kschoi
 *
 */
public class Message {
	private Map map;

	public static void main(String[] args) {
		Board test = new Board();
		test.setBoardNo(10);
		
	}
	
	
	/**
	 * 기본 생성자
	 */
	public Message() {
		this.map = new HashMap();
		this.put("exception", false);
		this.put("exceptionMsg", "");
		this.put("exceptionRedirectUrl", "");
	}

    // Object - > String
	// 내부 파싱으로 사용
    private String objToStr(Object objVal, String emptyValue){
        if(objVal == null || objVal.equals("")){
        	return emptyValue;
        }
        emptyValue = objVal.toString();
        return emptyValue;
    }	
	
	/**
	 * @param key
	 * @return object
	 */
	public Object get(String key) {
		return map.get(key);
	}

	/**
	 * @param key
	 * @param value
	 */
	public Message put(String key, int value) {
		map.put(key, new Integer(value));
		return this;
	}

	/**
	 * @param key
	 * @param value
	 */
	public Message put(String key, float value) {
		map.put(key, new Float(value));
		return this;
	}

	/**
	 * @param key
	 * @param value
	 */
	public Message put(String key, long value) {
		map.put(key, new Long(value));
		return this;
	}

	/**
	 * @param key
	 * @param d
	 */
	public Message put(String key, double value) {
		put(key, new Double(value));
		return this;
	}

	/**
	 * @param key
	 * @param value
	 */
	public Message put(String key, String value) {
	    if(value == null) value = "";
		map.put(key, new String(value));
		return this;
	}

	/**
	 * @param key
	 * @param value
	 */
	public Message put(String key, boolean value) {
	    map.put(key, new Boolean(value));
	    return this;
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public Message put(String key, Object value) {
		map.put(key, value);
		return this;
	}

	/**
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(this.objToStr(map.get(key),"").equals("")){
	    	return 0;
	    }
		return Integer.parseInt(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public float getFloat(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(this.objToStr(map.get(key),"").equals("")){
	    	return 0;
	    }
		return Float.parseFloat(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(this.objToStr(map.get(key),"").equals("")) {
	    	return 0;
	    }
		return (long)Double.parseDouble(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
	    if(map.containsKey(key) == false) return 0;
	    if(this.objToStr(map.get(key),"").equals("")){
	    	return 0;
	    }
		return Double.parseDouble(map.get(key).toString());
	}

	/**
	 * @param key
	 * @return
	 */
	public String getString(String key) {
        if(map.containsKey(key) == false) return "";
		return this.objToStr(map.get(key),"");
/**
	    if(map.containsKey(key) == false) return "";
	    if(ComUtil.objToStr(map.get(key),"").equals("")) return "";
		String wd = (String) map.get(key);
		return wd;
**/
	}

	/**
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
	    if(map.containsKey(key) == false) return null;
		return map.get(key);
	}

	public String toString() {
		return map.toString();
	}

	public Map get() {
		return this.map;
	}

	public void setMessage(HashMap hm){
		this.map = hm;
	}

	public Map replaceCase(String mode){
        Iterator iterator = this.map.entrySet().iterator();
        Entry entry = null;
        HashMap rtnMap = new HashMap();
		if(mode.equals("lower")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            rtnMap.put(entry.getKey().toString().toLowerCase(), entry.getValue());
	        }
		}else if(mode.equals("upper")){
	        while(iterator.hasNext()){
	            entry = (Entry)iterator.next();
	            rtnMap.put(entry.getKey().toString().toUpperCase(), entry.getValue());
	        }
		}
		this.setMessage(rtnMap);
		return this.map;
	}

	public void applyException(Exception ex){
		if (ex == null) {
			this.put("exception", false);
			this.put("exceptionMsg", "");
		}else {
			this.put("exception", true);
			this.put("exceptionMsg", ex.getMessage());
		}
	}	
}
