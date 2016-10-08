<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <import type="${packageName}.vm.${pageName}VM" />

        <variable
            name="viewModel"
            type="${pageName}VM" />
    </data>

    <RelativeLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
    <#if hasAppBar && appBarLayoutName??>
        xmlns:app="http://schemas.android.com/apk/res-auto"
    </#if>
        android:layout_width="match_parent"
        android:layout_height="match_parent"
    <#if hasAppBar && appBarLayoutName??>
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        tools:showIn="@layout/${appBarLayoutName}"
    </#if>
        tools:context="${relativePackage}.${activityClass}">

    <#if isNewProject!false>
        <TextView
            android:text="Hello World!"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />
    </#if>
    </RelativeLayout>
</layout>