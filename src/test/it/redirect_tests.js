var request = require('request');
var assert = require('assert');

var BASE_HOST = 'localhost';
var PORT = '4567';


testRedirect();

function testRedirect() {
    var rand = Math.floor(Math.random() * 100000000).toString();
    var expectedOriginalUrl = "http://example.com/" + rand;
    var requestPayload = {
        "originalUrl": expectedOriginalUrl
    };
    var postData = JSON.stringify(requestPayload);

    var baseUrl = 'http://' + BASE_HOST + ":" + PORT;
    var url = baseUrl + "/shorten/";
    var returnedShortenedUrl;
    request({
        method: 'POST',
        uri: url,
        body: postData,
        headers: {
            'content-type': 'application/json'
        }
    }, function (error, response, body) {
        assert(response.statusCode == 201);
        var shortenedUrl = JSON.parse(body);
        assert(shortenedUrl && shortenedUrl.shortenedUrl);
        returnedShortenedUrl = shortenedUrl.shortenedUrl;
        var originalUrl = baseUrl + "/s/" + returnedShortenedUrl;
        request({
            method: 'GET',
            uri: originalUrl
        }, function (error, response, body) {
            // this will be the result of a redirect to some 404 error page
            // check that this is the original URL in the href
            assert(response.request.href, expectedOriginalUrl);
        });
    });

    console.log("redirect test complete")
}