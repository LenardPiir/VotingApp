const proxy = require("http-proxy-middleware")

module.exports = function(app) {
    app.use(proxy.createProxyMiddleware("/voting-api", {target: "http://voting-api:8080", pathRewrite: {"/voting-api": ""}}))
    app.use(proxy.createProxyMiddleware("/results-api", {target: "http://results-api:8081", pathRewrite: {"/results-api": ""}, ws: true}))
}