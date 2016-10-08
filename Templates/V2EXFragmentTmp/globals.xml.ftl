<?xml version="1.0"?>
<globals>
    <#assign useSupport=(minApiLevel lt 23)>
    <#include "../../activities/common/common_globals.xml.ftl" />
    <global id="useSupport" type="boolean" value="${useSupport?string}" />
    <global id="SupportPackage" value="${useSupport?string('.support.v4','')}" />
    <global id="resOut" value="${resDir}" />
    <global id="srcOut" value="${srcDir}/${slashedPackageName(packageName)}" />
    <global id="relativePackage" value="<#if relativePackage?has_content>${relativePackage}<#else>${packageName}</#if>" />

    <global id="moduleNameUncapFirst" type="string" value="${pageName?uncap_first}VMModule"/>
    <global id="realAppPackage" type="string" value="${applicationPackage?replace('.debug','')}"/>
    <global id="libPackage" type="string" value="com.istuary.ironhide.lib"/>

</globals>
