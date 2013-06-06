package edu.auburn.akm0012.fl_roughdraft;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import edu.auburn.akm0012.fl_roughdraft_fragments.ModuleSelect_Fragment;
import edu.auburn.akm0012.fl_roughdraft_fragments.Optics_Fragment;
import edu.auburn.akm0012.fl_roughdraft_fragments.gravity_Fragment;
import edu.auburn.akm0012.fl_roughdraft_fragments.univ_Gravity_Fragment;

public class MainModuleSelector extends Activity {

	LinearLayout navBar;
	Fragment moduleSelector;

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

			navBar = (LinearLayout) menu.findViewById(R.id.navBar);

			FragmentManager frgman = getFragmentManager();
			FragmentTransaction frgTrans = frgman.beginTransaction();
			frgTrans.commit();

			moduleSelector = new ModuleSelect_Fragment();

			// "MODULE_FRAG" is a TAG
			frgTrans.replace(R.id.moduleSelectorFragment_Portrait,
					moduleSelector, "MODULE_FRAG_PORT");
		}

		// Else phone is in orientation mode
		else {
			FragmentManager frgman = getFragmentManager();
			FragmentTransaction frgTrans = frgman.beginTransaction();
			frgTrans.commit();

			moduleSelector = new ModuleSelect_Fragment();

			// "MODULE_FRAG" is a TAG
			frgTrans.replace(R.id.moduleSelectorFragment_Landscape,
					moduleSelector, "MODULE_FRAG_LAND");
		}

	}

	public void swapFragToGrav(View v) {

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

			Fragment gravFrag = new gravity_Fragment();

			fragmentTransaction.replace(R.id.module_Frame_Portrait, gravFrag,
					"GRAV_FRAG_PORT");
			Fragment moduleFrag = fragmentManager
					.findFragmentByTag("MODULE_FRAG_PORT");

			fragmentTransaction.remove(moduleFrag);

			// Add Shortcut to nav bar
			// navBar = (LinearLayout)
			// moduleSelector.getView().findViewById(R.id.navBar);

			ImageButton gravShortcut = new ImageButton(this);

			gravShortcut.setImageResource(R.drawable.module_icon_gravity);

			// Set the shortcut layout params
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, navBar.findViewById(
							R.id.Module_Button).getHeight());

			// Make it fit in the sliding menu
			// gravShortcut.setScaleType(ScaleType.FIT_XY);
			gravShortcut.setScaleType(ScaleType.CENTER_INSIDE);

			navBar.addView(gravShortcut, layoutParams);
		}

		else {

			Fragment gravFrag = new gravity_Fragment();

			fragmentTransaction.replace(R.id.moduleSelectorFragment_Landscape,
					gravFrag, "GRAV_FRAG_LAND");

			Fragment moduleFrag = fragmentManager
					.findFragmentByTag("MODULE_FRAG_LAND");

			fragmentTransaction.remove(moduleFrag);

			// ImageButton gravShortcut = new ImageButton(this);
			//
			// gravShortcut.setImageResource(R.drawable.module_icon_gravity);
			//
			// // Set the shortcut layout params
			// LinearLayout.LayoutParams layoutParams = new
			// LinearLayout.LayoutParams(
			// LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			//
			// // Make it fit in the sliding menu
			// // gravShortcut.setScaleType(ScaleType.FIT_XY);
			// gravShortcut.setScaleType(ScaleType.CENTER_INSIDE);
			//
			// navBar.addView(gravShortcut, layoutParams);
		}

		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}

	public void swapFragToUnivGrav(View v) {

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

			Fragment univGravFrag = new univ_Gravity_Fragment();

			fragmentTransaction.replace(R.id.module_Frame_Portrait,
					univGravFrag, "UNIV_GRAV_FRAG_PORT");
			Fragment moduleFrag = fragmentManager
					.findFragmentByTag("MODULE_FRAG_PORT");

			fragmentTransaction.remove(moduleFrag);

			// Add Shortcut to nav bar
			// navBar = (LinearLayout)
			// moduleSelector.getView().findViewById(R.id.navBar);

			ImageButton univGravShortcut = new ImageButton(this);

			univGravShortcut.setImageResource(R.drawable.module_icon_one);

			// Set the shortcut layout params, set the height equal to the
			// height of the DropBox picture
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, navBar.findViewById(
							R.id.Module_Button).getHeight());

			// Make it fit in the sliding menu
			// gravShortcut.setScaleType(ScaleType.FIT_XY);
			univGravShortcut.setScaleType(ScaleType.CENTER_INSIDE);

			navBar.addView(univGravShortcut, layoutParams);
		}

		else {

			Fragment univ_GravFrag = new univ_Gravity_Fragment();

			fragmentTransaction.replace(R.id.moduleSelectorFragment_Landscape,
					univ_GravFrag, "UNIV_GRAV_FRAG_LAND");

			Fragment moduleFrag = fragmentManager
					.findFragmentByTag("MODULE_FRAG_LAND");

			fragmentTransaction.remove(moduleFrag);

			// ImageButton gravShortcut = new ImageButton(this);
			//
			// gravShortcut.setImageResource(R.drawable.module_icon_gravity);
			//
			// // Set the shortcut layout params
			// LinearLayout.LayoutParams layoutParams = new
			// LinearLayout.LayoutParams(
			// LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			//
			// // Make it fit in the sliding menu
			// // gravShortcut.setScaleType(ScaleType.FIT_XY);
			// gravShortcut.setScaleType(ScaleType.CENTER_INSIDE);
			//
			// navBar.addView(gravShortcut, layoutParams);
		}

		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}

	public void swapFragToOptics(View v) {

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

			Fragment opticsFrag = new Optics_Fragment();

			fragmentTransaction.replace(R.id.module_Frame_Portrait, opticsFrag,
					"OPTICS_FRAG_PORT");
			Fragment moduleFrag = fragmentManager
					.findFragmentByTag("MODULE_FRAG_PORT");

			fragmentTransaction.remove(moduleFrag);

			// Add Shortcut to nav bar
			// navBar = (LinearLayout)
			// moduleSelector.getView().findViewById(R.id.navBar);

			ImageButton opticsShortcut = new ImageButton(this);

			opticsShortcut.setImageResource(R.drawable.optics);

			// Set the shortcut layout params, set the height equal to the
			// height of the DropBox picture
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, navBar.findViewById(
							R.id.Module_Button).getHeight());

			// Make it fit in the sliding menu
			// gravShortcut.setScaleType(ScaleType.FIT_XY);
			opticsShortcut.setScaleType(ScaleType.CENTER_INSIDE);

			navBar.addView(opticsShortcut, layoutParams);
		}

		else {

			Fragment opticsFrag = new Optics_Fragment();

			fragmentTransaction.replace(R.id.moduleSelectorFragment_Landscape,
					opticsFrag, "OPTICS_FRAG_LAND");

			Fragment moduleFrag = fragmentManager
					.findFragmentByTag("MODULE_FRAG_LAND");

			fragmentTransaction.remove(moduleFrag);

			// ImageButton gravShortcut = new ImageButton(this);
			//
			// gravShortcut.setImageResource(R.drawable.module_icon_gravity);
			//
			// // Set the shortcut layout params
			// LinearLayout.LayoutParams layoutParams = new
			// LinearLayout.LayoutParams(
			// LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			//
			// // Make it fit in the sliding menu
			// // gravShortcut.setScaleType(ScaleType.FIT_XY);
			// gravShortcut.setScaleType(ScaleType.CENTER_INSIDE);
			//
			// navBar.addView(gravShortcut, layoutParams);
		}

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
