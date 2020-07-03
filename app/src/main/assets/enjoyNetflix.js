(function () {
    window.enjoyNetflix = {
        makeMSLRequest: async (url, mode, jsonRequest) => {
            console.log("Received MSL Request: " + url, mode, jsonRequest);
            const request = JSON.parse(window.atob(jsonRequest));
            console.log("Received MSL Request: " + url, mode, request);
            Android.showToast("Received MSL Request: " + window.location.origin);
            const finalRes = await new Promise((resolve) => {
                fetch(url, request)
                    .then((res) => {

                        if (mode === "text") {
                            res.text().then((res) => {
                                resolve(res);
                            });
                        } else if (mode === "arrayBuffer") {
                            res.arrayBuffer().then((bres) => {
                                resolve(JSON.stringify(bres));
                            });
                        } else {
                            res.json().then((jres) => {
                                resolve(JSON.stringify(jres));
                            });
                        }
                    })
                    .catch((err) => {
                        console.log(err);
                        resolve(err.message);
                    });
            });
            console.log("Got it!!!", finalRes);
            Android.relayMSLResponse(finalRes);
            return finalRes;
        }
    };
    console.log("enjoyNetflix.js injected: " + window.location.origin);
})();