package jp.createbox.gifmagazine.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

public class DataConnection {
    // �ʐM����
    public final static int CONNECTION_SUCCESS = 1;
    // �ʐM�G���[
    public final static int CONNECTION_ERROR = CONNECTION_SUCCESS + 1;

    private int loaderId; // ���� JsonLoader�ȊO���g���ꍇ�̓C���X�^���X��1��ID�ł͂Ȃ�Loader�̎�ޖ���ID�𕪂���K�v������

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
     * �������[�h����Youtube�̓�����������܂��B
     * @param searchWord �������[�h
     * @param index �����J�n�ʒu
     * @param signupMailListener
     */
    public void signupMail(String putText, final int index, final SignupMailListener signupMailListener) {
        final String urlText = "http://ec2-54-178-51-66.ap-northeast-1.compute.amazonaws.com/users.json?type=sp&";

        // ���Ƀ��[�_�[������ꍇ�͔j��
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
                // �G���[������
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