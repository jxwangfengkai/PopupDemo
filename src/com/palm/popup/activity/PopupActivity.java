package com.palm.popup.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.palm.popup.R;
import com.palm.popup.util.UnitUtil;

/**
 * PopupWindow底部弹出两个按钮
 * 
 * @author weixiang.qin
 * 
 */
public class PopupActivity extends Activity implements OnClickListener {
	private Activity mActivity;
	private PopupWindow titleWindow;
	private PopupWindow bottomWindow;
	private PopupWindow menuWindow;
	private View lineView;
	private View titleView;
	private ImageView arrowIv;
	private Button menuBtn;
	private Button bottomBtn;
	private Button fullBtn;
	private Button promptBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup);
		mActivity = this;
		lineView = findViewById(R.id.line_view);
		titleView = findViewById(R.id.title_view);
		arrowIv = (ImageView) findViewById(R.id.arrow_iv);
		menuBtn = (Button) findViewById(R.id.menu_btn);
		bottomBtn = (Button) findViewById(R.id.bottom_btn);
		fullBtn = (Button) findViewById(R.id.full_btn);
		promptBtn = (Button) findViewById(R.id.prompt_btn);
		titleView.setOnClickListener(this);
		menuBtn.setOnClickListener(this);
		bottomBtn.setOnClickListener(this);
		fullBtn.setOnClickListener(this);
		promptBtn.setOnClickListener(this);
	}

	/**
	 * 控件下方显示popupwindow
	 */
	public void showTitleView() {
		if (titleWindow == null) {
			View view = LayoutInflater.from(mActivity).inflate(
					R.layout.popup_title_view, null);
			view.findViewById(R.id.button_view).setOnClickListener(this);
			view.findViewById(R.id.title_left_btn).setOnClickListener(this);
			view.findViewById(R.id.title_right_btn).setOnClickListener(this);
			titleWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			titleWindow.setOnDismissListener(new OnDismissListener() {
				@Override
				public void onDismiss() {
					arrowIv.startAnimation(AnimationUtils.loadAnimation(
							mActivity, R.anim.arrow_down));
				}
			});
			titleWindow.setBackgroundDrawable(new BitmapDrawable());
			titleWindow.setOutsideTouchable(true);
			titleWindow.setFocusable(true);
			titleWindow.update();
			titleWindow.showAsDropDown(titleView);
		} else {
			titleWindow.showAsDropDown(titleView);
			arrowIv.startAnimation(AnimationUtils.loadAnimation(mActivity,
					R.anim.arrow_up));
		}
	}

	/**
	 * 底部显示popupwindow
	 */
	private void showBottomView() {
		if (bottomWindow == null) {
			View view = LayoutInflater.from(mActivity).inflate(
					R.layout.popup_bottom_view, null);
			view.findViewById(R.id.bottom_left_btn).setOnClickListener(this);
			view.findViewById(R.id.bottom_right_btn).setOnClickListener(this);
			bottomWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			bottomWindow.setAnimationStyle(R.style.popup_anim_style);// 动画效果
			bottomWindow.setBackgroundDrawable(new BitmapDrawable());
			bottomWindow.setOutsideTouchable(true);
			bottomWindow.setFocusable(true);
			bottomWindow.update();
			bottomWindow.showAtLocation(titleView, Gravity.BOTTOM, 0, 0);
		} else {
			bottomWindow.showAtLocation(titleView, Gravity.BOTTOM, 0, 0);
		}
	}

	/**
	 * 菜单显示popupwindow
	 */
	private void showMenuView() {
		if (menuWindow == null) {
			View view = LayoutInflater.from(mActivity).inflate(
					R.layout.popup_menu_view, null);
			view.findViewById(R.id.one_week_btn).setOnClickListener(this);
			view.findViewById(R.id.three_week_btn).setOnClickListener(this);
			view.findViewById(R.id.one_month_btn).setOnClickListener(this);
			view.findViewById(R.id.three_month_btn).setOnClickListener(this);
			menuWindow = new PopupWindow(view, UnitUtil.dip2Px(
					mActivity, 150), LayoutParams.WRAP_CONTENT);
			menuWindow.setAnimationStyle(R.style.popop_menu_anim_style);
			menuWindow.setBackgroundDrawable(new BitmapDrawable());
			menuWindow.setOutsideTouchable(true);
			menuWindow.setFocusable(true);
			menuWindow.update();
			menuWindow.showAsDropDown(menuBtn);
		} else {
			menuWindow.showAsDropDown(menuBtn);
		}
	}

	/**
	 * 全屏引导页
	 */
	private void showFullView() {
		View view = LayoutInflater.from(mActivity).inflate(
				R.layout.popup_full_view, null);
		final PopupWindow window = new PopupWindow(view,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		View fullView = window.getContentView().findViewById(R.id.full_view);
		fullView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				window.dismiss();
			}
		});
		window.setBackgroundDrawable(new BitmapDrawable());
		window.setOutsideTouchable(true);
		window.showAsDropDown(lineView);
	}

	/**
	 * 提示
	 */
	private void showPromptView() {
		View view = LayoutInflater.from(mActivity).inflate(
				R.layout.popup_prompt_view, null);
		view.measure(0, 0);
		int height = view.getMeasuredHeight();
		int width = view.getMeasuredWidth();
		PopupWindow window = new PopupWindow(view, width, height);
		window.setBackgroundDrawable(new BitmapDrawable());
		window.setOutsideTouchable(true);
		window.setFocusable(true);
		window.update();
		int xoff = -window.getWidth() / 2 + promptBtn.getWidth() / 2;
		int yoff = -window.getHeight() - promptBtn.getHeight();
		window.showAsDropDown(promptBtn, xoff, yoff);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == titleView.getId()) {
			showTitleView();
		} else if (v.getId() == menuBtn.getId()) {
			showMenuView();
		} else if (v.getId() == bottomBtn.getId()) {
			showBottomView();
		} else if (v.getId() == fullBtn.getId()) {
			showFullView();
		} else if (v.getId() == promptBtn.getId()) {
			showPromptView();
		} else if (v.getId() == R.id.button_view) {
			titleWindow.dismiss();
		} else if (v.getId() == R.id.title_left_btn) {
			titleWindow.dismiss();
		} else if (v.getId() == R.id.title_right_btn) {
			titleWindow.dismiss();
		} else if (v.getId() == R.id.bottom_left_btn) {
			bottomWindow.dismiss();
		} else if (v.getId() == R.id.bottom_right_btn) {
			bottomWindow.dismiss();
		} else if (v.getId() == R.id.one_week_btn) {
			menuWindow.dismiss();
		} else if (v.getId() == R.id.three_week_btn) {
			menuWindow.dismiss();
		} else if (v.getId() == R.id.one_month_btn) {
			menuWindow.dismiss();
		} else if (v.getId() == R.id.three_month_btn) {
			menuWindow.dismiss();
		}
	}

}
