<html>
<#include "fragments/headTag.ftl">
<body>
<#include "fragments/header.ftl">

<br>
<br>
<div class="container-fluid">
    <div class="page-header">
        <h1><@spring.message "coffeelist.title"/></h1>
        <p>Now is ${now}:
    </div>
    <br>
    <h3>
    <@spring.message "coffeelist.welcome"/>
    </h3>

<#if errorValue??>
    <div class="alert alert-danger">
        <p><@spring.message errorValue/></p>
    </div>
    <br>
</#if>
    <div class="row">
        <div class="col-sm-6 col-md-4">

            <form action="orderlist" method="POST" id="catalogform">

                <table class="table table-hover table-bordered table-striped table-condensed">
                    <tr>
                        <th></th>
                        <th><@spring.message "coffeelist.name"/></th>
                        <th><@spring.message code="coffeelist.price"/></th>
                        <th><@spring.message code="coffeelist.quantity"/></th>
                    </tr>
                <#list coffeetypelist as type>
                    <tr>
                        <td>
                            <input type="hidden" name="id" value="${type.id}">
                            <input type="checkbox" name="selected" value="${type.id}">
                        </td>

                        <td><input type="hidden" name="typeName" value="${type.typeName}">${type.typeName}</td>
                        <td><input type="hidden" name="price" value="${type.price}">${type.price} TGR</td>
                        <td><input type="text" name="quantity" maxlength="4" size="4" <#--type="number" min="0" step="1" size="3" -->/>
                        <#--<#if (spring.status.errorMessages)?? ><@spring.showErrors "<br>" "control-group error"/></#if>-->
                        </td>
                    </tr>
                </#list>

                    <tr>
                        <td colspan="4"><input type="submit"
                                               value="<@spring.message "orderTO.confirmBTN"/>"</td>
                    </tr>

                <#--<tr>
                    <td colspan=4>* - <spring:message code="label.each" />
                    ${nCupFree} <spring:message code="label.cupFree" /></td>
                </tr>-->

                </table>
            </form>
        </div>
    </div>
</div>

<#if orderConfirmed??>
<script type="application/javascript">
    $(document).ready(function(){
        $("#confirmModal").modal({
            backdrop: 'static',
            keyboard: false
        });
    });
</script>

<div class="container">
    <!-- Trigger test the modal with a button -->
    <#--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#confirmModal">Open Small Modal</button>-->

</div>

<!-- Modal -->
<div class="modal fade" id="confirmModal" >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><@spring.message "orderTO.confirmTitle"/></h4>
            </div>
            <div class="modal-body">
                <p><@spring.message "orderTO.confirmBody"/></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-block"
                        data-dismiss="modal"><@spring.message "orderTO.backBTN"/></button>
            </div>
        </div>
    </div>
</div>
</#if>

</body>
</html>