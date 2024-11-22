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

//Page load
$(document).ready(function(){                
	const imgbg = document.getElementById("fileInput").style.backgroundImage = "url(/img/profile/default.png)";                
});

document.addEventListener('DOMContentLoaded', function () {
    var profileData = new Object();

    document.getElementById('submit').addEventListener("click", function () {
        let inputContainer = getElementsByXPath(".//div[@class='right']/label");
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
                default:                  
                        if (label.textContent == "DOB") {
                            isvalid = true

                            value = label.nextElementSibling.nextElementSibling.value;
                        } else {
                            isvalid = true
                            value = label.nextElementSibling.value;
                        }
                    
            }
            if (isvalid && label.textContent != "Male" && label.textContent != "Female") {
                profileData[label.textContent.replace(" ", "_").toLowerCase()] = value;
            }

        });
        console.log(profileData)

        var data1 = JSON.stringify(profileData);
        console.log(data1)

        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: "/profilePage",
            async: true,
            data: data1,

            success: function (result) {
                //debugger;
            },
            error: function (result) {
            }
        });
    });
});

var imgobj = new Object();
document.addEventListener('DOMContentLoaded', function () {
    let formData = new FormData();
    document.getElementById('fileInput').addEventListener("change", function () {
        debugger;
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
                //var actualPath = path.replace(" ","('").replace(")","')") ;
                var actualPath = path.replace(/['"]+/g, '');
                var inputele = document.getElementById('fileInput');
                debugger;
                inputele.style.backgroundImage = actualPath;
                console.log(res)

            },
            error: function (res) {
            }
        });

    });

});