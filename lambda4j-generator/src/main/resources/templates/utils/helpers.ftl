<#-- checks if given type represents a primitive type -->
<#function isPrimitive type = "">
    <#return (type?hasContent) && type.primitive>
</#function>

<#-- checks if the given lambda has primitive types -->
<#function isPrimitiveLambda target = lambda>
    <#return (target?hasContent) && (.namespace.isPrimitive(target.firstInputType) || .namespace.isPrimitive(target.secondInputType) || .namespace.isPrimitive(target.thirdInputType) || .namespace.isPrimitive(target.returnType))>
</#function>

<#-- checks if the given lambda has primitive types (excluding return) -->
<#function isPrimitiveLambdaInput target = lambda>
    <#return (target?hasContent) && (.namespace.isPrimitive(target.firstInputType) || .namespace.isPrimitive(target.secondInputType) || .namespace.isPrimitive(target.thirdInputType))>
</#function>

<#-- prints number string for first argument if lambdas arity is greater than 1 -->
<#function first target = lambda>
    <#return (target.arity > 1)?then("first ", "")>
</#function>

<#-- prints number if lambdas arity is greater than 1 -->
<#function number target = lambda>
    <#return (target.arity > 1)?then("1", "")>
</#function>

<#-- prints a plural 's' if arity is greater than 1 -->
<#function s target = lambda>
    <#return (target.arity > 1)?then("s", "")>
</#function>

<#-- prints return keyword only if given lambda not consumer type -->
<#function printReturnIfNotVoid target = lambda>
    <#local ret = "">
    <#if !LambdaUtils.isOfTypeConsumer(target) && !LambdaUtils.isOfTypeRunnable(target)>
        <#local ret = "return">
    </#if>
    <#return ret>
</#function>
