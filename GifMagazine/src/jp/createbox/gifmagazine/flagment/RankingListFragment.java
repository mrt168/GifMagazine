package jp.createbox.gifmagazine.flagment;

import jp.createbox.gifmagazine.json.JsonLoader;

import java.util.List;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

public class RankingListFragment {
/*extends ListFragment {

	// アプリリスト用の Adapter
	AppListAdapter mAdapter;

	// List<AppEntry> オブジェクトの LoaderCallbacks
	LoaderManager.LoaderCallbacks<List<AppEntry>> mCallbacks = new LoaderManager.LoaderCallbacks<List<AppEntry>>() {

		// 新しい Loader を生成するときに呼ばれる
		@Override
		public Loader<List<AppEntry>> onCreateLoader(int id, Bundle args) {
			// 端末にインストールされているアプリを取得する Loader を返す
			return new JsonLoader(getActivity());
		}

		// データの読み込みが完了したときに呼ばれる
		@Override
		public void onLoadFinished(Loader<List<AppEntry>> loader,
				List<AppEntry> data) {
			// 引数の１番目に Loader, ２番目に読み込んだデータが渡される

			// Adapter にデータをセット
			mAdapter.setData(data);

			if (isResumed()) {
				// リストを表示
				setListShown(true);
			} else {
				// 一時停止状態だったときはアニメーションなしでリストを表示
				setListShownNoAnimation(true);
			}
		}

		// Loader リセット時に呼ばれる
		@Override
		public void onLoaderReset(Loader<List<AppEntry>> loader) {
			// Adapter のデータをクリア
			mAdapter.setData(null);
		}
	};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// リストが空のときの文字をセット
		setEmptyText("No applications");

		// Action Bar にメニューを表示
		setHasOptionsMenu(true);

		// アプリリスト用の Adapter をセット
		mAdapter = new AppListAdapter(getActivity());
		setListAdapter(mAdapter);

		// リストが読み込まれるまでプログレスを表示
		setListShown(false);

		// Loader を初期化
		LoaderManager manager = getLoaderManager();
		manager.initLoader(0, null, mCallbacks);
	}
	*/
}
