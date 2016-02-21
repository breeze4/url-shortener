var request = require('request');
var assert = require('assert');

var BASE_HOST = 'localhost';
var PORT = '4567';


testNewShorten();

function testNewShorten() {
    var rand = Math.floor(Math.random() * 100000000).toString();
    var requestPayload = {
        "originalUrl": "http://example.com/" + rand
    };
    var postData = JSON.stringify(requestPayload);

    var url = 'http://' + BASE_HOST + ":" + PORT + "/shorten/";
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
    });

    console.log("shorten test complete");
}