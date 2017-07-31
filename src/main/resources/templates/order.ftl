<html xmlns="">
<#include "fragments/headTag.ftl">
<body>
<div class="container">
    <h2>Small Modal</h2>
    <!-- Trigger the modal with a button -->
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#confirmModal">Open Small Modal</button>

    <!-- Modal -->

</div>

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
</body>