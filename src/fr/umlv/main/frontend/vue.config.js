// vue.config.js
module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 8081,
        proxy: {
            '/event': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            },
            '/user': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            }
        }
    }
}