function Validatedate() {
    var startDate = document.getElementById("fromdate").value;
    var endDate = document.getElementById("todate").value;
 
    if ((Date.parse(endDate) <= Date.parse(startDate))) {
        alert("To date should be greater than From date");
            fromdate.focus();
        return false;
    }
return true;
}