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
                        <p><@spring.message "order.delivery"/></p>
                    </tr>
                    <tr>
                        <td class="active"><label for="name"><b><@spring.message "order.name"/></b></label></td>
                        <td><input name="name" id="name" maxlength="100" size="40" required></td>
                    </tr>

                    <tr>
                        <td class="active"><label for="adres"><b><@spring.message "order.adres"/></b></label></td>
                        <td><input name="deliveryAdress" id="adres" maxlength="200" size="40" required></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit"
                                               value="<@spring.message "order.confirmBTN"/>"</td>
                    </tr>
                </table>
            </div>


            <div class="col-sm-6 col-md-6">
                <p><@spring.message "order.confirm"/></p>
                <table class="table table-striped table-condensed" style="float: left;">
                    <tr>
                        <th></th>
                        <th><@spring.message "coffeelist.name"/></th>
                        <th><@spring.message code="coffeelist.price"/></th>
                        <th><@spring.message code="coffeelist.quantity"/></th>
                        <th><@spring.message code="coffeelist.total"/></th>
                    </tr>
                <#list order.items as type>
                    <tr>
                        <td><input type="hidden" name="id" value="${type.id}"></td>
                        <td><input type="hidden" name="typeName" value="${type.typeName}">${type.typeName}</td>
                        <td><input type="hidden" name="price" value="${type.price}">${type.price}</td>
                        <td><input type="hidden" name="quantity" value="${type.quantity}">${type.quantity}</td>
                        <td><input type="hidden" name="total" value="${type.total}">${type.total} TGR</td>
                        <#--<td>${type.typeName}</td>
                        <td>${type.price}</td>
                        <td>${type.quantity}</td>
                        <td>${type.total} TGR</td-->>
                    </tr>
                </#list>

                    <tr>
                        <td style="text-align: right;" colspan="3"><b>Сумма</b></td>
                        <td>${order.subtotal} TGR</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;" colspan="3"><b>Доставка</b></td>
                        <td>${order.delivery} TGR</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;" colspan="3"><b>Всего</b></td>
                        <td>${order.total} TGR</td>
                    </tr>


                </table>
            </div>
        </div>
    </form>

</div>
</body>