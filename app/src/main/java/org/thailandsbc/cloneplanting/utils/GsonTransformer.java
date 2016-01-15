package org.thailandsbc.cloneplanting.utils;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

public class GsonTransformer implements Transformer {

	public GsonTransformer() {
	}

	@Override
	public <T> T transform(String url, Class<T> type, String encoding,
			byte[] data, AjaxStatus status) {

		T t =  null;
		Gson g = new Gson();
		try {
			 t = g.fromJson(new String(data), type);

		}catch (Exception e){
			e.printStackTrace();

		}
		return t;
	}

}
