package jp.createbox.gifmagazine.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

public class DataConnection {
    // 通信成功
    public final static int CONNECTION_SUCCESS = 1;
    // 通信エラー
    public final static int CONNECTION_ERROR = CONNECTION_SUCCESS + 1;

    private int loaderId; // 注意 JsonLoader以外を使う場合はインスタンスに1つのIDではなくLoaderの種類毎にIDを分ける必要がある

    private LoaderManager manager;

    private  Activity activity;
	private JsonLoader loader;
	private DataConnection dc = new DataConnection();

	public DataConnection getInstance(){
		return dc.getInstance();
	}

    public interface SignupMailListener {
        public void didFinish(final int err, final List<GifData> resultList);
    }

    public interface GetYoutubeVideoDataListener {
        public void didFinish(final int err, final GifData data);
    }

    public  void UtasumaConnection(Activity activity) {
        this.activity = activity;
        this.manager = activity.getLoaderManager();
        this.loaderId = hashCode();
    }

    private JsonLoader createAndLoadJsonLoader(String urlText) {
        JsonLoader jsonLoader = new JsonLoader(activity);
        jsonLoader.forceLoad();
        loader = jsonLoader;
        return  jsonLoader;
    }

    /**
     * 検索ワードからYoutubeの動画を検索します。
     * @param searchWord 検索ワード
     * @param index 検索開始位置
     * @param signupMailListener
     */
    public void signupMail(String putText, final int index, final SignupMailListener signupMailListener) {
        final String urlText = "http://ec2-54-178-51-66.ap-northeast-1.compute.amazonaws.com/users.json?type=sp&";

        // 既にローダーがある場合は破棄
        if (manager.getLoader(loaderId) != null) {
            manager.destroyLoader(loaderId);
        }

        manager.initLoader(loaderId, null, new LoaderManager.LoaderCallbacks<JSONObject>() {
            @Override
            public Loader<JSONObject> onCreateLoader(int i, Bundle bundle) {
                return createAndLoadJsonLoader(urlText);
            }

            @Override
            public void onLoadFinished(Loader<JSONObject> objectLoader, JSONObject jsonObject) {
                if (jsonObject != null) {
                    List<GifData> resultList =  GifJsonAnalytics.analysisYoutubeSearchResultList(jsonObject);
                    signupMailListener.didFinish(CONNECTION_SUCCESS, resultList);
                }
                // エラー時処理
                else {
                    signupMailListener.didFinish(CONNECTION_ERROR, null);
                }
            }

            @Override
            public void onLoaderReset(Loader<JSONObject> objectLoader) {
                signupMailListener.didFinish(CONNECTION_SUCCESS, new ArrayList<GifData>());
            }
        });
    }

    public void cancel() {       
        if (loader != null) {
            loader.cancelLoad();
        }
    }

    public void destroy() {
        manager.destroyLoader(loaderId);
    }
}