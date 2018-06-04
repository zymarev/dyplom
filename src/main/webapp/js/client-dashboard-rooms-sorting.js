$(document).ready(function () {
    var URL = "controller?command=clientDashboard";
    $.urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results == null) {
            return null;
        }
        else {
            return results[1] || 0;
        }
    }
    var sortBy = $.urlParam('sortBy');
    var type = $.urlParam('type');

    $("th[column-name='" + sortBy + "']").attr('class', 'sortBy_' + type + '');
    
    $("th").click(function () {
        var currentSortingType = $("th[column-name='" + sortBy + "']").attr('class');
        var sortingType = 'asc';
        if ($(this).attr('class') == 'sortBy' || currentSortingType == 'sortBy_desc') {
            sortingType = 'asc';
        }
        else {
            sortingType = 'desc';
        }

        if ($(this).attr('column-name')) {
            window.location = URL
                + '&sortBy=' + $(this).attr('column-name')
                + '&type=' + sortingType;
        }
    });

});