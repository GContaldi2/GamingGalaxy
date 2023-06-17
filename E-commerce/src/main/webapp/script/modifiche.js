function deleteAddress() {
    // Send a request to the server to delete the address
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/deleteAddress');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Handle successful response
        } else {
            // Handle error
        }
    };
    xhr.send(encodeURI('userId=' + auth.getId()));
}