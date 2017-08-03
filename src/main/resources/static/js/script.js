function toggleInput(checkbox, itemId) {
    var disabled = !checkbox.checked;
    var inputField = $("#items" + itemId + "\\.quantity");
    inputField.prop('disabled', disabled);
    if (disabled) {
        inputField.val("");
    }
}