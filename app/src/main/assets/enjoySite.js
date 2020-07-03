(function () {
    window.enjoy = {
        onMSLResponse: (response) => {
            console.log("Got MSL Response " + window.location.origin);
            console.log("my responz is: ", response);
            window.postMessage(response);
        }
    };
    console.log("enjoySite.js injected: " + window.location.origin);
})();
