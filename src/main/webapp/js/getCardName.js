//function modal(url, cardId) {
////   $('#myModal').on('hidden.bs.modal', function (e) {
//   console.log('qweqwe');
//   cardId = {"cardId":cardId}
//   var request = url + "&cardId=" + cardId;
//     $.ajax({
//         type: "POST",
//         url: request,
//         // The key needs to match your method's input parameter (case-sensitive).
//        data: cardId,
//         contentType: "application/json; charset=utf-8",
//         dataType: "json",
//         success: function(data){alert(data);},
//         failure: function(errMsg) {
//             alert(errMsg);
//
//         }
//     });
//   })
function myFunction() {
console.log("1")
var value = document.getElementById("id").value;
var value2 = document.getElementById("accountId")
value2.value = value
}


