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

	// �A�v�����X�g�p�� Adapter
	AppListAdapter mAdapter;

	// List<AppEntry> �I�u�W�F�N�g�� LoaderCallbacks
	LoaderManager.LoaderCallbacks<List<AppEntry>> mCallbacks = new LoaderManager.LoaderCallbacks<List<AppEntry>>() {

		// �V���� Loader �𐶐�����Ƃ��ɌĂ΂��
		@Override
		public Loader<List<AppEntry>> onCreateLoader(int id, Bundle args) {
			// �[���ɃC���X�g�[������Ă���A�v�����擾���� Loader ��Ԃ�
			return new JsonLoader(getActivity());
		}

		// �f�[�^�̓ǂݍ��݂����������Ƃ��ɌĂ΂��
		@Override
		public void onLoadFinished(Loader<List<AppEntry>> loader,
				List<AppEntry> data) {
			// �����̂P�Ԗڂ� Loader, �Q�Ԗڂɓǂݍ��񂾃f�[�^���n�����

			// Adapter �Ƀf�[�^���Z�b�g
			mAdapter.setData(data);

			if (isResumed()) {
				// ���X�g��\��
				setListShown(true);
			} else {
				// �ꎞ��~��Ԃ������Ƃ��̓A�j���[�V�����Ȃ��Ń��X�g��\��
				setListShownNoAnimation(true);
			}
		}

		// Loader ���Z�b�g���ɌĂ΂��
		@Override
		public void onLoaderReset(Loader<List<AppEntry>> loader) {
			// Adapter �̃f�[�^���N���A
			mAdapter.setData(null);
		}
	};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// ���X�g����̂Ƃ��̕������Z�b�g
		setEmptyText("No applications");

		// Action Bar �Ƀ��j���[��\��
		setHasOptionsMenu(true);

		// �A�v�����X�g�p�� Adapter ���Z�b�g
		mAdapter = new AppListAdapter(getActivity());
		setListAdapter(mAdapter);

		// ���X�g���ǂݍ��܂��܂Ńv���O���X��\��
		setListShown(false);

		// Loader ��������
		LoaderManager manager = getLoaderManager();
		manager.initLoader(0, null, mCallbacks);
	}
	*/
}
