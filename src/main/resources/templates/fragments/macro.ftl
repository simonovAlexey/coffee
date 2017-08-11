<#macro textInput path attributes="" type="text">
    <@spring.bind path/>
    <#assign error><#if spring.status.errorMessages?has_content>has-error</#if></#assign>
<div class="${error} col-sm-8">
    <#assign id="${spring.status.expression?replace('[','')?replace(']','')}">
    <input type="${type}" id="${id}" name="${spring.status.expression}" value="${spring.stringStatusValue}" ${attributes}>
    <#nested>
</div>
</#macro>

<#macro showErrors separator class="">
    <#if error?has_content>
        <#if class == "">
        <b>${error}</b>
        <#else>
        <p class="${class}">${spring.status.errorMessages?first}</p>
        </#if>
    </#if>
</#macro>

<#macro textInputHidden path type="hidden">
    <@spring.bind path/>
    <#assign id="${spring.status.expression?replace('[','')?replace(']','')}">
<input type="${type}" id="${id}" name="${spring.status.expression}" value="${spring.stringStatusValue}">
    <#nested>
</#macro>