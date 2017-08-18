<html>
<#include "fragments/headTag.ftl">
<body>
<#include "fragments/header.ftl">

<br>
<br>
<div class="container-fluid">
    <div class="page-header">
        <h1><@spring.message "coffeelist.title"/></h1>
    </div>
    <br>
    <h3>
    <@spring.message "coffeelist.welcome"/>
    </h3>

<#if errorValue??>
    <div class="row">
        <div class="alert alert-warning col-sm-7 col-md-5">
            <p><@spring.message errorValue/></p>
        </div>
        <br>
    </div>
</#if>
    <div class="row">
        <div class="col-sm-7 col-md-5">

            <form action="orderlist" method="POST" id="catalogform">

                <table class="table table-hover">
                    <tr>
                        <th></th>
                        <th><@spring.message "coffeelist.name"/></th>
                        <th><@spring.message code="coffeelist.price"/></th>
                        <th><@spring.message code="coffeelist.quantity"/></th>
                        <th></th>
                    </tr>
                <#list typeToSelectedWraper.items as type>
                    <tr>
                        <td>
                            <@macros.textInputHidden "typeToSelectedWraper.items[${type_index}].id"/>
                            <input type="checkbox" name="items[${type_index}].selected" value="true" onclick="toggleInput(this, ${type_index})">
                        </td>
                        <td><@macros.textInputHidden "typeToSelectedWraper.items[${type_index}].typeName"/>${type.typeName}</td>
                        <td><@macros.textInputHidden "typeToSelectedWraper.items[${type_index}].price"/>${type.price}</td>
                        <td><@macros.textInput "typeToSelectedWraper.items[${type_index}].quantity" "size='2' maxlength='3' class='form-control' disabled=''" "number"/></td>
                        <td><@macros.showErrors "<br>" "text-danger"/></td>
                    </tr>
                </#list>

                    <tr>
                        <td colspan="5"><input type="submit"
                                               value="<@spring.message "orderTO.confirmBTN"/>"</td>
                    </tr>

                    <tr>
                        <td colspan=5>*
                            - <@spring.message "coffeelist.each" /> ${nCupFree} <@spring.message "coffeelist.freeCup"/></td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
</div>

<#if orderConfirmed??>
<script type="application/javascript">
    $(document).ready(function () {
        $("#confirmModal").modal({
            backdrop: 'static',
            keyboard: false
        });
    });
</script>

<!-- Modal -->
<div class="modal fade" id="confirmModal">
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