(function () {
    window.enjoy = {
        makeMSLRequest: () => {
            console.log("Sending MSL Request to Netflix: " + window.location.origin);
            Android.showToast(window.location.origin);
        }
    };
    console.log("enjoyNetflix.js injected: " + window.location.origin);
})();