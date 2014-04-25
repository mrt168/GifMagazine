package jp.createbox.gifmagazine.flagment;

import jp.createbox.gifmagazine.R;
import jp.createbox.gifmagazine.SigninActivity;
import jp.createbox.gifmagazine.SignupActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), SignupActivity.class);
				startActivity(intent);
			}
		});

		v.findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), SigninActivity.class);
				startActivity(intent);
			}
		});

		return v;
	}
}
