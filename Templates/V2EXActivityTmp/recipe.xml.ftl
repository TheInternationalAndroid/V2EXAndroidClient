<?xml version="1.0"?>
<recipe>
    <#include "../common/recipe_manifest.xml.ftl" />

<#if useFragment>
    <#include "recipe_fragment.xml.ftl" />
<#else>
    <#if appCompat && !(hasDependency('com.android.support:appcompat-v7'))>
      <dependency mavenUrl="com.android.support:appcompat-v7:${buildApi}.+"/>
    </#if>

        <instantiate from="root/res/layout/simple.xml.ftl"
                     to="${escapeXmlAttribute(resOut)}/layout/${simpleLayoutName}.xml" />

    <#if (isNewProject!false) && !(excludeMenu!false)>
        <#include "../common/recipe_simple_menu.xml.ftl" />
    </#if>

        <#include "../common/recipe_simple_dimens.xml" />
</#if>

<#if hasAppBar>
    <#if !(hasDependency('com.android.support:appcompat-v7'))>
        <dependency mavenUrl="com.android.support:appcompat-v7:${buildApi}.+"/>
    </#if>

    <#if !(hasDependency('com.android.support:design'))>
        <dependency mavenUrl="com.android.support:design:${buildApi}.+"/>
    </#if>

    <instantiate from="root/res/layout/app_bar.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${appBarLayoutName}.xml" />

    <merge from="../common/root/res/values/app_bar_dimens.xml"
             to="${escapeXmlAttribute(resOut)}/values/dimens.xml" />

    <#include "../common/recipe_no_actionbar.xml.ftl" />
</#if>

    <instantiate from="root/src/app_package/SimpleDIActivity.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityClass}.java" />
    <instantiate from="root/src/app_package/SimpleContract.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/contract/${pageName}Contract.java" />
    <instantiate from="root/src/app_package/SimplePresenter.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/presenter/${pageName}P.java" />
    <instantiate from="root/src/app_package/SimpleViewModel.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/vm/${pageName}VM.java" />
    <instantiate from="root/src/app_package/SimpleVMModule.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/vm/module/${pageName}VMModule.java" />

    <open file="${escapeXmlAttribute(srcOut)}/${activityClass}.java" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${simpleLayoutName}.xml" />
</recipe>
