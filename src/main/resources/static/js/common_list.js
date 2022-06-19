function clearFilter() {
    window.location = moduleURL;
}

function showDeleteConfirmModal(link, entityName) {
    // alert($(this).attr("href"));
    let entityId = link.attr("entityId");
    $("#deleteYesButton").attr("href", link.attr("href"));
    $("#confirmText").text("Are you sure want to delete this " + entityName + " Id " + entityId + " ?");
    $("#deleteConfirmModal").modal();
}