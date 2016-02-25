<#import "filters.ftl" as filters>
<#import "helpers.ftl" as helpers>

<#function buildParameter param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
        <#local genericString = .namespace.buildParameterType(param, target) + " " + .namespace.buildParameterName(param, target)>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterType param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
        <#if param.primitive>
        <#--<#local genericString = genericString + param.typeSimpleName>-->
            <#local genericString = genericString + param.typeName>
        <#else>
            <#local genericString = genericString + param.typeName>
        </#if>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterName param target = lambda>
    <#local genericString = "">
    <#if param?has_content>
    <#-- TODO remove if tested -->
    <#--<#if (param.count <= 0)>-->
    <#--<#local genericString = genericString + "ret">-->
    <#--<#else>-->
    <#--<#if LambdaUtils.isOfTypeOperator(target)>-->
    <#--<#local genericString = genericString + "operand" + (target.arity > 1)?then(param.count, "")>-->
    <#--<#else>-->
        <#local genericString = genericString + param.name?lower_case>
        <#if param.primitive && (param.count > 0)>
            <#local count = 0>
            <#if (target.firstInputType?has_content)>
                <#local count = target.firstInputType.primitive?then(count, count + 1)>
            </#if>
            <#if (target.secondInputType?has_content)>
                <#local count = target.secondInputType.primitive?then(count, count + 1)>
            </#if>
            <#local genericString = genericString + ((target.arity - count) > 1)?then((param.count), "")>
        </#if>
    <#-- TODO remove if tested -->
    <#--</#if>-->
    <#--</#if>-->
    </#if>
    <#return genericString>
</#function>


<#function buildParameterString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameter(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterTypeString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>

<#function buildParameterNameString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#local types = filters.filterEmpties(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterName(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
    </#if>
    <#return genericString>
</#function>


<#function buildGenericParameterTypeString target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!"", target.returnType!""]>
    </#if>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
        <#--<#local genericString = genericString + "<" + types?join(", ", "", ">")>-->
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function buildGenericParameterTypeStringWithErasure target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3, other4)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!"", target.returnType!""]>
    </#if>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#if type == target.returnType!"">
                <#if LambdaUtils.isOfTypeOperator(target)>
                    <#local genericString = genericString + .namespace.buildParameterType(type, target)>
                <#else>
                    <#local genericString = genericString + "? extends " + .namespace.buildParameterType(type, target)>
                </#if>
            <#else>
                <#local genericString = genericString + "? super " + .namespace.buildParameterType(type, target)>
            </#if>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#-- a helper function to build a generic input lambda string for boxed operation -->
<#function buildGenericInputParameterTypeString target = lambda other1 = "" other2 = "" other3 = "">
    <#local target = .namespace.otherParametersToTarget(target, other1, other2, other3)>
    <#local types = []>
    <#if LambdaUtils.isOfTypeOperator(target)>
        <#local types = [target.returnType!""]>
    <#else>
        <#local types = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    </#if>
    <#local types = filters.filterEmpties(types)>
    <#local types = filters.filterPrimitives(types)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list types as type>
            <#local genericString = genericString + .namespace.buildParameterType(type, target)>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>

<#function otherParametersToTarget target = lambda other1 = "" other2 = "" other3 = "" other4 = "">
    <#assign copy = LambdaUtils.copy(target)> <#-- copy lambda as we will change its input arguments -->
    <#if other1?has_content && copy.getFirstInputType()?has_content>
    ${copy.getFirstInputType().setTypeName(other1)}
    ${copy.getFirstInputType().setName(other1?lower_case)}
    </#if>
    <#if other2?has_content && copy.getSecondInputType()?has_content>
    ${copy.getSecondInputType().setTypeName(other2)}
    ${copy.getSecondInputType().setName(other2?lower_case)}
    </#if>
    <#if other3?has_content && copy.getThirdInputType()?has_content>
    ${copy.getThirdInputType().setTypeName(other3)}
    ${copy.getThirdInputType().setName(other3?lower_case)}
    </#if>
    <#if other4?has_content && copy.getReturnType()?has_content>
    ${copy.getReturnType().setTypeName(other4)}
    ${copy.getReturnType().setName(other4)}
    </#if>
    <#return copy>
</#function>