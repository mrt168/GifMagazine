package jp.createbox.gifmagazine.flagment;

import java.util.Arrays;
import java.util.List;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import jp.createbox.gifmagazine.R;
import jp.createbox.gifmagazine.utils.TwitterOAuthRequestTokenCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.LoaderManager;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

public class LoginFragment extends Fragment{
	//twitter用
	private Twitter mTwitter;

	private static final String TAG = LoginFragment.class.getSimpleName();

	private UiLifecycleHelper uiHelper;

	private final List<String> permissions;

	public LoginFragment() {
		permissions = Arrays.asList("user_status");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//facebookLoginのボタン
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		LoginButton authButton = (LoginButton) view.findViewById(R.id.fbLoginButton);
		authButton.setFragment(this);
		authButton.setReadPermissions(permissions);

		//twitterのログインボタン
        Button btn = (Button)view.findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				executeOauth();
			}
		});
	
		return view;
	}
	
	//twitterLoginのメソッド
	private void executeOauth(){
		mTwitter = new TwitterFactory().getInstance();
		mTwitter.setOAuthConsumer(getString(R.string.twitter_consumer_key),
				getString(R.string.twitter_consumer_key_secret));
		mTwitter.setOAuthAccessToken(null);
		LoaderManager.LoaderCallbacks<RequestToken> requestTokenCallbacks = new TwitterOAuthRequestTokenCallbacks(
				getActivity(), mTwitter);
		getActivity().getSupportLoaderManager()
				.initLoader(0, null, requestTokenCallbacks);
//		//Twitetr4Jの設定を読み込む
//    	Configuration conf = ConfigurationContext.getInstance();
//    	//Oauth認証オブジェクト作成
//		OAuthAuthorization oauth = new OAuthAuthorization(conf);
//		//Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定 
//		oauth.setOAuthConsumer("", "");
//		//アプリの認証オブジェクト作成
//		RequestToken _req = null;
//		try {
//			_req = oauth.getOAuthRequestToken("Callback://CallBackActivity");
//		} catch (TwitterException e) {
//			e.printStackTrace();
//		}
//		String _uri;
//		_uri = _req.getAuthorizationURL();
//		startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(_uri)), 0);
    }


	@Override
	public void onResume() {
		super.onResume();

		// For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }

		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.i(TAG, "Logged in...");
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};
}

