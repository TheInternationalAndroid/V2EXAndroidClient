<?xml version="1.0"?>
<globals>
    <#include "../common/common_globals.xml.ftl" />
    <global id="simpleLayoutName" value="<#if appCompatActivity>${contentLayoutName}<#else>${layoutName}</#if>" />
    <global id="appBarLayoutName" value="${layoutName}" />
    <global id="fragmentClass" value="${activityClass}Fragment" />
    <global id="moduleNameUncapFirst" type="string" value="${pageName?uncap_first}VMModule"/>
    <global id="realAppPackage" type="string" value="${applicationPackage?replace('.debug','')}"/>
    <global id="libPackage" type="string" value="com.ray.mvvm.lib"/>

</globals>
