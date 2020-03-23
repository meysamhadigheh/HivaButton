package info.meysam.hivabutton.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Space;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.ColorUtils;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import info.meysam.hivabutton.R;
import info.meysam.hivabutton.helper.ViewUIHelper;
import top.defaults.drawabletoolbox.DrawableBuilder;


/**
 * Created by ashkan on 2/8/18.
 */

public class HivaButton extends RelativeLayout {


	//views
	AppCompatTextView textView;
	AppCompatImageView imageView;
	LinearLayout linearLayout;
	ProgressBar indicatorView;
	Space spaceView;

	//global settings
	int textSize = ViewUIHelper.spToPx(15, getContext());
	int textLines = 2;
	String typeface = "";
	private Drawable circularDrawable;
	int radius = ViewUIHelper.dpToPx(5);
	boolean rounded = false;
	int widthPadding = ViewUIHelper.dpToPx(0);
	int heightPadding = ViewUIHelper.dpToPx(0);
	boolean isToggle = false;
	boolean isOn = true;
	boolean toggleGroupCanBeEmpty = true;
	String toggleGroup = null;
	int space = -999;
	int iconWidth = ViewUIHelper.dpToPx(0);
	int iconPosition = 0;
	int strokeWidth = 0;
	int topLeftCorner = 0;
	int bottomLeftCorner = 0;
	int topRightCorner = 0;
	int bottomRightCorner = 0;

	//on state
	String title = "دکمه";
	int icon = 0;
	Bitmap iconBitmap = null;
	int textColor = Color.WHITE;
	int backgroundColor = getContext().getResources().getColor(R.color.colorPrimary);
	int backgroundDrawable = 0;
	int backgroundSecondColor = 0;
	int gradientAngle = 0;
	int originalRippleColor = Color.TRANSPARENT;
	int rippleColor = Color.parseColor("#44ffffff");
	int iconTint = 0;
	int strokeColor = 0;
	int strokeDashGap = 0;
	int strokePressedColor = 0;
	int loadingTint = 0;

	//off state
	String titleOff = "دکمه";
	int iconOff = 0;
	Bitmap iconBitmapOff = null;
	int textColorOff = Color.WHITE;
	int backgroundColorOff = getContext().getResources().getColor(R.color.colorPrimary);
	int backgroundDrawableOff = 0;
	int backgroundSecondColorOff = 0;
	int gradientAngleOff = 0;
	int rippleColorOff = 0;
	int iconTintOff = 0;
	int strokeColorOff = 0;
	int strokeDashGapOff = 0;
	int strokePressedColorOff = 0;

	//disabled state
	int disabledDrawable = 0;
	int disabledBackground = 0;
	int disabledForeground = Color.LTGRAY;


	//extra
	Drawable currentRipple = null;
	Drawable offRipple = null;
	OnToggleListener toggleListener;



	public HivaButton(Context context) {
		super(context);

		init();
	}

	public HivaButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}


	public HivaButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		if (attrs != null) {
			TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HivaButton, defStyle, 0);

			title = a.getString(R.styleable.HivaButton_text);
			textColor = a.getColor(R.styleable.HivaButton_textColor, textColor);
			textSize = a.getDimensionPixelSize(R.styleable.HivaButton_textSize, textSize);
			textLines = a.getInteger(R.styleable.HivaButton_textLines, textLines);
			radius = a.getDimensionPixelSize(R.styleable.HivaButton_radius, radius);
			widthPadding = a.getDimensionPixelSize(R.styleable.HivaButton_widthPadding, widthPadding);
			heightPadding = a.getDimensionPixelSize(R.styleable.HivaButton_heightPadding, heightPadding);
			typeface = a.getString(R.styleable.HivaButton_typeface);
			loadingTint = a.getColor(R.styleable.HivaButton_loadingTint, textColor);

			isToggle = a.getBoolean(R.styleable.HivaButton_isToggle, isToggle);
			isOn = a.getBoolean(R.styleable.HivaButton_isOn, isOn);
			toggleGroupCanBeEmpty = a.getBoolean(R.styleable.HivaButton_toggleGroupCanBeEmpty, toggleGroupCanBeEmpty);
			toggleGroup = a.getString(R.styleable.HivaButton_toggleGroup);

			rounded = a.getBoolean(R.styleable.HivaButton_rounded, rounded);

			backgroundColor = a.getColor(R.styleable.HivaButton_backgroundColor, backgroundColor);
			backgroundDrawable = a.getResourceId(R.styleable.HivaButton_backgroundDrawable, backgroundDrawable);
			originalRippleColor = a.getColor(R.styleable.HivaButton_rippleColor, textColor);
			rippleColor = ColorUtils.setAlphaComponent(originalRippleColor, (int) Math.round(255 * 0.3));

			backgroundSecondColor = a.getColor(R.styleable.HivaButton_backgroundSecondColor, backgroundSecondColor);
			gradientAngle = a.getInt(R.styleable.HivaButton_gradientAgnle, 90);


			disabledBackground = a.getColor(R.styleable.HivaButton_disabledBackground, disabledBackground);
			disabledForeground = a.getColor(R.styleable.HivaButton_disabledForeground, disabledForeground);
			disabledDrawable = a.getResourceId(R.styleable.HivaButton_disabledDrawable, disabledDrawable);

			strokeColor = a.getColor(R.styleable.HivaButton_strokeColor, textColor);
			strokePressedColor = a.getColor(R.styleable.HivaButton_strokePressedColor, strokeColor);
			strokeWidth = a.getDimensionPixelSize(R.styleable.HivaButton_strokeWidth, strokeWidth);
			strokeDashGap = a.getInt(R.styleable.HivaButton_strokeDashGap, strokeDashGap);

			topLeftCorner = a.getDimensionPixelSize(R.styleable.HivaButton_topLeftCorner, topLeftCorner);
			bottomLeftCorner = a.getDimensionPixelSize(R.styleable.HivaButton_bottomLeftCorner, bottomLeftCorner);
			topRightCorner = a.getDimensionPixelSize(R.styleable.HivaButton_topRightCorner, topRightCorner);
			bottomRightCorner = a.getDimensionPixelSize(R.styleable.HivaButton_bottomRightCorner, bottomRightCorner);

			iconWidth = a.getDimensionPixelSize(R.styleable.HivaButton_iconWidth, iconWidth);
			space = a.getDimensionPixelSize(R.styleable.HivaButton_space, space);
			icon = a.getResourceId(R.styleable.HivaButton_icon, icon);
			iconPosition = a.getInt(R.styleable.HivaButton_iconPosition, iconPosition);
			iconTint = a.getColor(R.styleable.HivaButton_iconTint, textColor);


			//off state
			titleOff = a.getString(R.styleable.HivaButton_textOff);
			if(titleOff == null || titleOff.equals("")) titleOff = title;
			iconOff = a.getResourceId(R.styleable.HivaButton_iconOff, icon);
			textColorOff = a.getColor(R.styleable.HivaButton_textColorOff, textColor);
			backgroundColorOff = a.getColor(R.styleable.HivaButton_backgroundColorOff, backgroundColor);
			backgroundDrawableOff = a.getResourceId(R.styleable.HivaButton_backgroundDrawableOff, backgroundDrawable);
			rippleColorOff = ColorUtils.setAlphaComponent(a.getColor(R.styleable.HivaButton_rippleColor_Off, textColorOff), (int) Math.round(255 * 0.3));
			backgroundSecondColorOff = a.getColor(R.styleable.HivaButton_backgroundSecondColorOff, backgroundSecondColorOff);
			gradientAngleOff = a.getInt(R.styleable.HivaButton_gradientAgnleOff, gradientAngle);
			iconTintOff = a.getColor(R.styleable.HivaButton_iconTintOff, iconTint);
			strokeColorOff = a.getColor(R.styleable.HivaButton_strokeColorOff, strokeColor);
			strokePressedColorOff = a.getColor(R.styleable.HivaButton_strokePressedColorOff, strokeColorOff);
			strokeDashGapOff = a.getInt(R.styleable.HivaButton_strokeDashGapOff, strokeDashGap);

			a.recycle();
		}

		init();
	}




	//////////////////
	/// main functions
	/////////////////

	void init() {

		this.setClickable(true);

		if(isToggle){
			this.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {

					if(isOn && !toggleGroupCanBeEmpty) {
						//can not be turned off
					}else{
						toggle();
						if(toggleListener != null){
							toggleListener.toggled(HivaButton.this);
						}
					}

					if(getParent() instanceof ViewGroup){

						ViewGroup parent = (ViewGroup) getParent();

						for(int index = 0; index < parent.getChildCount(); index++){

							if(parent.getChildAt(index) instanceof HivaButton){
								HivaButton sibiling = (HivaButton) parent.getChildAt(index);

								if(sibiling == HivaButton.this){
									continue;
								}

								if(HivaButton.this.toggleGroup != null && sibiling.toggleGroup != null && sibiling.toggleGroup.equals(HivaButton.this.toggleGroup)){
									sibiling.setOn(false);

									if(sibiling.toggleListener != null){
										sibiling.toggleListener.toggled(sibiling);
									}
								}
							}
						}
					}
				}
			});
		}

		textView = new AppCompatTextView(getContext());
		setTextViewTitle();
		textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

		textView.setGravity(Gravity.CENTER);
		setTextViewColor();
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		textView.setMaxLines(textLines);
		if(textLines == 1){
			textView.setSingleLine(true);
		}

		if(typeface != null && !typeface.equals("")) {
			try {
				Typeface t = Typeface.createFromAsset(getContext().getAssets(), String.format("fonts/%s", typeface));
				textView.setTypeface(t);
			}catch (Exception e){
			}
		}

		//set indicator
		indicatorView = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleSmall);
		circularDrawable = new CircularProgressDrawable(getContext());

		((CircularProgressDrawable) circularDrawable).setStrokeCap(Paint.Cap.ROUND);
		circularDrawable.setColorFilter(new
				PorterDuffColorFilter(loadingTint, PorterDuff.Mode.SRC_ATOP));

		indicatorView.setAlpha(0.9f);

		indicatorView.setIndeterminateDrawable(circularDrawable);
		LayoutParams indicatorViewParams = null;

		indicatorViewParams = new LayoutParams(
				ViewUIHelper.dpToPx(25) ,
				ViewUIHelper.dpToPx(25) );

		((CircularProgressDrawable) circularDrawable).setStrokeWidth(ViewUIHelper.dpToPx(4));


		indicatorViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		indicatorView.setVisibility(GONE);
		indicatorView.setLayoutParams(indicatorViewParams);


		linearLayout = new LinearLayout(getContext());


		LayoutParams linearLayoutLayoutParams =
				new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);

		linearLayoutLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		linearLayout.setLayoutParams(linearLayoutLayoutParams);
		linearLayout.setClipToPadding(false);
		linearLayout.setClipChildren(false);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			linearLayout.setClipToOutline(false);
		}

		linearLayout.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), getPaddingBottom());
		this.setPadding(0,0,0,0);

		imageView = new AppCompatImageView(getContext());
		//imageView.setBackgroundColor(Color.YELLOW);
		spaceView = new Space(getContext());

		if(icon != 0 && iconWidth == 0){
			imageView.setLayoutParams(new LayoutParams(textSize,textSize));

		}else{
			imageView.setLayoutParams(new LayoutParams(iconWidth,iconWidth));
		}
		imageView.setImageResource(icon);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		setIconColor();


		if(icon != 0 && space == -999){
			space = textSize / 3;
		}


		linearLayout.setGravity(Gravity.CENTER);

		switch (iconPosition) {
			case 0 : //right
			default:
				linearLayout.setOrientation(LinearLayout.HORIZONTAL);
				spaceView.setLayoutParams(new LayoutParams(space,1));

				break;
			case 1 : //left
				linearLayout.setOrientation(LinearLayout.HORIZONTAL);
				spaceView.setLayoutParams(new LayoutParams(space,1));
				break;

			case 2: //top
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				spaceView.setLayoutParams(new LayoutParams(1,space));
				break;
			case 3 :
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				spaceView.setLayoutParams(new LayoutParams(1,space));
				break;
		}

		if(widthPadding > 0  && linearLayout.getOrientation() == LinearLayout.HORIZONTAL){
			Space widthPaddingSapce = new Space(getContext());
			widthPaddingSapce.setLayoutParams(new LayoutParams(widthPadding,1));
			linearLayout.addView(widthPaddingSapce);
		}

		if(heightPadding > 0  && linearLayout.getOrientation() == LinearLayout.VERTICAL){
			Space heightPaddingSapce = new Space(getContext());
			heightPaddingSapce.setLayoutParams(new LayoutParams(1,heightPadding));
			linearLayout.addView(heightPaddingSapce);
		}

		switch (iconPosition) {
			case 0 : //right
			default:
				linearLayout.addView(textView);
				linearLayout.addView(spaceView);
				linearLayout.addView(imageView);
				break;
			case 1 : //left
				linearLayout.addView(imageView);
				linearLayout.addView(spaceView);
				linearLayout.addView(textView);
				break;

			case 2: //top
				linearLayout.addView(imageView);
				linearLayout.addView(spaceView);
				linearLayout.addView(textView);
				break;
			case 3 :
				linearLayout.addView(textView);
				linearLayout.addView(spaceView);
				linearLayout.addView(imageView);
				break;
		}

		if(widthPadding > 0 && linearLayout.getOrientation() == LinearLayout.HORIZONTAL){
			View widthPaddingSapce = new View(getContext());
			widthPaddingSapce.setLayoutParams(new LayoutParams(widthPadding,1));
			linearLayout.addView(widthPaddingSapce);
		}

		if(heightPadding > 0 && linearLayout.getOrientation() == LinearLayout.VERTICAL){
			View heightPaddingSapce = new View(getContext());
			heightPaddingSapce.setLayoutParams(new LayoutParams(1, heightPadding));
			linearLayout.addView(heightPaddingSapce);
		}

		if(linearLayout.getOrientation() == LinearLayout.HORIZONTAL) {
			ViewUIHelper.setPaddingPx(this, 0, heightPadding, 0, heightPadding);
		}

		if(linearLayout.getOrientation() == LinearLayout.VERTICAL) {
			ViewUIHelper.setPaddingPx(this, widthPadding, 0, widthPadding, 0);
		}



		this.addView(indicatorView, indicatorViewParams);
		this.addView(linearLayout);

		this.setClipToPadding(false);
		this.invalidate();

		createCurrentRipple();
		createOffRipple();

		setOn(isOn);

	}

	private void createCurrentRipple(){
		DrawableBuilder builder = new DrawableBuilder();

		builder.rectangle()
				.strokeColor(strokeColor)
				.strokeWidth(strokeWidth)
				.strokeColorPressed(strokePressedColor);

		if(backgroundSecondColor != 0){

			builder.gradient()
					.linearGradient()
					.startColor(backgroundSecondColor)
					.endColor(backgroundColor)
					.angle(gradientAngle);
		}else{

			builder.solidColor(backgroundColor);
		}

		if(strokeDashGap > 0) {
			builder.dashWidth(strokeDashGap)
					.dashGap(strokeDashGap);
		}

		if(rounded){
			builder.rounded();

		} else {

			topLeftCorner = topLeftCorner != 0 ? topLeftCorner : radius;
			topRightCorner = topRightCorner != 0 ? topRightCorner : radius;
			bottomLeftCorner = bottomLeftCorner != 0 ? bottomLeftCorner : radius;
			bottomRightCorner = bottomRightCorner != 0 ? bottomRightCorner : radius;

			builder.cornerRadii(topLeftCorner, topRightCorner, bottomRightCorner, bottomLeftCorner);
		}

		if(originalRippleColor != Color.TRANSPARENT) {
			builder.ripple().rippleColor(rippleColor);
		}

		if(backgroundColor == Color.TRANSPARENT) {
			builder.solidColorPressedWhenRippleUnsupported(rippleColor);
		}else {
			builder.solidColorPressedWhenRippleUnsupported(ColorUtils.setAlphaComponent(backgroundColor,(int) Math.round(255 * 0.7)));
		}

		if(backgroundDrawable != 0){
			builder.baseDrawable(getContext().getResources().getDrawable(backgroundDrawable));
		}

		currentRipple = builder.build();

	}

	private void createOffRipple(){
		DrawableBuilder builder = new DrawableBuilder();

		builder.rectangle()
				.hairlineBordered()
				.strokeColor(strokeColorOff)
				.strokeWidth(strokeWidth)
				.strokeColorPressed(strokePressedColorOff);

		if(backgroundSecondColorOff != 0){

			builder.gradient()
					.linearGradient()
					.startColor(backgroundSecondColorOff)
					.endColor(backgroundColorOff)
					.angle(gradientAngleOff);
		}else{

			builder.solidColor(backgroundColorOff);
		}

		if(strokeDashGapOff > 0) {
			builder.dashWidth(strokeDashGapOff)
					.dashGap(strokeDashGapOff);
		}

		if(rounded){
			builder.rounded();

		} else {

			topLeftCorner = topLeftCorner != 0 ? topLeftCorner : radius;
			topRightCorner = topRightCorner != 0 ? topRightCorner : radius;
			bottomLeftCorner = bottomLeftCorner != 0 ? bottomLeftCorner : radius;
			bottomRightCorner = bottomRightCorner != 0 ? bottomRightCorner : radius;

			builder.cornerRadii(topLeftCorner, topRightCorner, bottomRightCorner, bottomLeftCorner);
		}

		builder.ripple().rippleColor(rippleColorOff);

		if(backgroundColorOff == Color.TRANSPARENT) {
			builder.solidColorPressedWhenRippleUnsupported(rippleColorOff);
		}else {
			builder.solidColorPressedWhenRippleUnsupported(ColorUtils.setAlphaComponent(backgroundColorOff,(int) Math.round(255 * 0.7)));
		}

		if(backgroundDrawableOff != 0){
			builder.baseDrawable(getContext().getResources().getDrawable(backgroundDrawableOff));
		}

		offRipple = builder.build();
	}

	/////////////////


	/////////////
	/// public functions
	////////////

	public void startLoadingState(){

		linearLayout.setVisibility(INVISIBLE);
		indicatorView.setVisibility(VISIBLE);
		indicatorView.setIndeterminate(false);
		this.setClickable(false);
	}

	public void setTitle(String title){

		this.title = title;
		this.titleOff = title;

		setTextViewTitle();
	}

	public void setTitle(String title, boolean state){

		if(state) {
			this.title = title;
		}else{
			titleOff = title;
		}
		setTextViewTitle();
	}


	public void setTitleColor(int color){

		this.textColor = color;
		this.textColorOff = color;

		setTextViewColor();
	}

	public void setTitleColor(int color, boolean state){

		if(state) {
			this.textColor = color;
		}else{
			this.textColorOff = color;
		}

		setTextViewColor();
	}

	public void setIcon(int icon){

		this.icon = icon;
		setIconImage();

	}

	public void setIcon(int icon, boolean state){

		if(state) {
			this.icon = icon;
		}else{
			this.iconOff = icon;
		}

		setIconImage();
	}

	public void setIcon(Bitmap icon){

		this.setIcon(0);
		this.iconBitmap = icon;
		this.iconBitmap = icon;
		this.setIconImage();
	}

	public void setIcon(Bitmap icon, boolean state){

		this.setIcon(0, state);

		if(state) {
			this.iconBitmap = icon;
		}else{
			this.iconBitmapOff = icon;
		}

		setIconImage();
	}

	public void setIconTint(int color){
		iconTint = color;
		iconTintOff = color;
		setIconColor();
	}

	public void setIconTint(int color, boolean state){

		if(state) {
			this.iconTint = color;
		}else{
			this.iconTintOff = color;
		}

		setIconColor();

	}

	public void setIconWidthDp(int width){

		int dp = ViewUIHelper.dpToPx(width);

		ViewGroup.LayoutParams params = imageView.getLayoutParams();
		params.width = dp;
		imageView.setLayoutParams(params);

	}

	public void setIconVisibility(int visible){

		imageView.setVisibility(visible);
		spaceView.setVisibility(visible);

	}

	public void stopLoadingState(){

		linearLayout.setVisibility(VISIBLE);
		indicatorView.setVisibility(GONE);
		indicatorView.setIndeterminate(true);

		this.setClickable(true);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		if(enabled == true){

			setOn(isOn);
		}else {

			disable();
		}
	}


	public boolean toggle(){
		isOn = !isOn;

		if(isOn){
			on();
		}else{
			off();
		}

		return isOn;
	}

	public void setOn(boolean isOn){
		this.isOn = isOn;

		if(isOn){
			enable();
		}else{
			off();
		}
	}

	public boolean getOn(){
		return this.isOn;
	}

	public void setOnToggleListener(OnToggleListener listener){
		this.toggleListener = listener;
	}


	public AppCompatTextView getTextView(){
		return textView;
	}
	
	public AppCompatImageView getImageView(){
	 	return imageView;
	}

	public void setLoadingDrawable(Drawable loading){
		this.circularDrawable = loading;
		this.circularDrawable.setColorFilter(new
				PorterDuffColorFilter(loadingTint, PorterDuff.Mode.SRC_ATOP));
	}

	public Drawable getLoadingDrawable(){
		return this.circularDrawable;
	}

	/////////////////


	/////////////////
	/// private functions
	/////////////////

	private void setIconImage() {

		if(icon > 0){
			if(isOn) {
				this.imageView.setImageResource(icon);
			}else{
				this.imageView.setImageResource(iconOff);
			}
		}else if(iconBitmap != null){
			if(isOn) {
				imageView.setImageBitmap(iconBitmap);
			}else{
				imageView.setImageBitmap(iconBitmapOff);
			}
		}

	}

	private void setIconColor() {

		if (isEnabled()) {
			if (isOn) {
				if (iconTint != Color.TRANSPARENT) {
					imageView.setColorFilter(iconTint, PorterDuff.Mode.SRC_IN);
				}
			}else{
				if (iconTintOff != Color.TRANSPARENT) {
					imageView.setColorFilter(iconTintOff, PorterDuff.Mode.SRC_IN);
				}
			}
		}else{
			if (disabledForeground != Color.TRANSPARENT) {
				imageView.setColorFilter(disabledForeground, PorterDuff.Mode.SRC_IN);
			}
		}

	}

	private void setTextViewTitle(){

		if(isOn){
			textView.setText(title);
		}else{
			textView.setText(titleOff);
		}
	}

	private void setTextViewColor(){

		if (isEnabled()) {
			if (isOn) {
				textView.setTextColor(textColor);
			}else{
				textView.setTextColor(textColorOff);
			}
		}else{
			textView.setTextColor(disabledForeground);
		}
	}

	private void setProgressColor(){

		if (isOn) {
			this.circularDrawable.setColorFilter(textColor, PorterDuff.Mode.SRC_IN);
		}else{
			this.circularDrawable.setColorFilter(textColorOff, PorterDuff.Mode.SRC_IN);
		}
	}

	private void setForegroundColor(){

		setTextViewColor();
		setIconImage();
		setIconColor();
		setProgressColor();
	}

	private void on(){

		enable();
	}

	private void off(){

		setTextViewTitle();
		this.setClickable(false);
		this.setBackground(offRipple);
		setClickable(true);
		this.setForegroundColor();


	}


	private void enable(){

		setTextViewTitle();
		this.setBackground(currentRipple);
		this.setForegroundColor();
	}

	private void disable(){

		DrawableBuilder builder = new DrawableBuilder().rectangle()
				.strokeColor(disabledForeground)
				.strokeWidth(strokeWidth)
				.solidColor(disabledBackground);


		if(disabledDrawable != 0){
			builder.baseDrawable(getContext().getResources().getDrawable(disabledDrawable));
		}

		if(rounded){
			builder.rounded();
		}else{
			builder.cornerRadii(topLeftCorner, topRightCorner, bottomRightCorner, bottomLeftCorner);

		}

		this.setBackground(builder.build());
		this.setForegroundColor();
	}

	public interface OnToggleListener{
		void toggled(HivaButton button);
	}
}
