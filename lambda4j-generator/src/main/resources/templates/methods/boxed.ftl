<#-- @formatter:off -->
<#import "../utils/filters.ftl" as filters>
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- TODO all primitive lambdas should have a boxed method (f.e. ByteFunction, CharSupplier, ...) -->
<#-- parse only if lambda input is primitive or a supplier -->
<#if helpers.isPrimitiveLambdaInput(lambda) || LambdaUtils.isOfTypeSupplier(lambda)>
    <#-- build a generic parameter type string including primitives also -->
    <#assign genericParameterTypeStringWithPrimitives = .namespace.buildGenericParameterTypeStringWithPrimitives()>
    <#-- get output lambda using a search for lambda type, arity, primitive and throwable flag -->
    <#--<#if LambdaUtils.isOfTypeSupplier(lambda)>-->
        <#--<#assign outputLambda = LambdaUtils.searchByReturnType(lambda.type, lambda.arity, Object, lambda.throwable)>-->
    <#if LambdaUtils.isOfTypeConsumer(lambda)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object, lambda.returnType, lambda.throwable)>
    <#elseif LambdaUtils.isOfTypePredicate(lambda)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object, lambda.returnType, lambda.throwable)>
    <#else>
        <#--<#assign outputLambda = LambdaUtils.searchByReturnType(lambda.type, lambda.arity, lambda.returnType, lambda.throwable)>-->
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, Object, Object, Object, Object, lambda.throwable)>
    </#if>
    <#-- print boxed method -->
    <@.namespace.boxedMethod genericParameterTypeStringWithPrimitives outputLambda/>
</#if>

<#-- a helper macro to centralize boxed method and to avoid unnecessary indenting -->
<#macro boxedMethod genericParameterTypeStringWithPrimitives outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} which represents this {@link ${lambda.name}}. Thereby the primitive
 * input argument for this ${lambda.type.simpleName} is autoboxed.
<#if lambda.jdk??>
 * This method is just convenience to provide the ability to use this {@code ${lambda.name}} with JRE specific methods,
 * only accepting {@code ${outputLambda.name}}.
</#if>
 *
 * @return A composed {@code ${outputLambda.name}} which represents this {@code ${lambda.name}}.
 */
${annotation.nonnull}
default ${outputLambda.name}${genericParameterTypeStringWithPrimitives} boxed() {
    return this::${lambda.type.method};
}
</#macro>

<#-- a helper function to build a generic parameter type string with primitives for given target lambda -->
<#function buildGenericParameterTypeStringWithPrimitives target = lambda>
    <#local parameters = [target.firstInputType!"", target.secondInputType!"", target.thirdInputType!""]>
    <#if helpers.isPrimitive(target.returnType)>
        <#local parameters = parameters + [target.returnType!""]>
    </#if>
    <#local parameters = filters.filterEmpties(parameters)>
    <#local genericString = "">
    <#if (types?has_content)>
        <#local genericString = genericString + "<">
        <#list parameters as type>
            <#local genericString = genericString + types.buildParameterType(type)?cap_first>
            <#if type?has_next>
                <#local genericString = genericString + ", ">
            </#if>
        </#list>
        <#local genericString = genericString + ">">
    </#if>
    <#return genericString>
</#function>
<#-- @formatter:on -->