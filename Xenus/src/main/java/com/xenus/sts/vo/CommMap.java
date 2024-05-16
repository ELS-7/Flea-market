package com.xenus.sts.vo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//VO
@SuppressWarnings("rawtypes")
public class CommMap extends HashMap {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CommMap.class);

	public CommMap() {
		super();
	}

	public CommMap(HttpServletRequest request) {
		Map map = request.getParameterMap();
		Iterator it = map.keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			this.put(key, request.getParameter(key).replace("<", "&lt;").replace(">", "&gt;").trim());

			//if (logger.isDebugEnabled())				
				logger.debug("param map ==> {} : {}", key, this.get(key));
		}
	}

	@Override
	public Object get(Object key) {
		return super.get(key) == null ? new String("") : super.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		value = (value == null ? new String("") : value);
		return super.put(key, value);
	}

	public String getString(Object key) {
		return getString(key, "");
	}

	public String getString(Object key, String defaultString) {
		Object value = this.get(key);
		if (!value.equals("")) {
			return value.toString();

		} else {
			if (defaultString == null) {
				defaultString = "";
			}
			return defaultString;
		}
	}

	public int getInt(Object key) {
		try {
			return Integer.parseInt(this.get(key).toString());

		} catch (Exception e) {
			return 0;
		}
	}

	public int getInt(Object key, int value) {
		try {
			return Integer.parseInt(this.get(key).toString());

		} catch (Exception e) {
			return value;
		}
	}

	public float getFloat(Object key) {
		try {
			return Float.parseFloat(this.get(key).toString());

		} catch (Exception e) {
			return 0;
		}
	}

	public float getFloat(Object key, float value) {
		try {
			return Float.parseFloat(this.get(key).toString());

		} catch (Exception e) {
			return value;
		}
	}
}
