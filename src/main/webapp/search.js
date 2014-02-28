function dataTableSelectOneRadio(radio) {
    var radioId = radio.name.substring(radio.name.lastIndexOf(':'));
    for (var i = 0; i < radio.form.elements.length; i++) {
        var element = radio.form.elements[i];
        if (element.name.substring(element.name.lastIndexOf(':')) == radioId) {
            element.checked = false;
        }
    }
    radio.checked = true;
}
