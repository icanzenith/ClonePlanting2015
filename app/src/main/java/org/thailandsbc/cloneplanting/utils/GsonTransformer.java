package org.thailandsbc.cloneplanting.utils;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

public class GsonTransformer implements Transformer {

	public GsonTransformer() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public <T> T transform(String url, Class<T> type, String encoding,
			byte[] data, AjaxStatus status) {
		// TODO Auto-generated method stub
		Gson g = new Gson();
		return g.fromJson(new String(data), type);
	}

}