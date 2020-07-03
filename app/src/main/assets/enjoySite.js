(function () {
    window.enjoy = {
        onMSLResponse: (response) => {
            console.log("Got MSL Response " + window.location.origin);
            console.log("my responz is: ", response);
            const decodedRes = window.atob(response);
            console.log("my responz is: ", decodedRes);
            window.postMessage(decodedRes);
        }
    };
    console.log("enjoySite.js injected: " + window.location.origin);
})();
