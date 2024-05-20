const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api',	// 요청이 api로 시작하면 프록시가 동작하도록
        createProxyMiddleware({
            target: 'http://localhost:8080',	// 서버 URL or localhost:설정한포트번호
            changeOrigin: true,
        })
    );
};