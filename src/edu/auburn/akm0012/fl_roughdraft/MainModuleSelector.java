package edu.auburn.akm0012.fl_roughdraft;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import edu.auburn.akm0012.fl_roughdraft_fragments.gravity_Fragment;

public class MainModuleSelector extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_module_selector);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			SlidingMenu menu;
			menu = new SlidingMenu(this);
			menu.setMode(SlidingMenu.LEFT);
			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			menu.setShadowWidth(5);
			menu.setFadeDegree(0.0f);
			menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
			menu.setBehindWidth(200);
			menu.setMenu(R.layout.menu_frame);
		}

	}

	public void swapFragToGrav(View v) {

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		Fragment gravFrag = new gravity_Fragment();

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			
			fragmentTransaction.replace(R.id.module_Frame_Portrait, gravFrag,
					"GRAV_FRAG");
		}
		
		else {
			fragmentTransaction.replace(R.id.moduleSelectorFragment_landscape, gravFrag,
					"GRAV_FRAG");
		}
		// Fragment moduleSelectFrag =
		// findFragmentById(R.id.moduleSelectorFragment);

		// fragmentTransaction.remove(moduleSelectFrag);

		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_module_selector, menu);
		return true;
	}

}
