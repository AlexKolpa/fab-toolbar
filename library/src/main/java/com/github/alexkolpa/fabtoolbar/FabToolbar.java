package com.github.alexkolpa.fabtoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.melnykov.fab.FloatingActionButton;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealFrameLayout;

public class FabToolbar extends RevealFrameLayout {

	private static final int DEFAULT_ANIMATION_DURATION = 500;

	private LinearLayout container;
	private FloatingActionButton button;
	private float screenWidth;
	private int animationDuration = DEFAULT_ANIMATION_DURATION;
	private OnClickListener clickListener;

	public FabToolbar(Context context) {
		super(context);
		init();
	}

	public FabToolbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		loadAttributes(attrs);
	}

	public FabToolbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		loadAttributes(attrs);
	}

	private void init() {
		screenWidth = getResources().getDisplayMetrics().widthPixels;

		inflate(getContext(), R.layout.fab_toolbar, this);
		button = (FloatingActionButton) findViewById(R.id.button);
		button.setOnClickListener(new ButtonClickListener());
		container = ((LinearLayout) findViewById(R.id.container));
	}

	private void loadAttributes(AttributeSet attrs) {
		TypedArray a = getContext().getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.FabToolbar,
				0, 0);

		int containerGravity;
		int buttonGravity;
		try {
			setColor(a.getColor(R.styleable.FabToolbar_tb_color, getResources().getColor(R.color.blue)));
			animationDuration = a.getInteger(R.styleable.FabToolbar_tb_anim_duration, DEFAULT_ANIMATION_DURATION);
			containerGravity = a.getInteger(R.styleable.FabToolbar_tb_container_gravity, 1);
			buttonGravity = a.getInteger(R.styleable.FabToolbar_tb_button_gravity, 2);

		}
		finally {
			a.recycle();
		}

		container.setGravity(getGravity(containerGravity));

		FrameLayout.LayoutParams buttonParams = (LayoutParams) button.getLayoutParams();
		buttonParams.gravity = getGravity(buttonGravity);
	}

	private int getGravity(int gravityEnum) {
		return (gravityEnum == 0 ? Gravity.START : gravityEnum == 1 ? Gravity.CENTER_HORIZONTAL : Gravity.END)
				| Gravity.CENTER_VERTICAL;
	}

	public void setColor(int color) {
		button.setColorNormal(color);
		button.setColorPressed(color);
		container.setBackgroundColor(color);
	}

	public void setAnimationDuration(int duration) {
		animationDuration = duration;
	}

	public void setButtonOnClickListener(OnClickListener listener) {
		clickListener = listener;
	}

	public void attachToListView(AbsListView listView) {
		button.attachToListView(listView);
	}

	public void attachToRecyclerView(RecyclerView recyclerView) {
		button.attachToRecyclerView(recyclerView);
	}

	public void setButtonIcon(Drawable drawable) {
		button.setImageDrawable(drawable);
	}

	public void setButtonIcon(int resId) {
		button.setImageResource(resId);
	}

	public void show() {
		button.setOnClickListener(null);
		container.setVisibility(VISIBLE);
		animateCircle(0, screenWidth, null);
	}

	public void hide() {
		//If button was attached to list and got hidden, closing the toolbar should still show the button
		button.show(false);
		animateCircle(screenWidth, 0, new ToolbarCollapseListener());
	}

	private void animateCircle(float startRadius, float endRadius, SupportAnimator.AnimatorListener listener) {
		int cx = (button.getLeft() + button.getRight()) / 2;
		int cy = (button.getTop() + button.getBottom()) / 2;

		SupportAnimator animator =
				ViewAnimationUtils.createCircularReveal(container, cx, cy, startRadius, endRadius);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.setDuration(animationDuration);
		if (listener != null) {
			animator.addListener(listener);
		}
		animator.start();
	}

	@Override
	public void addView(@NonNull View child) {
		if (canAddViewToContainer(child)) {
			container.addView(child);
		}
		else {
			super.addView(child);
		}
	}

	@Override
	public void addView(@NonNull View child, int width, int height) {
		if (canAddViewToContainer(child)) {
			container.addView(child, width, height);
		}
		else {
			super.addView(child, width, height);
		}
	}

	@Override
	public void addView(@NonNull View child, ViewGroup.LayoutParams params) {
		if (canAddViewToContainer(child)) {
			container.addView(child, params);
		}
		else {
			super.addView(child, params);
		}
	}

	@Override
	public void addView(@NonNull View child, int index, ViewGroup.LayoutParams params) {
		if (canAddViewToContainer(child)) {
			container.addView(child, index, params);
		}
		else {
			super.addView(child, index, params);
		}
	}

	private boolean canAddViewToContainer(View child) {
		return container != null && !(child instanceof FloatingActionButton);
	}

	private class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			show();

			if (clickListener != null) {
				clickListener.onClick(v);
			}
		}
	}

	private class ToolbarCollapseListener implements SupportAnimator.AnimatorListener {
		@Override
		public void onAnimationEnd() {
			container.setVisibility(GONE);
			button.setOnClickListener(new ButtonClickListener());
		}

		@Override public void onAnimationStart() {}

		@Override public void onAnimationCancel() {}

		@Override public void onAnimationRepeat() {}
	}
}
