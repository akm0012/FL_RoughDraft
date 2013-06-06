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

			// Create the right sliding menu
			SlidingMenu rightMenu;
			rightMenu = new SlidingMenu(this);
			rightMenu.setMode(SlidingMenu.RIGHT);
			// This will make the menu only open when you swipe near the edge of
			// the screen. Other option is FULLSCREEN
			rightMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			rightMenu.setShadowWidth(5);
			rightMenu.setFadeDegree(0.0f);
			rightMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
			rightMenu.setBehindWidth(200);
			rightMenu.setMenu(R.layout.right_menu_frame);

			// Create the left sliding menu
			SlidingMenu leftMenu;
			leftMenu = new SlidingMenu(this);
			leftMenu.setMode(SlidingMenu.LEFT);
			// This will make the menu only open when you swipe near the edge of
			// the screen. Other option is FULLSCREEN
			leftMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			leftMenu.setShadowWidth(5);
			leftMenu.setFadeDegree(0.0f);
			leftMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
			leftMenu.setBehindWidth(200);
			leftMenu.setMenu(R.layout.left_menu_frame);

			navBar = (LinearLayout) leftMenu.findViewById(R.id.navBar);

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

			// Create the left STATIC menu
			SlidingMenu leftMenu;
			leftMenu = new SlidingMenu(this);
			leftMenu.setMode(SlidingMenu.LEFT);
			// This will make the menu only open when you swipe near the edge of
			// the screen. Other option is FULLSCREEN
			leftMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			leftMenu.setShadowWidth(5);
			leftMenu.setFadeDegree(0.0f);
			leftMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
			leftMenu.setBehindWidth(200);
			leftMenu.setSlidingEnabled(false);
			leftMenu.showMenu();
			leftMenu.setMenu(R.layout.left_menu_frame);

			// Create the right sliding menu
			SlidingMenu rightMenu;
			rightMenu = new SlidingMenu(this);
			rightMenu.setMode(SlidingMenu.RIGHT);
			// This will make the menu only open when you swipe near the edge of
			// the screen. Other option is FULLSCREEN
			rightMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			rightMenu.setShadowWidth(5);
			rightMenu.setFadeDegree(0.0f);
			rightMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
			rightMenu.setBehindWidth(200);
			rightMenu.setMenu(R.layout.right_menu_frame);

			navBar = (LinearLayout) leftMenu.findViewById(R.id.navBar);

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

			fragmentTransaction.remove(moduleSelector);

			fragmentTransaction.add(R.id.moduleSelectorFragment_Landscape,
					gravFrag, "GRAV_FRAG_LAND");

			// fragmentTransaction.replace(R.id.moduleSelectorFragment_Landscape,
			// gravFrag, "GRAV_FRAG_LAND");

			// Fragment moduleFrag = fragmentManager
			// .findFragmentByTag("MODULE_FRAG_LAND");
			//
			// fragmentTransaction.remove(moduleFrag);

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

			fragmentTransaction.remove(moduleSelector);

			fragmentTransaction.add(R.id.moduleSelectorFragment_Landscape,
					univ_GravFrag, "UNIV_GRAV_FRAG_LAND");

			// fragmentTransaction.replace(R.id.moduleSelectorFragment_Landscape,
			// univ_GravFrag, "UNIV_GRAV_FRAG_LAND");
			//
			// Fragment moduleFrag = fragmentManager
			// .findFragmentByTag("MODULE_FRAG_LAND");
			//
			// fragmentTransaction.remove(moduleFrag);

			ImageButton univGravShortcut = new ImageButton(this);

			univGravShortcut.setImageResource(R.drawable.module_icon_one);

			// Set the shortcut layout params
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, navBar.findViewById(
							R.id.Module_Button).getHeight());

			// Make it fit in the sliding menu
			// gravShortcut.setScaleType(ScaleType.FIT_XY);
			univGravShortcut.setScaleType(ScaleType.CENTER_INSIDE);

			navBar.addView(univGravShortcut, layoutParams);
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

			fragmentTransaction.remove(moduleSelector);

			fragmentTransaction.add(R.id.moduleSelectorFragment_Landscape,
					opticsFrag, "OPTICS_FRAG_LAND");

			// fragmentTransaction.replace(R.id.moduleSelectorFragment_Landscape,
			// opticsFrag, "OPTICS_FRAG_LAND");
			//
			// Fragment moduleFrag = fragmentManager
			// .findFragmentByTag("MODULE_FRAG_LAND");
			//
			// fragmentTransaction.remove(moduleFrag);

			ImageButton opticsShortcut = new ImageButton(this);

			opticsShortcut.setImageResource(R.drawable.optics);

			// Set the shortcut layout params
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, navBar.findViewById(
							R.id.Module_Button).getHeight());

			// Make it fit in the sliding menu
			// gravShortcut.setScaleType(ScaleType.FIT_XY);
			opticsShortcut.setScaleType(ScaleType.CENTER_INSIDE);

			navBar.addView(opticsShortcut, layoutParams);
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
