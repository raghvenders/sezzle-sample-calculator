var path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');


module.exports = {
    entry: './src/main/js/index.js',
    cache: true,
    resolve: {
        alias: {
            'stompjs': __dirname + '/node_modules' + '/stompjs/lib/stomp.js',
        }
    },
    output: {
        path: path.resolve(__dirname, './src/main/resources/static/'),
        filename: 'bundle.js',
        publicPath: '/'
    },
    plugins: [new MiniCssExtractPlugin()],
	module: {
		rules: [
			{
				test: path.join(__dirname, '.'),
				exclude: /(node_modules)/,
				use: [{
					loader: 'babel-loader',
					options: {
						presets: ["@babel/preset-env",
						          "@babel/preset-react",
						          {'plugins': ['@babel/plugin-proposal-class-properties']}]
					}
				}]
			},
			{
                test: /\.css$/i,
                use: [
                    {
                       loader: MiniCssExtractPlugin.loader,
                          options: {
                              publicPath: '/',
                            },
                    },
                    'css-loader',
                ]
            }
		]
	}
};