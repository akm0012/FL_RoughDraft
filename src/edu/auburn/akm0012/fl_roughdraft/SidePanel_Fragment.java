package edu.auburn.akm0012.fl_roughdraft;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SidePanel_Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInsttanceState) {
		View view = inflater.inflate(R.layout.side_panel_fragment, container,
				false);

		return view;
	}

}
