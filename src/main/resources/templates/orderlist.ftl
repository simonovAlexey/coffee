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

    <form action="order" method="POST" id="delivery">

        <div class="row">
            <div class="col-sm-8 col-md-5">
                <table class="table table-condensed">
                    <tr class="active">
                        <p><@spring.message "orderTO.delivery"/></p>
                    </tr>
                    <tr>
                        <td class="active"><label for="name"><b><@spring.message "orderTO.name"/></b></label></td>
                        <td><@spring.formInput  "orderTO.name" "maxlength='200' size='35' required"/></td>
                        <td><@spring.showErrors "<br>" "bg-danger"/></td>
                    </tr>

                    <tr>
                        <td class="active"><label for="adres"><b><@spring.message "orderTO.adres"/></b></label></td>
                        <td><@spring.formInput  "orderTO.deliveryAdress" "maxlength='200' size='35' required"/></td>
                        <td><@spring.showErrors "<br>" "bg-danger"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit"
                                               value="<@spring.message "orderTO.confirmBTN"/>"</td>
                    </tr>
                </table>
            </div>


            <div class="col-sm-6 col-md-6">
                <p><@spring.message "orderTO.confirm"/></p>
                <table class="table table-striped table-condensed" style="float: left;">
                    <tr>
                        <th></th>
                        <th><@spring.message "coffeelist.name"/></th>
                        <th><@spring.message code="coffeelist.price"/></th>
                        <th><@spring.message code="coffeelist.quantity"/></th>
                        <th><@spring.message code="coffeelist.total"/></th>
                    </tr>
                <#list orderTO.items as type>
                    <tr>
                        <td><input type="hidden" name="items[${type_index}].id" value="${type.id}">
                        <input type="hidden" name="items[${type_index}].selected" value="${type.selected?then('true', 'false')}">

                        </td>
                        <td><input type="hidden" name="items[${type_index}].typeName" value="${type.typeName}">${type.typeName}</td>
                        <td><input type="hidden" name="items[${type_index}].price" value="${type.price}">${type.price}</td>
                        <td><input type="hidden" name="items[${type_index}].quantity" value="${type.quantity}">${type.quantity}</td>
                        <td <#if type.selected>class="text-danger"</#if>><input type="hidden" name="items[${type_index}].total" value="${type.total}">${type.total} TGR</td>
                    </tr>
                </#list>
                    <tr>
                        <td style="text-align: right;" colspan="4"><@spring.message "orderTO.subtotal"/></td>
                        <td><input type="hidden" name="subtotal" value="${orderTO.subtotal}">${orderTO.subtotal} TGR</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;" colspan="4"><@spring.message "orderTO.delivery"/></td>
                        <td><input type="hidden" name="delivery" value="${orderTO.delivery}">${orderTO.delivery} TGR</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;" colspan="4"><b><@spring.message "coffeelist.total"/></b></td>
                        <td><input type="hidden" name="total" value="${orderTO.total}">${orderTO.total} TGR</td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
<#if spring.status.error>
    <div class="alert alert-danger">
        <@spring.message "exception.validationList"/>
        <ul>
            <#list spring.status.errorMessages as error>
                <li>${error}</li>
            </#list>
        </ul>
    </div>
</#if>
</div>
</body>