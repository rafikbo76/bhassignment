function getUrlParams(){
    urlParams = {};
    window.location.href.replace(location.hash, '').replace(
        /[?&]+([^=&]+)=?([^&]*)?/gi, // regexp
        function (m, key, value) { // callback
            urlParams[key] = value !== undefined ? value : '';
        }
    );
    return urlParams;
}

function $_GET(param) {
    urlParams = getUrlParams();
    return param? urlParams[param]:urlParams;

}