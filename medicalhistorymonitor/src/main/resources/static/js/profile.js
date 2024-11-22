//Function to find element by Xpath
function getElementByXpath(path) {
    return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
}

//Function to find elements by Xpath
function getElementsByXPath(xpath, parent) {
    let results = [];
    let query = document.evaluate(xpath, parent || document,
        null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    for (let i = 0, length = query.snapshotLength; i < length; ++i) {
        results.push(query.snapshotItem(i));
    }
    return results;
}

function BindNameAndMobile() {
    $.ajax({
        url: "/profileData",
        type: 'POST',
        success: function (res) {
            var dropdown = [];
            var str = "";
            dropdown = res.name == "Hospital" ? ["Role","Status"] : "";
            if (dropdown) {
                dropdown.forEach(function (item) {
                    str += '<label class="labels labelgap">' + item + '</label><select class="form-control" name="'+item+'" id="'+item+'">'
                    if (item.toLowerCase() in res) {
                        res[item.toLowerCase()].forEach(function (option) {
                            str += '<option value="">' + option + '</option>';
                        });
                    }
                    str += '</select>'
                });
                var wrapper = document.getElementById('right');

                wrapper.insertAdjacentHTML('beforeend', str);
            }
            if (res.filePath != null) {
                var path = "url(" + res.filePath.replace(/\\/g, "/") + ")";
                document.getElementById("fileInput").style.backgroundImage = path;
            }
            else{
                var path = "url(img/profile/default.png)";
                document.getElementById("fileInput").style.backgroundImage = path;
            }
            let title = getElementByXpath("//h8[@class='titlepro']");
            title.textContent = res.name;
            let inputContainer = getElementsByXPath(".//div[@class='profile']//label");
            inputContainer.forEach(function (label) {
                switch (label.textContent) {
                    case "Gender":
                    case "Blood Group":
                    case "Marital Status":

                        var options = label.nextElementSibling.options;
                        for (i = 0; i < options.length; i++) {
                            if (options[i].text == res[label.textContent.toLowerCase().replace(" ", "_")]) {
                                label.nextElementSibling.options[i].selected = true;
                            }
                        }
                        break;
                    default:
                        if (label.textContent == "Name" || label.textContent == "Mobile" || label.textContent == "Age") {
                            label.nextElementSibling.readOnly = true;
                        }
                        label.nextElementSibling.value = res[label.textContent.toLowerCase()];
                        break;

                }


            });
        },
        error: function (res) {
        }
    });

}

document.addEventListener('DOMContentLoaded', function () {
    BindNameAndMobile();

    let formData = new FormData();
    document.getElementById('fileInput').addEventListener("change", function () {
        var totalFiles = document.getElementById('fileInput').files.length;
        for (var i = 0; i < totalFiles; i++) {
            var file = document.getElementById('fileInput').files[i];
            formData.append("httpPostedFileBase", file);
        }
        $.ajax({
            url: "/upload",
            type: 'POST',
            data: formData,
            enctype: 'multipart/form-data',
            contentType: false,
            processData: false,
            success: function (res) {
                var path = "url(" + res.replace("file:///E:", "") + ")";
                var actualPath = path.replace(/['"]+/g, '');
                var inputele = document.getElementById('fileInput');
                inputele.style.backgroundImage = actualPath;

            },
            error: function (res) {
            }
        });
    });


    var inputContainer = getElementsByXPath(".//div[@class='profile']//label");

    var profileData = new Object();
    document.getElementById('submit').addEventListener("click", function () {

        var isValid = false;
        var value;
        inputContainer.forEach(function (label) {
            switch (label.textContent) {
                case "Gender":
                case "Blood Group":
                case "Marital Status":
                    if (!(label.nextElementSibling.options[label.nextElementSibling.selectedIndex].text.includes("Select"))) {

                        isvalid = true
                        value = label.nextElementSibling.options[label.nextElementSibling.selectedIndex].text;

                    }
                    break;
                case "Email":
                    var reg = new RegExp('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$');
                    if (label.nextElementSibling.value === "" || !reg.test(label.nextElementSibling.value)) {
                        label.nextElementSibling.value = "";
                        label.nextElementSibling.nextElementSibling.style.display = "";
                        label.nextElementSibling.nextElementSibling.textContent = "Please enter a valid Email Address.";
                        label.nextElementSibling.nextElementSibling.style.color = 'red'
                        isvalid = false;
                    }
                    else {
                        isvalid = true;
                        value = label.nextElementSibling.value;
                    }
                    break;
                case "Height":
                    var reg = new RegExp('^[3-8]+\. ?[1-9]$');
                    if (label.nextElementSibling.value === "" || !reg.test(label.nextElementSibling.value)) {
                        label.nextElementSibling.value = "";
                        label.nextElementSibling.nextElementSibling.style.display = "";
                        label.nextElementSibling.nextElementSibling.textContent = "Please enter a valid Height. Your height range must be have between 3 to 8 feet and inches between 1-9 inches";
                        label.nextElementSibling.nextElementSibling.style.color = 'red'
                        isvalid = false;
                    }
                    else {
                        value = label.nextElementSibling.value;
                        isvalid = true;
                    }
                    break;
                case "Weight":
                    var reg = new RegExp('^[1-9]?[1-9]?[1-9]$');
                    if (label.nextElementSibling.value === "" || !reg.test(label.nextElementSibling.value)) {
                        label.nextElementSibling.value = "";
                        label.nextElementSibling.nextElementSibling.style.display = "";
                        label.nextElementSibling.nextElementSibling.textContent = "Inncorrect Weight. Please enter a valid Weight.";
                        label.nextElementSibling.nextElementSibling.style.color = 'red'
                        isvalid = false;
                    }
                    else {
                        value = label.nextElementSibling.value;
                        isvalid = true;
                    }
                    break;
                case "DOB":
                    var reg = new RegExp('^([0-9]{2})+\/?([0-9]{2})+\/?([0-9]{4})$');
                    var date = label.nextElementSibling.value
                    var datearray = date.split("-");
                    var newdate = datearray[2] + '/' + datearray[1] + '/' + datearray[0];
                    if (label.nextElementSibling.value === "" || !reg.test(newdate)) {
                        label.nextElementSibling.value = "";
                        label.nextElementSibling.nextElementSibling.style.display = "";
                        label.nextElementSibling.nextElementSibling.textContent = "Please select valid date";
                        label.nextElementSibling.nextElementSibling.style.color = 'red'
                        isvalid = false;
                    }
                    else {
                        value = label.nextElementSibling.value;
                        isvalid = true;
                    }
                    break;
                case "Age":
                    var date = profileData["dob"]
                    var dob = new Date(date);
                    var today = new Date();
                    var age = today.getFullYear() - dob.getFullYear();
                    label.nextElementSibling.value = age;
                    var reg = new RegExp('^[1-9]?[0-9]{1}$|^100$');
                    if (label.nextElementSibling.value === "" || !reg.test(label.nextElementSibling.value)) {
                        label.nextElementSibling.value = "";
                        label.nextElementSibling.nextElementSibling.style.display = "";
                        label.nextElementSibling.nextElementSibling.textContent = "Please select valid date";
                        label.nextElementSibling.nextElementSibling.style.color = 'red'
                        isvalid = false;
                    }
                    else {
                        label.nextElementSibling.readOnly = false;
                        value = label.nextElementSibling.value;
                        isvalid = true;
                    }
                default:
                    isvalid = true
                    value = label.nextElementSibling.value;
            }
            if (isvalid) {
                profileData[label.textContent.replace(" ", "_").toLowerCase()] = value;
            }

        });
        console.log(profileData)

        var data1 = JSON.stringify(profileData);
        console.log(data1)
        $.ajax({
            url: "/profilePage",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            async: false,
            data: data1,
            success: function (result) {
            },
            error: function (result) {
                console.log("error")
                console.log(result)
            }
        });
    });
});






