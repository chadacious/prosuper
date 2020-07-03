(function () {
    window.enjoy = {
        testConnection: () => {
            console.log("netflix.js loaded " + window.location.origin);
            Android.showToast(window.location.origin);
        }
    };
    console.log("netflix.js attached" + window.enjoy  + " " + window.location.origin);
})();