from flask import Flask

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/test')
def test():
    msg = 'Hi~~'
    return msg


if __name__ == '__main__':
    app.run()
    app.run(host='127.0.0.1', port=5000)
