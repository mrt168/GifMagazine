package jp.createbox.gifmagazine.json;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

//JsonをロードするためのAsyncTaskを継承したクラス
public class JsonLoader extends AsyncTaskLoader<JSONObject> {

	//APIのアドレス
	private String urlText = "http://ec2-54-178-51-66.ap-northeast-1.compute.amazonaws.com/api/v1/gifs/100.json?user_key=twitter_2337176724&key=zvbfi94aamiii7tlsuv9yivu2bba4u0bv&offset=0&limit=4";

	public JsonLoader(Context context) {
		super(context);
	}

	@Override
	public JSONObject loadInBackground() {
		HttpClient httpClient = new DefaultHttpClient();

		StringBuilder uri = new StringBuilder(urlText);
		HttpGet request = new HttpGet(uri.toString());
		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(request);
		} catch (Exception e) {
			Log.d("JsonLoader", "Error Execute");
			return null;
		}

		int status = httpResponse.getStatusLine().getStatusCode();

		if (HttpStatus.SC_OK == status) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				httpResponse.getEntity().writeTo(outputStream);
				String data;
				data = outputStream.toString(); // JSONデータ

				JSONObject rootObject = new JSONObject(data);
				// JSON 形式データ文字列にインデントを加えた形に成形
	            String parsedText = rootObject.toString(4);
	            Log.d("data", parsedText);
				return rootObject;

			} catch (Exception e) {
				Log.d("JsonLoader", "Error");
			}
		} else {
			Log.d("JsonLoader", "Status" + status);
			return null;
		}
		return null;
	}
}