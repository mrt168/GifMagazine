package jp.createbox.gifmagazine.flagment;

import jp.createbox.gifmagazine.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class SignupFragment extends Fragment {
	View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_signup, container, false);
		v.findViewById(R.id.mailsignup_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (checkInputAllData()) {
							String data = "user[email]="
									+ ((EditText) v
											.findViewById(R.id.signup_edit_mail))
											.getText()
									+ "&user[password]="
									+ ((EditText) v
											.findViewById(R.id.signup_edit_pass))
											.getText()
									+ "&user[password_confirmation]="
									+ ((EditText) v
											.findViewById(R.id.signup_edit_pass))
											.getText()
									+ "&user[name]="
									+ ((EditText) v
											.findViewById(R.id.signup_edit_username))
											.getText();
						}
					}
				});
		return v;
	}

	private boolean checkInputAllData() {
		// mailCheck
		if (CheckInput(((EditText) v.findViewById(R.id.signup_edit_username)),
				0)
				&& CheckInput(
						((EditText) v.findViewById(R.id.signup_edit_mail)), 1)
				&& CheckInput(
						((EditText) v.findViewById(R.id.signup_edit_pass)), 2)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean CheckInput(EditText ev, int id) {
		String[] message = { "ユーザネームを入力してください", "メールアドレスを入力してください",
				"パスワードを入力してください" };
		if (!ev.getText().toString().equals("")) {
			if (ev.getText().toString().contains(" ")) {
				ev.setError("スペースは使えません");
				Toast.makeText(getActivity(), "スペースは使えません", 3000).show();
				return false;
			} else {
				return true;
			}
		} else {
			ev.setError(message[id]);
			Toast.makeText(getActivity(), message[id], 3000).show();
			return false;
		}

	}
}
