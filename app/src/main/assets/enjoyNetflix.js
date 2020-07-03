(function () {
    window.enjoyNetflix = {
        makeMSLRequest: (url, mode, jsonRequest) => {
            console.log("Received MSL Request: " + url, mode, jsonRequest);
            const request = JSON.parse(jsonRequest);
            console.log("Received MSL Request: " + url, mode, request);
            Android.showToast("Received MSL Request: " + window.location.origin);
            fetch(url, request)
                .then((res) => {
                    if (mode === "text") {
                        res.text().then((res) => {
                            console.log("Got it!!!", res);
                            Android.relayMSLResponse(JSON.stringify(res));
                        });
                    } else if (mode === "arrayBuffer") {
                        res.arrayBuffer().then((bres) => {
                            console.log("Got it!!!", bres);
                            Android.relayMSLResponse(JSON.stringify(bres));
                        });
                    } else {
                        res.json().then((jres) => {
                            console.log("Got it!!!", jres);
                            Android.relayMSLResponse(JSON.stringify(jres));
                        });
                    }
                })
                .catch((err) => {
                    console.log(err);
                });
        }
    };
    console.log("enjoyNetflix.js injected: " + window.location.origin);
})();