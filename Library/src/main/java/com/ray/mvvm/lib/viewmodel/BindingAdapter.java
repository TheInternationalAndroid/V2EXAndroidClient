/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:56:12 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:56:12 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.mvvm.lib.viewmodel;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.ray.mvvm.lib.interfaces.ILoadMore;
import com.ray.mvvm.lib.interfaces.OnBitmapLoadedListener;
import com.ray.mvvm.lib.interfaces.OnItemSwitch;
import com.ray.mvvm.lib.interfaces.OnScrollListener;
import com.ray.mvvm.lib.interfaces.OnTextChanged;
import com.ray.mvvm.lib.interfaces.OnTextChangedIncludeBefore;
import com.ray.mvvm.lib.widget.anotations.ListType;
import com.ray.mvvm.lib.widget.transform.BlurTransformation;
import com.ray.mvvm.lib.widget.utils.DeviceUtil;
import com.ray.mvvm.lib.widget.utils.StringUtil;
import com.ray.mvvm.lib.widget.view.ExItemDecoration;
import com.ray.mvvm.lib.widget.view.OnLoadMoreListener;

import static com.bumptech.glide.Glide.with;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 21:56
 * \ ___________________
 * \| Happy New Year!  |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class BindingAdapter {

    @android.databinding.BindingAdapter(value = {"normalTitleColor", "selectedTitleColor"}, requireAll = true)
    public static void setTabLayoutTextColor(TabLayout tabLayout, int normalTitleColor, int selectedTitleColor) {
        tabLayout.setTabTextColors(normalTitleColor, selectedTitleColor);
    }

    @android.databinding.BindingAdapter(value = {"android:checked", "onCheckedChangeListener"})
    public static void setCheckedState(CompoundButton compoundButton, boolean checked, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        compoundButton.setOnCheckedChangeListener(null);
        compoundButton.setChecked(checked);
        compoundButton.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @android.databinding.BindingAdapter(value = {"viewPager", "adapter"})
    public static void setTabLayoutIcons(TabLayout tabLayout, int viewPagerId, PagerAdapter pagerAdapter) {
        ViewPager viewPager = (ViewPager) tabLayout.getRootView().findViewById(viewPagerId);
        if (viewPager.getAdapter() == null)
            viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @android.databinding.BindingAdapter(value = {"isDrawerOpen", "drawerGravity"})
    public static void controlDrawer(DrawerLayout drawerLayout, boolean isDrawerOpen, int gravity) {
        if (isDrawerOpen) {
            drawerLayout.openDrawer(gravity);
        } else {
            drawerLayout.closeDrawer(gravity);
        }
    }

    @android.databinding.BindingAdapter(value = {"weight", "height"})
    public static void setViewWeightSize(View view, float weight, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int heightSize = (int) (DeviceUtil.sScreenHeight * height);
        int widthSize = (int) (DeviceUtil.sScreenWidth * weight);
        layoutParams.height = heightSize;
        layoutParams.width = widthSize;
        view.setLayoutParams(layoutParams);
    }

    @android.databinding.BindingAdapter(value = {"weightWith", "weightHeight"})
    public static void setWeightSizeView(View view, float weightWith, float weightHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int heightSize = (int) (DeviceUtil.sScreenWidth * weightHeight);
        int widthSize = (int) (DeviceUtil.sScreenWidth * weightWith);
        layoutParams.height = heightSize;
        layoutParams.width = widthSize;
        view.setLayoutParams(layoutParams);
    }

    @android.databinding.BindingAdapter(value = {"blurImageUrl", "placeHolder"})
    public static void loadImageByUrlBlur(ImageView imageView, String url, Drawable placeHolder) {
        if (StringUtil.isEmpty(url)) {
            with(imageView.getContext())
                    .load(placeHolder)
                    .crossFade()
                    .bitmapTransform(new BlurTransformation(imageView.getContext()))
                    .into(imageView);
            return;
        }
        with(imageView.getContext())
                .load(url)
                .crossFade()
                .bitmapTransform(new BlurTransformation(imageView.getContext()))
                .placeholder(placeHolder)
                .into(imageView);
    }

    @android.databinding.BindingAdapter(value = {"blurImageUrl", "placeHolder"})
    public static void loadImageByUrlBlur(ImageView imageView, String url, int placeHolderRes) {
        if (StringUtil.isEmpty(url)) {
            with(imageView.getContext())
                    .load(placeHolderRes)
                    .crossFade()
                    .bitmapTransform(new BlurTransformation(imageView.getContext()))
                    .placeholder(placeHolderRes)
                    .into(imageView);
            return;
        }
        with(imageView.getContext())
                .load(url)
                .crossFade()
                .bitmapTransform(new BlurTransformation(imageView.getContext()))
                .placeholder(placeHolderRes)
                .into(imageView);
    }

    @android.databinding.BindingAdapter(value = {"imgurl", "placeHolder", "showFade"}, requireAll = false)
    public static void loadImageByPath(ImageView imageView, String imgurl, Drawable placeHolder, boolean showFade) {
        Glide.clear(imageView);
        if (StringUtil.isEmpty(imgurl)) {
            imageView.setImageDrawable(placeHolder);
            return;
        }
        if (showFade) {
            with(imageView.getContext()).load(imgurl).centerCrop().placeholder(placeHolder).crossFade().into(imageView);
        } else {
            with(imageView.getContext()).load(imgurl).centerCrop().placeholder(placeHolder).dontAnimate().into(imageView);
        }
    }

    @android.databinding.BindingAdapter(value = {"imgurl", "placeHolder", "callback"}, requireAll = false)
    public static void loadScaleImageByPath(SubsamplingScaleImageView imageView, String imgurl, Drawable placeHolder, OnBitmapLoadedListener callback) {
        Glide
                .with(imageView.getContext())
                .load(imgurl)
                .asBitmap()
                .placeholder(placeHolder)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        callback.onLoaded(resource);
                        imageView.setImage(ImageSource.cachedBitmap(resource));
                        final float srcW = resource.getWidth();
                        final float srcH = resource.getHeight();
                        final int deviceW = DeviceUtil.sScreenWidth;
                        final int deviceH = DeviceUtil.sScreenHeight;
                        float limit = 4.0f;
                        float scaleMaxRatio = 3.0f;
                        float widthScale = deviceW / srcW;
                        if (widthScale > limit && deviceH / srcH > limit) {
                            imageView.setMinScale(limit);
                            imageView.setMaxScale(limit * scaleMaxRatio);
                            imageView.setDoubleTapZoomScale(widthScale * scaleMaxRatio);

                        } else {
                            imageView.setMinScale(widthScale);
                            imageView.setMaxScale(widthScale * scaleMaxRatio);
                            imageView.setDoubleTapZoomScale(widthScale * scaleMaxRatio);
                        }

                        callback.onLoaded(resource);
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        callback.onStart();
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        callback.onFail();
                    }
                });
    }

    @android.databinding.BindingAdapter(value = {"imgurl", "placeHolder", "imageViewId"})
    public static void loadCollapsingImage(CollapsingToolbarLayout toolbarLayout, String url, Drawable placeHolder, int imageViewId) {
        View view = toolbarLayout.findViewById(imageViewId);
        if (view == null || !(view instanceof ImageView)) {
            return;
        }
        ImageView imageView = (ImageView) view;
        imageView.setImageDrawable(placeHolder);
        if (StringUtil.isEmpty(url)) {
            return;

        }
        with(imageView.getContext())
                .load(url)
                .asBitmap()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Palette.from(resource).generate((palette) -> {
                            Palette.Swatch vibrantSwatch = palette.getLightVibrantSwatch();
                            if (vibrantSwatch != null) {
                                toolbarLayout.setExpandedTitleColor(vibrantSwatch.getBodyTextColor());
                            }
                        });
                    }
                });
    }

    @android.databinding.BindingAdapter(value = {"android:src"})
    public static void setImageResource(ImageView imageView, int res) {
        imageView.setImageResource(res);
    }

    @android.databinding.BindingAdapter(value = {"android:drawableRight"})
    public static void setDrawableRight(TextView textView, int res) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, res, 0);
    }

    @android.databinding.BindingAdapter(value = {"textId"})
    public static void setTextRes(TextView textView, int res) {
        textView.setText(res);
    }

    @android.databinding.BindingAdapter(value = {"onTextChanged"})
    public static void bindTextChangedListener(final EditText editText, final OnTextChanged onTextChanged) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (onTextChanged instanceof OnTextChangedIncludeBefore) {
                    ((OnTextChangedIncludeBefore) onTextChanged).beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (onTextChanged != null)
                    onTextChanged.onChanged(editText, StringUtil.isEmpty(s) ? "" : s.toString());
            }
        });
    }

    @android.databinding.BindingAdapter(value = {"expand", "withAnim"})
    public static void setAppBarLayoutExpended(AppBarLayout appBarLayout, boolean expand, boolean withAnim) {
        appBarLayout.setExpanded(expand, withAnim);
    }

    @android.databinding.BindingAdapter(value = {"layoutManager", "onLoadMore", "onItemSwitch", "onScroll"}, requireAll = false)
    public static void bindLoadMoreRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore, OnItemSwitch onItemSwitch, OnScrollListener scrollListener) {
        OnLoadMoreListener onLoadMore = new OnLoadMoreListener((LinearLayoutManager) layoutManager, iLoadMore);
        onLoadMore.setOnScrollListener(scrollListener);
        onLoadMore.setOnItemSwitch(onItemSwitch);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(onLoadMore);
    }

    @android.databinding.BindingAdapter(value = {"dividerHeight", "dividerLeftOffet", "dividerRightOffet", "dividerColor", "hideFirstItemOffset", "showLastDivider"}, requireAll = false)
    public static void bindRecyclerViewDecoration(RecyclerView recyclerView, float dividerHeight, float dividerLeftOffet, float dividerRightOffet, int dividerColor, boolean hideFirstItemOffset, boolean showLastDivider) {
        RecyclerView.ItemDecoration itemDecoration = new ExItemDecoration((int) dividerHeight, hideFirstItemOffset, showLastDivider, (int) dividerLeftOffet, (int) dividerRightOffet, dividerColor);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @android.databinding.BindingAdapter(value = {"showPassword"})
    public static void bindEditTextInputType(EditText editText, boolean showPassword) {
        editText.setInputType(showPassword ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        editText.setSelection(editText.getText() == null ? 0 : editText.getText().length());
    }

    @android.databinding.BindingAdapter(value = "orientation")
    public static void bindRecyclerView(RecyclerView recyclerView, @ListType String orientation) {
        int ori;
        switch (orientation) {
            default:
            case ListType.HORIZONTAL:
                ori = RecyclerView.HORIZONTAL;
                break;
            case ListType.VERTICAL:
                ori = RecyclerView.VERTICAL;
                break;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), ori, false));
    }

    @android.databinding.BindingAdapter(value = "requestFocus")
    public static void bindRequestFocusEvent(EditText editText, boolean requestFocus) {
        if (requestFocus) {
            editText.requestFocusFromTouch();
        } else {
            editText.clearFocus();
        }
    }

    @android.databinding.BindingAdapter(value = "refresh")
    public static void bindSwipRefreshingState(SwipeRefreshLayout swipeRefreshLayout, boolean refresh) {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(refresh));
    }

    @android.databinding.BindingAdapter(value = {"drawerVisible"})
    public static void toggleDrawer(DrawerLayout drawerLayout, boolean drawerVisible) {
        if (drawerVisible) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                return;
            drawerLayout.openDrawer(GravityCompat.START, true);
        } else {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                return;
            drawerLayout.closeDrawer(GravityCompat.START, true);
        }
    }

    @android.databinding.BindingAdapter(value = "android:text")
    public static void bindText(EditText editText, String text) {
        editText.setText(text);
        if (TextUtils.isEmpty(text)) {
            editText.setSelection(0);
        } else {
            editText.setSelection(text.length());
        }
    }

    @android.databinding.BindingAdapter(value = {"icon", "msg"})
    public static void bindEmptyViewDrawable(TextView textView, int iconRes, int msgRes) {
        if (iconRes != 0)
            textView.setCompoundDrawablesWithIntrinsicBounds(0, iconRes, 0, 0);
        if (msgRes != 0)
            textView.setText(msgRes);
    }

    @android.databinding.BindingAdapter(value = {"positionY"})
    public static void bindScrollViewPosition(NestedScrollView nestedScrollView, int positionY) {
        nestedScrollView.post(() ->
                nestedScrollView.smoothScrollTo(0, positionY));
    }

    @android.databinding.BindingAdapter(value = {"globalLayoutListener"})
    public static void bindOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

}

